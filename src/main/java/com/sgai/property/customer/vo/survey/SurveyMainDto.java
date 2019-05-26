package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 问卷dto
 *
 * @author Hou
 * @create 2018-03-01 14:40
 */
public class SurveyMainDto implements Serializable {

    @ApiModelProperty(value = "问卷名称")
    private String smName;

    @ApiModelProperty(value = "问卷状态(0,未开始;1进行中,2已结束)")
    private Long smStatus;

    public String getSmName() {
        return smName;
    }

    public SurveyMainDto setSmName(String smName) {
        this.smName = smName;
        return this;
    }

    public Long getSmStatus() {
        return smStatus;
    }

    public SurveyMainDto setSmStatus(Long smStatus) {
        this.smStatus = smStatus;
        return this;
    }
}
