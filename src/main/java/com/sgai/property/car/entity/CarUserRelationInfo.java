package com.sgai.property.car.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class CarUserRelationInfo extends BoEntity<CarUserRelationInfo> {

    @ApiModelProperty(value = "审核状态 0 未提交审核 1 审核中 2 审核通过 3审核不通过 4已归还")
    private Long riAuditStatus; //审核状态 0 未提交审核 1 审核中 2 审核通过 3审核不通过 4已归还
    @ApiModelProperty(value = "申请人姓名")
    private String riApplyName; //申请人姓名
    @ApiModelProperty(value = "开始行程公里数")
    private Long startJourney; //开始行程公里数
    @ApiModelProperty(value = "预约者手机号")
    private String riUserPhone; //预约者手机号
    @ApiModelProperty(value = "是否删除 0:可用 1:删除")
    private Long riIsDelete; //是否删除 0:可用 1:删除
    @ApiModelProperty(value = "申请理由")
    private String riAuditId; //申请理由
    @ApiModelProperty(value = "审核人姓名")
    private String riAuditName; //审核人姓名
    @ApiModelProperty(value = "结束行程公里数")
    private Long endJourney; //结束行程公里数
    @ApiModelProperty(value = "申请人id")
    private String riApplyId; //申请人id
    @ApiModelProperty(value = "车辆id")
    private String riCarId; //车辆id
    @ApiModelProperty(value = "预约者预约用途")
    private String riUsePurpose; //预约者预约用途
    @ApiModelProperty(value = "预约者预约归还时间")
    private Long riUseEnd; //预约者预约归还时间
    @ApiModelProperty(value = "预约者预约时长")
    private String riUseTimes; //预约者预约时长
    @ApiModelProperty(value = "审核时间")
    private Long riAuditTime; //审核时间
    @ApiModelProperty(value = "预约者预约开始时间")
    private Long riUseStart; //预约者预约开始时间
    @ApiModelProperty(value = "审核人ID")
    private String riAuditName1; //审核人ID
    @ApiModelProperty(value = "行程总公里数")
    private Long journeyAmount; //行程总公里数
    @ApiModelProperty(value = "预约者姓名")
    private String riUserName; //预约者姓名
    @ApiModelProperty(value = "车牌号码")
    private String ciNumber; //车牌号码
    @ApiModelProperty(value = "所属部门")
    private String ciDepartment; //所属部门

    @ApiModelProperty(value = "预约前行驶里程数")
    private Long lastJourney;

    public Long getLastJourney() {
        return lastJourney;
    }

    public CarUserRelationInfo setLastJourney(Long lastJourney) {
        this.lastJourney = lastJourney;
        return this;
    }

    public Long getRiAuditStatus() {
        return riAuditStatus;
    }

    public void setRiAuditStatus(Long riAuditStatus) {
        this.riAuditStatus = riAuditStatus;
    }

    public String getRiApplyName() {
        return riApplyName;
    }

    public void setRiApplyName(String riApplyName) {
        this.riApplyName = riApplyName;
    }

    public Long getStartJourney() {
        return startJourney;
    }

    public void setStartJourney(Long startJourney) {
        this.startJourney = startJourney;
    }

    public String getRiUserPhone() {
        return riUserPhone;
    }

    public void setRiUserPhone(String riUserPhone) {
        this.riUserPhone = riUserPhone;
    }

    public Long getRiIsDelete() {
        return riIsDelete;
    }

    public void setRiIsDelete(Long riIsDelete) {
        this.riIsDelete = riIsDelete;
    }

    public String getRiAuditId() {
        return riAuditId;
    }

    public void setRiAuditId(String riAuditId) {
        this.riAuditId = riAuditId;
    }

    public String getRiAuditName() {
        return riAuditName;
    }

    public void setRiAuditName(String riAuditName) {
        this.riAuditName = riAuditName;
    }

    public Long getEndJourney() {
        return endJourney;
    }

    public void setEndJourney(Long endJourney) {
        this.endJourney = endJourney;
    }

    public String getRiApplyId() {
        return riApplyId;
    }

    public void setRiApplyId(String riApplyId) {
        this.riApplyId = riApplyId;
    }

    public String getRiCarId() {
        return riCarId;
    }

    public void setRiCarId(String riCarId) {
        this.riCarId = riCarId;
    }

    public String getRiUsePurpose() {
        return riUsePurpose;
    }

    public void setRiUsePurpose(String riUsePurpose) {
        this.riUsePurpose = riUsePurpose;
    }

    public Long getRiUseEnd() {
        return riUseEnd;
    }

    public void setRiUseEnd(Long riUseEnd) {
        this.riUseEnd = riUseEnd;
    }

    public String getRiUseTimes() {
        return riUseTimes;
    }

    public void setRiUseTimes(String riUseTimes) {
        this.riUseTimes = riUseTimes;
    }

    public Long getRiAuditTime() {
        return riAuditTime;
    }

    public void setRiAuditTime(Long riAuditTime) {
        this.riAuditTime = riAuditTime;
    }

    public Long getRiUseStart() {
        return riUseStart;
    }

    public void setRiUseStart(Long riUseStart) {
        this.riUseStart = riUseStart;
    }

    public String getRiAuditName1() {
        return riAuditName1;
    }

    public void setRiAuditName1(String riAuditName1) {
        this.riAuditName1 = riAuditName1;
    }

    public Long getJourneyAmount() {
        return journeyAmount;
    }

    public void setJourneyAmount(Long journeyAmount) {
        this.journeyAmount = journeyAmount;
    }

    public String getRiUserName() {
        return riUserName;
    }

    public void setRiUserName(String riUserName) {
        this.riUserName = riUserName;
    }

    public String getCiNumber() {
        return ciNumber;
    }

    public void setCiNumber(String ciNumber) {
        this.ciNumber = ciNumber;
    }

    public String getCiDepartment() {
        return ciDepartment;
    }

    public void setCiDepartment(String ciDepartment) {
        this.ciDepartment = ciDepartment;
    }
}