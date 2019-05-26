package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 审核页面任务信息统计
 * @author wuzhihui
 *
 */
public class CheckStatisticsVo extends IdType {
	@ApiModelProperty(value = "审核发起人")
	private String checkUserName;
	@ApiModelProperty(value = "部门")
	private String deptName;
	@ApiModelProperty(value = "头像")
	private String icon;
	@ApiModelProperty(value = "任务名称")
	private String taskName;
	@ApiModelProperty(value = "任务状态描述")
	private String statusDesc;
	@ApiModelProperty(value = "开始时间")
	private Long startTime;
	@ApiModelProperty(value = "结束时间")
	private Long endTime;
	@ApiModelProperty(value = "检验概况")
	private List<Map<String,Object>> inspectionProfileList;
	@ApiModelProperty(value = "未检验项")
	private List<Map<String,Object>> noCheckList;
	@ApiModelProperty(value = "未发起缺陷整改")
	private List<String> notRectificationList;
	@ApiModelProperty(value = "发起缺陷整改")
	private List<String> rectificationList;
	@ApiModelProperty(value = "审核信息记录")
	private List<ApprovalRecordVo> recordList;
	
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public List<Map<String, Object>> getInspectionProfileList() {
		return inspectionProfileList;
	}
	public void setInspectionProfileList(List<Map<String, Object>> inspectionProfileList) {
		this.inspectionProfileList = inspectionProfileList;
	}
	public List<Map<String, Object>> getNoCheckList() {
		return noCheckList;
	}
	public void setNoCheckList(List<Map<String, Object>> noCheckList) {
		this.noCheckList = noCheckList;
	}
	public List<String> getNotRectificationList() {
		return notRectificationList;
	}
	public void setNotRectificationList(List<String> notRectificationList) {
		this.notRectificationList = notRectificationList;
	}
	public List<String> getRectificationList() {
		return rectificationList;
	}
	public void setRectificationList(List<String> rectificationList) {
		this.rectificationList = rectificationList;
	}
	public List<ApprovalRecordVo> getRecords() {
		return recordList;
	}
	public void setRecords(List<ApprovalRecordVo> recordList) {
		this.recordList = recordList;
	}
	
}
