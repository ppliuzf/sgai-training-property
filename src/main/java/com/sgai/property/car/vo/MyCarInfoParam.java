package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 我预约的车辆
 *
 * @author Hou
 * @create 2018-05-21 10:53
 */
public class MyCarInfoParam implements Serializable {

    @ApiModelProperty(value = "车辆排量")
    private String ciDisplacement;
    @ApiModelProperty(value = "预约时间")
    private Long riUseEnd;
    @ApiModelProperty(value = "变速箱类型id")
    private String ciBoxTypeId;
    @ApiModelProperty(value = "荷载人数")
    private Long ciLoadNumber;

    public String getCiDisplacement() {
        return ciDisplacement;
    }

    public MyCarInfoParam setCiDisplacement(String ciDisplacement) {
        this.ciDisplacement = ciDisplacement;
        return this;
    }

    public Long getRiUseEnd() {
        return riUseEnd;
    }

    public MyCarInfoParam setRiUseEnd(Long riUseEnd) {
        this.riUseEnd = riUseEnd;
        return this;
    }

    public String getCiBoxTypeId() {
        return ciBoxTypeId;
    }

    public MyCarInfoParam setCiBoxTypeId(String ciBoxTypeId) {
        this.ciBoxTypeId = ciBoxTypeId;
        return this;
    }

    public Long getCiLoadNumber() {
        return ciLoadNumber;
    }

    public MyCarInfoParam setCiLoadNumber(Long ciLoadNumber) {
        this.ciLoadNumber = ciLoadNumber;
        return this;
    }
}
