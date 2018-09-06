package com.ak.mvvmdemo.data;


import com.ak.mvvmdemo.data.local.db.DbHelper;
import com.ak.mvvmdemo.data.local.prefs.PreferencesHelper;
import com.ak.mvvmdemo.data.models.UserInfo;
import com.ak.mvvmdemo.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager{

    private DbHelper dbHelper;
    private PreferencesHelper preferencesHelper;
    private ApiHelper apiHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper,ApiHelper apiHelper) {
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
    }



    @Override
    public void setUserId(String id) {
        preferencesHelper.setUserId(id);
    }

    @Override
    public String getUserId() {
        return preferencesHelper.getUserId();
    }

    @Override
    public String getCurrentUserFullName() {
        return preferencesHelper.getCurrentUserFullName();
    }

    @Override
    public void setCurrentUserFullName(String fullName) {

        preferencesHelper.setCurrentUserFullName(fullName);
    }

    @Override
    public String getCurrentContactNo() {
        return preferencesHelper.getCurrentContactNo();
    }

    @Override
    public void setCurrentContactNo(String number) {

        preferencesHelper.setCurrentContactNo(number);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return preferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {

        preferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public String getCurrentUserAddress() {
        return preferencesHelper.getCurrentUserAddress();
    }

    @Override
    public void setCurrentUserAddress(String address) {

        preferencesHelper.setCurrentUserAddress(address);
    }

    @Override
    public String getCurrentUserDailCode() {
        return preferencesHelper.getCurrentUserDailCode();
    }

    @Override
    public void setCurrentUserDailCode(String dailCode) {

        preferencesHelper.setCurrentUserDailCode(dailCode);
    }

    @Override
    public String getCurrentUserStoreName() {
        return preferencesHelper.getCurrentUserStoreName();
    }

    @Override
    public void setCurrentUserStoreName(String storeName) {
        preferencesHelper.setCurrentUserStoreName(storeName);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        preferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserEmail() {
        return preferencesHelper.getCurrentUserEmail();
    }


    @Override
    public void setCurrentUserInfo(UserInfo userInfo) {

        preferencesHelper.setCurrentUserInfo(userInfo);
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        return preferencesHelper.getCurrentUserInfo();
    }

    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }

    @Override
    public String getAboutUsText() {
        return preferencesHelper.getAboutUsText();
    }

    @Override
    public String getAdminContactNo() {
        return preferencesHelper.getAdminContactNo();
    }

    @Override
    public String getFcmToken() {
        return preferencesHelper.getFcmToken();
    }

    @Override
    public void setFcmToken(String token) {

        preferencesHelper.setFcmToken(token);
    }

    @Override
    public void setAccessToken(String accessToken) {

        preferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public void setUserLoggedIn(boolean isUserLoggedIn) {
        preferencesHelper.setUserLoggedIn(isUserLoggedIn);
    }

    @Override
    public boolean isIsUserLoggedIn() {
        return preferencesHelper.isIsUserLoggedIn();
    }


    @Override
    public ApiHelper getApiHelper() {
        return apiHelper;
    }
}
