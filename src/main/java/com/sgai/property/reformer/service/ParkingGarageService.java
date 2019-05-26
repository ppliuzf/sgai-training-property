package com.sgai.property.reformer.service;

import com.sgai.property.alm.vo.Packing;

import java.math.BigDecimal;

/**
 * 停车场.
 *
 * @author ppliu
 * created in 2019/1/15 14:02
 */
public interface ParkingGarageService {
    /**
     * @return 今日总流量.
     */
    BigDecimal totalflowDay();

    /**
     * @return 进场.
     */
    BigDecimal enter();

    /**
     * @return 出场.
     */
    BigDecimal out();

    /**
     * @return 总车位数.
     */
    BigDecimal totalNum();

    /**
     * @return 空闲车位.
     */
    BigDecimal freeNum();

    /**
     * @return 占用车位.
     */
    BigDecimal useNum();

    Packing getData();

}
