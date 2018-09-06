
package com.ak.mvvmdemo.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.ak.mvvmdemo.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {


    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }
}
