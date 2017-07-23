package com.litt.nic.pojo;

import java.util.Date;

public class suggest {
    private Integer suggestId;

    private Integer userId;

    private String suggestTitle;

    private String suggestContent;

    private Date suggestTime;

    public Integer getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Integer suggestId) {
        this.suggestId = suggestId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSuggestTitle() {
        return suggestTitle;
    }

    public void setSuggestTitle(String suggestTitle) {
        this.suggestTitle = suggestTitle == null ? null : suggestTitle.trim();
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent == null ? null : suggestContent.trim();
    }

    public Date getSuggestTime() {
        return suggestTime;
    }

    public void setSuggestTime(Date suggestTime) {
        this.suggestTime = suggestTime;
    }
}