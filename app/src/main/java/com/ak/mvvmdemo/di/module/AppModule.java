package com.ak.mvvmdemo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.ak.mvvmdemo.data.AppDataManager;
import com.ak.mvvmdemo.data.DataManager;
import com.ak.mvvmdemo.data.local.db.AppDatabase;
import com.ak.mvvmdemo.data.local.db.AppDbHelper;
import com.ak.mvvmdemo.data.local.db.DbHelper;
import com.ak.mvvmdemo.data.local.prefs.AppPreferencesHelper;
import com.ak.mvvmdemo.data.local.prefs.PreferencesHelper;
import com.ak.mvvmdemo.data.remote.ApiConstants;
import com.ak.mvvmdemo.data.remote.ApiHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ak.mvvmdemo.data.remote.ApiConstants.REQUEST_TIMEOUT;


@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "my_db")
                .fallbackToDestructiveMigration()
                .build();
    }
    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper dbHelper){
        return dbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper preferencesHelper){
        return preferencesHelper;
    }


    @Provides
    @Singleton
    ApiHelper provideApiHelper(Retrofit retrofit){
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient){

        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final PreferencesHelper preferencesHelper){

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json");

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
                if (!TextUtils.isEmpty(preferencesHelper.getAccessToken())) {
                    requestBuilder.addHeader("Authorization", preferencesHelper.getAccessToken());
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return httpClient.build();

    }
}
