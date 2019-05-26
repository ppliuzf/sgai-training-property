package com.sgai.property.customer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sgai.common.persistence.BoEntity;
import com.sgai.common.utils.DateJsonDeserializer;
import com.sgai.common.utils.DateJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/** 
 * ClassName: EmSecuRecordVo  
 * Description: (安保事件临时对象)
 * @author lenovo  
 * Date 2018年6月6日  
 * Company 首自信--智慧城市创新中心
 */
public class EmSecuRecordVo extends BoEntity<EmSecuRecordVo> {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 事件编号
	@ApiModelProperty(value = "事件类型")
	private String emType;     //类型  AB
	@ApiModelProperty(value = "事件类别")
	private String emCategory;//事件类别
	@ApiModelProperty(value = "报案人")
	private String reportPerson;		// 报案人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "报案地址")
	private String address;		// 报案地址
	@ApiModelProperty(value = "报案时间")
	@JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date reportDatetime;		// 报案时间
	@ApiModelProperty(value = "报案内容")
	private String reportContent;		// 报案内容：文字图片视频
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：待核实：0，待指派:1，待处理：2已完成：3，已终止：4
	@ApiModelProperty(value = "是否可用")
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
	 * emCode.
	 *
	 * @return  the emCode
	 * @since   JDK 1.8
	 */
	public String getEmCode() {
		return emCode;
	}
	/**
	 * emCode.
	 *
	 * @param   emCode    the emCode to set
	 * @since   JDK 1.8
	 */
	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	/**
	 * emType.
	 *
	 * @return  the emType
	 * @since   JDK 1.8
	 */
	public String getEmType() {
		return emType;
	}
	/**
	 * emType.
	 *
	 * @param   emType    the emType to set
	 * @since   JDK 1.8
	 */
	public void setEmType(String emType) {
		this.emType = emType;
	}
	/**
	 * emCategory.
	 *
	 * @return  the emCategory
	 * @since   JDK 1.8
	 */
	public String getEmCategory() {
		return emCategory;
	}
	/**
	 * emCategory.
	 *
	 * @param   emCategory    the emCategory to set
	 * @since   JDK 1.8
	 */
	public void setEmCategory(String emCategory) {
		this.emCategory = emCategory;
	}
	/**
	 * reportPerson.
	 *
	 * @return  the reportPerson
	 * @since   JDK 1.8
	 */
	public String getReportPerson() {
		return reportPerson;
	}
	/**
	 * reportPerson.
	 *
	 * @param   reportPerson    the reportPerson to set
	 * @since   JDK 1.8
	 */
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
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
	 * reportDatetime.
	 *
	 * @return  the reportDatetime
	 * @since   JDK 1.8
	 */
	public Date getReportDatetime() {
		return reportDatetime;
	}
	/**
	 * reportDatetime.
	 *
	 * @param   reportDatetime    the reportDatetime to set
	 * @since   JDK 1.8
	 */
	public void setReportDatetime(Date reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	/**
	 * reportContent.
	 *
	 * @return  the reportContent
	 * @since   JDK 1.8
	 */
	public String getReportContent() {
		return reportContent;
	}
	/**
	 * reportContent.
	 *
	 * @param   reportContent    the reportContent to set
	 * @since   JDK 1.8
	 */
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
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

