package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class SurveyQuestion extends BoEntity<SurveyQuestion> {

    @ApiModelProperty(value = "问题详细描述")
    private String sqDetail;
    @ApiModelProperty(value = "问题是否必填(0,必填,1非)")
    private Long sqIsRequired;
    @ApiModelProperty(value = "问题标题")
    private String sqTopic;
    @ApiModelProperty(value = "关联的问卷表ID")
    private String smId;
    @ApiModelProperty(value = "问题类型(0,单选,1,多选,2,文本)")
    private Long sqType;
    @ApiModelProperty(value = "选项内容")
    private String soContent;
    @ApiModelProperty(value = "问题序号,数字,用来排序")
    private Long sqNo;
    @ApiModelProperty(value = "是否删除(0,否:1.是)")
    private Long isDelete;

    public String getSqDetail() {
        return sqDetail;
    }

    public void setSqDetail(String sqDetail) {
        this.sqDetail = sqDetail;
    }

    public Long getSqIsRequired() {
        return sqIsRequired;
    }

    public void setSqIsRequired(Long sqIsRequired) {
        this.sqIsRequired = sqIsRequired;
    }

    public String getSqTopic() {
        return sqTopic;
    }

    public void setSqTopic(String sqTopic) {
        this.sqTopic = sqTopic;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

    public Long getSqType() {
        return sqType;
    }

    public void setSqType(Long sqType) {
        this.sqType = sqType;
    }

    public String getSoContent() {
        return soContent;
    }

    public void setSoContent(String soContent) {
        this.soContent = soContent;
    }

    public Long getSqNo() {
        return sqNo;
    }

    public void setSqNo(Long sqNo) {
        this.sqNo = sqNo;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }
}