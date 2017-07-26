package com.litt.nic.pojo;

public class user {
    private Integer userId;

    private String userName;

    private String userTelephone;

    private String userDepartment;

    private String userOpenid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment == null ? null : userDepartment.trim();
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }
}