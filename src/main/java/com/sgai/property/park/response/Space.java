package com.sgai.property.park.response;

import java.util.Date;

public class Space {
    private int status;
    private String carCode;
    private String parkingSpaceNo;
    private Date enterTime;
    private String areaName;
    private String areaId;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getParkingSpaceNo() {
        return parkingSpaceNo;
    }

    public void setParkingSpaceNo(String parkingSpaceNo) {
        this.parkingSpaceNo = parkingSpaceNo;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }
}
