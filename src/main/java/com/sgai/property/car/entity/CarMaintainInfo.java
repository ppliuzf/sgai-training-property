package com.sgai.property.car.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class CarMaintainInfo extends BoEntity<CarMaintainInfo> {

    @ApiModelProperty(value = "")
    private Long isDelete;
    @ApiModelProperty(value = "保养名称")
    private String maintainName;
    @ApiModelProperty(value = "保养人姓名")
    private String maintainUserName;
    @ApiModelProperty(value = "保养时间")
    private Long maintainTime;
    @ApiModelProperty(value = "保养类型")
    private String maintainType;
    @ApiModelProperty(value = "车辆id")
    private String carId;
    @ApiModelProperty(value = "保养单位名称")
    private String maintainComName;
    @ApiModelProperty(value = "")
    private String maintainDesc;

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public String getMaintainName() {
        return maintainName;
    }

    public void setMaintainName(String maintainName) {
        this.maintainName = maintainName;
    }

    public String getMaintainUserName() {
        return maintainUserName;
    }

    public void setMaintainUserName(String maintainUserName) {
        this.maintainUserName = maintainUserName;
    }

    public Long getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Long maintainTime) {
        this.maintainTime = maintainTime;
    }

    public String getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(String maintainType) {
        this.maintainType = maintainType;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getMaintainComName() {
        return maintainComName;
    }

    public void setMaintainComName(String maintainComName) {
        this.maintainComName = maintainComName;
    }

    public String getMaintainDesc() {
        return maintainDesc;
    }

    public void setMaintainDesc(String maintainDesc) {
        this.maintainDesc = maintainDesc;
    }
}