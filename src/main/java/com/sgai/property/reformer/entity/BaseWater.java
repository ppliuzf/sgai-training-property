package com.sgai.property.reformer.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *水耗基础数据.
 * @author ppliu
 * created in 2019/1/21 17:52
 */
@Entity
@Table(name = "record_water_base")
public class BaseWater {
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
    }

}
