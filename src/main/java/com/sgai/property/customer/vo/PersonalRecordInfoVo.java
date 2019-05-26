package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PersonalRecordInfoVo implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = 8047504517735829532L;
    @ApiModelProperty(value = "主键id")
    private String prId; // 主键id
    @ApiModelProperty(value = "客户名称")
    private String prName; // 客户名称
    @ApiModelProperty(value = "手机号1")
    private Long prPhoneFirst; // 手机号1
    @ApiModelProperty(value = "类型id")
    private String ctId; // 类型id
    @ApiModelProperty(value = "类型名称")
    private String ctName; // 类型id
    @ApiModelProperty(value = "级别名称")
    private String clName; // 级别名称
    @ApiModelProperty(value = "级别id")
    private String clId; // 级别名称
    @ApiModelProperty(value = "公司ID")
    private String comId; // 公司ID


    public String getPrId() {
        return prId;
    }

    public PersonalRecordInfoVo setPrId(String prId) {
        this.prId = prId;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCtId() {
        return ctId;
    }

    public PersonalRecordInfoVo setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public PersonalRecordInfoVo setClId(String clId) {
        this.clId = clId;
        return this;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getClName() {
        return clName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

}