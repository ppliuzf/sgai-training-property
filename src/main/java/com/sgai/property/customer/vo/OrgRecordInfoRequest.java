package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OrgRecordInfoRequest implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -5709586034056605473L;
    @ApiModelProperty(value = "机构详情主键id")
    private String orId; //机构详情主键id
    @ApiModelProperty(value = "公司名称")
    private String orCompanyName; //公司名称
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID


    public String getOrId() {
        return orId;
    }

    public OrgRecordInfoRequest setOrId(String orId) {
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

    public OrgRecordInfoRequest setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public OrgRecordInfoRequest setClId(String clId) {
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