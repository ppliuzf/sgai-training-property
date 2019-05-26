package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;

import com.sgai.common.persistence.BoEntity;

/**
 * 供应商数据Entity
 * @author liushang
 * @version 2017-11-24
 */
public class MdmSupplierInfo extends BoEntity<MdmSupplierInfo> {
	
	private static final long serialVersionUID = 1L;
	private String supplierNo;		// 供应商编号
	private String supplierName;		// 供应商名称
	private String regAddress;		// 注 册 地 址
	private String lowPerson;		// 法人
	private String lowPersonCarid;		// 法人身份证号码
	private String comType;		// 国营民营外企合资
	private String orgCode;		// 组织机构代码
	private String taxNo;		// 税号
	private String openBank;		// 开户行
	private String openBankCode;		// 开户行代码
	private String accountNo;		// 账户编号
	private String zipCode;		// 邮编
	private String officeAddress;		// 办公地址
	private String eMail;		// 邮箱地址
	private String supplyProd;		// 描述信息
	private String province;//省份
	private String assessmentLevel;//评价等级
	private String contactPhone; //联系电话
	//private String remarks;
	public MdmSupplierInfo() {
		super();
	}

	public MdmSupplierInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="供应商编号长度必须介于 0 和 64 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Length(min=0, max=128, message="供应商名称长度必须介于 0 和 128 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@Length(min=0, max=128, message="注 册 地 址长度必须介于 0 和 128 之间")
	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	
	@Length(min=0, max=128, message="法人长度必须介于 0 和 128 之间")
	public String getLowPerson() {
		return lowPerson;
	}

	public void setLowPerson(String lowPerson) {
		this.lowPerson = lowPerson;
	}
	
	@Length(min=0, max=32, message="法人身份证号码长度必须介于 0 和 32 之间")
	public String getLowPersonCarid() {
		return lowPersonCarid;
	}

	public void setLowPersonCarid(String lowPersonCarid) {
		this.lowPersonCarid = lowPersonCarid;
	}
	
	@Length(min=0, max=32, message="国营民营外企合资长度必须介于 0 和 32 之间")
	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}
	
	@Length(min=0, max=32, message="组织机构代码长度必须介于 0 和 32 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=32, message="税号长度必须介于 0 和 32 之间")
	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	
	@Length(min=0, max=64, message="开户行长度必须介于 0 和 64 之间")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	@Length(min=0, max=32, message="开户行代码长度必须介于 0 和 32 之间")
	public String getOpenBankCode() {
		return openBankCode;
	}

	public void setOpenBankCode(String openBankCode) {
		this.openBankCode = openBankCode;
	}
	
	@Length(min=0, max=64, message="账户编号长度必须介于 0 和 64 之间")
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	@Length(min=0, max=32, message="邮编长度必须介于 0 和 32 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=256, message="办公地址长度必须介于 0 和 256 之间")
	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	@Length(min=0, max=64, message="邮箱地址长度必须介于 0 和 64 之间")
	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	@Length(min=0, max=512, message="描述信息长度必须介于 0 和 512 之间")
	public String getSupplyProd() {
		return supplyProd;
	}

	public void setSupplyProd(String supplyProd) {
		this.supplyProd = supplyProd;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAssessmentLevel() {
		return assessmentLevel;
	}

	public void setAssessmentLevel(String assessmentLevel) {
		this.assessmentLevel = assessmentLevel;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/*public String getRemarks() {
		return this.remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}*/
	
	
}