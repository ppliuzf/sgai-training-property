package com.sgai.property.quality.vo.plan;

import com.sgai.property.quality.entity.plan.TaskPerson;
import com.sgai.property.quality.vo.TaskImgVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PlanTaskVo {

	@ApiModelProperty(value = "任务id")
	private String id;
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

    @ApiModelProperty(value = "审核意见")
    private String taskApprTarget; //审核意见
    @ApiModelProperty(value = "任务计划详情(分类详情，json对象)")
    private String taskPlanDetail; //任务计划详情(分类详情，json对象)
    @ApiModelProperty(value = "审核情况详情(json)")
    private String taskApprDetail; //审核情况详情(json)
    @ApiModelProperty(value = "任务完成详情（json）")
    private String taskResult; //任务完成详情（json）
    @ApiModelProperty(value = "关联对象选中的值(JSON)")
    private String associatedObject; //关联对象选中的值(JSON)
    
	@ApiModelProperty(value = "图片")
	private List<TaskImgVo> imgList;
	@ApiModelProperty(value = "当前是否是审核人")
	private Long isApprover;
	@ApiModelProperty(value = "责任人列表")
	private List<TaskPerson> dutyPersonList;
	@ApiModelProperty(value = "审核人列表")
	private List<TaskPerson> approverList;
	@ApiModelProperty(value = "显示任务状态")
	private String viewState;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getTaskCreatorEiEmpName() {
		return taskCreatorEiEmpName;
	}

	public void setTaskCreatorEiEmpName(String taskCreatorEiEmpName) {
		this.taskCreatorEiEmpName = taskCreatorEiEmpName;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public String getTaskCreatorEiId() {
		return taskCreatorEiId;
	}

	public void setTaskCreatorEiId(String taskCreatorEiId) {
		this.taskCreatorEiId = taskCreatorEiId;
	}

	public Long getTaskApprTime() {
		return taskApprTime;
	}

	public void setTaskApprTime(Long taskApprTime) {
		this.taskApprTime = taskApprTime;
	}

	public String getTaskApprEiId() {
		return taskApprEiId;
	}

	public void setTaskApprEiId(String taskApprEiId) {
		this.taskApprEiId = taskApprEiId;
	}

	public Long getTaskNeedAppr() {
		return taskNeedAppr;
	}

	public void setTaskNeedAppr(Long taskNeedAppr) {
		this.taskNeedAppr = taskNeedAppr;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Long getTaskSort() {
		return taskSort;
	}

	public void setTaskSort(Long taskSort) {
		this.taskSort = taskSort;
	}

	public Long getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Long taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskApprFeedId() {
		return taskApprFeedId;
	}

	public void setTaskApprFeedId(String taskApprFeedId) {
		this.taskApprFeedId = taskApprFeedId;
	}

	public String getTaskApprToonUserId() {
		return taskApprToonUserId;
	}

	public void setTaskApprToonUserId(String taskApprToonUserId) {
		this.taskApprToonUserId = taskApprToonUserId;
	}

	public Long getTaskIsComplete() {
		return taskIsComplete;
	}

	public void setTaskIsComplete(Long taskIsComplete) {
		this.taskIsComplete = taskIsComplete;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getPushTime() {
		return pushTime;
	}

	public void setPushTime(Long pushTime) {
		this.pushTime = pushTime;
	}

	public Long getTaskType() {
		return taskType;
	}

	public void setTaskType(Long taskType) {
		this.taskType = taskType;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public Long getTaskBeginTime() {
		return taskBeginTime;
	}

	public void setTaskBeginTime(Long taskBeginTime) {
		this.taskBeginTime = taskBeginTime;
	}

	public Long getTaskApprState() {
		return taskApprState;
	}

	public void setTaskApprState(Long taskApprState) {
		this.taskApprState = taskApprState;
	}

	public String getTaskApprEiEmpName() {
		return taskApprEiEmpName;
	}

	public void setTaskApprEiEmpName(String taskApprEiEmpName) {
		this.taskApprEiEmpName = taskApprEiEmpName;
	}

	public Long getTaskResultTime() {
		return taskResultTime;
	}

	public void setTaskResultTime(Long taskResultTime) {
		this.taskResultTime = taskResultTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TaskImgVo> getImgList() {
		return imgList;
	}

	public void setImgList(List<TaskImgVo> imgList) {
		this.imgList = imgList;
	}

	public String getTaskCreatorEiEmpImg() {
		return taskCreatorEiEmpImg;
	}

	public void setTaskCreatorEiEmpImg(String taskCreatorEiEmpImg) {
		this.taskCreatorEiEmpImg = taskCreatorEiEmpImg;
	}

	public String getTaskApprTarget() {
		return taskApprTarget;
	}

	public void setTaskApprTarget(String taskApprTarget) {
		this.taskApprTarget = taskApprTarget;
	}

	public String getTaskPlanDetail() {
		return taskPlanDetail;
	}

	public void setTaskPlanDetail(String taskPlanDetail) {
		this.taskPlanDetail = taskPlanDetail;
	}

	public String getTaskApprDetail() {
		return taskApprDetail;
	}

	public void setTaskApprDetail(String taskApprDetail) {
		this.taskApprDetail = taskApprDetail;
	}

	public String getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	public String getAssociatedObject() {
		return associatedObject;
	}

	public void setAssociatedObject(String associatedObject) {
		this.associatedObject = associatedObject;
	}

	public List<TaskPerson> getDutyPersonList() {
		return dutyPersonList;
	}

	public void setDutyPersonList(List<TaskPerson> dutyPersonList) {
		this.dutyPersonList = dutyPersonList;
	}

	public List<TaskPerson> getApproverList() {
		return approverList;
	}

	public void setApproverList(List<TaskPerson> approverList) {
		this.approverList = approverList;
	}

	public Long getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Long isApprover) {
		this.isApprover = isApprover;
	}

	public String getViewState() {
		return viewState;
	}

	public void setViewState(String viewState) {
		this.viewState = viewState;
	}


}