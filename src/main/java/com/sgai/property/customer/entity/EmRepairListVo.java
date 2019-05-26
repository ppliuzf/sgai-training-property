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
 * ClassName: EmRepairListVo  
 * Description: (维修事件临时对象)
 * @author lenovo  
 * Date 2018年6月6日  
 * Company 首自信--智慧城市创新中心
 */
public class EmRepairListVo extends BoEntity<EmRepairListVo> {

	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 事件编号：对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类别")
	private String repairType;  // 事件类别：维修检修保养保修自动
	@ApiModelProperty(value = "流程类别")
	private String emType;		// 流程类别：WX修理事件 TS投诉事件等等
	@ApiModelProperty(value = "事件标题")
	private String emTitle;		// 事件标题
	@ApiModelProperty(value = "报修人")
	private String repairPerson;		// 报修人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "报修地址")
	private String address;		// 报修地址
	@ApiModelProperty(value = "报修时间")
	@JsonSerialize(using=DateJsonSerializer.class)
    @JsonDeserialize(using=DateJsonDeserializer.class)
	private Date repairDate;		// 报修时间
	@ApiModelProperty(value = "报修内容")
	private String repairContent;		// 报修内容
	@ApiModelProperty(value = "报修说明")
	private String repairDesc;		// 报修说明
	@ApiModelProperty(value = "事件来源")
	private String repairFrom;		// 事件来源
	@ApiModelProperty(value = "工单号")
	private String fromNum;		// 工单号
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：待核实待指派待处理已完成已终止
	@ApiModelProperty(value = "维修照片")
	private String repairPhoto;    //图片
	@ApiModelProperty(value = "执行人代码")
	private String userCode;    //执行人代码
	@ApiModelProperty(value = "执行人名字")
	private String userName;	//执行人名字
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 可用标识：1.选项为:'Y':是'N':否默认为'Y'
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
	 * repairType.
	 *
	 * @return  the repairType
	 * @since   JDK 1.8
	 */
	public String getRepairType() {
		return repairType;
	}
	/**
	 * repairType.
	 *
	 * @param   repairType    the repairType to set
	 * @since   JDK 1.8
	 */
	public void setRepairType(String repairType) {
		this.repairType = repairType;
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
	 * emTitle.
	 *
	 * @return  the emTitle
	 * @since   JDK 1.8
	 */
	public String getEmTitle() {
		return emTitle;
	}
	/**
	 * emTitle.
	 *
	 * @param   emTitle    the emTitle to set
	 * @since   JDK 1.8
	 */
	public void setEmTitle(String emTitle) {
		this.emTitle = emTitle;
	}
	/**
	 * repairPerson.
	 *
	 * @return  the repairPerson
	 * @since   JDK 1.8
	 */
	public String getRepairPerson() {
		return repairPerson;
	}
	/**
	 * repairPerson.
	 *
	 * @param   repairPerson    the repairPerson to set
	 * @since   JDK 1.8
	 */
	public void setRepairPerson(String repairPerson) {
		this.repairPerson = repairPerson;
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
	 * repairDate.
	 *
	 * @return  the repairDate
	 * @since   JDK 1.8
	 */
	public Date getRepairDate() {
		return repairDate;
	}
	/**
	 * repairDate.
	 *
	 * @param   repairDate    the repairDate to set
	 * @since   JDK 1.8
	 */
	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	/**
	 * repairContent.
	 *
	 * @return  the repairContent
	 * @since   JDK 1.8
	 */
	public String getRepairContent() {
		return repairContent;
	}
	/**
	 * repairContent.
	 *
	 * @param   repairContent    the repairContent to set
	 * @since   JDK 1.8
	 */
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	/**
	 * repairDesc.
	 *
	 * @return  the repairDesc
	 * @since   JDK 1.8
	 */
	public String getRepairDesc() {
		return repairDesc;
	}
	/**
	 * repairDesc.
	 *
	 * @param   repairDesc    the repairDesc to set
	 * @since   JDK 1.8
	 */
	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}
	/**
	 * repairFrom.
	 *
	 * @return  the repairFrom
	 * @since   JDK 1.8
	 */
	public String getRepairFrom() {
		return repairFrom;
	}
	/**
	 * repairFrom.
	 *
	 * @param   repairFrom    the repairFrom to set
	 * @since   JDK 1.8
	 */
	public void setRepairFrom(String repairFrom) {
		this.repairFrom = repairFrom;
	}
	/**
	 * fromNum.
	 *
	 * @return  the fromNum
	 * @since   JDK 1.8
	 */
	public String getFromNum() {
		return fromNum;
	}
	/**
	 * fromNum.
	 *
	 * @param   fromNum    the fromNum to set
	 * @since   JDK 1.8
	 */
	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
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
	 * repairPhoto.
	 *
	 * @return  the repairPhoto
	 * @since   JDK 1.8
	 */
	public String getRepairPhoto() {
		return repairPhoto;
	}
	/**
	 * repairPhoto.
	 *
	 * @param   repairPhoto    the repairPhoto to set
	 * @since   JDK 1.8
	 */
	public void setRepairPhoto(String repairPhoto) {
		this.repairPhoto = repairPhoto;
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
	/**
	 * serialversionuid.
	 *
	 * @return  the serialversionuid
	 * @since   JDK 1.8
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

