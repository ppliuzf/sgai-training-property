package com.sgai.property.customer.vo;

import com.sgai.common.persistence.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 问卷摘要
 *
 * @author Hou
 * @create 2018-04-12 11:15
 */
public class SurveyStatisticsVo implements Serializable {

    @ApiModelProperty(value = "问卷ID")
    private String smId;
    @ApiModelProperty(value = "问卷题目总数量")
    private Long smCount;
    @ApiModelProperty(value = "问卷名称")
    private String smName;
    @ApiModelProperty(value = "问卷结束时间")
    private String smEndTime;
    @ApiModelProperty(value = "")
    private List<Long> arrayNo;
    @ApiModelProperty(value = "当前问题Id")
    private String sqId;
    @ApiModelProperty(value = "问题标题")
    private String sqTopic;
    @ApiModelProperty(value = "问题类型(0,单选,1,多选,2,文本)")
    private Long sqType;
    @ApiModelProperty(value = "问题序号,数字,用来排序")
    private Long sqNo;

    @ApiModelProperty(value = "选项信息")
    private Page<SurveyOptionStatisticsVo> optionInfoPageInfo;
    @ApiModelProperty(value = "文本问题回答情况")
    private Page<SurveyTextAnswerVo> textAnswerVoPageInfo;

    public Page<SurveyOptionStatisticsVo> getOptionInfoPageInfo() {
        return optionInfoPageInfo;
    }

    public SurveyStatisticsVo setOptionInfoPageInfo(Page<SurveyOptionStatisticsVo> optionInfoPageInfo) {
        this.optionInfoPageInfo = optionInfoPageInfo;
        return this;
    }

    public List<Long> getArrayNo() {
        if(null != this.getSmCount()){
            List<Long> longList = new LinkedList<>();
            for(int i = 1;i<= this.getSmCount();i++){
                longList.add(new Long(i));
            }
            return longList;
        }
        return arrayNo;
    }

    public SurveyStatisticsVo setArrayNo(List<Long> arrayNo) {
        this.arrayNo = arrayNo;
        return this;
    }

    public Page<SurveyTextAnswerVo> getTextAnswerVoPageInfo() {
        return textAnswerVoPageInfo;
    }

    public SurveyStatisticsVo setTextAnswerVoPageInfo(Page<SurveyTextAnswerVo> textAnswerVoPageInfo) {
        this.textAnswerVoPageInfo = textAnswerVoPageInfo;
        return this;
    }

    public String getSqId() {
        return sqId;
    }

    public SurveyStatisticsVo setSqId(String sqId) {
        this.sqId = sqId;
        return this;
    }

    public String getSqTopic() {
        return sqTopic;
    }

    public SurveyStatisticsVo setSqTopic(String sqTopic) {
        this.sqTopic = sqTopic;
        return this;
    }

    public Long getSqType() {
        return sqType;
    }

    public SurveyStatisticsVo setSqType(Long sqType) {
        this.sqType = sqType;
        return this;
    }

    public Long getSqNo() {
        return sqNo;
    }

    public SurveyStatisticsVo setSqNo(Long sqNo) {
        this.sqNo = sqNo;
        return this;
    }



    public String getSmId() {
        return smId;
    }

    public SurveyStatisticsVo setSmId(String smId) {
        this.smId = smId;
        return this;
    }

    public Long getSmCount() {
        return smCount;
    }

    public SurveyStatisticsVo setSmCount(Long smCount) {
        this.smCount = smCount;
        return this;
    }

    public String getSmName() {
        return smName;
    }

    public SurveyStatisticsVo setSmName(String smName) {
        this.smName = smName;
        return this;
    }

    public String getSmEndTime() {
        return smEndTime;
    }

    public SurveyStatisticsVo setSmEndTime(String smEndTime) {
        this.smEndTime = smEndTime;
        return this;
    }

}
