package com.sgai.property.park.service;

import com.sgai.property.park.request.ParkingLotInfoRequest;
import com.sgai.property.park.response.ParkingLotInfoResponse;

/**
 * @author ppliu
 * created in 2019/1/18 10:40
 */
public interface ParkSpaceService {
    /**
     * 查询车场信息接口
     */
    ParkingLotInfoResponse getParkingLotInfo(ParkingLotInfoRequest parkingLotInfoRequest);
}
