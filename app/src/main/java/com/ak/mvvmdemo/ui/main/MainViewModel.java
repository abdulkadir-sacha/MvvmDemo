package com.ak.mvvmdemo.ui.main;

import android.app.Application;
import android.support.annotation.NonNull;


import com.ak.mvvmdemo.data.DataManager;
import com.ak.mvvmdemo.ui.base.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private int currentPosition=0;
    @Inject
    MainViewModel(@NonNull Application application, DataManager dataManager) {
        super(application, dataManager);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
