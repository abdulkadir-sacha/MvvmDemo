package com.ak.mvvmdemo.ui.base;

import android.content.Context;

public interface BaseNavigator {

    void showLoading();
    void hideLoading();
    void showToast(String msg);
    void showAlert(String title, String msg);
    void showRetryAlert(String title, String msg, final Object c, final String method);
    void gotoLogin();
    Context getContext();
}
