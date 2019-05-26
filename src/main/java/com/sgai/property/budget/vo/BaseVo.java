package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *@author 严浩淼
 *@date 2018年1月6日--上午11:37:31
 */
public class BaseVo {

	@ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "备注")
    private String remarks; // 备注
    @ApiModelProperty(value = "创建者")
    private String createdBy; // 创建者
    @ApiModelProperty(value = "创建日期")
    private Date createdDt; // 创建日期
    @ApiModelProperty(value = "更新者")
    private String updatedBy; // 更新者
    @ApiModelProperty(value = " 更新日期")
    private Date updatedDt; // 更新日期
    @ApiModelProperty(value = "删除标记（0：正常；1：删除；2：审核）")
	private String delFlag; // 删除标记（0：正常；1：删除；2：审核）
    @ApiModelProperty(value = "数据版本 初始化为1")
	private Integer version; //数据版本 初始化为1
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
}
