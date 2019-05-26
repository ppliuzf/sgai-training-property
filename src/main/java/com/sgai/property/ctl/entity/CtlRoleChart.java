package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 角色和图表关系Entity
 * @author admin
 * @version 2018-01-04
 */
public class CtlRoleChart extends BoEntity<CtlRoleChart> {
	
	private static final long serialVersionUID = 1L;
	private String chartCode;		// 图标编码
	private String roleCode;		// role_code
	public CtlRoleChart() {
		super();
	}

	public CtlRoleChart(String id){
		super(id);
	}
	@Length(min=0, max=60, message="图标编码长度必须介于 0 和 60 之间")
	public String getChartCode() {
		return chartCode;
	}

	public void setChartCode(String chartCode) {
		this.chartCode = chartCode;
	}
	
	@Length(min=0, max=60, message="role_code长度必须介于 0 和 60 之间")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}