package com.sgai.property.alm.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.entity.HourEnergyConsumption;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 14:18
 */
public class EnergyVo {
    private LocalDateTime localDateTime;
    /** 最近几小时能耗. */
    private List<HourEnergyConsumption> energyHour;
    /** 最近几天能耗. */
    private List<DayEnergyConsumption> energyDay;

    public EnergyVo() {
        this.localDateTime = LocalDateTime.now();
    }

    public List<HourEnergyConsumption> getEnergyHour() {
        return energyHour;
    }

    public void setEnergyHour(List<HourEnergyConsumption> energyHour) {
        this.energyHour = energyHour;
    }

    public List<DayEnergyConsumption> getEnergyDay() {
        return energyDay;
    }

    public void setEnergyDay(List<DayEnergyConsumption> energyDay) {
        this.energyDay = energyDay;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }

}
