package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmDeviceProf  
    * com.sgai.property.commonService.vo;(设备专业实体类)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmDeviceProf extends BoEntity<MdmDeviceProf> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "专业代码")
	private String profCode;		// 专业代码
	@ApiModelProperty(value = "专业名称")
	private String profName;		// 专业名称
	@ApiModelProperty(value = "专业说明")
	private String profDesc;		// 专业说明
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "可控标识")
	private String controlFlag; //'Y'代表可控，'N'代表不可控
	public MdmDeviceProf() {
		super();
	}

	public MdmDeviceProf(String id){
		super(id);
	}

	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=60, message="专业名称长度必须介于 0 和 60 之间")
	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@Length(min=0, max=512, message="专业说明长度必须介于 0 和 512 之间")
	public String getProfDesc() {
		return profDesc;
	}

	public void setProfDesc(String profDesc) {
		this.profDesc = profDesc;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getControlFlag() {
		return controlFlag;
	}

	public void setControlFlag(String controlFlag) {
		this.controlFlag = controlFlag;
	}
	
}