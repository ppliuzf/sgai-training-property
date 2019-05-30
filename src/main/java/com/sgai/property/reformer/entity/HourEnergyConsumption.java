package com.sgai.property.reformer.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 小时级的能源数据统计.
 *
 * @author ppliu
 * created in 2019/1/22 9:49
 */
@Entity
@Table(name = "record_energy_hour")
public class HourEnergyConsumption {
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
    private LocalDateTime recordTime;
    /** 冰球馆给排水. */
    private BigDecimal puckDrainage;
    /** 冰球馆照明. */
    private BigDecimal puckIllumination;
    /** 冰球馆电梯 */
    private BigDecimal puckElevator;
    /** 冰壶馆给排水. */
    private BigDecimal curlingDrainage;
    /** 冰壶馆照明. */
    private BigDecimal curlingIllumination;
    /** 冰壶馆电梯 */
    private BigDecimal curlingElevator;
    /** 花滑/速滑给排水. */
    private BigDecimal slipDrainage;
    /** 花滑/速滑照明. */
    private BigDecimal slipIllumination;
    /** 花滑/速滑电梯 */
    private BigDecimal slipElevator;
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

    public String getStrRecordTime() {
        return strRecordTime;
    }

    public void setStrRecordTime(String strRecordTime) {
        this.strRecordTime = strRecordTime;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
        this.strRecordTime = recordTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public BigDecimal getPuckDrainage() {
        return puckDrainage;
    }

    public void setPuckDrainage(BigDecimal puckDrainage) {
        this.puckDrainage = puckDrainage;
    }

    public BigDecimal getPuckIllumination() {
        return puckIllumination;
    }

    public void setPuckIllumination(BigDecimal puckIllumination) {
        this.puckIllumination = puckIllumination;
    }

    public BigDecimal getPuckElevator() {
        return puckElevator;
    }

    public void setPuckElevator(BigDecimal puckElevator) {
        this.puckElevator = puckElevator;
    }

    public BigDecimal getCurlingDrainage() {
        return curlingDrainage;
    }

    public void setCurlingDrainage(BigDecimal curlingDrainage) {
        this.curlingDrainage = curlingDrainage;
    }

    public BigDecimal getCurlingIllumination() {
        return curlingIllumination;
    }

    public void setCurlingIllumination(BigDecimal curlingIllumination) {
        this.curlingIllumination = curlingIllumination;
    }

    public BigDecimal getCurlingElevator() {
        return curlingElevator;
    }

    public void setCurlingElevator(BigDecimal curlingElevator) {
        this.curlingElevator = curlingElevator;
    }

    public BigDecimal getSlipDrainage() {
        return slipDrainage;
    }

    public void setSlipDrainage(BigDecimal slipDrainage) {
        this.slipDrainage = slipDrainage;
    }

    public BigDecimal getSlipIllumination() {
        return slipIllumination;
    }

    public void setSlipIllumination(BigDecimal slipIllumination) {
        this.slipIllumination = slipIllumination;
    }

    public BigDecimal getSlipElevator() {
        return slipElevator;
    }

    public void setSlipElevator(BigDecimal slipElevator) {
        this.slipElevator = slipElevator;
    }
}
