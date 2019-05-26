package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 问题答案vo
 *
 * @author Hou
 * @create 2018-04-12 11:45
 */
public class SurveyOptionStatisticsVo implements Serializable {

    @ApiModelProperty(value = "问题选项")
    private String saContent;
    @ApiModelProperty(value = "问题票数")
    private Double count;
    @ApiModelProperty(value = "问题被选占比")
    private BigDecimal scale;
    @ApiModelProperty(value = "关联问题id")
    private String sqId;

    public String getSaContent() {
        return saContent;
    }

    public SurveyOptionStatisticsVo setSaContent(String saContent) {
        this.saContent = saContent;
        return this;
    }

    public Double getCount() {
        return count;
    }

    public SurveyOptionStatisticsVo setCount(Double count) {
        this.count = count;
        return this;
    }

    public BigDecimal getScale() {
        return scale;
    }

    public SurveyOptionStatisticsVo setScale(BigDecimal scale) {
        this.scale = scale;
        return this;
    }

    public String getSqId() {
        return sqId;
    }

    public SurveyOptionStatisticsVo setSqId(String sqId) {
        this.sqId = sqId;
        return this;
    }
}
