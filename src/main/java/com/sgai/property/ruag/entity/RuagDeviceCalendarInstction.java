package com.sgai.property.ruag.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 *
    * @ClassName: RuagDeviceCalendarInstction
    * @com.sgai.property.commonService.vo;(设备指令状态Entity)
    * @author ASUS
    * @date 2018年1月2日
    * @Company 首自信--智慧城市创新中心
 */
public class RuagDeviceCalendarInstction extends BoEntity<RuagDeviceCalendarInstction> {

	private static final long serialVersionUID = 1L;
	private Date dciDate;		// 日期
	private String modelId;		// 模式代码
	private String modelName;		// 模式名称
	private String modelDegree;		// 策略优先级
	private String controlCode;		// 控制类型编码
	private String controlType;		// 控制类型date_model：日期模式time_model：时间模式
	private String spaceCode;		// 空间编码
	private String spaceName;		// 空间名称引用节点类型的数据的名称
	private String profCode;		// 专业代码
	private String profName;		// 专业名称
	private String deviceCode;		// 设备代码
	private String deviceName;		// 设备名称
	private String parameterId;		// 参数id
	private String parameterName;		// 参数名称
	private String parameterValue;		// 参数值
	private String timePoint;		// 时间点
	private String effectiveStatus1;		// 0：未用1：启用
	private Long instructionStatus;		// 指令状态  0--未发送 、1--已发送、2--已接收、3--已执行、4--异常
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String classCode;    //类型编码
	private String className;    //类型名称
	private String calendarId;  //策略日程id
	private String compareObj;//对比对象，主要用来标识指令是相对那种策略类型的指令无效的
	public RuagDeviceCalendarInstction() {
		super();
	}

	public RuagDeviceCalendarInstction(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getDciDate() {
		return dciDate;
	}

	public void setDciDate(Date dciDate) {
		this.dciDate = dciDate;
	}

	@Length(min=0, max=60, message="模式代码长度必须介于 0 和 60 之间")
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Length(min=0, max=60, message="模式名称长度必须介于 0 和 60 之间")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Length(min=0, max=60, message="策略优先级长度必须介于 0 和 60 之间")
	public String getModelDegree() {
		return modelDegree;
	}

	public void setModelDegree(String modelDegree) {
		this.modelDegree = modelDegree;
	}

	@Length(min=0, max=60, message="控制类型编码长度必须介于 0 和 60 之间")
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	@Length(min=0, max=60, message="控制类型date_model：日期模式time_model：时间模式长度必须介于 0 和 60 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	@Length(min=0, max=60, message="空间编码长度必须介于 0 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}

	@Length(min=0, max=60, message="空间名称引用节点类型的数据的名称长度必须介于 0 和 60 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
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

	@Length(min=0, max=60, message="设备代码长度必须介于 0 和 60 之间")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Length(min=0, max=60, message="设备名称长度必须介于 0 和 60 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Length(min=0, max=60, message="参数id长度必须介于 0 和 60 之间")
	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	@Length(min=0, max=60, message="参数名称长度必须介于 0 和 60 之间")
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	@Length(min=0, max=60, message="时间点长度必须介于 0 和 60 之间")
	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}

	@Length(min=0, max=60, message="0：未用1：启用长度必须介于 0 和 60 之间")
	public String getEffectiveStatus1() {
		return effectiveStatus1;
	}

	public void setEffectiveStatus1(String effectiveStatus1) {
		this.effectiveStatus1 = effectiveStatus1;
	}

	public Long getInstructionStatus() {
		return instructionStatus;
	}

	public void setInstructionStatus(Long instructionStatus) {
		this.instructionStatus = instructionStatus;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCompareObj() {
		return compareObj;
	}

	public void setCompareObj(String compareObj) {
		this.compareObj = compareObj;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
	}
}
