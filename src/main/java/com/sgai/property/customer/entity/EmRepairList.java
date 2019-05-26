package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 修理事件维护Entity
 * @author guanze
 * @version 2017-12-05
 */
public class EmRepairList extends BoEntity<EmRepairList> {
	
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
	
	public EmRepairList() {
		super();
	}

	public EmRepairList(String id){
		super(id);
	}

	
	@Length(min=0, max=32, message="事件编号：对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等长度必须介于 0 和 32 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=60, message="事件类别：维修检修保养保修自动长度必须介于 0 和 60 之间")
	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}
	
	@Length(min=0, max=60, message="事件标题长度必须介于 0 和 60 之间")
	public String getEmTitle() {
		return emTitle;
	}

	public void setEmTitle(String emTitle) {
		this.emTitle = emTitle;
	}
	
	@Length(min=0, max=60, message="报修人长度必须介于 0 和 60 之间")
	public String getRepairPerson() {
		return repairPerson;
	}

	public void setRepairPerson(String repairPerson) {
		this.repairPerson = repairPerson;
	}
	
	@Length(min=0, max=60, message="联系电话长度必须介于 0 和 60 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=60, message="报修地址长度必须介于 0 和 60 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	


	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}
	
	@Length(min=0, max=60, message="报修内容长度必须介于 0 和 60 之间")
	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	
	@Length(min=0, max=60, message="报修说明长度必须介于 0 和 60 之间")
	public String getRepairDesc() {
		return repairDesc;
	}

	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}
	
	@Length(min=0, max=60, message="事件来源长度必须介于 0 和 60 之间")
	public String getRepairFrom() {
		return repairFrom;
	}

	public void setRepairFrom(String repairFrom) {
		this.repairFrom = repairFrom;
	}
	
	@Length(min=0, max=60, message="工单号长度必须介于 0 和 60 之间")
	public String getFromNum() {
		return fromNum;
	}

	public void setFromNum(String fromNum) {
		this.fromNum = fromNum;
	}
	
	@Length(min=0, max=60, message="事件状态：待核实待指派待处理已完成已终止长度必须介于 0 和 60 之间")
	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRepairDate() {
		return repairDate;
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
	
}