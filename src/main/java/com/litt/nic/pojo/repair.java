package com.litt.nic.pojo;

import java.util.Date;

public class repair {
    private Integer repairId;

    private Integer userId;

    private String repairDepartment;

    private String repairDevicename;

    private String repairLocation;

    private String repairDescribe;

    private String repairPicture;

    private String repairFeedback;

    private String repairUptime;

    private String repairEndtime;

    private Integer statusId;

    private Integer managerId;

    public Integer getRepairId() {
        return repairId;
    }

    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRepairDepartment() {
        return repairDepartment;
    }

    public void setRepairDepartment(String repairDepartment) {
        this.repairDepartment = repairDepartment == null ? null : repairDepartment.trim();
    }

    public String getRepairDevicename() {
        return repairDevicename;
    }

    public void setRepairDevicename(String repairDevicename) {
        this.repairDevicename = repairDevicename == null ? null : repairDevicename.trim();
    }

    public String getRepairLocation() {
        return repairLocation;
    }

    public void setRepairLocation(String repairLocation) {
        this.repairLocation = repairLocation == null ? null : repairLocation.trim();
    }

    public String getRepairDescribe() {
        return repairDescribe;
    }

    public void setRepairDescribe(String repairDescribe) {
        this.repairDescribe = repairDescribe == null ? null : repairDescribe.trim();
    }

    public String getRepairPicture() {
        return repairPicture;
    }

    public void setRepairPicture(String repairPicture) {
        this.repairPicture = repairPicture == null ? null : repairPicture.trim();
    }

    public String getRepairFeedback() {
        return repairFeedback;
    }

    public void setRepairFeedback(String repairFeedback) {
        this.repairFeedback = repairFeedback == null ? null : repairFeedback.trim();
    }

   /* public Date getRepairUptime() {
        return repairUptime;
    }

    public void setRepairUptime(Date repairUptime) {
        this.repairUptime = repairUptime;
    }*/
    

  /*  public Date getRepairEndtime() {
        return repairEndtime;
    }*/
    

    public String getRepairUptime() {
		return repairUptime;
	}

	public String getRepairEndtime() {
		return repairEndtime;
	}

	public void setRepairEndtime(String repairEndtime) {
		this.repairEndtime = repairEndtime;
	}

	public void setRepairUptime(String repairUptime) {
		this.repairUptime = repairUptime;
	}

	/*public void setRepairEndtime(Date repairEndtime) {
        this.repairEndtime = repairEndtime;
    }*/

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}