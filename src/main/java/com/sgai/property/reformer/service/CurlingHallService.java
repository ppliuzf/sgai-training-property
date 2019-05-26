package com.sgai.property.reformer.service;

import com.sgai.property.alm.vo.Curling;

import java.math.BigDecimal;

/**
 * 冰壶馆数据.
 *
 * @author ppliu
 * created in 2019/1/15 13:29
 */
public interface CurlingHallService {

    /**
     * @return 总能耗（电）.
     */
    BigDecimal totalelec();

    /**
     * @return 总能耗（水）.
     */
    BigDecimal totalWater();

    /**
     * @return 平均温度.
     */
    BigDecimal averageT();

    /**
     * @return 平均PM2.5.
     */
    BigDecimal averagePM25();

    /**
     * @return 室内平均湿度.
     */
    BigDecimal averageH();

    /**
     * @return 室内平均二氧化碳浓度.
     */
    BigDecimal averageCO2();

    /**
     * @return 制冰机开启量.
     */
    BigDecimal ASTopenNum();

    /**
     * @return 新风机组开启量.
     */
    BigDecimal ATFUopenNum();

    /**
     * @return 风机盘管.
     */
    BigDecimal ATFCopenNum();

    /**
     * @return 热转轮机组.
     */
    BigDecimal HotrunneropenNum();

    /**
     * @return 当日累计能耗（电）.
     */
    BigDecimal totalelecDay();

    /**
     * @return 当日累计能耗（水）.
     */
    BigDecimal totalWaterDay();

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

    Curling getData();

}
