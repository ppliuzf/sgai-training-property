package com.sgai.property.alm.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgai.property.alm.entity.AlmDockingData;
import com.sgai.property.park.dto.IntervalRecord;
import com.sgai.property.park.entity.InOutRecord;
import com.sgai.property.reformer.entity.*;
import com.sgai.property.reformer.xmlBean.IceMaker;

import java.util.List;

/**
 * 报警数据.
 *
 * @author ppliu
 * created in 2019/1/18 13:27
 */
public class ScreenVo {
    /** 数据类型 【1报警】【2监测】 */
    private int type;
    /** 总览. */
    private Overview overview;
    /** 冰壶馆. */
    private Curling curling;
    /** 冰球馆. */
    private Puck puck;
    /** 花滑/速滑. */
    private Slip slip;
    /** 停车. */
    private Packing packing;
    /** 报警数据. */
    private List<AlmDockingData> almDockingData;
    /** 进出场记录. */
    private InOutRecord inOutRecord;
    /** 最近几小时能耗. */
    private List<HourEnergyConsumption> energyHour;
    /** 最近几天能耗. */
    private List<DayEnergyConsumption> energyDay;
    /** 流量按时间排序 */
    private List<IntervalRecord> intervalOrderTime;
    /** 流量按流量倒序 */
    private List<IntervalRecord> intervalOrderFlow;
    /** 最近几小时水耗. */
    private List<HourWaterConsumption> waterHour;
    /** 最近几天水耗. */
    private List<DayWater> waterDay;
    private DayEnergyConsumption energyToday;
    private DayEnergyConsumption energyYesterday;
    private DayWater waterToday;
    private DayWater waterYesterday;
    private List<IceMaker> iceMakerList;

    public Overview getOverview() {
        return overview;
    }

    public void setOverview(Overview overview) {
        this.overview = overview;
    }

    public Curling getCurling() {
        return curling;
    }

    public void setCurling(Curling curling) {
        this.curling = curling;
    }

    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
    }

    public Slip getSlip() {
        return slip;
    }

    public void setSlip(Slip slip) {
        this.slip = slip;
    }

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public InOutRecord getInOutRecord() {
        return inOutRecord;
    }

    public void setInOutRecord(InOutRecord inOutRecord) {
        this.inOutRecord = inOutRecord;
    }

    public List<HourEnergyConsumption> getEnergyHour() {
        return energyHour;
    }

    public void setEnergyHour(List<HourEnergyConsumption> energyHour) {
        this.energyHour = energyHour;
    }

    public List<AlmDockingData> getAlmDockingData() {
        return almDockingData;
    }

    public void setAlmDockingData(List<AlmDockingData> almDockingData) {
        this.almDockingData = almDockingData;
    }

    public List<DayEnergyConsumption> getEnergyDay() {
        return energyDay;
    }

    public void setEnergyDay(List<DayEnergyConsumption> energyDay) {
        this.energyDay = energyDay;
    }

    public List<IntervalRecord> getIntervalOrderTime() {
        return intervalOrderTime;
    }

    public void setIntervalOrderTime(List<IntervalRecord> intervalOrderTime) {
        this.intervalOrderTime = intervalOrderTime;
    }

    public List<IntervalRecord> getIntervalOrderFlow() {
        return intervalOrderFlow;
    }

    public void setIntervalOrderFlow(List<IntervalRecord> intervalOrderFlow) {
        this.intervalOrderFlow = intervalOrderFlow;
    }

    public List<HourWaterConsumption> getWaterHour() {
        return waterHour;
    }

    public void setWaterHour(List<HourWaterConsumption> waterHour) {
        this.waterHour = waterHour;
    }

    public List<DayWater> getWaterDay() {
        return waterDay;
    }

    public void setWaterDay(List<DayWater> waterDay) {
        this.waterDay = waterDay;
    }

    public DayEnergyConsumption getEnergyToday() {
        return energyToday;
    }

    public void setEnergyToday(DayEnergyConsumption energyToday) {
        this.energyToday = energyToday;
    }

    public DayEnergyConsumption getEnergyYesterday() {
        return energyYesterday;
    }

    public void setEnergyYesterday(DayEnergyConsumption energyYesterday) {
        this.energyYesterday = energyYesterday;
    }

    public DayWater getWaterToday() {
        return waterToday;
    }

    public void setWaterToday(DayWater waterToday) {
        this.waterToday = waterToday;
    }

    public DayWater getWaterYesterday() {
        return waterYesterday;
    }

    public void setWaterYesterday(DayWater waterYesterday) {
        this.waterYesterday = waterYesterday;
    }

    public List<IceMaker> getIceMakerList() {
        return iceMakerList;
    }

    public void setIceMakerList(List<IceMaker> iceMakerList) {
        this.iceMakerList = iceMakerList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }
}
