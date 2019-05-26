package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/** 
 * ClassName: EmComplaintsVo  
 * Description: (投诉时间临时对象)
 * @author lenovo  
 * Date 2018年6月6日  
 * Company 首自信--智慧城市创新中心
 */
public class EmComplaintsVo extends BoEntity<EmComplaintsVo> {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "投诉编号")
	private String complCode;		// 投诉编号
	@ApiModelProperty(value = "投诉类别")
	private String compCategory;	// 投诉类别：设备服务其他
	@ApiModelProperty(value = "投诉类型")
	private String complType;		//投诉类型 TS
	@ApiModelProperty(value = "投诉来源")
	private String complFrom;		// 投诉来源：0:app 1:电话
	@ApiModelProperty(value = "投诉人")
	private String complPerson;		// 投诉人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "投诉地址")
	private String address;		// 投诉地址
	@ApiModelProperty(value = "投诉事件")
	private String complTime;		// 投诉时间
	@ApiModelProperty(value = "投诉内容")
	private String complContent;		// 投诉内容
	@ApiModelProperty(value = "说明")
	private String desc;		// 说明
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：待指派:0,待处理:1,待回访:2,已完成:3,已终止:4
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 可用标识：1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "用户代码")
	private String userCode;		// 用户代码
	@ApiModelProperty(value = "节点执行人")
	private String userName;		// 节点执行人
	@ApiModelProperty(value = "处理人")
	private String procPerson;	//处理人
	@ApiModelProperty(value = "附件")
	private List<CtlAttachfile> files;
	/**
	 * complCode.
	 *
	 * @return  the complCode
	 * @since   JDK 1.8
	 */
	public String getComplCode() {
		return complCode;
	}
	/**
	 * complCode.
	 *
	 * @param   complCode    the complCode to set
	 * @since   JDK 1.8
	 */
	public void setComplCode(String complCode) {
		this.complCode = complCode;
	}
	/**
	 * compCategory.
	 *
	 * @return  the compCategory
	 * @since   JDK 1.8
	 */
	public String getCompCategory() {
		return compCategory;
	}
	/**
	 * compCategory.
	 *
	 * @param   compCategory    the compCategory to set
	 * @since   JDK 1.8
	 */
	public void setCompCategory(String compCategory) {
		this.compCategory = compCategory;
	}
	/**
	 * complType.
	 *
	 * @return  the complType
	 * @since   JDK 1.8
	 */
	public String getComplType() {
		return complType;
	}
	/**
	 * complType.
	 *
	 * @param   complType    the complType to set
	 * @since   JDK 1.8
	 */
	public void setComplType(String complType) {
		this.complType = complType;
	}
	/**
	 * complFrom.
	 *
	 * @return  the complFrom
	 * @since   JDK 1.8
	 */
	public String getComplFrom() {
		return complFrom;
	}
	/**
	 * complFrom.
	 *
	 * @param   complFrom    the complFrom to set
	 * @since   JDK 1.8
	 */
	public void setComplFrom(String complFrom) {
		this.complFrom = complFrom;
	}
	/**
	 * complPerson.
	 *
	 * @return  the complPerson
	 * @since   JDK 1.8
	 */
	public String getComplPerson() {
		return complPerson;
	}
	/**
	 * complPerson.
	 *
	 * @param   complPerson    the complPerson to set
	 * @since   JDK 1.8
	 */
	public void setComplPerson(String complPerson) {
		this.complPerson = complPerson;
	}
	/**
	 * telphone.
	 *
	 * @return  the telphone
	 * @since   JDK 1.8
	 */
	public String getTelphone() {
		return telphone;
	}
	/**
	 * telphone.
	 *
	 * @param   telphone    the telphone to set
	 * @since   JDK 1.8
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/**
	 * address.
	 *
	 * @return  the address
	 * @since   JDK 1.8
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * address.
	 *
	 * @param   address    the address to set
	 * @since   JDK 1.8
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * complTime.
	 *
	 * @return  the complTime
	 * @since   JDK 1.8
	 */
	public String getComplTime() {
		return complTime;
	}
	/**
	 * complTime.
	 *
	 * @param   complTime    the complTime to set
	 * @since   JDK 1.8
	 */
	public void setComplTime(String complTime) {
		this.complTime = complTime;
	}
	/**
	 * complContent.
	 *
	 * @return  the complContent
	 * @since   JDK 1.8
	 */
	public String getComplContent() {
		return complContent;
	}
	/**
	 * complContent.
	 *
	 * @param   complContent    the complContent to set
	 * @since   JDK 1.8
	 */
	public void setComplContent(String complContent) {
		this.complContent = complContent;
	}
	/**
	 * desc.
	 *
	 * @return  the desc
	 * @since   JDK 1.8
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * desc.
	 *
	 * @param   desc    the desc to set
	 * @since   JDK 1.8
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * states.
	 *
	 * @return  the states
	 * @since   JDK 1.8
	 */
	public String getStates() {
		return states;
	}
	/**
	 * states.
	 *
	 * @param   states    the states to set
	 * @since   JDK 1.8
	 */
	public void setStates(String states) {
		this.states = states;
	}
	/**
	 * enabledFlag.
	 *
	 * @return  the enabledFlag
	 * @since   JDK 1.8
	 */
	public String getEnabledFlag() {
		return enabledFlag;
	}
	/**
	 * enabledFlag.
	 *
	 * @param   enabledFlag    the enabledFlag to set
	 * @since   JDK 1.8
	 */
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * userCode.
	 *
	 * @return  the userCode
	 * @since   JDK 1.8
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * userCode.
	 *
	 * @param   userCode    the userCode to set
	 * @since   JDK 1.8
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * userName.
	 *
	 * @return  the userName
	 * @since   JDK 1.8
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * userName.
	 *
	 * @param   userName    the userName to set
	 * @since   JDK 1.8
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * procPerson.
	 *
	 * @return  the procPerson
	 * @since   JDK 1.8
	 */
	public String getProcPerson() {
		return procPerson;
	}
	/**
	 * procPerson.
	 *
	 * @param   procPerson    the procPerson to set
	 * @since   JDK 1.8
	 */
	public void setProcPerson(String procPerson) {
		this.procPerson = procPerson;
	}
	/**
	 * files.
	 *
	 * @return  the files
	 * @since   JDK 1.8
	 */
	public List<CtlAttachfile> getFiles() {
		return files;
	}
	/**
	 * files.
	 *
	 * @param   files    the files to set
	 * @since   JDK 1.8
	 */
	public void setFiles(List<CtlAttachfile> files) {
		this.files = files;
	}
	
	
}

