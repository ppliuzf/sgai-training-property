package com.sgai.property.depot.entity;

import java.util.Date;

import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

public class WarehouseInOut extends BoEntity<WarehouseInOut>{
    @ApiModelProperty(value = "出入库单号")
    private String whInOutNo; //出入库单号
    @ApiModelProperty(value = "出入库类型？0：出库；1:入库")
    private Long inOutType;//出入库类型
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "创建时间")
    private Date createdDt;
    @ApiModelProperty(value = "操作人名称")
    private String inOutEmpName; //操作人名称
    @ApiModelProperty(value = "出库状态?1:未出库;2:部分出库;3:全部出库  入库状态?1:未入库;2:部分入库;3:全部入库")
    private Long whStat; //出库状态?1:未出库;2:部分出库;3:全部出库
	
	private Date dtBegin;
	private Date dtEnd;
	
	public Date getDtBegin() {
		return dtBegin;
	}
	public void setDtBegin(Date dtBegin) {
		this.dtBegin = dtBegin;
	}
	public Date getDtEnd() {
		return dtEnd;
	}
	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}
	public String getWhInOutNo() {
		return whInOutNo;
	}
	public void setWhInOutNo(String whInOutNo) {
		this.whInOutNo = whInOutNo;
	}
	public Long getInOutType() {
		return inOutType;
	}
	public void setInOutType(Long inOutType) {
		this.inOutType = inOutType;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public Long getWhType() {
		return whType;
	}
	public void setWhType(Long whType) {
		this.whType = whType;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public String getInOutEmpName() {
		return inOutEmpName;
	}
	public void setInOutEmpName(String inOutEmpName) {
		this.inOutEmpName = inOutEmpName;
	}
	public Long getWhStat() {
		return whStat;
	}
	public void setWhStat(Long whStat) {
		this.whStat = whStat;
	}
	
}
