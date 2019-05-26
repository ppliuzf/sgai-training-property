package com.sgai.property.customer.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class OrgRecordInfoDto implements Serializable {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -5709586034056605473L;
    @ApiModelProperty(value = "机构详情主键id")
    private String orId; //机构详情主键id
    @ApiModelProperty(value = "公司名称")
    private String orCompanyName; //公司名称
    @ApiModelProperty(value = "企业法人")
    private String orCompanyLegal; //企业法人
    @ApiModelProperty(value = "注册地址")
    private String orRegistAddress; //注册地址
    @ApiModelProperty(value = "类型id")
    private String ctId; //类型id
    @ApiModelProperty(value = "级别id")
    private String clId; //级别id
    @ApiModelProperty(value = "类型名称")
    private String ctName; // 类型id
    @ApiModelProperty(value = "级别名称")
    private String clName; // 级别名称
    @ApiModelProperty(value = "注册资金")
    private String orRegistFund; //注册资金
    @ApiModelProperty(value = "经营范围")
    private String orBisScope; //经营范围
    @ApiModelProperty(value = "注册日期")
    private Long registDate; //注册日期
    @ApiModelProperty(value = "经营期限开始日期")
    private Long bisStartDate; //经营期限开始日期
    @ApiModelProperty(value = "经营期限结束日期")
    private Long bisEndDate; //经营期限结束日期
    @ApiModelProperty(value = "公司ID")
    private Long comId; //公司ID
    @JsonIgnore
    @ApiModelProperty(value = "是否删除")
    private Long orIsDelete; //是否删除
    @ApiModelProperty(value = "证件信息")
    private List<OrgRecordCardDto> orgRecordCardDtos;
    @ApiModelProperty(value = "联系人信息")
    private List<OrgRecordLinkmanDto> orgRecordLinkmanDtos;


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

    public List<OrgRecordCardDto> getOrgRecordCardDtos() {
        return orgRecordCardDtos;
    }

    public void setOrgRecordCardDtos(List<OrgRecordCardDto> orgRecordCardDtos) {
        this.orgRecordCardDtos = orgRecordCardDtos;
    }

    public List<OrgRecordLinkmanDto> getOrgRecordLinkmanDtos() {
        return orgRecordLinkmanDtos;
    }

    public void setOrgRecordLinkmanDtos(List<OrgRecordLinkmanDto> orgRecordLinkmanDtos) {
        this.orgRecordLinkmanDtos = orgRecordLinkmanDtos;
    }

    public String getOrId() {
        return orId;
    }

    public OrgRecordInfoDto setOrId(String orId) {
        this.orId = orId;
        return this;
    }

    public String getOrCompanyName() {
        return orCompanyName;
    }

    public void setOrCompanyName(String orCompanyName) {
        this.orCompanyName = orCompanyName;
    }

    public String getOrCompanyLegal() {
        return orCompanyLegal;
    }

    public void setOrCompanyLegal(String orCompanyLegal) {
        this.orCompanyLegal = orCompanyLegal;
    }

    public String getOrRegistAddress() {
        return orRegistAddress;
    }

    public void setOrRegistAddress(String orRegistAddress) {
        this.orRegistAddress = orRegistAddress;
    }

    public String getCtId() {
        return ctId;
    }

    public OrgRecordInfoDto setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public OrgRecordInfoDto setClId(String clId) {
        this.clId = clId;
        return this;
    }

    public String getOrRegistFund() {
        return orRegistFund;
    }

    public void setOrRegistFund(String orRegistFund) {
        this.orRegistFund = orRegistFund;
    }

    public String getOrBisScope() {
        return orBisScope;
    }

    public void setOrBisScope(String orBisScope) {
        this.orBisScope = orBisScope;
    }

    public Long getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Long registDate) {
        this.registDate = registDate;
    }

    public Long getBisStartDate() {
        return bisStartDate;
    }

    public void setBisStartDate(Long bisStartDate) {
        this.bisStartDate = bisStartDate;
    }

    public Long getBisEndDate() {
        return bisEndDate;
    }

    public void setBisEndDate(Long bisEndDate) {
        this.bisEndDate = bisEndDate;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOrIsDelete() {
        return orIsDelete;
    }

    public OrgRecordInfoDto setOrIsDelete(Long orIsDelete) {
        this.orIsDelete = orIsDelete;
        return this;
    }
}