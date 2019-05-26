package com.sgai.property.reformer.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 能源周统计.
 *
 * @author ppliu
 * created in 2019/3/25 10:57
 */
public class EnergyWeekSummary {
    /** 能源位置. */
    private String energyPosition;
    /** 上周. */
    private EnergyWeekDetail lasTWeek;
    /** 本周. */
    private EnergyWeekDetail thisWeek;

    public String getEnergyPosition() {
        return energyPosition;
    }

    public void setEnergyPosition(String energyPosition) {
        this.energyPosition = energyPosition;
    }

    public EnergyWeekDetail getLasTWeek() {
        return lasTWeek;
    }

    public void setLasTWeek(EnergyWeekDetail lasTWeek) {
        this.lasTWeek = lasTWeek;
    }

    public EnergyWeekDetail getThisWeek() {
        return thisWeek;
    }

    public void setThisWeek(EnergyWeekDetail thisWeek) {
        this.thisWeek = thisWeek;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
