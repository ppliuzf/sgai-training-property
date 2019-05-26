package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

/**
 * 物料分类表Entity
 * @author liushang
 * @version 2017-11-24
 */
public class MdmMatClass extends BoEntity<MdmMatClass> {
	
	private static final long serialVersionUID = 1L;
	private String matTypeCode;		// 物料分类编码
	private String matTypeName;		// 物料分类名称
	private String matTypeDesc;		// 说明
	private String enabledFlag;		// 生效标识：1.选项为:'Y':是'N':否默认为'Y'
	
	public MdmMatClass() {
		super();
	}

	public MdmMatClass(String id){
		super(id);
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
	
	@Length(min=0, max=512, message="说明长度必须介于 0 和 512 之间")
	public String getMatTypeDesc() {
		return matTypeDesc;
	}

	public void setMatTypeDesc(String matTypeDesc) {
		this.matTypeDesc = matTypeDesc;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}