package com.sgai.property.car.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class CarRepairRecodeParam extends BoEntity<CarRepairRecodeParam>{
    @ApiModelProperty(value = "id")
    private String id; //id
    @ApiModelProperty(value = "备注信息")
    private String rrRemark; //备注信息
    @ApiModelProperty(value = "维修地点")
    private String rrAddress; //维修地点
    @ApiModelProperty(value = "维修人")
    private String rrName; //维修人
    @ApiModelProperty(value = "车辆id")
    private String carId; //车辆id
    @ApiModelProperty(value = "维修内容")
    private String rrContent; //维修内容
    @ApiModelProperty(value = "维修日期")
    private Long rrDate; //维修日期
    @ApiModelProperty(value = "电话")
    private Long rrPhoneNum;

    public Long getRrPhoneNum() {
        return rrPhoneNum;
    }

    public CarRepairRecodeParam setRrPhoneNum(Long rrPhoneNum) {
        this.rrPhoneNum = rrPhoneNum;
        return this;
    }

    public Long getRrDate() {
        return rrDate;
    }

    public CarRepairRecodeParam setRrDate(Long rrDate) {
        this.rrDate = rrDate;
        return this;
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

    public String getRrContent() {
        return rrContent;
    }

    public void setRrContent(String rrContent) {
        this.rrContent = rrContent;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}