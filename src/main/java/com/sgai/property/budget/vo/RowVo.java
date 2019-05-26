package com.sgai.property.budget.vo;
import io.swagger.annotations.ApiModelProperty;
public class RowVo {

	@ApiModelProperty(value = "计划id")
	private String recordId; //计划id	
	@ApiModelProperty(value = "记录id（预算表格中每一行数据的id）")
	private String rsiId;
	@ApiModelProperty(value = "科目编号")
	private String subCode; //科目编号
	@ApiModelProperty(value = "科目名称")
	private String subName; //科目名称
	@ApiModelProperty(value = "科目长名称")
	private String subLongName; //科目长名称
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
	@ApiModelProperty(value = "预算金额")
	private String budget;
	@ApiModelProperty(value = "预算结余")
	private String surplus;
	@ApiModelProperty(value = "支出（将这行记录每个月的支出用逗号隔开拼成一个字符串）")
	private String expend;
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRsiId() {
		return rsiId;
	}
	public void setRsiId(String rsiId) {
		this.rsiId = rsiId;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getSubLongName() {
		return subLongName;
	}
	public void setSubLongName(String subLongName) {
		this.subLongName = subLongName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getSurplus() {
		return surplus;
	}
	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	public String getExpend() {
		return expend;
	}
	public void setExpend(String expend) {
		this.expend = expend;
	}
    
}