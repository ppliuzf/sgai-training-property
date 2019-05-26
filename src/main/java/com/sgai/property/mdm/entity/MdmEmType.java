package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

/**
 * 事件类别维护Entity
 * @author liushang
 * @version 2017-12-05
 */
public class MdmEmType extends BoEntity<MdmEmType> {
	
	private static final long serialVersionUID = 1L;
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String emTypeCode;		// em_type_code
	private String emTypeName;		// em_type_name
	
	public MdmEmType() {
		super();
	}

	public MdmEmType(String id){
		super(id);
	}

	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=60, message="em_type_code长度必须介于 0 和 60 之间")
	public String getEmTypeCode() {
		return emTypeCode;
	}

	public void setEmTypeCode(String emTypeCode) {
		this.emTypeCode = emTypeCode;
	}
	
	@Length(min=0, max=128, message="em_type_name长度必须介于 0 和 128 之间")
	public String getEmTypeName() {
		return emTypeName;
	}

	public void setEmTypeName(String emTypeName) {
		this.emTypeName = emTypeName;
	}
	
}