package com.sgai.property.quality.entity;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtDefectOrder  extends BoEntity<QtDefectOrder> {
	@ApiModelProperty(value = "工单名称")
	private String doName; // 工单名称
	@ApiModelProperty(value = "专业范畴ID")
	private String categoryId; // 专业范畴ID
	@ApiModelProperty(value = "任务ID")
	private String taskId; // 任务ID
	@ApiModelProperty(value = "检查项ID")
	private String itemId; // 检查项ID
	@ApiModelProperty(value = "检查对象ID")
	private String objectId; // 检查对象ID
	@ApiModelProperty(value = "检查对象名称")
	private String objectName; // 检查对象名称
	@ApiModelProperty(value = "工单当前状态 0新发起 1处理中 2已反馈3已完成 4已关闭5已撤消，工单反馈之后自动变成已完成，不需要确认完成操作")
	private Integer doStatus; // 工单当前状态 0新发起 1处理中 2已反馈3已完成
								// 4已关闭5已撤消，工单反馈之后自动变成已完成，不需要确认完成操作
	@ApiModelProperty(value = "创建人ID")
	private String doCreateId; // 创建人ID
	@ApiModelProperty(value = "创建人姓名")
	private String doCreateName; // 创建人姓名
	@ApiModelProperty(value = "创建人头像")
	private String doCreateIcon; // 创建人头像
	@ApiModelProperty(value = "创建人部门")
	private String doCreateDept; // 创建人部门
	@ApiModelProperty(value = "创建人职位")
	private String doCreatePosition; // 创建人职位
	@ApiModelProperty(value = "创建时间")
	private Long doCreateTime; // 创建时间
	@ApiModelProperty(value = "当前处理人ID")
	private String doUpdateId; // 当前处理人ID
	@ApiModelProperty(value = "当前处理人姓名")
	private String doUpdateName; // 当前处理人姓名
	@ApiModelProperty(value = "当前处理人头像")
	private String doUpdateIcon; // 当前处理人头像
	@ApiModelProperty(value = "当前处理人部门")
	private String doUpdateDept; // 当前处理人部门
	@ApiModelProperty(value = "当前处理人职位")
	private String doUpdatePosition; // 当前处理人职位
	@ApiModelProperty(value = "处理时间")
	private Long doUpdateTime; // 处理时间
	@ApiModelProperty(value = "执行人ID")
	private String doExecutorId; // 执行人ID
	@ApiModelProperty(value = "执行人姓名")
	private String doExecutorName; // 执行人姓名
	@ApiModelProperty(value = "整改内容")
	private String doContent; // 整改内容
	@ApiModelProperty(value = "截止时间")
	private Long doDeadline; // 截止时间
	@ApiModelProperty(value = "工单列表显示的图片，如果工单有图片就取第一张，没有就为空，界面上给个默认图片")
	private String doIcon; // 工单列表显示的图片，如果工单有图片就取第一张，没有就为空，界面上给个默认图片
	@ApiModelProperty(value = "组织ID")
	private Long comId; // 组织ID

	public String getDoName() {
		return doName;
	}

	public void setDoName(String doName) {
		this.doName = doName;
	}

	public String getDoCreateIcon() {
		return doCreateIcon;
	}

	public void setDoCreateIcon(String doCreateIcon) {
		this.doCreateIcon = doCreateIcon;
	}

	public String getDoCreateDept() {
		return doCreateDept;
	}

	public void setDoCreateDept(String doCreateDept) {
		this.doCreateDept = doCreateDept;
	}

	public String getDoCreatePosition() {
		return doCreatePosition;
	}

	public void setDoCreatePosition(String doCreatePosition) {
		this.doCreatePosition = doCreatePosition;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Integer getDoStatus() {
		return doStatus;
	}

	public void setDoStatus(Integer doStatus) {
		this.doStatus = doStatus;
	}

	public String getDoCreateId() {
		return doCreateId;
	}

	public void setDoCreateId(String doCreateId) {
		this.doCreateId = doCreateId;
	}

	public String getDoCreateName() {
		return doCreateName;
	}

	public void setDoCreateName(String doCreateName) {
		this.doCreateName = doCreateName;
	}

	public Long getDoCreateTime() {
		return doCreateTime;
	}

	public void setDoCreateTime(Long doCreateTime) {
		this.doCreateTime = doCreateTime;
	}

	public String getDoUpdateId() {
		return doUpdateId;
	}

	public void setDoUpdateId(String doUpdateId) {
		this.doUpdateId = doUpdateId;
	}

	public String getDoUpdateName() {
		return doUpdateName;
	}

	public void setDoUpdateName(String doUpdateName) {
		this.doUpdateName = doUpdateName;
	}

	public Long getDoUpdateTime() {
		return doUpdateTime;
	}

	public void setDoUpdateTime(Long doUpdateTime) {
		this.doUpdateTime = doUpdateTime;
	}

	public String getDoExecutorId() {
		return doExecutorId;
	}

	public void setDoExecutorId(String doExecutorId) {
		this.doExecutorId = doExecutorId;
	}

	public String getDoExecutorName() {
		return doExecutorName;
	}

	public void setDoExecutorName(String doExecutorName) {
		this.doExecutorName = doExecutorName;
	}

	public String getDoContent() {
		return doContent;
	}

	public void setDoContent(String doContent) {
		this.doContent = doContent;
	}

	public Long getDoDeadline() {
		return doDeadline;
	}

	public void setDoDeadline(Long doDeadline) {
		this.doDeadline = doDeadline;
	}

	public String getDoIcon() {
		return doIcon;
	}

	public void setDoIcon(String doIcon) {
		this.doIcon = doIcon;
	}

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public String getDoUpdateIcon() {
		return doUpdateIcon;
	}

	public void setDoUpdateIcon(String doUpdateIcon) {
		this.doUpdateIcon = doUpdateIcon;
	}

	public String getDoUpdateDept() {
		return doUpdateDept;
	}

	public void setDoUpdateDept(String doUpdateDept) {
		this.doUpdateDept = doUpdateDept;
	}

	public String getDoUpdatePosition() {
		return doUpdatePosition;
	}

	public void setDoUpdatePosition(String doUpdatePosition) {
		this.doUpdatePosition = doUpdatePosition;
	}
}