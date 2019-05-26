package com.sgai.property.budget.vo;

import java.util.List;

import com.sgai.property.budget.entity.ExpensesItem;

import io.swagger.annotations.ApiModelProperty;

/**
 *@author 严浩淼
 *@date 2018年1月18日--下午2:02:59
 */
public class RelationDataParam {
	
    @ApiModelProperty(value = "模板id")
    private String templateId; //模板id
    @ApiModelProperty(value = "科目费项关联数据")
    List<SubjectItemsRelationVo> subjectItems;
    
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public List<SubjectItemsRelationVo> getSubjectItems() {
		return subjectItems;
	}
	public void setSubjectItems(List<SubjectItemsRelationVo> subjectItems) {
		this.subjectItems = subjectItems;
	}
    
}
