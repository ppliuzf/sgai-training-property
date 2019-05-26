package com.sgai.property.quality.entity.plan;

import java.util.List;

import com.sgai.property.quality.vo.plan.JhDayVo;
import com.sgai.property.quality.vo.plan.JhMonthVo;
import com.sgai.property.quality.vo.plan.JhWeekVo;
import io.swagger.annotations.ApiModelProperty;

public class TaskTPStat {
	@ApiModelProperty(value = "状态，未开始，进行中，已完成，已逾期")
    private String stat;
	@ApiModelProperty(value = "关联模板名称")
	private String taskName;
	@ApiModelProperty(value = "关联模板ID")
	private String tp_id;
	@ApiModelProperty(value = "计划ID")
	private String record_id; 
	@ApiModelProperty(value = "执行人信息")
	private List<String> List_eiEmpName;
	@ApiModelProperty(value = "执行人Id")
	private List<String> List_eiEmpId;

	public List<String> getList_eiEmpId() {
		return List_eiEmpId;
	}
	public void setList_eiEmpId(List<String> list_eiEmpId) {
		List_eiEmpId = list_eiEmpId;
	}

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


	@ApiModelProperty(value = "任务开始时间")
	private Long taskBeginTime; //任务开始时间
	@ApiModelProperty(value = "任务结束时间")
	private Long taskEndTime; //任务结束时间




	public TaskTPStat() {
	}
	public TaskTPStat(String stat, String taskName) {
		super();
		this.stat = stat;
		this.taskName = taskName;
	}
	public List<String> getList_eiEmpName() {
		return List_eiEmpName;
	}
	public String getRecord_id() {
		return record_id;
	}
	public String getStat() {
		return stat;
	}
	public String getTaskName() {
		return taskName;
	}
	public String getTp_id() {
		return tp_id;
	}
	public void setList_eiEmpName(List<String> list_eiEmpName) {
		List_eiEmpName = list_eiEmpName;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

    public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

	public Long getTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(Long taskFlag) {
		this.taskFlag = taskFlag;
	}

	public Long getOptionFlag() {
		return optionFlag;
	}

	public void setOptionFlag(Long optionFlag) {
		this.optionFlag = optionFlag;
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

	public String getTaskDay() {
		return taskDay;
	}

	public void setTaskDay(String taskDay) {
		this.taskDay = taskDay;
	}
}
