package com.sgai.property.budget.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

import com.sgai.property.budget.entity.ExpensesItem;

public class SubjectItemsRelationVo{

	    @ApiModelProperty(value = "科目id")
	    private String subId; //科目id
	    @ApiModelProperty(value = "科目名称")
	    private String subName; //科目名称
	    @ApiModelProperty(value = "科目编号")
	    private String subCode; //科目编号
		
	    @ApiModelProperty(value = "费项集合")
	    List<ExpensesItemVo> itemList;

		public String getSubId() {
			return subId;
		}

		public void setSubId(String subId) {
			this.subId = subId;
		}

		public String getSubName() {
			return subName;
		}

		public void setSubName(String subName) {
			this.subName = subName;
		}

		public String getSubCode() {
			return subCode;
		}

		public void setSubCode(String subCode) {
			this.subCode = subCode;
		}

		public List<ExpensesItemVo> getItemList() {
			return itemList;
		}

		public void setItemList(List<ExpensesItemVo> itemList) {
			this.itemList = itemList;
		}
}