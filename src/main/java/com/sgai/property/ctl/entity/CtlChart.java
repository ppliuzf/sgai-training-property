package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 图表Entity
 * @author admin
 * @version 2018-01-04
 */
public class CtlChart extends BoEntity<CtlChart> {
	
	private static final long serialVersionUID = 1L;
	private String chartCode;		// 图标编码
	private String chartName;       // 图表名称
	private String chartLevel;		// 图标分级
	private String chartUrl;		// 图标请求地址或方法
	private String parentChartCode;		// 父图标编码
	private String chartType;		// 结点类型
	private List<CtlChart>  childrenCharts;   //
	private String option ; //图标展示json
	
	private String tick ="0" ; // 0 表示 未勾选   1表示已经勾选
	
	public CtlChart() {
		super();
	}

	public CtlChart(String id){
		super(id);
	}

	@Length(min=0, max=60, message="图标编码长度必须介于 0 和 60 之间")
	public String getChartCode() {
		return chartCode;
	}

	public void setChartCode(String chartCode) {
		this.chartCode = chartCode;
	}
	
	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	@Length(min=0, max=60, message="图标分级长度必须介于 0 和 60 之间")
	public String getChartLevel() {
		return chartLevel;
	}

	public void setChartLevel(String chartLevel) {
		this.chartLevel = chartLevel;
	}
	
	@Length(min=0, max=60, message="图标请求地址或方法长度必须介于 0 和 60 之间")
	public String getChartUrl() {
		return chartUrl;
	}

	public void setChartUrl(String chartUrl) {
		this.chartUrl = chartUrl;
	}
	
	@Length(min=0, max=60, message="父图标编码长度必须介于 0 和 60 之间")
	public String getParentChartCode() {
		return parentChartCode;
	}

	public void setParentChartCode(String parentChartCode) {
		this.parentChartCode = parentChartCode;
	}
	
	@Length(min=0, max=60, message="结点类型长度必须介于 0 和 60 之间")
	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	

	public List<CtlChart> getChildrenCharts() {
		return childrenCharts;
	}

	public void setChildrenCharts(List<CtlChart> childrenCharts) {
		this.childrenCharts = childrenCharts;
	}

	public String getOption() {
		return option;
	}
	
	public void setOption(String option) {
		this.option = option;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}
	
	
	
}