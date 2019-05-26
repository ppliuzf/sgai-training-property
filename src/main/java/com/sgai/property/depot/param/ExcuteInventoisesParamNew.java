package com.sgai.property.depot.param;

import com.sgai.property.depot.vo.WhInParam;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by gaojianqun on 2018/6/7.
 */
public class ExcuteInventoisesParamNew {

    @ApiModelProperty(value = "盘点单号")
    private String ivtNo; //盘点单号
    @ApiModelProperty(value = "盘点标识？1：未开始；2：盘点中；3已完成")
    private Long matStat; //盘点标识？1：未开始；2：盘点中；3已完成
    @ApiModelProperty(value = "盘点开始时间")
    private Date ivtExecBegin; //盘点开始时间
    @ApiModelProperty(value = "盘点结束时间")
    private Date ivtExecEnd; //盘点结束时间
    @ApiModelProperty(value = "盘点执行人id")
    private String ivtExecEmpId; //盘点执行人id
    @ApiModelProperty(value = "盘点执行人名称")
    private String ivtExecEmpName; //盘点执行人名称
    @ApiModelProperty(value = "差异原因")
    private String ivtDiffReason; //差异原因

    public String getIvtNo() {
        return ivtNo;
    }

    public void setIvtNo(String ivtNo) {
        this.ivtNo = ivtNo;
    }

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
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

    public String getIvtExecEmpId() {
        return ivtExecEmpId;
    }

    public void setIvtExecEmpId(String ivtExecEmpId) {
        this.ivtExecEmpId = ivtExecEmpId;
    }

    public String getIvtExecEmpName() {
        return ivtExecEmpName;
    }

    public void setIvtExecEmpName(String ivtExecEmpName) {
        this.ivtExecEmpName = ivtExecEmpName;
    }

    public String getIvtDiffReason() {
        return ivtDiffReason;
    }

    public void setIvtDiffReason(String ivtDiffReason) {
        this.ivtDiffReason = ivtDiffReason;
    }

}
