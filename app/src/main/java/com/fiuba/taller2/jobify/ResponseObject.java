package com.fiuba.taller2.jobify;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jlambre on 05/04/2018.
 */

public class ResponseObject {

    @SerializedName("valueA") String valueA;
    @SerializedName("valueB") String valueB;


    public static ResponseObject hydrate(JsonObject json) {
        return new Gson().fromJson(json, ResponseObject.class);
    }

    public String getValueA() {
        return valueA;
    }

    public String getValueB() {
        return valueB;
    }

}
