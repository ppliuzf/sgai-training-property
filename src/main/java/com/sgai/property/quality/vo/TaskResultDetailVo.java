package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 成果详情
 * @author wuzhihui
 *
 */
public class TaskResultDetailVo {
	@ApiModelProperty(value = "任务id")
	private String id;
	@ApiModelProperty(value = "任务名称")
	private String name;
	@ApiModelProperty(value = "状态")
	private String status;
	@ApiModelProperty(value = "检测对象")
	private String objName;
	@ApiModelProperty(value = "执行时间")
	private Long checkTime;
	@ApiModelProperty(value = "成果描述")
	private String resultDesc;
	@ApiModelProperty(value = "责任岗位(任务创建人)")
	private String createName;
	@ApiModelProperty(value = "检查项数组")
	private List<ResultItem> list;
	public static class ResultItem{
		@ApiModelProperty(value = "检查项id")
		private String id;
		@ApiModelProperty(value = "任务名称")
		private String name;
		@ApiModelProperty(value = "组名称")
		private String groupName;
		@ApiModelProperty(value = "答案类型")
		private String answerType;
		@ApiModelProperty(value = "检验人")
		private String checkName;
		@ApiModelProperty(value = "检查时间")
		private Long checkTime;
		@ApiModelProperty(value = "检验状态")
		private String status;
		@ApiModelProperty(value = "检验结果")
		private String result;
		@ApiModelProperty(value = "备注")
		private String remark;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getAnswerType() {
			return answerType;
		}
		public void setAnswerType(String answerType) {
			this.answerType = answerType;
		}
		public String getCheckName() {
			return checkName;
		}
		public void setCheckName(String checkName) {
			this.checkName = checkName;
		}
		public Long getCheckTime() {
			return checkTime;
		}
		public void setCheckTime(Long checkTime) {
			this.checkTime = checkTime;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public List<ResultItem> getList() {
		return list;
	}
	public void setList(List<ResultItem> list) {
		this.list = list;
	}
	
}
