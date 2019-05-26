package com.sgai.property.commonService.quartz.vo;

public class ScheduleJob {
	/** 任务id */
    private String jobId;
 
    /** 任务名称 */
    private String name;
 
    /** 任务分组 */
    private String group;
 
    /** 任务状态 0禁用 1启用 2删除*/
    private String status;
 
    /** 任务运行时间表达式 */
    private String expression;
 
    /** 任务描述 */
    private String desc;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	} 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
    
}
