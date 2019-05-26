package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by gaojianqun on 2018/6/8.
 */
public class InventoriesWhVoNew {

    @ApiModelProperty(value = "盘点主题")
    private String ivtTitle; //盘点主题
    @ApiModelProperty(value = "盘点单号")
    private String orderNumber; //盘点单号
    @ApiModelProperty(value = "盘点标识？1：未开始；2：盘点中；3已完成")
    private Long matStat; //盘点标识？1：未开始；2：盘点中；3已完成
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "计划结束时间")
    private Date ivtPlanEnd; //计划结束时间
    @ApiModelProperty(value = "计划开始时间")
    private Date ivtPlanBegin; //计划开始时间
    @ApiModelProperty(value = "盘点职位json")
    private String ivtPiJson; //盘点职位json
    @ApiModelProperty(value = "盘点开始时间")
    private Date ivtExecBegin; //盘点开始时间
    @ApiModelProperty(value = "盘点结束时间")
    private Date ivtExecEnd; //盘点结束时间

    public String getIvtTitle() {
        return ivtTitle;
    }

    public void setIvtTitle(String ivtTitle) {
        this.ivtTitle = ivtTitle;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public Date getIvtPlanEnd() {
        return ivtPlanEnd;
    }

    public void setIvtPlanEnd(Date ivtPlanEnd) {
        this.ivtPlanEnd = ivtPlanEnd;
    }

    public Date getIvtPlanBegin() {
        return ivtPlanBegin;
    }

    public void setIvtPlanBegin(Date ivtPlanBegin) {
        this.ivtPlanBegin = ivtPlanBegin;
    }

    public String getIvtPiJson() {
        return ivtPiJson;
    }

    public void setIvtPiJson(String ivtPiJson) {
        this.ivtPiJson = ivtPiJson;
    }

    public Date getIvtExecBegin() {
        return ivtExecBegin;
    }

    public void setIvtExecBegin(Date ivtExecBegin) {
        this.ivtExecBegin = ivtExecBegin;
    }

    public Date getIvtExecEnd() {
        return ivtExecEnd;
    }

    public void setIvtExecEnd(Date ivtExecEnd) {
        this.ivtExecEnd = ivtExecEnd;
    }

}
