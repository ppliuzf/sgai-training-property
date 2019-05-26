package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 问卷问题入参
 *
 * @author Hou
 * @create 2018-03-01 15:37
 */
public class SurveyQuestionParam implements Serializable {

    @ApiModelProperty(value = "问题标题")
    private String sqTopic;
    @ApiModelProperty(value = "问题类型(0,单选,1,多选,2,文本)")
    private Long sqType;
    @ApiModelProperty(value = "选项内容")
    private String soContent;
    @ApiModelProperty(value = "问题序号,数字,用来排序")
    private Long sqNo;


    public String getSqTopic() {
        return sqTopic;
    }

    public SurveyQuestionParam setSqTopic(String sqTopic) {
        this.sqTopic = sqTopic;
        return this;
    }

    public Long getSqType() {
        return sqType;
    }

    public SurveyQuestionParam setSqType(Long sqType) {
        this.sqType = sqType;
        return this;
    }

    public String getSoContent() {
        return soContent;
    }

    public SurveyQuestionParam setSoContent(String soContent) {
        this.soContent = soContent;
        return this;
    }

    public Long getSqNo() {
        return sqNo;
    }

    public SurveyQuestionParam setSqNo(Long sqNo) {
        this.sqNo = sqNo;
        return this;
    }
}
