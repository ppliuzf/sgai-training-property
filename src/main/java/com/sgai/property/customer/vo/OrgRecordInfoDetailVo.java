package com.sgai.property.customer.vo;

import com.sgai.common.persistence.BoEntity;
import com.sgai.property.customer.entity.OrgRecordInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class OrgRecordInfoDetailVo extends BoEntity<OrgRecordInfo> {

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
    private String comId; //公司ID
    @ApiModelProperty(value = "部门ID")
    private String deptId; //部门ID
    @ApiModelProperty(value = "部门名称")
    private String deptName; //部门名称
    @ApiModelProperty(value = "证件信息")
    private List<OrgRecordCardVo> orgRecordCardVos;
    @ApiModelProperty(value = "联系人信息")
    private List<OrgRecordLinkmanVo> orgRecordLinkmanVos;

    

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

    public List<OrgRecordCardVo> getOrgRecordCardVos() {
        return orgRecordCardVos;
    }

    public void setOrgRecordCardVos(List<OrgRecordCardVo> orgRecordCardVos) {
        this.orgRecordCardVos = orgRecordCardVos;
    }

    public List<OrgRecordLinkmanVo> getOrgRecordLinkmanVos() {
        return orgRecordLinkmanVos;
    }

    public void setOrgRecordLinkmanVos(List<OrgRecordLinkmanVo> orgRecordLinkmanVos) {
        this.orgRecordLinkmanVos = orgRecordLinkmanVos;
    }

    public String getOrId() {
        return orId;
    }

    public OrgRecordInfoDetailVo setOrId(String orId) {
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

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getCtId() {
        return ctId;
    }

    public OrgRecordInfoDetailVo setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public OrgRecordInfoDetailVo setClId(String clId) {
        this.clId = clId;
        return this;
    }
}