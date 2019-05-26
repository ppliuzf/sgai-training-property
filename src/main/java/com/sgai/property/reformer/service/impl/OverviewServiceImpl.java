package com.sgai.property.reformer.service.impl;

import com.sgai.property.alm.vo.Overview;
import com.sgai.property.reformer.service.OverviewService;
import com.sgai.property.reformer.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ppliu
 * created in 2019/1/15 10:58
 */
@Service
public class OverviewServiceImpl implements OverviewService {
    @Autowired
    private RestTemplateService restTemplateService;
    private static final String BASE_URL = "http://10.111.1.193:8001/obix/config/Folder/Pushdata/Overview/%s/out/";

    @Override
    public BigDecimal totalelec() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalelec"));
    }

    @Override
    public BigDecimal totalWater() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "totalWater"));
    }

    @Override
    public BigDecimal entranceNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "entranceNum"));
    }

    @Override
    public BigDecimal parkuseRate() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "parkuseRate"));
    }

    @Override
    public BigDecimal averagePM25() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averagePM25"));
    }

    @Override
    public BigDecimal averageT() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "averageT"));
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
    public BigDecimal charginguseNum() {
        return restTemplateService.getWithUrl(String.format(BASE_URL, "charginguseNum"));
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
    public Overview getData() {
        Overview overview = new Overview();
        overview.setTotalelec(totalelec());
        overview.setTotalWater(totalWater());
        overview.setEntranceNum(entranceNum());
        overview.setParkuseRate(parkuseRate());
        overview.setAveragePM25(averagePM25());
        overview.setAverageT(averageT());
        overview.setAverageH(averageH());
        overview.setAverageCO2(averageCO2());
        overview.setCharginguseNum(charginguseNum());
        overview.setAstOpenNum(ASTopenNum());
        overview.setAtfuOpenNum(ATFUopenNum());
        overview.setAtfcOpenNum(ATFCopenNum());
        overview.setHotrunneropenNum(HotrunneropenNum());
        overview.setAcOpenNum(ACopenNum());
        overview.setElNum(ELNum());
        overview.setVsNum(VSNum());
        return overview;
    }

}
