package com.sgai.property.reformer.entity;

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 天级单位能源统计.
 *
 * @author ppliu
 * created in 2019/1/22 9:51
 */
@Entity
@Table(name = "record_energy_day")
public class DayEnergyConsumption {
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
    private Date recordTime;
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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
        this.strRecordTime = DateFormatUtils.format(recordTime, "yyyy/MM/dd");
    }

    public String getStrRecordTime() {
        return strRecordTime;
    }

    public void setStrRecordTime(String strRecordTime) {
        this.strRecordTime = strRecordTime;
    }

    public static DayEnergyConsumption emptyRecord() {
        DayEnergyConsumption dayEnergyConsumption = new DayEnergyConsumption();
        dayEnergyConsumption.setTotal(new BigDecimal(0));
        dayEnergyConsumption.setSlip(new BigDecimal(0));
        dayEnergyConsumption.setPuck(new BigDecimal(0));
        dayEnergyConsumption.setCurling(new BigDecimal(0));
        return dayEnergyConsumption;
    }
    public static DayEnergyConsumption emptyRecordWithDate(Date date) {
        DayEnergyConsumption dayEnergyConsumption = new DayEnergyConsumption();
        dayEnergyConsumption.setTotal(new BigDecimal(0));
        dayEnergyConsumption.setSlip(new BigDecimal(0));
        dayEnergyConsumption.setPuck(new BigDecimal(0));
        dayEnergyConsumption.setCurling(new BigDecimal(0));
        dayEnergyConsumption.setRecordTime(date);
        return dayEnergyConsumption;
    }
}
