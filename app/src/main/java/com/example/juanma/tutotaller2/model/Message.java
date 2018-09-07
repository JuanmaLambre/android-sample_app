package com.example.juanma.tutotaller2.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("message")
    private String text;

    public String getText() {
        return text;
    }
}
