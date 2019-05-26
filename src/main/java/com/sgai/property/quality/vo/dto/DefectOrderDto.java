package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 新建工单dto
 * 
 * @author Administrator
 *
 */
public class DefectOrderDto {

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
	@ApiModelProperty(value = "执行人ID")
	private String doExecutorId; // 执行人ID
	@ApiModelProperty(value = "整改内容")
	private String doContent; // 整改内容
	@ApiModelProperty(value = "截止时间")
	private Long doDeadline; // 截止时间
	@ApiModelProperty(value = "组织ID")
	private Long comId; // 组织ID
	@ApiModelProperty(value = "创建时间")
	private Long doCreateTime; // 创建时间

	@ApiModelProperty(value = "附件地址数组")
	private List<String> urlList; // 附件地址数组

	public String getDoName() {
		return doName;
	}

	public void setDoName(String doName) {
		this.doName = doName;
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

	public String getDoExecutorId() {
		return doExecutorId;
	}

	public void setDoExecutorId(String doExecutorId) {
		this.doExecutorId = doExecutorId;
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

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public Long getDoCreateTime() {
		return doCreateTime;
	}

	public void setDoCreateTime(Long doCreateTime) {
		this.doCreateTime = doCreateTime;
	}

}