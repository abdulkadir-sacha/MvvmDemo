package com.ak.mvvmdemo.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @Expose
    @SerializedName("id")
     private String userId;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    @SerializedName("store_name")
    private String storeName;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("dial_code")
    private String dailCode;

    @Expose
    @SerializedName("contact_number")
    private String contactNo;

    @Expose
    @SerializedName("image")
    private String userImage;

    @Expose
    @SerializedName("admin_about_us")
    private String aboutUs;

    @Expose
    @SerializedName("admin_contact_no")
    private String adminContactNo;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDailCode() {
        return dailCode;
    }

    public void setDailCode(String dailCode) {
        this.dailCode = dailCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getAdminContactNo() {
        return adminContactNo;
    }

    public void setAdminContactNo(String adminContactNo) {
        this.adminContactNo = adminContactNo;
    }
}
