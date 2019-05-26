package com.sgai.property.park.service.impl;

import com.sgai.property.park.request.ParkingLotInfoRequest;
import com.sgai.property.park.response.ParkingLotInfoResponse;
import com.sgai.property.park.service.ParkSpaceService;
import com.sgai.property.reformer.service.RestTemplateService;
import com.sgai.property.reformer.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ppliu
 * created in 2019/1/18 10:40
 */
@Service
public class ParkSpaceServiceImpl implements ParkSpaceService {

    private static final String URL = "http://10.111.6.100:9988/Parking/Handheld/";
    @Autowired
    private RestTemplateService restTemplateService;

    @Override
    public ParkingLotInfoResponse getParkingLotInfo(ParkingLotInfoRequest parkingLotInfoRequest) {
        ParkingLotInfoResponse response;
        String url = URL + "GetParkingLotInfo";
        String psdStr = JsonUtil.beanFormatToJson(parkingLotInfoRequest);
        String dateSoure = restTemplateService.strPost(psdStr, url);
        response = JsonUtil.jsonFormatToBean(dateSoure, ParkingLotInfoResponse.class);
        return response;
    }
}
