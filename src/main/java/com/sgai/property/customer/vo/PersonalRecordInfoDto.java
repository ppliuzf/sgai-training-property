package com.sgai.property.customer.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class PersonalRecordInfoDto implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -2023886991237245257L;
    @ApiModelProperty(value = "主键id")
    private String prId; //主键id
    @ApiModelProperty(value = "客户名称")
    private String prName; //客户名称
    @ApiModelProperty(value = "手机号1")
    private Long prPhoneFirst; //手机号1
    @ApiModelProperty(value = "手机号2")
    private Long prPhoneSecond; //手机号2
    @ApiModelProperty(value = "邮箱")
    private String prEmail; //邮箱
    @ApiModelProperty(value = "性别(0 男 1 女)")
    private Integer prSex; //性别(0 男 1 女)
    @ApiModelProperty(value = "出生日期")
    private Long prBirth; //出生日期
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID
    @ApiModelProperty(value = "证件信息")
    private List<CustomCardInfoDto> customCardInfoDtos; // 证件信息
    @JsonIgnore
    @ApiModelProperty(value = "是否删除")
    private Long prIsDelete; //是否删除
    @ApiModelProperty(value = "部门ID")
    private String deptId; //部门ID
    @ApiModelProperty(value = "部门名称")
    private String deptName; //部门名称
    

    public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getPrIsDelete() {
        return prIsDelete;
    }

    public PersonalRecordInfoDto setPrIsDelete(Long prIsDelete) {
        this.prIsDelete = prIsDelete;
        return this;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Long getPrPhoneFirst() {
        return prPhoneFirst;
    }

    public void setPrPhoneFirst(Long prPhoneFirst) {
        this.prPhoneFirst = prPhoneFirst;
    }

    public Long getPrPhoneSecond() {
        return prPhoneSecond;
    }

    public void setPrPhoneSecond(Long prPhoneSecond) {
        this.prPhoneSecond = prPhoneSecond;
    }

    public String getPrEmail() {
        return prEmail;
    }

    public void setPrEmail(String prEmail) {
        this.prEmail = prEmail;
    }

    public Integer getPrSex() {
        return prSex;
    }

    public void setPrSex(Integer prSex) {
        this.prSex = prSex;
    }

    public Long getPrBirth() {
        return prBirth;
    }

    public void setPrBirth(Long prBirth) {
        this.prBirth = prBirth;
    }

    public String getPrId() {
        return prId;
    }

    public PersonalRecordInfoDto setPrId(String prId) {
        this.prId = prId;
        return this;
    }

    public String getCtId() {
        return ctId;
    }

    public PersonalRecordInfoDto setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public PersonalRecordInfoDto setClId(String clId) {
        this.clId = clId;
        return this;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public List<CustomCardInfoDto> getCustomCardInfoDtos() {
        return customCardInfoDtos;
    }

    public void setCustomCardInfoDtos(List<CustomCardInfoDto> customCardInfoDtos) {
        this.customCardInfoDtos = customCardInfoDtos;
    }

}