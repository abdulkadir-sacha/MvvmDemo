package com.ak.mvvmdemo.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.ak.mvvmdemo.data.models.UserInfo;
import com.google.gson.Gson;

import javax.inject.Inject;


public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN= "PREF_KEY_USER_LOGGED_IN";
    private static final String PREF_KEY_CURRENT_USER_INFO = "PREF_KEY_CURRENT_USER_INFO";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_NOTIFICATION_ACCESS_TOKEN = "PREF_KEY_NOTIFICATION_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;
    private Gson gson;

    @Inject
    public AppPreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        gson=new Gson();
    }


    @Override
    public void setUserId(String id) {

        UserInfo info=getCurrentUserInfo();
        info.setUserId(id);
        setCurrentUserInfo(info);
    }

    @Override
    public String getUserId() {
        return getCurrentUserInfo().getUserId();
    }

    @Override
    public String getCurrentUserFullName() {
        return getCurrentUserInfo().getFullName();
    }

    @Override
    public void setCurrentUserFullName(String fullName) {

        UserInfo info=getCurrentUserInfo();
        info.setFullName(fullName);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentContactNo() {
        return getCurrentUserInfo().getContactNo();
    }

    @Override
    public void setCurrentContactNo(String number) {

        UserInfo info=getCurrentUserInfo();
        info.setContactNo(number);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return getCurrentUserInfo().getUserImage();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {

        UserInfo info=getCurrentUserInfo();
        info.setUserImage(profilePicUrl);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentUserAddress() {
        return getCurrentUserInfo().getAddress();
    }

    @Override
    public void setCurrentUserAddress(String address) {

        UserInfo info=getCurrentUserInfo();
        info.setAddress(address);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentUserDailCode() {
        return getCurrentUserInfo().getDailCode();
    }

    @Override
    public void setCurrentUserDailCode(String dailCode) {

        UserInfo info=getCurrentUserInfo();
        info.setDailCode(dailCode);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentUserStoreName() {
        return getCurrentUserInfo().getStoreName();
    }

    @Override
    public void setCurrentUserStoreName(String storeName) {


        UserInfo info=getCurrentUserInfo();
        info.setStoreName(storeName);
        setCurrentUserInfo(info);
    }

    @Override
    public void setCurrentUserEmail(String email) {

        UserInfo info=getCurrentUserInfo();
        info.setEmail(email);
        setCurrentUserInfo(info);
    }

    @Override
    public String getCurrentUserEmail() {
        return getCurrentUserInfo().getEmail();
    }

    @Override
    public void setCurrentUserInfo(UserInfo userInfo) {

        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_INFO,gson.toJson(userInfo)).apply();
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        UserInfo info=gson.fromJson(mPrefs.getString(PREF_KEY_CURRENT_USER_INFO,"{}"),UserInfo.class);
        return info;
    }


    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public String getAboutUsText() {
        return getCurrentUserInfo().getAboutUs();
    }

    @Override
    public String getAdminContactNo() {
        return getCurrentUserInfo().getAdminContactNo();
    }

    @Override
    public String getFcmToken() {
        return mPrefs.getString(PREF_KEY_NOTIFICATION_ACCESS_TOKEN,"");
    }

    @Override
    public void setFcmToken(String token) {
        mPrefs.edit().putString(PREF_KEY_NOTIFICATION_ACCESS_TOKEN, token).apply();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public void setUserLoggedIn(boolean isUserLoggedIn) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN, isUserLoggedIn).apply();
    }

    @Override
    public boolean isIsUserLoggedIn() {
        return mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }

}
