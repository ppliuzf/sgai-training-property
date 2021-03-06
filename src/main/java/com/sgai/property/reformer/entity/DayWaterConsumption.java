package com.sgai.property.reformer.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 天级单位水耗统计.
 *
 * @author ppliu
 * created in 2019/1/22 9:51
 */
@Entity
@Table(name = "record_water_day")
public class DayWaterConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 总览. */
    private BigDecimal total;
    /** 冰球馆. */
    private BigDecimal puck;
    /** 冰壶馆. */
    private BigDecimal curling;
    /** 花滑/速滑. */
    private BigDecimal slip;
    /** 记录时间. */
    private LocalDate recordTime;
    @Transient
    private String strRecordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPuck() {
        return puck;
    }

    public void setPuck(BigDecimal puck) {
        this.puck = puck;
    }

    public BigDecimal getCurling() {
        return curling;
    }

    public void setCurling(BigDecimal curling) {
        this.curling = curling;
    }

    public BigDecimal getSlip() {
        return slip;
    }

    public void setSlip(BigDecimal slip) {
        this.slip = slip;
    }

    public LocalDate getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDate recordTime) {
        this.recordTime = recordTime;
        this.strRecordTime = recordTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public String getStrRecordTime() {
        return strRecordTime;
    }

    public void setStrRecordTime(String strRecordTime) {
        this.strRecordTime = strRecordTime;
    }
}
