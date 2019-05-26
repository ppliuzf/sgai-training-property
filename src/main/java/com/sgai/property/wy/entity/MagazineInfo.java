package com.sgai.property.wy.entity;


import com.sgai.common.persistence.BoEntity;

/*
    * @ClassName: Visitor  
    *
    * @author LiuXiaobing  
    * @date 2018年1月26日  
    * @Company 首自信--智慧城市创新中心
*/

public class MagazineInfo extends BoEntity<MagazineInfo> {
	  
	private static final long serialVersionUID = 1L;
	
	// 报刊杂志数据库表对应的属性成员字段
	private String magazineName; //报刊杂志名字
	
	private Integer encoder; //报刊杂志编号
	
	private String createdDate; // 报刊杂志添加时间
	
	private String personName; //维护人姓名
	
	private String display; // 逻辑删除(Y/N)
	
	private String remarks;	// 备注信息

	public String getMagazineName() {
		return magazineName;
	}

	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}

	public Integer getEncoder() {
		return encoder;
	}

	public void setEncoder(Integer encoder) {
		this.encoder = encoder;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
