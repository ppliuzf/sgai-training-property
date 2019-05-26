package com.sgai.property.energy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 描述:小时级别点能耗.
 *
 * @author ppliu
 * created in 2019/4/17 19:14
 */
@Table(name = "md_energy_electric_hour")
@Entity
public class HourElectric {
    /** 主键. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 电表编码. */
    private String meterCode;
    /** 记录日期. */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
    /** 记录值. */
    private BigDecimal recordValue;
    @Transient
    private String timeString;

    public static int timeConvert(LocalDateTime recordTime){
        return recordTime.getHour();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public LocalDateTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(LocalDateTime recordTime) {
        this.recordTime = recordTime;
    }

    public BigDecimal getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(BigDecimal recordValue) {
        this.recordValue = recordValue;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
