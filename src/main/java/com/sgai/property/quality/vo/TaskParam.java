package com.sgai.property.quality.vo;

import com.sgai.property.quality.vo.plan.JhDayVo;
import com.sgai.property.quality.vo.plan.JhMonthVo;
import com.sgai.property.quality.vo.plan.JhWeekVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TaskParam {

	@ApiModelProperty(value = "任务名称")
    private String taskName; //任务名称
	@ApiModelProperty(value = "任务开始时间")
    private Long taskBeginTime; //任务开始时间
	@ApiModelProperty(value = "任务结束时间")
    private Long taskEndTime; //任务结束时间
	@ApiModelProperty(value = "任务说明")
    private String taskDesc; //任务说明
	@ApiModelProperty(value = "责任人id列表")
	private List<String> dutyPersonIdList;
	@ApiModelProperty(value = "是否审核(0:否;1:是)")
    private Long taskNeedAppr; //是否审核(0:否;1:是)
	@ApiModelProperty(value = "审核人id列表")
	private List<String> approverList;
	@ApiModelProperty(value = "任务类型(1:执行;2:检验)")
    private Long taskType; //任务类型(1:执行;2:检验)
	@ApiModelProperty(value = "计划id")
    private String recordId; //计划id
	@ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
	@ApiModelProperty(value = "阶段id")
	private String periodId; //阶段id
	@ApiModelProperty(value = "阶段名称")
    private String periodName; //阶段名称

	@ApiModelProperty(value = "关联对象选中的值(JSON)")
    private String associatedObject; //关联对象选中的值(JSON)
	@ApiModelProperty(value = "审核要求")
    private String taskApprRequire; //审核要求

    @ApiModelProperty(value = "图片")
    private List<TaskImgVo> imgList;
	@ApiModelProperty(value = "方案ID")
    private String planId;
	@ApiModelProperty(value = "关联配置名称;多个以、隔开")
	private String relationName;

	@ApiModelProperty(value = "关联模板id")
	private String templateId;
	@ApiModelProperty(value = "关联模板名称")
	private String templateName;

	@ApiModelProperty(value = "是否有重复执行 0 没有  1 有")
	private Long taskFlag;//是否有重复执行 0 没有  1 有
	@ApiModelProperty(value = "选择重复标示：1 选择天  2 选择周  3 选择月")
	private Long optionFlag;

	@ApiModelProperty(value = "0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日")
	private String taskDay; //0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日


	@ApiModelProperty(value = "按每日重复执行")
	private JhDayVo dayVo;
	@ApiModelProperty(value = "按每周重复执行")
	private JhWeekVo weekVo;//按每周重复执行
	@ApiModelProperty(value = "按每月重复执行")
	private JhMonthVo monthVo;
	private List<String> weekAry;

	@ApiModelProperty(value = "任务范围类型（1关联的空间主数据，2物料主数据，3设备主数据，4供应商主数据）")
	private String taskScopeType;
	@ApiModelProperty(value = "关联的空间主数据")
	private String spaceData;
	@ApiModelProperty(value = "关联物料主数据")
	private String materielData;
	@ApiModelProperty(value = "关联设备主数据")
	private String equipmentData;
	@ApiModelProperty(value = "关联的供应商主数据")
	private String supplierData;

	@ApiModelProperty(value = "选择年时，存放年月")
	private String taskYear;
	@ApiModelProperty(value = "开始日")
	private String beginDate;
	@ApiModelProperty(value = "截止日")
	private String endDate;
	@ApiModelProperty(value = "开始时间")
	private String beginTime;
	@ApiModelProperty(value = "截止时间")
	private String endTime;
	private String taskdayEnd;
	private String taskdayStart;

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer type;


	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTaskYear() {
		return taskYear;
	}

	public void setTaskYear(String taskYear) {
		this.taskYear = taskYear;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(Long taskFlag) {
		this.taskFlag = taskFlag;
	}

	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Long getTaskBeginTime() {
		return taskBeginTime;
	}
	public void setTaskBeginTime(Long taskBeginTime) {
		this.taskBeginTime = taskBeginTime;
	}
	public Long getTaskEndTime() {
		return taskEndTime;
	}
	public void setTaskEndTime(Long taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public List<String> getDutyPersonIdList() {
		return dutyPersonIdList;
	}
	public void setDutyPersonIdList(List<String> dutyPersonIdList) {
		this.dutyPersonIdList = dutyPersonIdList;
	}
	public Long getTaskNeedAppr() {
		return taskNeedAppr;
	}
	public void setTaskNeedAppr(Long taskNeedAppr) {
		this.taskNeedAppr = taskNeedAppr;
	}
	public List<String> getApproverList() {
		return approverList;
	}
	public void setApproverList(List<String> approverList) {
		this.approverList = approverList;
	}
	public String getTaskApprRequire() {
		return taskApprRequire;
	}
	public void setTaskApprRequire(String taskApprRequire) {
		this.taskApprRequire = taskApprRequire;
	}
	public Long getTaskType() {
		return taskType;
	}
	public void setTaskType(Long taskType) {
		this.taskType = taskType;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getAssociatedObject() {
		return associatedObject;
	}
	public void setAssociatedObject(String associatedObject) {
		this.associatedObject = associatedObject;
	}
	public List<TaskImgVo> getImgList() {
		return imgList;
	}
	public void setImgList(List<TaskImgVo> imgList) {
		this.imgList = imgList;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public JhDayVo getDayVo() {
		return dayVo;
	}

	public void setDayVo(JhDayVo dayVo) {
		this.dayVo = dayVo;
	}

	public JhWeekVo getWeekVo() {
		return weekVo;
	}

	public void setWeekVo(JhWeekVo weekVo) {
		this.weekVo = weekVo;
	}

	public JhMonthVo getMonthVo() {
		return monthVo;
	}

	public void setMonthVo(JhMonthVo monthVo) {
		this.monthVo = monthVo;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTaskScopeType() {
		return taskScopeType;
	}

	public void setTaskScopeType(String taskScopeType) {
		this.taskScopeType = taskScopeType;
	}

	public String getSpaceData() {
		return spaceData;
	}

	public void setSpaceData(String spaceData) {
		this.spaceData = spaceData;
	}

	public String getMaterielData() {
		return materielData;
	}

	public void setMaterielData(String materielData) {
		this.materielData = materielData;
	}

	public String getEquipmentData() {
		return equipmentData;
	}

	public void setEquipmentData(String equipmentData) {
		this.equipmentData = equipmentData;
	}

	public String getSupplierData() {
		return supplierData;
	}

	public void setSupplierData(String supplierData) {
		this.supplierData = supplierData;
	}

	public Long getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(Long optionFlag) {
		this.optionFlag = optionFlag;
	}

	public String getTaskDay() {
		return taskDay;
	}

	public void setTaskDay(String taskDay) {
		this.taskDay = taskDay;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTaskdayEnd() {
		return taskdayEnd;
	}

	public void setTaskdayEnd(String taskdayEnd) {
		this.taskdayEnd = taskdayEnd;
	}

	public String getTaskdayStart() {
		return taskdayStart;
	}

	public void setTaskdayStart(String taskdayStart) {
		this.taskdayStart = taskdayStart;
	}

	public List<String> getWeekAry() {
		return weekAry;
	}

	public void setWeekAry(List<String> weekAry) {
		this.weekAry = weekAry;
	}
}
