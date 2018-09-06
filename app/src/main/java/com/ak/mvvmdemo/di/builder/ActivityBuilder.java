package com.ak.mvvmdemo.di.builder;


import com.ak.mvvmdemo.ui.main.MainActivity;
import com.ak.mvvmdemo.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity bindMainActivity();

}
