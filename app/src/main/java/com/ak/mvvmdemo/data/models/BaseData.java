package com.ak.mvvmdemo.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseData {

    @Expose
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
