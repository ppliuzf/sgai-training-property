package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OrgRecordInfoVo implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -4247095623533957349L;
    @ApiModelProperty(value = "机构详情主键id")
    private String orId; //机构详情主键id
    @ApiModelProperty(value = "公司名称")
    private String orCompanyName; //公司名称
    @ApiModelProperty(value = "联系人名称")
    private String orlLinkman; //联系人名称
    @ApiModelProperty(value = "联系人电话")
    private String orlPhone; //联系人电话
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "类型名称")
    private String ctName; // 类型id
    @ApiModelProperty(value = "级别名称")
    private String clName; // 级别名称
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID


    public String getOrlLinkman() {
        return orlLinkman;
    }

    public void setOrlLinkman(String orlLinkman) {
        this.orlLinkman = orlLinkman;
    }

    public String getOrlPhone() {
        return orlPhone;
    }

    public void setOrlPhone(String orlPhone) {
        this.orlPhone = orlPhone;
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

    public String getOrId() {
        return orId;
    }

    public OrgRecordInfoVo setOrId(String orId) {
        this.orId = orId;
        return this;
    }

    public String getOrCompanyName() {
        return orCompanyName;
    }

    public void setOrCompanyName(String orCompanyName) {
        this.orCompanyName = orCompanyName;
    }

    public String getCtId() {
        return ctId;
    }

    public OrgRecordInfoVo setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public OrgRecordInfoVo setClId(String clId) {
        this.clId = clId;
        return this;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }
}