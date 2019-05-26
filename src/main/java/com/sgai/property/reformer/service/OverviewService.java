package com.sgai.property.reformer.service;

import com.sgai.property.alm.vo.Overview;

import java.math.BigDecimal;

/**
 * 总览.
 *
 * @author ppliu
 * created in 2019/1/15 10:48
 */
public interface OverviewService {
    /**
     * @return 用电总能耗.
     */
    BigDecimal totalelec();

    /**
     * @return 用水总能耗.
     */
    BigDecimal totalWater();

    /**
     * @return 累计入场人次.
     */
    BigDecimal entranceNum();

    /**
     * @return 停车场占有率.
     */
    BigDecimal parkuseRate();

    /**
     * @return 平均PM2.5.
     */
    BigDecimal averagePM25();

    /**
     * @return 室内平均温度.
     */
    BigDecimal averageT();

    /**
     * @return 室内平均湿度.
     */
    BigDecimal averageH();

    /**
     * @return 室内平均二氧化碳浓度.
     */
    BigDecimal averageCO2();

    /**
     * @return 充电桩使用数.
     */
    BigDecimal charginguseNum();

    /**
     * @return 制冰机开启量.
     */
    BigDecimal ASTopenNum();

    /**
     * @return 新风机组开启量.
     */
    BigDecimal ATFUopenNum();

    /**
     * @return 风机盘管开启量.
     */
    BigDecimal ATFCopenNum();

    /**
     * @return 热转轮机组开启量.
     */
    BigDecimal HotrunneropenNum();

    /**
     * @return 空调机组开启量.
     */
    BigDecimal ACopenNum();

    /**
     * @return 电梯的开启数/总数.
     */
    BigDecimal ELNum();

    /**
     * @return 摄像头的在线/离线数量.
     */
    BigDecimal VSNum();

    Overview getData();
}
