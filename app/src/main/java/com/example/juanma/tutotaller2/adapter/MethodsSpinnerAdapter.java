package com.example.juanma.tutotaller2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.juanma.tutotaller2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MethodsSpinnerAdapter extends ArrayAdapter<String> {

    static List<String> options;

    static {
        options = Arrays.asList(
                "Create user",
                "Edit user"
        );
    }

    public MethodsSpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.view_spinner, options);
    }

}
