package com.ak.mvvmdemo.data;


import com.ak.mvvmdemo.data.local.db.DbHelper;
import com.ak.mvvmdemo.data.local.prefs.PreferencesHelper;
import com.ak.mvvmdemo.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper,DbHelper {

    public ApiHelper getApiHelper();
}
