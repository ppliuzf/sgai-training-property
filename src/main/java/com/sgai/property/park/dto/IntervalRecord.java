package com.sgai.property.park.dto;

import java.math.BigDecimal;

/**
 * 进出场区间数据.
 *
 * @author ppliu
 * created in 2019/1/23 13:00
 */
public class IntervalRecord {
    /** 流量. */
    private BigDecimal flow;
    /** 时段. */
    private String interVal;
    /** 进场流量. */
    private BigDecimal flowIn;
    /** 出场流量. */
    private BigDecimal flowOut;
    /**时间排序*/
    private int timeSorter;

    public BigDecimal getFlow() {
        return flow;
    }

    public void setFlow(BigDecimal flow) {
        this.flow = flow;
    }

    public String getInterVal() {
        return interVal;
    }

    public void setInterVal(String interVal) {
        this.interVal = interVal;
    }

    public BigDecimal getFlowIn() {
        return flowIn;
    }

    public void setFlowIn(BigDecimal flowIn) {
        this.flowIn = flowIn;
    }

    public BigDecimal getFlowOut() {
        return flowOut;
    }

    public void setFlowOut(BigDecimal flowOut) {
        this.flowOut = flowOut;
    }

    public int getTimeSorter() {
        return timeSorter;
    }

    public void setTimeSorter(int timeSorter) {
        this.timeSorter = timeSorter;
    }
}
