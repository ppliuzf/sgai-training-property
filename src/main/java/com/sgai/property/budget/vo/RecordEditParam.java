package com.sgai.property.budget.vo;
import io.swagger.annotations.ApiModelProperty;
public class RecordEditParam {
	@ApiModelProperty(value = "计划id")
	private String id;
	@ApiModelProperty(value = "计划名称")
	private String recordName; //计划名称
	@ApiModelProperty(value = "计划部门id")
    private String deptId; //计划部门id
	@ApiModelProperty(value = "计划部门名称")
    private String deptName; //计划部门名称
	@ApiModelProperty(value = "预算类型id")
    private String typeId; //预算类型id
    @ApiModelProperty(value = "预算类型名称")
    private String typeName; //预算类型名称
    @ApiModelProperty(value = "预算年份")
    private String year; //预算年份
    
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    
	
}