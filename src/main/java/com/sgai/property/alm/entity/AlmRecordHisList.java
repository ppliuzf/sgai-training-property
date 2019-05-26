package com.sgai.property.alm.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
    *  
    * ClassName: AlmRecordHisList  
    * com.sgai.property.commonService.vo;(报警记录历史表Entity)
    * @author ASUS  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class AlmRecordHisList extends BoEntity<AlmRecordHisList> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "报警记录id")
	private String masterId;		// 报警记录id
	@ApiModelProperty(value = "报警记录编号")
	private String recordNum;		// 年月日+001
	@ApiModelProperty(value = "报警等级")
	private String levelCode;		// 报警等级通过设备类型，来确定报警等级
	@ApiModelProperty(value = "等级名称")
	private String levelName;		// 等级名称
	@ApiModelProperty(value = "报警分类编码")
	private String alermTypeCode;		// 报警分类编码
	@ApiModelProperty(value = "报警分类名称")
	private String alermTypeName;		// 报警分类名称
	@ApiModelProperty(value = "专业代码")
	private String profCode;		// 专业代码
	@ApiModelProperty(value = "专业名称")
	private String profName;		// 专业名称
	@ApiModelProperty(value = "业主编号")
	private String devicesCode;		// 业主编号
	@ApiModelProperty(value = "设备名称")
	private String devicesName;		// 设备名称
	@ApiModelProperty(value = "空间位置")
	private String spaceId;		// 空间位置
	@ApiModelProperty(value = "空间名称")
	private String spaceName;		// 空间名称
	@ApiModelProperty(value = "报警时间")
	private Date alarmTime;		// 报警时间
	@ApiModelProperty(value = "处理时间")
	private Date processTime;	//处理时间
	@ApiModelProperty(value = "报警信息")
	private String alarmMsg;		// 报警信息
	@ApiModelProperty(value = "状态")
	private String states;		// 10：已接收20：处理中30：终止40：已处理
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	// 非数据库字段
	private String equipmentCode;		 //运行监控的设备编码
	private String equipmentName;       //运行监控的设备名称
	
	
	
	
	public AlmRecordHisList() {
		super();
	}

	public AlmRecordHisList(String id){
		super(id);
	}
	
	
	

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	@Length(min=0, max=60, message="报警记录id长度必须介于 0 和 60 之间")
	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	
	@Length(min=0, max=60, message="年月日+001长度必须介于 0 和 60 之间")
	public String getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}
	
	@Length(min=0, max=60, message="报警等级通过设备类型，来确定报警等级长度必须介于 0 和 60 之间")
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	@Length(min=0, max=60, message="等级名称长度必须介于 0 和 60 之间")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Length(min=0, max=60, message="报警分类编码长度必须介于 0 和 60 之间")
	public String getAlermTypeCode() {
		return alermTypeCode;
	}

	public void setAlermTypeCode(String alermTypeCode) {
		this.alermTypeCode = alermTypeCode;
	}
	
	@Length(min=0, max=60, message="报警分类名称长度必须介于 0 和 60 之间")
	public String getAlermTypeName() {
		return alermTypeName;
	}

	public void setAlermTypeName(String alermTypeName) {
		this.alermTypeName = alermTypeName;
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
	
	@Length(min=0, max=60, message="业主编号长度必须介于 0 和 60 之间")
	public String getDevicesCode() {
		return devicesCode;
	}

	public void setDevicesCode(String devicesCode) {
		this.devicesCode = devicesCode;
	}
	
	@Length(min=0, max=60, message="设备名称长度必须介于 0 和 60 之间")
	public String getDevicesName() {
		return devicesName;
	}

	public void setDevicesName(String devicesName) {
		this.devicesName = devicesName;
	}
	
	@Length(min=0, max=60, message="空间位置长度必须介于 0 和 60 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=0, max=128, message="空间名称长度必须介于 0 和 128 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	
	@Length(min=0, max=512, message="报警信息长度必须介于 0 和 512 之间")
	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}
	
	@Length(min=0, max=2, message="10：已接收20：处理中30：终止40：已处理长度必须介于 0 和 2 之间")
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
	public Date getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
}