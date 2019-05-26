package com.sgai.property.purchase.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/3/1.
 */
public class ConReceParam {
    @ApiModelProperty(value = "id")
    private String id; //id
    @ApiModelProperty(value = "收货人id")
    private String takeCargoId; //收货人id
    @ApiModelProperty(value = "收货人姓名")
    private String takeCargoName; //收货人姓名
    @ApiModelProperty(value = "仓库名称")
    private String warName; //
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarName() {
        return warName;
    }

    public void setWarName(String warName) {
        this.warName = warName;
    }

    public String getTakeCargoId() {
        return takeCargoId;
    }

    public void setTakeCargoId(String takeCargoId) {
        this.takeCargoId = takeCargoId;
    }

    public String getTakeCargoName() {
        return takeCargoName;
    }

    public void setTakeCargoName(String takeCargoName) {
        this.takeCargoName = takeCargoName;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }
}
