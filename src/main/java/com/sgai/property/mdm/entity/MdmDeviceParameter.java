package com.sgai.property.mdm.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmDeviceParameter  
    * com.sgai.property.commonService.vo;(设备运行参数)
    * @author yangyz  
    * Date 2017年12月30日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmDeviceParameter extends BoEntity<MdmDeviceParameter> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "设备专业编码")
	private String deviceProfCode;            //设备专业代码
	@ApiModelProperty(value = "设备专业名称")
	private String deviceProfName;            //设备专业名称
	@ApiModelProperty(value = "设备类型编码")
	private String deviceClassCode;		// 设备类型编码
	@ApiModelProperty(value = "设备类型名称")
	private String deviceClassName;     //设备类型名称
	@ApiModelProperty(value = "参数代码")
	private String paramCode;		// 参数代码
	@ApiModelProperty(value = "参数名称")
	private String paramName;		// 参数名称
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String switchFlag; //开关标识
	
	public MdmDeviceParameter() {
		super();
	}

	public MdmDeviceParameter(String id){
		super(id);
	}

	@Length(min=0, max=60, message="设备类型编码长度必须介于 0 和 60 之间")
	public String getDeviceClassCode() {
		return deviceClassCode;
	}

	public void setDeviceClassCode(String deviceClassCode) {
		this.deviceClassCode = deviceClassCode;
	}
	
	@Length(min=0, max=60, message="参数代码长度必须介于 0 和 60 之间")
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	@Length(min=0, max=60, message="参数名称长度必须介于 0 和 60 之间")
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	/**
	 * deviceProfCode.
	 *
	 * @return  the deviceProfCode
	 * @since   JDK 1.8
	 */
	public String getDeviceProfCode() {
		return deviceProfCode;
	}

	/**
	 * deviceProfCode.
	 *
	 * @param   deviceProfCode    the deviceProfCode to set
	 * @since   JDK 1.8
	 */
	public void setDeviceProfCode(String deviceProfCode) {
		this.deviceProfCode = deviceProfCode;
	}

	/**
	 * deviceProfName.
	 *
	 * @return  the deviceProfName
	 * @since   JDK 1.8
	 */
	public String getDeviceProfName() {
		return deviceProfName;
	}

	/**
	 * deviceProfName.
	 *
	 * @param   deviceProfName    the deviceProfName to set
	 * @since   JDK 1.8
	 */
	public void setDeviceProfName(String deviceProfName) {
		this.deviceProfName = deviceProfName;
	}

	/**
	 * deviceClassName.
	 *
	 * @return  the deviceClassName
	 * @since   JDK 1.8
	 */
	public String getDeviceClassName() {
		return deviceClassName;
	}

	/**
	 * deviceClassName.
	 *
	 * @param   deviceClassName    the deviceClassName to set
	 * @since   JDK 1.8
	 */
	public void setDeviceClassName(String deviceClassName) {
		this.deviceClassName = deviceClassName;
	}

	public String getSwitchFlag() {
		return switchFlag;
	}

	public void setSwitchFlag(String switchFlag) {
		this.switchFlag = switchFlag;
	}
}