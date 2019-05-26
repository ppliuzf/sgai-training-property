package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 任务信息
 * @author wuzhihui
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskInfoVo extends IdType{
	//身份(0：普通人,1:发起人,2:审核人
	public static final Integer ID_TYPE_NORMALER=0;
	public static final Integer ID_TYPE_SATRTER=1;
	public static final Integer ID_TYPE_CHECKER=2;
	//当前用户审核状态(0：未审核,1:已审核)前置条件为是审核人身份")
	public static final Integer ID_NO_CHECK=0;
	public static final Integer ID_CHECKED=1;
	@ApiModelProperty(value = "专业范畴id")
	private String pcId;
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName;
	@ApiModelProperty(value = "任务id")
	private String taskId;
	@ApiModelProperty(value = "任务名称")
	private String taskName;
	@ApiModelProperty(value = "检验对象id")
	private String objId;
	@ApiModelProperty(value = "检验对象名称")
	private String objName;
	@ApiModelProperty(value = "任务图片")
	private String icon;
	@ApiModelProperty(value = "时间")
	private Long time;
	@ApiModelProperty(value = "开始时间")
	private Long startTime;
	@ApiModelProperty(value = "截止时间")
	private Long endTime;
	@ApiModelProperty(value = "状态")
	private Integer status;
	@ApiModelProperty(value = "状态描述")
	private String statusDesc;
	@ApiModelProperty(value = "总检查项数")
	private Integer total;
	@ApiModelProperty(value = "已检查项数")
	private Integer checkedCount;
	
	@ApiModelProperty(value = "任务中的组")
	private List<TaskGroupVo> list;
	@ApiModelProperty(value = "审核信息记录")
	private List<ApprovalRecordVo> recordList;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public List<TaskGroupVo> getList() {
		return list;
	}
	public void setList(List<TaskGroupVo> list) {
		this.list = list;
	}
	public List<ApprovalRecordVo> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<ApprovalRecordVo> recordList) {
		this.recordList = recordList;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(Integer checkedCount) {
		this.checkedCount = checkedCount;
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
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
}
