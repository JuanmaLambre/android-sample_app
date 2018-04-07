package com.fiuba.taller2.jobify.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fiuba.taller2.jobify.ResponseObject;
import com.fiuba.taller2.jobify.utils.AppServerRequest;
import com.fiuba.taller2.jobify.utils.HttpCallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.taller2.fiuba.jobify.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class StartActivity extends Activity {

    TextView response;
    EditText params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button sendBtn = (Button) findViewById(R.id.send_button);
        sendBtn.setOnClickListener(new OnSendClickListener());

        response = (TextView) findViewById(R.id.response_text);

        params = (EditText) findViewById(R.id.params_edittext);

    }

    public class OnSendClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String parameters = params.getText().toString();
            AppServerRequest.sendTestRequest(parameters, new TestRequestCallback());
        }

        public class TestRequestCallback extends HttpCallback {

            ResponseObject resp;

            @Override
            public void onResponse() {
                try {
                    JsonObject objJson = new JsonParser().parse(getJSONObject("args").toString()).getAsJsonObject();
                    resp = ResponseObject.hydrate(objJson);
                    StartActivity.this.runOnUiThread(new SetResults());
                } catch (Exception e) {
                    Log.e("TEST REQUEST CALLBACK", "Error");
                    e.printStackTrace();
                }
                StartActivity.this.runOnUiThread(new SetResults());
            }

            class SetResults implements Runnable {
                @Override
                public void run() {
                    String text = String.format(
                            "Value A = %s\nValue B = %s",
                            resp.getValueA(), resp.getValueB()
                    );
                    StartActivity.this.response.setText(text);
                }
            }
        }

    }
}
