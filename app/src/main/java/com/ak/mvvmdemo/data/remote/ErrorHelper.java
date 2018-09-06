package com.ak.mvvmdemo.data.remote;

import com.ak.mvvmdemo.ui.base.BaseNavigator;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;

public class ErrorHelper<N extends BaseNavigator> {

    public ErrorHelper(N navigator) {
        this.navigator = navigator;
    }

    private N navigator;


    public void handleError(Throwable e) {

        navigator.hideLoading();

        if (e instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            navigator.showAlert("Error", getErrorMessage(responseBody));
        } else if (e instanceof SocketTimeoutException) {
            navigator.showAlert("Timeout Error", "Connection timed out");

        } else if (e instanceof IOException) {
            navigator.showAlert("Error", "Something went wrong please try after sometime.");

        } else {
            navigator.showAlert("Unknown error", "Something went wrong please try after sometime.");

        }

    }

    public void handleError(Throwable e, Object object, String name) {

        navigator.hideLoading();

        if (e instanceof HttpException) {
            HttpException httpException = ((HttpException) e);
            ResponseBody responseBody = httpException.response().errorBody();

            switch (httpException.code()) {

                case 401:
                    navigator.showToast( getErrorMessage(responseBody));
                    navigator.gotoLogin();
                    break;

                default:
                    navigator.showRetryAlert("Error:" + httpException.code(), getErrorMessage(responseBody), object, name);
                    break;
            }

        } else if (e instanceof SocketTimeoutException) {
            navigator.showRetryAlert("Timeout Error", "Connection timed out", object, name);

        } else if (e instanceof IOException) {
            navigator.showRetryAlert("Error", "Something went wrong please try after sometime.", object, name);

        } else {
            navigator.showRetryAlert("Unknown error", "Something went wrong please try after sometime.", object, name);

        }

    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

