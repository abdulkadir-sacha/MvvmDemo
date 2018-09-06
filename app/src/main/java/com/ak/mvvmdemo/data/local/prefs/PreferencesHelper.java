package com.ak.mvvmdemo.data.local.prefs;


import com.ak.mvvmdemo.data.models.UserInfo;

public interface PreferencesHelper {

    void setUserId(String id);

    String getUserId();

    String getCurrentUserFullName();

    void setCurrentUserFullName(String fullName);

    String getCurrentContactNo();

    void setCurrentContactNo(String number);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getCurrentUserAddress();

    void setCurrentUserAddress(String address);

    String getCurrentUserDailCode();

    void setCurrentUserDailCode(String dailCode);

    String getCurrentUserStoreName();

    void setCurrentUserStoreName(String storeName);

    void setCurrentUserEmail(String email);

    String getCurrentUserEmail();

    void setCurrentUserInfo(UserInfo userInfo);

    UserInfo getCurrentUserInfo();

    String getAccessToken();

    String getAboutUsText();

    String getAdminContactNo();

    String getFcmToken();

    void setFcmToken(String token);

    void setAccessToken(String accessToken);

    void setUserLoggedIn(boolean isUserLoggedIn);

    boolean isIsUserLoggedIn();

}
