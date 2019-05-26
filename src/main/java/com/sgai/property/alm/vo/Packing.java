package com.sgai.property.alm.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

/**
 * 停车库.
 *
 * @author ppliu
 * created in 2019/1/18 13:33
 */
public class Packing {
    /** 今日总流量. */
    private BigDecimal totalflowDay;
    /** 进场. */
    private BigDecimal enter;
    /** 出场. */
    private BigDecimal out;
    /** 总车位数. */
    private BigDecimal totalNum;
    /** 空闲车位. */
    private BigDecimal freeNum;
    /** 占用车位. */
    private BigDecimal useNum;

    public BigDecimal getTotalflowDay() {
        return totalflowDay;
    }

    public void setTotalflowDay(BigDecimal totalflowDay) {
        this.totalflowDay = totalflowDay;
    }

    public BigDecimal getEnter() {
        return enter;
    }

    public void setEnter(BigDecimal enter) {
        this.enter = enter;
    }

    public BigDecimal getOut() {
        return out;
    }

    public void setOut(BigDecimal out) {
        this.out = out;
    }

    public BigDecimal getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(BigDecimal freeNum) {
        this.freeNum = freeNum;
    }

    public BigDecimal getUseNum() {
        return useNum;
    }

    public void setUseNum(BigDecimal useNum) {
        this.useNum = useNum;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }

    public static Packing exception() {
        Packing packing = new Packing();
        BigDecimal data = BigDecimal.ZERO;
        packing.setTotalflowDay(data);
        packing.setEnter(data);
        packing.setOut(data);
        packing.setTotalNum(data);
        packing.setFreeNum(data);
        packing.setUseNum(data);
        return packing;
    }
}
