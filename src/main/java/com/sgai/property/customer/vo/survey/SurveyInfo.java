package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 问卷信息
 *
 * @author Hou
 * @create 2018-03-01 14:35
 */
public class SurveyInfo implements Serializable {

    @ApiModelProperty(value = "问卷主要信息")
    private SurveyMainVo surveyMainVo;

    @ApiModelProperty(value = "问卷所有问题的list")
    private List<SurveyQuestionDetailVo> surveyQuestionDetailVoList;

    public SurveyMainVo getSurveyMainVo() {
        return surveyMainVo;
    }

    public SurveyInfo setSurveyMainVo(SurveyMainVo surveyMainVo) {
        this.surveyMainVo = surveyMainVo;
        return this;
    }

    public List<SurveyQuestionDetailVo> getSurveyQuestionDetailVoList() {
        return surveyQuestionDetailVoList;
    }

    public SurveyInfo setSurveyQuestionDetailVoList(List<SurveyQuestionDetailVo> surveyQuestionDetailVoList) {
        this.surveyQuestionDetailVoList = surveyQuestionDetailVoList;
        return this;
    }
}
