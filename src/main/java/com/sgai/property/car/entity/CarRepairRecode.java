package com.sgai.property.car.entity;

import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class CarRepairRecode extends BoEntity<CarRepairRecode> {

    @ApiModelProperty(value = "电话")
    private Long rrPhoneNum;
    @ApiModelProperty(value = "备注信息")
    private String rrRemark;
    @ApiModelProperty(value = "维修地点")
    private String rrAddress;
    @ApiModelProperty(value = "维修人")
    private String rrName;
    @ApiModelProperty(value = "车辆id")
    private String carId;
    @ApiModelProperty(value = "维修日期")
    private Long rrDate;
    @ApiModelProperty(value = "维修内容")
    private String rrContent;
    @ApiModelProperty(value = "是否删除 0:可用 1:删除")
    private Long rrIsDelete;

    public Long getRrPhoneNum() {
        return rrPhoneNum;
    }

    public void setRrPhoneNum(Long rrPhoneNum) {
        this.rrPhoneNum = rrPhoneNum;
    }

    public String getRrRemark() {
        return rrRemark;
    }

    public void setRrRemark(String rrRemark) {
        this.rrRemark = rrRemark;
    }

    public String getRrAddress() {
        return rrAddress;
    }

    public void setRrAddress(String rrAddress) {
        this.rrAddress = rrAddress;
    }

    public String getRrName() {
        return rrName;
    }

    public void setRrName(String rrName) {
        this.rrName = rrName;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Long getRrDate() {
        return rrDate;
    }

    public void setRrDate(Long rrDate) {
        this.rrDate = rrDate;
    }

    public String getRrContent() {
        return rrContent;
    }

    public void setRrContent(String rrContent) {
        this.rrContent = rrContent;
    }

    public Long getRrIsDelete() {
        return rrIsDelete;
    }

    public void setRrIsDelete(Long rrIsDelete) {
        this.rrIsDelete = rrIsDelete;
    }
}