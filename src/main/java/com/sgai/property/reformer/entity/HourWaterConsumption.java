package com.sgai.property.reformer.entity;

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *小时单位水耗统计.
 *
 * @author ppliu
 * created in 2019/1/22 9:51
 */
@Entity
@Table(name = "record_water_hour")
public class HourWaterConsumption {
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
        this.strRecordTime = DateFormatUtils.format(recordTime, "HH:mm");
    }

    public String getStrRecordTime() {
        return strRecordTime;
    }

    public void setStrRecordTime(String strRecordTime) {
        this.strRecordTime = strRecordTime;
    }
    public void summary(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        this.total = this.puck.add(this.curling).add(this.slip);
        this.recordTime = calendar.getTime();
    }
    public static HourWaterConsumption emptyRecordWithDate(Date date) {
        HourWaterConsumption hour = new HourWaterConsumption();
        hour.setTotal(new BigDecimal(0));
        hour.setSlip(new BigDecimal(0));
        hour.setPuck(new BigDecimal(0));
        hour.setCurling(new BigDecimal(0));
        hour.setRecordTime(date);
        return hour;
    }
}
