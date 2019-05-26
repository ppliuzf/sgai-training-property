package com.sgai.property.reformer.service.impl;

import com.sgai.property.alm.vo.Curling;
import com.sgai.property.reformer.service.CurlingHallService;
import com.sgai.property.reformer.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ppliu
 * created in 2019/1/15 13:35
 */
@Service
public class CurlingHallServiceImpl implements CurlingHallService {
    @Autowired
    private RestTemplateService restTemplateService;
    private static final String BASE_URL = "http://10.111.1.193:8001/obix/config/Folder/Pushdata/BH/%s/out/";

    @Override
    public BigDecimal totalelec() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal totalWater() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalWater"));
    }

    @Override
    public BigDecimal averageT() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averageT"));
    }

    @Override
    public BigDecimal averagePM25() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averagePM25"));
    }

    @Override
    public BigDecimal averageH() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averageH"));
    }

    @Override
    public BigDecimal averageCO2() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averageCO2"));
    }

    @Override
    public BigDecimal ASTopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "ASTopenNum"));
    }

    @Override
    public BigDecimal ATFUopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "ATFUopenNum"));
    }

    @Override
    public BigDecimal ATFCopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "ATFCopenNum"));
    }

    @Override
    public BigDecimal HotrunneropenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "HotrunneropenNum"));
    }

    @Override
    public BigDecimal totalelecDay() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelecDay"));
    }

    @Override
    public BigDecimal totalWaterDay() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalWaterDay"));
    }

    @Override
    public BigDecimal ACopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "ACopenNum"));
    }

    @Override
    public BigDecimal ELNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "ELNum"));
    }

    @Override
    public BigDecimal VSNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "VSNum"));
    }

    @Override
    public Curling getData() {
        Curling curling = new Curling();
        curling.setTotalelec(totalelec());
        curling.setTotalWater(totalWater());
        curling.setAverageT(averageT());
        curling.setAveragePM25(averagePM25());
        curling.setAverageH(averageH());
        curling.setAverageCO2(averageCO2());
        curling.setAstOpenNum(ASTopenNum());
        curling.setAtfuOpenNum(ATFUopenNum());
        curling.setAtfcOpenNum(ATFCopenNum());
        curling.setHotrunneropenNum(HotrunneropenNum());
        curling.setTotalelecDay(totalelecDay());
        curling.setTotalWaterDay(totalWaterDay());
        curling.setAcOpenNum(ACopenNum());
        curling.setElNum(ELNum());
        curling.setVsNum(VSNum());
        return curling;
    }
}
