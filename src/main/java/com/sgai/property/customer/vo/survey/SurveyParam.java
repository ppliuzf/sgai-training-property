package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷创建时入参
 *
 * @author Hou
 * @create 2018-03-01 15:39
 */
public class SurveyParam implements Serializable {

    @ApiModelProperty(value = "问卷主信息")
    private SurveyMainParam surveyMainParam;
    @ApiModelProperty(value = "问题list")
    private List<SurveyQuestionParam> surveyQuestionParamList;

    public SurveyMainParam getSurveyMainParam() {
        return surveyMainParam;
    }

    public SurveyParam setSurveyMainParam(SurveyMainParam surveyMainParam) {
        this.surveyMainParam = surveyMainParam;
        return this;
    }

    public List<SurveyQuestionParam> getSurveyQuestionParamList() {
        return surveyQuestionParamList;
    }

    public SurveyParam setSurveyQuestionParamList(List<SurveyQuestionParam> surveyQuestionParamList) {
        this.surveyQuestionParamList = surveyQuestionParamList;
        return this;
    }
}
