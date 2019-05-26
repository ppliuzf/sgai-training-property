package com.sgai.property.alm.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgai.property.reformer.entity.DayWater;
import com.sgai.property.reformer.entity.HourWaterConsumption;

import java.util.List;

/**
 * @author ppliu
 * created in 2019/1/22 14:18
 */
public class WaterVo {
    /** 最近几小时能耗. */
    private List<HourWaterConsumption> waterHour;
    /** 最近几天能耗. */
    private List<DayWater> waterDay;

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

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }
}
