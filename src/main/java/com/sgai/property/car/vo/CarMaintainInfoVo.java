package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 保养信息
 *
 * @author Hou
 * @create 2018-05-22 14:01
 */
public class CarMaintainInfoVo implements Serializable {

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

    public String getMaintainName() {
        return maintainName;
    }

    public CarMaintainInfoVo setMaintainName(String maintainName) {
        this.maintainName = maintainName;
        return this;
    }

    public String getMaintainUserName() {
        return maintainUserName;
    }

    public CarMaintainInfoVo setMaintainUserName(String maintainUserName) {
        this.maintainUserName = maintainUserName;
        return this;
    }

    public Long getMaintainTime() {
        return maintainTime;
    }

    public CarMaintainInfoVo setMaintainTime(Long maintainTime) {
        this.maintainTime = maintainTime;
        return this;
    }

    public String getMaintainType() {
        return maintainType;
    }

    public CarMaintainInfoVo setMaintainType(String maintainType) {
        this.maintainType = maintainType;
        return this;
    }

    public String getCarId() {
        return carId;
    }

    public CarMaintainInfoVo setCarId(String carId) {
        this.carId = carId;
        return this;
    }

    public String getMaintainComName() {
        return maintainComName;
    }

    public CarMaintainInfoVo setMaintainComName(String maintainComName) {
        this.maintainComName = maintainComName;
        return this;
    }

    public String getMaintainDesc() {
        return maintainDesc;
    }

    public CarMaintainInfoVo setMaintainDesc(String maintainDesc) {
        this.maintainDesc = maintainDesc;
        return this;
    }
}
