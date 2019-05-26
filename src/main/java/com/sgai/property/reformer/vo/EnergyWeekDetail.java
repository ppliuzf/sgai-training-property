package com.sgai.property.reformer.vo;

import java.math.BigDecimal;

/**
 * 周能源明细,默认值为0.
 *
 * @author ppliu
 * created in 2019/3/25 11:10
 */
public class EnergyWeekDetail {
    /** 周一. */
    private BigDecimal monday = new BigDecimal(0);
    /** 周二. */
    private BigDecimal tuesday = new BigDecimal(0);
    /** 周三. */
    private BigDecimal wednesday = new BigDecimal(0);
    /** 周四. */
    private BigDecimal thursday = new BigDecimal(0);
    /** 周五. */
    private BigDecimal friday = new BigDecimal(0);
    /** 周六. */
    private BigDecimal saturday = new BigDecimal(0);
    /** 周日. */
    private BigDecimal sunday = new BigDecimal(0);

    public BigDecimal getMonday() {
        return monday;
    }

    public void setMonday(BigDecimal monday) {
        this.monday = monday;
    }

    public BigDecimal getTuesday() {
        return tuesday;
    }

    public void setTuesday(BigDecimal tuesday) {
        this.tuesday = tuesday;
    }

    public BigDecimal getWednesday() {
        return wednesday;
    }

    public void setWednesday(BigDecimal wednesday) {
        this.wednesday = wednesday;
    }

    public BigDecimal getThursday() {
        return thursday;
    }

    public void setThursday(BigDecimal thursday) {
        this.thursday = thursday;
    }

    public BigDecimal getFriday() {
        return friday;
    }

    public void setFriday(BigDecimal friday) {
        this.friday = friday;
    }

    public BigDecimal getSaturday() {
        return saturday;
    }

    public void setSaturday(BigDecimal saturday) {
        this.saturday = saturday;
    }

    public BigDecimal getSunday() {
        return sunday;
    }

    public void setSunday(BigDecimal sunday) {
        this.sunday = sunday;
    }
}
