package com.sgai.property.alm.vo;

import java.math.BigDecimal;

/**
 * 总览.
 *
 * @author ppliu
 * created in 2019/1/18 13:27
 */
public class Overview {
    /** 用电总能耗. */
    private BigDecimal totalelec;
    /** 用水总能耗. */
    private BigDecimal totalWater;
    /** 累计入场人次. */
    private BigDecimal entranceNum;
    /** 停车场占用率. */
    private BigDecimal parkuseRate;
    /** 平均PM2. */
    private BigDecimal averagePM25;
    /** 室内平均温度. */
    private BigDecimal averageT;
    /** 室内平均湿度. */
    private BigDecimal averageH;
    /** 室内平均二氧化碳浓度. */
    private BigDecimal averageCO2;
    /** 充电桩使用数. */
    private BigDecimal charginguseNum;
    /** 制冰机开启量. */
    private BigDecimal astOpenNum;
    /** 新风机组开启量. */
    private BigDecimal atfuOpenNum;
    /** 风机盘管开启量. */
    private BigDecimal atfcOpenNum;
    /** 热转轮机组开启量. */
    private BigDecimal hotrunneropenNum;
    /** 空调机组开启量. */
    private BigDecimal acOpenNum;
    /** 电梯的开启数/总数. */
    private BigDecimal elNum;
    /** 摄像头的在线/离线数量. */
    private BigDecimal vsNum;

    public BigDecimal getTotalelec() {
        return totalelec;
    }

    public BigDecimal getTotalWater() {
        return totalWater;
    }

    public BigDecimal getEntranceNum() {
        return entranceNum;
    }

    public BigDecimal getParkuseRate() {
        return parkuseRate;
    }

    public BigDecimal getAveragePM25() {
        return averagePM25;
    }

    public BigDecimal getAverageT() {
        return averageT;
    }

    public BigDecimal getAverageH() {
        return averageH;
    }

    public BigDecimal getAverageCO2() {
        return averageCO2;
    }

    public BigDecimal getCharginguseNum() {
        return charginguseNum;
    }

    public BigDecimal getAstOpenNum() {
        return astOpenNum;
    }

    public BigDecimal getAtfuOpenNum() {
        return atfuOpenNum;
    }

    public BigDecimal getAtfcOpenNum() {
        return atfcOpenNum;
    }

    public BigDecimal getHotrunneropenNum() {
        return hotrunneropenNum;
    }

    public BigDecimal getAcOpenNum() {
        return acOpenNum;
    }

    public BigDecimal getElNum() {
        return elNum;
    }

    public BigDecimal getVsNum() {
        return vsNum;
    }

    public void setTotalelec(BigDecimal totalelec) {
        this.totalelec = totalelec;
    }

    public void setTotalWater(BigDecimal totalWater) {
        this.totalWater = totalWater;
    }

    public void setEntranceNum(BigDecimal entranceNum) {
        this.entranceNum = entranceNum;
    }

    public void setParkuseRate(BigDecimal parkuseRate) {
        this.parkuseRate = parkuseRate;
    }

    public void setAveragePM25(BigDecimal averagePM25) {
        this.averagePM25 = averagePM25;
    }

    public void setAverageT(BigDecimal averageT) {
        this.averageT = averageT;
    }

    public void setAverageH(BigDecimal averageH) {
        this.averageH = averageH;
    }

    public void setAverageCO2(BigDecimal averageCO2) {
        this.averageCO2 = averageCO2;
    }

    public void setCharginguseNum(BigDecimal charginguseNum) {
        this.charginguseNum = charginguseNum;
    }

    public void setAstOpenNum(BigDecimal astOpenNum) {
        this.astOpenNum = astOpenNum;
    }

    public void setAtfuOpenNum(BigDecimal atfuOpenNum) {
        this.atfuOpenNum = atfuOpenNum;
    }

    public void setAtfcOpenNum(BigDecimal atfcOpenNum) {
        this.atfcOpenNum = atfcOpenNum;
    }

    public void setHotrunneropenNum(BigDecimal hotrunneropenNum) {
        this.hotrunneropenNum = hotrunneropenNum;
    }

    public void setAcOpenNum(BigDecimal acOpenNum) {
        this.acOpenNum = acOpenNum;
    }

    public void setElNum(BigDecimal elNum) {
        this.elNum = elNum;
    }

    public void setVsNum(BigDecimal vsNum) {
        this.vsNum = vsNum;
    }
}
