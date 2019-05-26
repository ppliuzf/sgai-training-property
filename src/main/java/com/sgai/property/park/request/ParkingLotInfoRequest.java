package com.sgai.property.park.request;

/**
 * 查询车场信息
 * 
 * @author zhangyalei
 *
 */
public class ParkingLotInfoRequest {

	private String stationNo;// 终端号

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public ParkingLotInfoRequest(String stationNo) {
		this.stationNo = stationNo;
	}
}
