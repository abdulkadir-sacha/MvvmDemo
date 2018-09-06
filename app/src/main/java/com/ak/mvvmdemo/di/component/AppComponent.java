package com.ak.mvvmdemo.di.component;

import android.app.Application;


import com.ak.mvvmdemo.MyApp;
import com.ak.mvvmdemo.di.builder.ActivityBuilder;
import com.ak.mvvmdemo.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})

public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(MyApp app);


}
