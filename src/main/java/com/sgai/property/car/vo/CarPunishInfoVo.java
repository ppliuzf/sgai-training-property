package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 违章记录详情
 *
 * @author Hou
 * @create 2018-05-22 14:33
 */
public class CarPunishInfoVo implements Serializable {
    @ApiModelProperty(value = "违章人姓名")
    private String punishUserName;
    @ApiModelProperty(value = "违章内容")
    private String punishContent;
    @ApiModelProperty(value = "违章备注")
    private String punishDesc;
    @ApiModelProperty(value = "违章日期")
    private Long punishTime;
    @ApiModelProperty(value = "违章人id")
    private String punishUserId;
    @ApiModelProperty(value = "违章类型")
    private String punishType;
    @ApiModelProperty(value = "违章人手机号")
    private String punishUserPhoneNum;
    @ApiModelProperty(value = "车辆id")
    private String carId;

    public String getPunishUserName() {
        return punishUserName;
    }

    public CarPunishInfoVo setPunishUserName(String punishUserName) {
        this.punishUserName = punishUserName;
        return this;
    }

    public String getPunishContent() {
        return punishContent;
    }

    public CarPunishInfoVo setPunishContent(String punishContent) {
        this.punishContent = punishContent;
        return this;
    }

    public String getPunishDesc() {
        return punishDesc;
    }

    public CarPunishInfoVo setPunishDesc(String punishDesc) {
        this.punishDesc = punishDesc;
        return this;
    }

    public Long getPunishTime() {
        return punishTime;
    }

    public CarPunishInfoVo setPunishTime(Long punishTime) {
        this.punishTime = punishTime;
        return this;
    }

    public String getPunishUserId() {
        return punishUserId;
    }

    public CarPunishInfoVo setPunishUserId(String punishUserId) {
        this.punishUserId = punishUserId;
        return this;
    }

    public String getPunishType() {
        return punishType;
    }

    public CarPunishInfoVo setPunishType(String punishType) {
        this.punishType = punishType;
        return this;
    }

    public String getPunishUserPhoneNum() {
        return punishUserPhoneNum;
    }

    public CarPunishInfoVo setPunishUserPhoneNum(String punishUserPhoneNum) {
        this.punishUserPhoneNum = punishUserPhoneNum;
        return this;
    }

    public String getCarId() {
        return carId;
    }

    public CarPunishInfoVo setCarId(String carId) {
        this.carId = carId;
        return this;
    }
}
