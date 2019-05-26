package com.sgai.property.mdm.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 供应商&mdash;物料分类关系表Entity
 * @author liushang
 * @version 2017-11-24
 */
public class MdmSuppMatClassRelation extends BoEntity<MdmSuppMatClassRelation> {
	
	private static final long serialVersionUID = 1L;
	private String supplierNo;		// 供应商编号
	private String matTypeCode;		// 物料分类编码
	private String matTypeName;		// 物料分类名称
	private String enabledFlag;		// 生效标识：1.选项为:'Y':是'N':否默认为'Y'

	public MdmSuppMatClassRelation() {
		super();
	}

	public MdmSuppMatClassRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="供应商编号长度必须介于 0 和 64 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Length(min=0, max=60, message="物料分类编码长度必须介于 0 和 60 之间")
	public String getMatTypeCode() {
		return matTypeCode;
	}

	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
	
	@Length(min=0, max=60, message="物料分类名称长度必须介于 0 和 60 之间")
	public String getMatTypeName() {
		return matTypeName;
	}

	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}