package com.sgai.property.car.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class CarPunishInfo extends BoEntity<CarPunishInfo> {

    @ApiModelProperty(value = "违章人姓名")
    private String punishUserName;
    @ApiModelProperty(value = "违章内容")
    private String punishContent;
    @ApiModelProperty(value = "违章备注")
    private String punishDesc;
    @ApiModelProperty(value = "是否删除")
    private Long isDelete;
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

    public void setPunishUserName(String punishUserName) {
        this.punishUserName = punishUserName;
    }

    public String getPunishContent() {
        return punishContent;
    }

    public void setPunishContent(String punishContent) {
        this.punishContent = punishContent;
    }

    public String getPunishDesc() {
        return punishDesc;
    }

    public void setPunishDesc(String punishDesc) {
        this.punishDesc = punishDesc;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public Long getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(Long punishTime) {
        this.punishTime = punishTime;
    }

    public String getPunishUserId() {
        return punishUserId;
    }

    public void setPunishUserId(String punishUserId) {
        this.punishUserId = punishUserId;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public String getPunishUserPhoneNum() {
        return punishUserPhoneNum;
    }

    public void setPunishUserPhoneNum(String punishUserPhoneNum) {
        this.punishUserPhoneNum = punishUserPhoneNum;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}