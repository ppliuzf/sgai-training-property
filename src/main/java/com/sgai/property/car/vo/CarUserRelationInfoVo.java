package com.sgai.property.car.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CarUserRelationInfoVo extends BoEntity<CarUserRelationInfoVo> {
    @ApiModelProperty(value = "预约者姓名")
    private String riUserName; //预约者姓名
    @ApiModelProperty(value = "预约者手机号")
    private String riUserPhone; //预约者手机号
    @ApiModelProperty(value = "预约者预约用途")
    private String riUsePurpose; //预约者预约用途
    @ApiModelProperty(value = "车辆id")
    private String riCarId; //车辆id
    @ApiModelProperty(value = "预约者预约时长")
    private String riUseTimes; //预约者预约时长
    @ApiModelProperty(value = "预约者预约归还时间")
    private Long riUseEnd; //预约者预约归还时间
    @ApiModelProperty(value = "预约者预约开始时间")
    private Long riUseStart; //预约者预约开始时间
    @ApiModelProperty(value = "审核状态 0 未提交审核 1 审核中 2 审核通过 3审核不通过 4已归还 5归还中")
    private Long riAuditStatus; //审核状态 0 未提交审核 1 审核中 2 审核通过 3审核不通过4已归还 5归还中
    @ApiModelProperty(value = "是否删除 0:可用 1:删除")
    private Long riIsDelete; //是否删除 0:可用 1:删除
    @ApiModelProperty(value = "申请理由")
    private String riAuditId; //申请理由
    @ApiModelProperty(value = "审核人姓名")
    private String riAuditName; //审核人姓名
    @ApiModelProperty(value = "审核人ID")
    private String riAuditName1; //审核人ID
    @ApiModelProperty(value = "审核人部门")
    private String deptName; //审核人部门
    @ApiModelProperty(value = "审核时间")
    private Long riAuditTime; //审核时间

    @ApiModelProperty(value = "预约前行驶里程数")
    private Long lastJourney;
    @ApiModelProperty(value = "结束行程公里数")
    private Long endJourney;
    @ApiModelProperty(value = "开始行程公里数")
    private Long startJourney;
    @ApiModelProperty(value = "行程总公里数")
    private Long journeyAmount;


    @ApiModelProperty(value = "申请人姓名")
    private String riApplyName; //申请人姓名
    //车辆信息
    @ApiModelProperty(value = "车牌号码")
    private String ciNumber; //车牌号码
    @ApiModelProperty(value = "所属部门id")
    private String ciDepartmentId; //所属部门id
    @ApiModelProperty(value = "所属部门")
    private String ciDepartment; //所属部门
    @ApiModelProperty(value = "购置日期")
    private Long ciBuyDate; //购置日期
    @ApiModelProperty(value = "发动机号码")
    private String ciEngineNumber; //发动机号码
    @ApiModelProperty(value = "车辆品牌")
    private String ciBrand; //车辆品牌
    @ApiModelProperty(value = "车辆型号")
    private String ciModel; //车辆型号
    @ApiModelProperty(value = "车辆颜色")
    private String ciColor; //车辆颜色
    @ApiModelProperty(value = "车辆排量")
    private String ciDisplacement; //车辆排量
    @ApiModelProperty(value = "变速箱类型名称")
    private String ciBoxTypeName; //变速箱类型名称
    @ApiModelProperty(value = "荷载人数")
    private Long ciLoadNumber; //荷载人数
    @ApiModelProperty(value = "车主姓名")
    private String ciOwnerName; //车主姓名
    @ApiModelProperty(value = "车主手机号")
    private Long ciOwnerPhone; //车主手机号
    @ApiModelProperty(value = "车辆类型id")
    private String ciTypeId; //车辆类型ID
    @ApiModelProperty(value = "车辆类型名称")
    private String ciTypeName; //车辆类型名称
    @ApiModelProperty(value = "车辆截图")
    private String ciImage; //车辆截图

    @ApiModelProperty(value = "申请人id")
    private String riApplyId; //申请人id

    @ApiModelProperty(value = "搜索条件")
    private String condition; //搜索条件

    @ApiModelProperty(value = "long类型的创建时间")
    private Long createdDtLong; //long类型的创建时间

    public Long getLastJourney() {
        return lastJourney;
    }

    public CarUserRelationInfoVo setLastJourney(Long lastJourney) {
        this.lastJourney = lastJourney;
        return this;
    }

    public Long getStartJourney() {
        return startJourney;
    }

    public CarUserRelationInfoVo setStartJourney(Long startJourney) {
        this.startJourney = startJourney;
        return this;
    }

    public Long getJourneyAmount() {
        return journeyAmount;
    }

    public CarUserRelationInfoVo setJourneyAmount(Long journeyAmount) {
        this.journeyAmount = journeyAmount;
        return this;
    }

    public String getRiUserPhone() {
        return riUserPhone;
    }

    public void setRiUserPhone(String riUserPhone) {
        this.riUserPhone = riUserPhone;
    }

    public String getRiUsePurpose() {
        return riUsePurpose;
    }

    public void setRiUsePurpose(String riUsePurpose) {
        this.riUsePurpose = riUsePurpose;
    }

    public String getRiCarId() {
        return riCarId;
    }

    public void setRiCarId(String riCarId) {
        this.riCarId = riCarId;
    }

    public String getRiUseTimes() {
        return riUseTimes;
    }

    public void setRiUseTimes(String riUseTimes) {
        this.riUseTimes = riUseTimes;
    }

    public Long getRiUseEnd() {
        return riUseEnd;
    }

    public void setRiUseEnd(Long riUseEnd) {
        this.riUseEnd = riUseEnd;
    }

    public Long getRiUseStart() {
        return riUseStart;
    }

    public void setRiUseStart(Long riUseStart) {
        this.riUseStart = riUseStart;
    }

    public Long getRiAuditStatus() {
        return riAuditStatus;
    }

    public void setRiAuditStatus(Long riAuditStatus) {
        this.riAuditStatus = riAuditStatus;
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

    public String getCiDepartmentId() {
        return ciDepartmentId;
    }

    public void setCiDepartmentId(String ciDepartmentId) {
        this.ciDepartmentId = ciDepartmentId;
    }

    public String getCiDepartment() {
        return ciDepartment;
    }

    public void setCiDepartment(String ciDepartment) {
        this.ciDepartment = ciDepartment;
    }

    public Long getRiIsDelete() {
        return riIsDelete;
    }

    public void setRiIsDelete(Long riIsDelete) {
        this.riIsDelete = riIsDelete;
    }

    public Long getCiBuyDate() {
        return ciBuyDate;
    }

    public void setCiBuyDate(Long ciBuyDate) {
        this.ciBuyDate = ciBuyDate;
    }

    public String getCiEngineNumber() {
        return ciEngineNumber;
    }

    public void setCiEngineNumber(String ciEngineNumber) {
        this.ciEngineNumber = ciEngineNumber;
    }

    public String getCiBrand() {
        return ciBrand;
    }

    public void setCiBrand(String ciBrand) {
        this.ciBrand = ciBrand;
    }

    public String getCiModel() {
        return ciModel;
    }

    public void setCiModel(String ciModel) {
        this.ciModel = ciModel;
    }

    public String getCiColor() {
        return ciColor;
    }

    public void setCiColor(String ciColor) {
        this.ciColor = ciColor;
    }

    public String getCiDisplacement() {
        return ciDisplacement;
    }

    public void setCiDisplacement(String ciDisplacement) {
        this.ciDisplacement = ciDisplacement;
    }

    public String getCiBoxTypeName() {
        return ciBoxTypeName;
    }

    public void setCiBoxTypeName(String ciBoxTypeName) {
        this.ciBoxTypeName = ciBoxTypeName;
    }

    public Long getCiLoadNumber() {
        return ciLoadNumber;
    }

    public void setCiLoadNumber(Long ciLoadNumber) {
        this.ciLoadNumber = ciLoadNumber;
    }

    public String getCiOwnerName() {
        return ciOwnerName;
    }

    public void setCiOwnerName(String ciOwnerName) {
        this.ciOwnerName = ciOwnerName;
    }

    public String getRiApplyId() {
        return riApplyId;
    }

    public void setRiApplyId(String riApplyId) {
        this.riApplyId = riApplyId;
    }

    public Long getCiOwnerPhone() {
        return ciOwnerPhone;
    }

    public void setCiOwnerPhone(Long ciOwnerPhone) {
        this.ciOwnerPhone = ciOwnerPhone;
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

    public String getRiAuditName1() {
        return riAuditName1;
    }

    public void setRiAuditName1(String riAuditName1) {
        this.riAuditName1 = riAuditName1;
    }

    public String getRiApplyName() {
        return riApplyName;
    }

    public void setRiApplyName(String riApplyName) {
        this.riApplyName = riApplyName;
    }

    public String getCiTypeId() {
        return ciTypeId;
    }

    public void setCiTypeId(String ciTypeId) {
        this.ciTypeId = ciTypeId;
    }

    public String getCiTypeName() {
        return ciTypeName;
    }

    public void setCiTypeName(String ciTypeName) {
        this.ciTypeName = ciTypeName;
    }

    public String getCiImage() {
        return ciImage;
    }

    public void setCiImage(String ciImage) {
        this.ciImage = ciImage;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getCreatedDtLong() {
        return createdDtLong;
    }

    public void setCreatedDtLong(Long createdDtLong) {
        this.createdDtLong = createdDtLong;
    }

    public Long getRiAuditTime() {
        return riAuditTime;
    }

    public void setRiAuditTime(Long riAuditTime) {
        this.riAuditTime = riAuditTime;
    }

    public Long getEndJourney(){
        return endJourney;
    }

    public void setEndJourney(Long endJourney){
        this.endJourney = endJourney;
    }
}
