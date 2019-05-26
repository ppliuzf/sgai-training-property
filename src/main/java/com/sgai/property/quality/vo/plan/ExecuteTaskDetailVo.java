package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ExecuteTaskDetailVo {

    @ApiModelProperty(value = "taskId")
    private String taskId;

    @ApiModelProperty(value = "关联模板id")
    private String templateId;
    @ApiModelProperty(value = "关联模板名称")
    private String templateName;
    @ApiModelProperty(value = "状态")
    private String status;

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

    @ApiModelProperty(value = "任务开始时间")
    private Long taskBeginTime; //任务开始时间
    @ApiModelProperty(value = "任务结束时间")
    private Long taskEndTime; //任务结束时间

    @ApiModelProperty(value = "成果描述")
    private String desc;

    @ApiModelProperty(value = "责任岗位")
    private String dutyPerson;

    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    //新增任务专业名称 by fanchao
    @ApiModelProperty(value = "任务专业名称")
    private String pcName; //任务专业名称
    //任务项

    private List<ExPlanItemlVo> exPlanItemls;

	public String getDesc() {
        return desc;
    }

	public String getDutyPerson() {
        return dutyPerson;
    }


    public String getEquipmentData() {
        return equipmentData;
    }

    public List<ExPlanItemlVo> getExPlanItemls() {
        return exPlanItemls;
    }

    public String getMaterielData() {
        return materielData;
    }

    public String getPcName() {
		return pcName;
	}

    public String getRecordId() {
        return recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public String getSpaceData() {
        return spaceData;
    }

    public String getStatus() {
        return status;
    }

    public String getSupplierData() {
        return supplierData;
    }

    public Long getTaskBeginTime() {
        return taskBeginTime;
    }

    public Long getTaskEndTime() {
        return taskEndTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskScopeType() {
        return taskScopeType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDutyPerson(String dutyPerson) {
        this.dutyPerson = dutyPerson;
    }

    public void setEquipmentData(String equipmentData) {
        this.equipmentData = equipmentData;
    }

    public void setExPlanItemls(List<ExPlanItemlVo> exPlanItemls) {
        this.exPlanItemls = exPlanItemls;
    }

    public void setMaterielData(String materielData) {
        this.materielData = materielData;
    }

    public void setPcName(String pcName) {
		this.pcName = pcName;
	}

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public void setSpaceData(String spaceData) {
        this.spaceData = spaceData;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSupplierData(String supplierData) {
        this.supplierData = supplierData;
    }

    public void setTaskBeginTime(Long taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public void setTaskEndTime(Long taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskScopeType(String taskScopeType) {
        this.taskScopeType = taskScopeType;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
