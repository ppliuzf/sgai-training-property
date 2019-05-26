package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 策略运行日程Entity
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagModelCalendar extends BoEntity<RuagModelCalendar> {
	
	private static final long serialVersionUID = 1L;
	private Date curDate;		// 日期
	private String modelId;		// 模式代码
	private String modelName;		// 模式名称
	private String modelStatus;		// 模式状态
	private String controlCode;		// 控制类型编码
	private String controlType;		// 控制类型
	private Long modelDegree;		// 0123456
	private String timeEnd;		// 结束日期
	private String timeStart;		// 开始日期
	private String changeFlag;  //修改标识，N代表没有修改过某一天某个模式的设置，Y反之
	public RuagModelCalendar() {
		super();
	}

	public RuagModelCalendar(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
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
	
	@Length(min=0, max=60, message="模式状态长度必须介于 0 和 60 之间")
	public String getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(String modelStatus) {
		this.modelStatus = modelStatus;
	}
	
	@Length(min=0, max=60, message="控制类型编码长度必须介于 0 和 60 之间")
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	
	@Length(min=0, max=60, message="控制类型长度必须介于 0 和 60 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	public Long getModelDegree() {
		return modelDegree;
	}

	public void setModelDegree(Long modelDegree) {
		this.modelDegree = modelDegree;
	}
	
	@Length(min=0, max=60, message="结束日期长度必须介于 0 和 60 之间")
	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	@Length(min=0, max=60, message="开始日期长度必须介于 0 和 60 之间")
	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	
}