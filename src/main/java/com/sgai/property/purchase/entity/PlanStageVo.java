package com.sgai.property.purchase.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public class PlanStageVo {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "阶段名称")
    private String stageName; //阶段名称
    @ApiModelProperty(value = "任务数据")
    private List<PlanTaskVo> planTaskVoList;

    public List<PlanTaskVo> getPlanTaskVoList() {
        return planTaskVoList;
    }

    public void setPlanTaskVoList(List<PlanTaskVo> planTaskVoList) {
        this.planTaskVoList = planTaskVoList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStageName(){
        return stageName;
    }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }
}
