package com.example.juanma.tutotaller2.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.juanma.tutotaller2.R;
import com.example.juanma.tutotaller2.model.Message;
import com.example.juanma.tutotaller2.utils.AppServerRequest;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_activity_button).setOnClickListener(new OnSendListener());
    }


    class OnSendListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String url = ((EditText) MainActivity.this.findViewById(R.id.main_activity_url_text)).getText().toString();
            AppServerRequest.get(url, new GetCallback());
        }
    }

    class GetCallback implements Callback {

        Response r;

        @Override
        public void onFailure(Call call, IOException e) {}

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            r = response;

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String json = r.body().string();
                        Message msg = new Gson().fromJson(json, Message.class);
                        ((TextView)findViewById(R.id.main_activity_response)).setText(msg.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

