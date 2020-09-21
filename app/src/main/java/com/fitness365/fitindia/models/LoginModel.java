package com.fitness365.fitindia.models;

public class LoginModel {
    /**
     * IsSuccess : true
     * Message : User Loggedin Successfully
     * userid : 1
     */

    private boolean IsSuccess;
    private String message;
    private int userid;
    private String name,mobile;

    public boolean isIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(boolean IsSuccess) {
        this.IsSuccess = IsSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String Message) {
        this.message = message;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
