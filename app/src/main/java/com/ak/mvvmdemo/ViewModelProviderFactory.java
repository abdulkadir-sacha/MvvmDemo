package com.ak.mvvmdemo;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelProviderFactory<T> implements ViewModelProvider.Factory {

    private T viewModel;

    public ViewModelProviderFactory(T viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            return modelClass.cast(viewModel);
        }
        throw new IllegalArgumentException("Unknown class name");
    }

}
