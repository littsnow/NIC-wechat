package com.litt.nic.pojo;

public class type {
    private Integer typeId;

    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

	@Override
	public String toString() {
		return "type [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
    
}