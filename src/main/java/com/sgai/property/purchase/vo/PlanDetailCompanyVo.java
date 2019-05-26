package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

public class PlanDetailCompanyVo {
	@ApiModelProperty(value = "id")
    private String id; //任务Id
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "供应商名称")
    private String supplyComName; //供应商名称
    @ApiModelProperty(value = "物料类型ID")
    private String matTypeId; //物料类型ID
    @ApiModelProperty(value = "物料id")
    private String detailId; //物料id
    @ApiModelProperty(value = "任务Id")
    private String taskId; //任务Id
    @ApiModelProperty(value = "需求数量")
    private Long applyCount; //需求数量
    @ApiModelProperty(value = "供应商id")
    private String supplyComId; //供应商id
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getMatSpec() {
		return matSpec;
	}
	public void setMatSpec(String matSpec) {
		this.matSpec = matSpec;
	}
	public Long getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Long buyCount) {
		this.buyCount = buyCount;
	}
	public String getSupplyComName() {
		return supplyComName;
	}
	public void setSupplyComName(String supplyComName) {
		this.supplyComName = supplyComName;
	}
	public String getMatTypeId() {
		return matTypeId;
	}
	public void setMatTypeId(String matTypeId) {
		this.matTypeId = matTypeId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Long getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(Long applyCount) {
		this.applyCount = applyCount;
	}
	public String getSupplyComId() {
		return supplyComId;
	}
	public void setSupplyComId(String supplyComId) {
		this.supplyComId = supplyComId;
	}
	public String getMatTypeCode() {
		return matTypeCode;
	}
	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
    
    

}
