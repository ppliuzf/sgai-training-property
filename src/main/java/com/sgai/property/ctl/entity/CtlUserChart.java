package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 用户图表关系表Entity
 * @author admin
 * @version 2018-01-04
 */
public class CtlUserChart extends BoEntity<CtlUserChart> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户编码
	private String chartCode;		// 图标编码
	private String homeShow;        //是否展示在首页
	
	public CtlUserChart() {
		super();
	}

	public CtlUserChart(String id){
		super(id);
	}

	@Length(min=0, max=60, message="用户编码长度必须介于 0 和 60 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=60, message="图标编码长度必须介于 0 和 60 之间")
	public String getChartCode() {
		return chartCode;
	}

	public void setChartCode(String chartCode) {
		this.chartCode = chartCode;
	}

	public String getHomeShow() {
		return homeShow;
	}

	public void setHomeShow(String homeShow) {
		this.homeShow = homeShow;
	}
	
	
}