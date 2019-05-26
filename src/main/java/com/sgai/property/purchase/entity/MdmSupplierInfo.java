package com.sgai.property.purchase.entity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;
public class MdmSupplierInfo extends BoEntity<MdmSupplierInfo>{

    @ApiModelProperty(value = "")
    private String moduCode; //
    @ApiModelProperty(value = "国营民营 外企合资")
    private String comType; //国营民营外企合资
    @ApiModelProperty(value = "开户行")
    private String openBank; //开户行
    @ApiModelProperty(value = "开户行代码")
    private String openBankCode; //开户行代码
    @ApiModelProperty(value = "邮编")
    private String zipCode; //邮编
    @ApiModelProperty(value = "创建时间")
    private Date createDate; //创建时间
    @ApiModelProperty(value = "删除标记")
    private String delFlag; //删除标记
    @ApiModelProperty(value = "机构编码")
    private String comCode; //机构编码
    @ApiModelProperty(value = "注 册 地 址")
    private String regAddress; //注 册 地 址
    @ApiModelProperty(value = "部门编码")
    private String deptCode; //部门编码
    @ApiModelProperty(value = "供应商编号")
    private String supplierNo; //供应商编号
    @ApiModelProperty(value = "供应商名称")
    private String supplierName; //供应商名称
    @ApiModelProperty(value = "账户编号")
    private String accountNo; //账户编号
    @ApiModelProperty(value = "邮箱地址")
    private String eMail; //邮箱地址
    @ApiModelProperty(value = "更新者")
    private String updateBy; //更新者
    @ApiModelProperty(value = "评价等级")
    private Long assessmentLevel; //评价等级
    @ApiModelProperty(value = "组织机构代码")
    private String orgCode; //组织机构代码
    @ApiModelProperty(value = "描述信息")
    private String supplyProd; //描述信息
    @ApiModelProperty(value = "更新时间")
    private Date updateDate; //更新时间
    @ApiModelProperty(value = "省份")
    private String province; //省份
    @ApiModelProperty(value = "")
    private String contactPhone; //
    @ApiModelProperty(value = "法人")
    private String lowPerson; //法人
    @ApiModelProperty(value = "法人身份证号码")
    private String lowPersonCarid; //法人身份证号码
    @ApiModelProperty(value = "办公地址")
    private String officeAddress; //办公地址
    @ApiModelProperty(value = "税号")
    private String taxNo; //税号
    @ApiModelProperty(value = "创建者")
    private String createBy; //创建者

    @Override
    public String getModuCode() {
        return moduCode;
    }

    @Override
    public void setModuCode(String moduCode) {
        this.moduCode = moduCode;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenBankCode() {
        return openBankCode;
    }

    public void setOpenBankCode(String openBankCode) {
        this.openBankCode = openBankCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String getComCode() {
        return comCode;
    }

    @Override
    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Long getAssessmentLevel() {
        return assessmentLevel;
    }

    public void setAssessmentLevel(Long assessmentLevel) {
        this.assessmentLevel = assessmentLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSupplyProd() {
        return supplyProd;
    }

    public void setSupplyProd(String supplyProd) {
        this.supplyProd = supplyProd;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLowPerson() {
        return lowPerson;
    }

    public void setLowPerson(String lowPerson) {
        this.lowPerson = lowPerson;
    }

    public String getLowPersonCarid() {
        return lowPersonCarid;
    }

    public void setLowPersonCarid(String lowPersonCarid) {
        this.lowPersonCarid = lowPersonCarid;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}