package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷问题Vo
 *
 * @author Hou
 * @create 2018-03-01 14:33
 */
public class SurveyQuestionDetailVo implements Serializable,Comparable<SurveyQuestionDetailVo> {

    @ApiModelProperty(value = "当前问题Id")
    private String sqId;
    @ApiModelProperty(value = "问题详细描述")
    private String sqDetail;
    @ApiModelProperty(value = "问题是否必填(0,必填,1非)")
    private Long sqIsRequired;
    @ApiModelProperty(value = "问题标题")
    private String sqTopic;
    @ApiModelProperty(value = "问题类型(0,单选,1,多选,2,文本)")
    private Long sqType;
    @ApiModelProperty(value = "选项内容")
    private String soContent;
    @ApiModelProperty(value = "问题序号,数字,用来排序")
    private Long sqNo;
    @ApiModelProperty(value = "所填答案内容List")
    private List<String> saContentList;

    public List<String> getSaContentList() {
        return saContentList;
    }

    public SurveyQuestionDetailVo setSaContentList(List<String> saContentList) {
        this.saContentList = saContentList;
        return this;
    }

    public String getSqId() {
        return sqId;
    }

    public SurveyQuestionDetailVo setSqId(String sqId) {
        this.sqId = sqId;
        return this;
    }

    public String getSqDetail() {
        return sqDetail;
    }

    public SurveyQuestionDetailVo setSqDetail(String sqDetail) {
        this.sqDetail = sqDetail;
        return this;
    }

    public Long getSqIsRequired() {
        return sqIsRequired;
    }

    public SurveyQuestionDetailVo setSqIsRequired(Long sqIsRequired) {
        this.sqIsRequired = sqIsRequired;
        return this;
    }

    public String getSqTopic() {
        return sqTopic;
    }

    public SurveyQuestionDetailVo setSqTopic(String sqTopic) {
        this.sqTopic = sqTopic;
        return this;
    }

    public Long getSqType() {
        return sqType;
    }

    public SurveyQuestionDetailVo setSqType(Long sqType) {
        this.sqType = sqType;
        return this;
    }

    public String getSoContent() {
        return soContent;
    }

    public SurveyQuestionDetailVo setSoContent(String soContent) {
        this.soContent = soContent;
        return this;
    }

    public Long getSqNo() {
        return sqNo;
    }

    public SurveyQuestionDetailVo setSqNo(Long sqNo) {
        this.sqNo = sqNo;
        return this;
    }

    @Override
    public int compareTo(SurveyQuestionDetailVo o) {
        return this.getSqNo().compareTo(o.getSqNo());
    }
}
