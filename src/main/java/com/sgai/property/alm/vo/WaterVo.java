package com.sgai.property.alm.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgai.property.reformer.entity.DayWaterConsumption;
import com.sgai.property.reformer.entity.HourWaterConsumption;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 14:18
 */
public class WaterVo {
    private LocalDateTime localDateTime;
    /** 最近几小时能耗. */
    private List<HourWaterConsumption> waterHour;
    /** 最近几天能耗. */
    private List<DayWaterConsumption> waterDay;

    public WaterVo() {
        this.localDateTime = LocalDateTime.now();
    }

    public List<HourWaterConsumption> getWaterHour() {
        return waterHour;
    }

    public void setWaterHour(List<HourWaterConsumption> waterHour) {
        this.waterHour = waterHour;
    }

    public List<DayWaterConsumption> getWaterDay() {
        return waterDay;
    }

    public void setWaterDay(List<DayWaterConsumption> waterDay) {
        this.waterDay = waterDay;
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
