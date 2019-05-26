package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2018/1/24.
 */
public class WarehouseAllotImgParam {
    @ApiModelProperty(value = "id")
    private String id ;
    @ApiModelProperty(value = "图片地址")
    private String allotImgUrl; //图片地址
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllotImgUrl() {
        return allotImgUrl;
    }

    public void setAllotImgUrl(String allotImgUrl) {
        this.allotImgUrl = allotImgUrl;
    }

    public String getAllotId() {
        return allotId;
    }

    public void setAllotId(String allotId) {
        this.allotId = allotId;
    }
}
