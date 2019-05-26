package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
    * @ClassName: RuagModelTemplate  
    * @com.sgai.property.commonService.vo;(运行模式定义Entity)
    * @author 王天尧  
    * @date 2018年1月2日  
    * @Company 首自信--智慧城市创新中心
 */
public class RuagModelTemplate extends BoEntity<RuagModelTemplate> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "控制类型编码")
	private String controlCode;		// 控制类型编码
	@ApiModelProperty(value = "策略类型编码")
	private String strategyCode;		// 策略类型编码
	@ApiModelProperty(value = "控制类型")
	private String controlType;		// 控制类型
	@ApiModelProperty(value = "策略类型")
	private String strategyType;		// 策略类型
	@ApiModelProperty(value = "策略优先级")
	private String modelDegree;		// 策略优先级
	@ApiModelProperty(value = "模式名称")
	private String workModeName;		// 模式名称
	@ApiModelProperty(value = "开始日期")
	private String startDate;		// 开始日期
	@ApiModelProperty(value = "结束日期")
	private String endDate;		// 结束日期
	@ApiModelProperty(value = "模式状态")
	private String status;		// 模式状态  未启动--0已启动--1
	@ApiModelProperty(value = "工作日")
	private String workTime;		// 星期一,星期二,星期三,星期四,星期五,星期六,星期日
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	public RuagModelTemplate() {
		super();
	}

	public RuagModelTemplate(String id){
		super(id);
	}

	@Length(min=0, max=60, message="控制类型编码长度必须介于 0 和 60 之间")
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	
	@Length(min=0, max=60, message="策略类型编码长度必须介于 0 和 60 之间")
	public String getStrategyCode() {
		return strategyCode;
	}

	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}
	
	@Length(min=0, max=60, message="控制类型长度必须介于 0 和 60 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	@Length(min=0, max=60, message="策略类型长度必须介于 0 和 60 之间")
	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	
	@Length(min=0, max=60, message="策略优先级长度必须介于 0 和 60 之间")
	public String getModelDegree() {
		return modelDegree;
	}

	public void setModelDegree(String modelDegree) {
		this.modelDegree = modelDegree;
	}
	
	@Length(min=0, max=60, message="模式名称长度必须介于 0 和 60 之间")
	public String getWorkModeName() {
		return workModeName;
	}

	public void setWorkModeName(String workModeName) {
		this.workModeName = workModeName;
	}
	
	@Length(min=0, max=60, message="开始日期长度必须介于 0 和 60 之间")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@Length(min=0, max=60, message="结束日期长度必须介于 0 和 60 之间")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=60, message="模式状态  未启动--0已启动--1长度必须介于 0 和 60 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=128, message="星期一,星期二,星期三,星期四,星期五,星期六,星期日长度必须介于 0 和 128 之间")
	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
}