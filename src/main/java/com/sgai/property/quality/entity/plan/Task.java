package com.sgai.property.quality.entity.plan;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class Task extends BoEntity<Task> implements Cloneable {
     
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "创建人姓名")
    private String taskCreatorEiEmpName; //创建人姓名
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "是否删除(1:是;0:否)")
    private Long isDelete; //是否删除(1:是;0:否)
    @ApiModelProperty(value = "创建人员工id")
    private String taskCreatorEiId; //创建人员工id
    @ApiModelProperty(value = "审核完成时间")
    private Long taskApprTime; //审核完成时间
    @ApiModelProperty(value = "审核人员工id")
    private String taskApprEiId; //审核人员工id
    @ApiModelProperty(value = "是否审核(0:否;1:是)")
    private Long taskNeedAppr; //是否审核(0:否;1:是)
    @ApiModelProperty(value = "任务说明")
    private String taskDesc; //任务说明
    @ApiModelProperty(value = "创建人头像")
    private String taskCreatorEiEmpImg; //创建人头像
    @ApiModelProperty(value = "任务排序")
    private Long taskSort; //任务排序
    @ApiModelProperty(value = "任务结束时间")
    private Long taskEndTime; //任务结束时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "审核人feed_id")
    private String taskApprFeedId; //审核人feed_id
    @ApiModelProperty(value = "审核人toon_user_id")
    private String taskApprToonUserId; //审核人toon_user_id
    @ApiModelProperty(value = "任务是否完成(1:是;0:否)")
    private Long taskIsComplete; //任务是否完成(1:是;0:否)
    @ApiModelProperty(value = "任务名称")
    private String taskName; //任务名称
    @ApiModelProperty(value = "推送时间")
    private Long pushTime; //推送时间
    @ApiModelProperty(value = "任务类型(1:执行;2:检验)")
    private Long taskType; //任务类型(1:执行;2:检验)
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    @ApiModelProperty(value = "任务开始时间")
    private Long taskBeginTime; //任务开始时间
    @ApiModelProperty(value = "审核状态(0:未开始;1:审核中;2:已打回;3:已通过)")
    private Long taskApprState; //审核状态(0:未开始;1:审核中;2:已打回;3:已通过)
    @ApiModelProperty(value = "审核人名字")
    private String taskApprEiEmpName; //审核人名字
    @ApiModelProperty(value = "任务完成提交时间")
    private Long taskResultTime; //任务完成提交时间
    @ApiModelProperty(value = "组织id")
    private String orgId; //组织id
    @ApiModelProperty(value = "阶段名称")
    private String periodName; //阶段名称
    @ApiModelProperty(value = "阶段id")
    private String periodId; //阶段id

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
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "截止时间")
    private String endTime;

    @ApiModelProperty(value = "开始月份")
    private String beginMonth;
    @ApiModelProperty(value = "截止月份")
    private String endMonth;
    @ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
    private Integer typeFlag;


    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        this.beginMonth = beginMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

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

    @ApiModelProperty(value = "截止日")
    private String endDate;

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

    public String getTaskCreatorEiEmpName(){
        return taskCreatorEiEmpName;  
    }
      
   public void setTaskCreatorEiEmpName(String taskCreatorEiEmpName){  
     this.taskCreatorEiEmpName = taskCreatorEiEmpName;  
    }  
    public String getRecordId(){  
        return recordId;  
    }
      
   public void setRecordId(String recordId){  
     this.recordId = recordId;  
    }  
    public Long getIsDelete(){  
        return isDelete;  
    }
      
   public void setIsDelete(Long isDelete){  
     this.isDelete = isDelete;  
    }  
    public String getTaskCreatorEiId(){  
        return taskCreatorEiId;  
    }
      
   public void setTaskCreatorEiId(String taskCreatorEiId){  
     this.taskCreatorEiId = taskCreatorEiId;  
    }  
    public Long getTaskApprTime(){  
        return taskApprTime;  
    }
      
   public void setTaskApprTime(Long taskApprTime){  
     this.taskApprTime = taskApprTime;  
    }  
    public String getTaskApprEiId(){  
        return taskApprEiId;  
    }
      
   public void setTaskApprEiId(String taskApprEiId){  
     this.taskApprEiId = taskApprEiId;  
    }  
    public Long getTaskNeedAppr(){  
        return taskNeedAppr;  
    }
      
   public void setTaskNeedAppr(Long taskNeedAppr){  
     this.taskNeedAppr = taskNeedAppr;  
    }  
    public String getTaskDesc(){  
        return taskDesc;  
    }
      
   public void setTaskDesc(String taskDesc){  
     this.taskDesc = taskDesc;  
    }  
    public String getTaskCreatorEiEmpImg(){  
        return taskCreatorEiEmpImg;  
    }
      
   public void setTaskCreatorEiEmpImg(String taskCreatorEiEmpImg){  
     this.taskCreatorEiEmpImg = taskCreatorEiEmpImg;  
    }  
    public Long getTaskSort(){  
        return taskSort;  
    }
      
   public void setTaskSort(Long taskSort){  
     this.taskSort = taskSort;  
    }  
    public Long getTaskEndTime(){  
        return taskEndTime;  
    }
      
   public void setTaskEndTime(Long taskEndTime){  
     this.taskEndTime = taskEndTime;  
    }  
    public Long getUpdateTime(){  
        return updateTime;  
    }
      
   public void setUpdateTime(Long updateTime){  
     this.updateTime = updateTime;  
    }  
    public String getTaskApprFeedId(){  
        return taskApprFeedId;  
    }
      
   public void setTaskApprFeedId(String taskApprFeedId){  
     this.taskApprFeedId = taskApprFeedId;  
    }  
    public String getTaskApprToonUserId(){  
        return taskApprToonUserId;  
    }
      
   public void setTaskApprToonUserId(String taskApprToonUserId){  
     this.taskApprToonUserId = taskApprToonUserId;  
    }  
    public Long getTaskIsComplete(){  
        return taskIsComplete;  
    }
      
   public void setTaskIsComplete(Long taskIsComplete){  
     this.taskIsComplete = taskIsComplete;  
    }  
    public String getTaskName(){  
        return taskName;  
    }
      
   public void setTaskName(String taskName){  
     this.taskName = taskName;  
    }  
    public Long getPushTime(){  
        return pushTime;  
    }
      
   public void setPushTime(Long pushTime){  
     this.pushTime = pushTime;  
    }  
    public Long getTaskType(){  
        return taskType;  
    }
      
   public void setTaskType(Long taskType){  
     this.taskType = taskType;  
    }  
    public String getRecordName(){  
        return recordName;  
    }
      
   public void setRecordName(String recordName){  
     this.recordName = recordName;  
    }  
    public Long getTaskBeginTime(){  
        return taskBeginTime;  
    }
      
   public void setTaskBeginTime(Long taskBeginTime){  
     this.taskBeginTime = taskBeginTime;  
    }  
    public Long getTaskApprState(){  
        return taskApprState;  
    }
      
   public void setTaskApprState(Long taskApprState){  
     this.taskApprState = taskApprState;  
    }  
    public String getTaskApprEiEmpName(){  
        return taskApprEiEmpName;  
    }
      
   public void setTaskApprEiEmpName(String taskApprEiEmpName){  
     this.taskApprEiEmpName = taskApprEiEmpName;  
    }  
    public Long getTaskResultTime(){  
        return taskResultTime;  
    }
      
   public void setTaskResultTime(Long taskResultTime){  
     this.taskResultTime = taskResultTime;  
    }  
    public String getOrgId(){  
        return orgId;  
    }
      
   public void setOrgId(String orgId){  
     this.orgId = orgId;  
    }  
    public String getPeriodName(){  
        return periodName;  
    }
      
   public void setPeriodName(String periodName){  
     this.periodName = periodName;  
    }  
    public String getPeriodId(){  
        return periodId;  
    }
      
   public void setPeriodId(String periodId){  
     this.periodId = periodId;  
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Long taskFlag) {
        this.taskFlag = taskFlag;
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
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }
}