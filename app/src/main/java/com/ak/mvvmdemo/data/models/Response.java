package com.ak.mvvmdemo.data.models;

import java.util.List;

public class Response<T> {

    private List<String> error;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    private Integer success;
    private T data;

    public Response() {
    }


    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return getSuccess()==1;
    }

}

