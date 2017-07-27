package com.litt.nic.pojo;

public class manager {
    private Integer managerId;

    private String managerName;

    private String managerTelephone;

    private String managerDuty;

    private Integer managerTyp;

    private String managerPassword;
    
    public manager() {};
    
    public manager(Integer managerId,String managerName,String managerTelephone,String managerDuty,Integer managerTyp,String managerPassword) {
    	this.setManagerId(managerId);
    	this.setManagerName(managerName);
    	this.setManagerTelephone(managerTelephone);
    	this.setManagerDuty(managerDuty);
    	this.setManagerTyp(managerTyp);
    	this.setManagerPassword(managerPassword);
    }
    
    public Integer getManagerId() {
        return managerId;
    }
    
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerTelephone() {
        return managerTelephone;
    }

    public void setManagerTelephone(String managerTelephone) {
        this.managerTelephone = managerTelephone == null ? null : managerTelephone.trim();
    }

    public String getManagerDuty() {
        return managerDuty;
    }

    public void setManagerDuty(String managerDuty) {
        this.managerDuty = managerDuty == null ? null : managerDuty.trim();
    }

    public Integer getManagerTyp() {
        return managerTyp;
    }

    public void setManagerTyp(Integer managerTyp) {
        this.managerTyp = managerTyp;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword == null ? null : managerPassword.trim();
    }

	@Override
	public String toString() {
		return "manager [managerId=" + managerId + ", managerName=" + managerName + ", managerTelephone="
				+ managerTelephone + ", managerDuty=" + managerDuty + ", managerTyp=" + managerTyp
				+ ", managerPassword=" + managerPassword + "]";
	}
    
}