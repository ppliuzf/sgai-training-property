package com.sgai.property.alm.vo;

import java.math.BigDecimal;

/**
 * 冰壶馆数据.
 *
 * @author ppliu
 * created in 2019/1/18 13:30
 */
public class Curling {
    /** 总能耗（电）. */
    private BigDecimal totalelec;
    /** 总能耗（水）. */
    private BigDecimal totalWater;
    /** 平均温度. */
    private BigDecimal averageT;
    /** 平均PM2.5. */
    private BigDecimal averagePM25;
    /** 室内平均湿度. */
    private BigDecimal averageH;
    /** 室内平均二氧化碳浓度. */
    private BigDecimal averageCO2;
    /** 制冰机开启量. */
    private BigDecimal astOpenNum;
    /** 新风机组开启量. */
    private BigDecimal atfuOpenNum;
    /** 风机盘管. */
    private BigDecimal atfcOpenNum;
    /** 热转轮机组. */
    private BigDecimal hotrunneropenNum;
    /** 当日累计能耗（电）. */
    private BigDecimal totalelecDay;
    /** 当日累计能耗（水）. */
    private BigDecimal totalWaterDay;
    /** 空调机组开启量. */
    private BigDecimal acOpenNum;
    /** 电梯的开启数/总数. */
    private BigDecimal elNum;
    /** 摄像头的在线/离线数量. */
    private BigDecimal vsNum;

    public BigDecimal getTotalelec() {
        return totalelec;
    }

    public void setTotalelec(BigDecimal totalelec) {
        this.totalelec = totalelec;
    }

    public BigDecimal getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(BigDecimal totalWater) {
        this.totalWater = totalWater;
    }

    public BigDecimal getAverageT() {
        return averageT;
    }

    public void setAverageT(BigDecimal averageT) {
        this.averageT = averageT;
    }

    public BigDecimal getAveragePM25() {
        return averagePM25;
    }

    public void setAveragePM25(BigDecimal averagePM25) {
        this.averagePM25 = averagePM25;
    }

    public BigDecimal getAverageH() {
        return averageH;
    }

    public void setAverageH(BigDecimal averageH) {
        this.averageH = averageH;
    }

    public BigDecimal getAverageCO2() {
        return averageCO2;
    }

    public void setAverageCO2(BigDecimal averageCO2) {
        this.averageCO2 = averageCO2;
    }

    public BigDecimal getAstOpenNum() {
        return astOpenNum;
    }

    public void setAstOpenNum(BigDecimal astOpenNum) {
        this.astOpenNum = astOpenNum;
    }

    public BigDecimal getAtfuOpenNum() {
        return atfuOpenNum;
    }

    public void setAtfuOpenNum(BigDecimal atfuOpenNum) {
        this.atfuOpenNum = atfuOpenNum;
    }

    public BigDecimal getAtfcOpenNum() {
        return atfcOpenNum;
    }

    public void setAtfcOpenNum(BigDecimal atfcOpenNum) {
        this.atfcOpenNum = atfcOpenNum;
    }

    public BigDecimal getHotrunneropenNum() {
        return hotrunneropenNum;
    }

    public void setHotrunneropenNum(BigDecimal hotrunneropenNum) {
        this.hotrunneropenNum = hotrunneropenNum;
    }

    public BigDecimal getTotalelecDay() {
        return totalelecDay;
    }

    public void setTotalelecDay(BigDecimal totalelecDay) {
        this.totalelecDay = totalelecDay;
    }

    public BigDecimal getTotalWaterDay() {
        return totalWaterDay;
    }

    public void setTotalWaterDay(BigDecimal totalWaterDay) {
        this.totalWaterDay = totalWaterDay;
    }

    public BigDecimal getAcOpenNum() {
        return acOpenNum;
    }

    public void setAcOpenNum(BigDecimal acOpenNum) {
        this.acOpenNum = acOpenNum;
    }

    public BigDecimal getElNum() {
        return elNum;
    }

    public void setElNum(BigDecimal elNum) {
        this.elNum = elNum;
    }

    public BigDecimal getVsNum() {
        return vsNum;
    }

    public void setVsNum(BigDecimal vsNum) {
        this.vsNum = vsNum;
    }
}
