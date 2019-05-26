package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;

/**
 * 事件详细类别维护Entity
 * @author liushang
 * @version 2017-12-05
 */
public class MdmEmTypeDetail extends BoEntity<MdmEmTypeDetail> {
	
	private static final long serialVersionUID = 1L;
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String emTypeDetailCode;		// 事件类型明细编码
	private String emTypeDetailName;		// 事件类型明细名称
	private String emTypeCode;		// 事件类型ID
	
	/*自定义属性*/
	private String emTypeName;
	public MdmEmTypeDetail() {
		super();
	}

	public MdmEmTypeDetail(String id){
		super(id);
	}

	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=60, message="事件类型明细编码长度必须介于 0 和 60 之间")
	public String getEmTypeDetailCode() {
		return emTypeDetailCode;
	}

	public void setEmTypeDetailCode(String emTypeDetailCode) {
		this.emTypeDetailCode = emTypeDetailCode;
	}
	
	@Length(min=0, max=128, message="事件类型明细名称长度必须介于 0 和 128 之间")
	public String getEmTypeDetailName() {
		return emTypeDetailName;
	}

	public void setEmTypeDetailName(String emTypeDetailName) {
		this.emTypeDetailName = emTypeDetailName;
	}
	
	@Length(min=0, max=60, message="事件类型ID长度必须介于 0 和 60 之间")
	public String getEmTypeCode() {
		return emTypeCode;
	}

	public void setEmTypeCode(String emTypeCode) {
		this.emTypeCode = emTypeCode;
	}

	public String getEmTypeName() {
		return emTypeName;
	}

	public void setEmTypeName(String emTypeName) {
		this.emTypeName = emTypeName;
	}
	
}