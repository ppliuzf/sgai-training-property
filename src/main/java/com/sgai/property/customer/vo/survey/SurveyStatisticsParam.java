package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 问卷详情
 *
 * @author Hou
 * @create 2018-04-13 14:29
 */
public class SurveyStatisticsParam implements Serializable {

    @ApiModelProperty(value = "关联的问卷表ID")
    private String smId;
    @ApiModelProperty(value = "关联的问题表ID")
    private String sqId;
    @ApiModelProperty(value = "问题选项")
    private String saContent;


    public String getSmId() {
        return smId;
    }

    public SurveyStatisticsParam setSmId(String smId) {
        this.smId = smId;
        return this;
    }

    public String getSqId() {
        return sqId;
    }

    public SurveyStatisticsParam setSqId(String sqId) {
        this.sqId = sqId;
        return this;
    }

    public String getSaContent() {
        return saContent;
    }

    public SurveyStatisticsParam setSaContent(String saContent) {
        this.saContent = saContent;
        return this;
    }
}
