package com.sgai.property.mdm.entity;

import com.sgai.common.persistence.BoEntity;

/**
 * ClassName:MdmDevicesUseInfoVo <br/>
 *  ADD FUNCTION. <br/>
 * Reason:	  REASON. <br/>
 * Date:     2017年11月30日 下午3:36:01 <br/>
 * @author   yangyz
 * @version  
 * @since    JDK 1.8	 
 */
public class MdmAttribute extends BoEntity<MdmAttribute>{

	private static final long serialVersionUID = 1L;
	private String devicesCode;		// 业主编号
	private String attrName;   //属性名称
	private String attrValue;   //属性值（只存实时值）
	public String getDevicesCode() {
		return devicesCode;
	}
	public void setDevicesCode(String devicesCode) {
		this.devicesCode = devicesCode;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
	
	
	
	
}

