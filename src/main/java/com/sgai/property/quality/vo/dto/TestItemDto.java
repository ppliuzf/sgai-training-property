package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

public class TestItemDto {
	@ApiModelProperty(value = "检验项Id")
	private String id; // 检验项名称
	@ApiModelProperty(value = "检验项名称")
	private String tiName; // 检验项名称

	@ApiModelProperty(value = "专业范畴id")
	private String pcId; // 专业范畴id

	@ApiModelProperty(value = "专业范畴名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "检查标准")
	private String tiStandard; // 检查标准
	@ApiModelProperty(value = "答题类型(0:数字型,1:选择型)  如果是0-则tiMax和tiMin要填数字 , 如果是1-则tiMax和tiMin不填 ")
	private Integer tiIsInput; // 答题类型(0:数字型,1:选择型)
	@ApiModelProperty(value = "待选项(多个选项拼接)")
	private String tiOptions; // 待选项(多个选项拼接)
	@ApiModelProperty(value = "合格选项")
	private String tiStandardOption; // 合格选项
	@ApiModelProperty(value = "上限")
	private String tiMax; // 上限
	@ApiModelProperty(value = "下限")
	private String tiMin; // 下限
	@ApiModelProperty(value = "单位")
	private String tiUnit; // 单位
	@ApiModelProperty(value = "完成时限")
	private Integer tiLimitTime; // 完成时限
	@ApiModelProperty(value = "完成时限单位(1天,2小时,3分钟)")
	private Integer tiFinshUnit; // 完成时限单位(1天,2小时,3分钟)
	@ApiModelProperty(value = "缺陷责任岗位id")
	private Long tiPostId; // 缺陷责任岗位id
	@ApiModelProperty(value = "缺陷责任岗位名称")
	private String tiPostName; // 缺陷责任岗位名称
	@ApiModelProperty(value = "整改要求")
	private String tiRectificationRequirements; // 整改要求
	@ApiModelProperty(value = "图片")
	private String urls; // 图片
	@ApiModelProperty(value = "是否答题(0未检测,1已检测)")
	private Integer tiIsSubmit;
	@ApiModelProperty(value = "是否提交缺陷整改(0:未提交整改,1:已提交整改)")
	private Integer tiHasDefect;
	@ApiModelProperty(value = "填入的检测结果")
	private String tiInputResult;
	@ApiModelProperty(value = "状态(1合格,2缺陷)")
	private Integer tiStatus;
	private Integer taskType;

	@ApiModelProperty(value = "检测时间")
	private Long tiCheckTime;

	@ApiModelProperty(value = "计划ID")
	private String recordId;

	@ApiModelProperty(value = "模板ID")
	private String tpId;

	@ApiModelProperty(value = "责任人ID")
	private String tiDutyEmpIds;

	@ApiModelProperty(value = "日期时间")
	private Long dateTime ;

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer type;



	public Long getDateTime() {
		return dateTime;
	}

	public String getId() {
		return id;
	}

	public String getPcId() {
		return pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public String getRecordId() {
		return recordId;
	}

	public Long getTiCheckTime() {
		return tiCheckTime;
	}

	public String getTiDutyEmpIds() {
		return tiDutyEmpIds;
	}

	public Integer getTiFinshUnit() {
		return tiFinshUnit;
	}

	public Integer getTiHasDefect() {
		return tiHasDefect;
	}


	public String getTiInputResult() {
		return tiInputResult;
	}

	public Integer getTiIsInput() {
		return tiIsInput;
	}

	public Integer getTiIsSubmit() {
		return tiIsSubmit;
	}

	public Integer getTiLimitTime() {
		return tiLimitTime;
	}

	public String getTiMax() {
		return tiMax;
	}

	public String getTiMin() {
		return tiMin;
	}

	public String getTiName() {
		return tiName;
	}

	public String getTiOptions() {
		return tiOptions;
	}

	public Long getTiPostId() {
		return tiPostId;
	}

	public String getTiPostName() {
		return tiPostName;
	}

	public String getTiRectificationRequirements() {
		return tiRectificationRequirements;
	}

	public String getTiStandard() {
		return tiStandard;
	}

	public String getTiStandardOption() {
		return tiStandardOption;
	}

	public Integer getTiStatus() {
		return tiStatus;
	}

	public String getTiUnit() {
		return tiUnit;
	}

	public String getTpId() {
		return tpId;
	}

	public String getUrls() {
		return urls;
	}

	public void setDateTime(Long dateTime) {
		this.dateTime = dateTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public void setTiCheckTime(Long tiCheckTime) {
		this.tiCheckTime = tiCheckTime;
	}

	public void setTiDutyEmpIds(String tiDutyEmpIds) {
		this.tiDutyEmpIds = tiDutyEmpIds;
	}


	public void setTiFinshUnit(Integer tiFinshUnit) {
		this.tiFinshUnit = tiFinshUnit;
	}

	public void setTiHasDefect(Integer tiHasDefect) {
		this.tiHasDefect = tiHasDefect;
	}

	public void setTiInputResult(String tiInputResult) {
		this.tiInputResult = tiInputResult;
	}

	public void setTiIsInput(Integer tiIsInput) {
		this.tiIsInput = tiIsInput;
	}

	public void setTiIsSubmit(Integer tiIsSubmit) {
		this.tiIsSubmit = tiIsSubmit;
	}

	public void setTiLimitTime(Integer tiLimitTime) {
		this.tiLimitTime = tiLimitTime;
	}

	public void setTiMax(String tiMax) {
		this.tiMax = tiMax;
	}

	public void setTiMin(String tiMin) {
		this.tiMin = tiMin;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public void setTiOptions(String tiOptions) {
		this.tiOptions = tiOptions;
	}

	public void setTiPostId(Long tiPostId) {
		this.tiPostId = tiPostId;
	}

	public void setTiPostName(String tiPostName) {
		this.tiPostName = tiPostName;
	}

	public void setTiRectificationRequirements(String tiRectificationRequirements) {
		this.tiRectificationRequirements = tiRectificationRequirements;
	}

	public void setTiStandard(String tiStandard) {
		this.tiStandard = tiStandard;
	}

	public void setTiStandardOption(String tiStandardOption) {
		this.tiStandardOption = tiStandardOption;
	}

	public void setTiStatus(Integer tiStatus) {
		this.tiStatus = tiStatus;
	}

	public void setTiUnit(String tiUnit) {
		this.tiUnit = tiUnit;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
}