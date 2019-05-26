package com.sgai.property.reformer.service.impl;

import com.sgai.property.alm.vo.Slip;
import com.sgai.property.reformer.service.RestTemplateService;
import com.sgai.property.reformer.service.SpeedSkatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ppliu
 * created in 2019/1/15 13:46
 */
@Service
public class SpeedSkatingServiceImpl implements SpeedSkatingService {
    @Autowired
    private RestTemplateService restTemplateService;
    private static final String BASE_URL = "http://10.111.1.193:8001/obix/config/Folder/Pushdata/HHSH/%s/out/";

    @Override
    public BigDecimal totalelec() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal totalWater() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal averageT() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal averagePM25() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal averageH() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal averageCO2() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal ASTopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal ATFUopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal ATFCopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal HotrunneropenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal totalelecDay() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal totalWaterDay() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal ACopenNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal ELNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal VSNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public Slip getData() {
        Slip slip = new Slip();
        slip.setTotalelec(totalelec());
        slip.setTotalWater(totalWater());
        slip.setAverageT(averageT());
        slip.setAveragePM25(averagePM25());
        slip.setAverageH(averageH());
        slip.setAverageCO2(averageCO2());
        slip.setAstOpenNum(ASTopenNum());
        slip.setAtfuOpenNum(ATFUopenNum());
        slip.setAtfcOpenNum(ATFCopenNum());
        slip.setHotrunneropenNum(HotrunneropenNum());
        slip.setTotalelecDay(totalelecDay());
        slip.setTotalWaterDay(totalWaterDay());
        slip.setAcOpenNum(ACopenNum());
        slip.setElNum(ELNum());
        slip.setVsNum(VSNum());
        return slip;
    }
}
