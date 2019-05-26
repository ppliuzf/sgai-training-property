package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

public class CarUserRelationInfoParam {
    /*@ApiModelProperty(value = "申请人id")
    private String riApplyId; //申请人id
    @ApiModelProperty(value = "申请人姓名")
    private String riApplyName; //申请人姓名
*/
    @ApiModelProperty(value = "车辆id")
    private String riCarId; //车辆id
    @ApiModelProperty(value = "预约者预约时长")
    private String riUseTimes; //预约者预约时长
    @ApiModelProperty(value = "预约者预约归还时间")
    private Long riUseEnd; //预约者预约归还时间
    @ApiModelProperty(value = "预约者预约开始时间")
    private Long riUseStart; //预约者预约开始时间
    @ApiModelProperty(value = "预约者姓名")
    private String riUserName; //预约者姓名
    @ApiModelProperty(value = "预约者手机号")
    private String riUserPhone; //预约者手机号
    @ApiModelProperty(value = "预约者预约用途")
    private String riUsePurpose; //预约者预约用途

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

    public Long getRiUseStart() {
        return riUseStart;
    }

    public void setRiUseStart(Long riUseStart) {
        this.riUseStart = riUseStart;
    }


    public String getRiUserName() {
        return riUserName;
    }

    public void setRiUserName(String riUserName) {
        this.riUserName = riUserName;
    }



    public Long getRiUseEnd() {
        return riUseEnd;
    }

    public void setRiUseEnd(Long riUseEnd) {
        this.riUseEnd = riUseEnd;
    }
}
