package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gaojianqun on 2018/6/7.
 */
public class InventoriesWhMatVo {

    @ApiModelProperty(value = "仓库Id")
    private String whId;

    @ApiModelProperty(value = "仓库名称")
    private String whName;

    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType;

    @ApiModelProperty(value = "盘点物料实体列表")
    private List<InventoiresMatVoNew1> inventoriesMatList;

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }


    public List<InventoiresMatVoNew1> getInventoriesMatList() {
        return inventoriesMatList;
    }

    public void setInventoriesMatList(List<InventoiresMatVoNew1> inventoriesMatList) {
        this.inventoriesMatList = inventoriesMatList;
    }

    public Long getWhType() {
        return whType;
    }

    public void setWhType(Long whType) {
        this.whType = whType;
    }
}
