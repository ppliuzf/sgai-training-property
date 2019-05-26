package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SurveyQuestionVO implements Serializable,Comparable<SurveyQuestionVO> {

    
      
	    /**  
	    * @Fields field:field:{}(用一句话描述这个变量表示什么)
	    */  
	    
	private static final long serialVersionUID = -1360920217428302370L;
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

    @Override
    public int compareTo(SurveyQuestionVO o) {
        return this.getSqNo().compareTo(o.getSqNo());
    }
}