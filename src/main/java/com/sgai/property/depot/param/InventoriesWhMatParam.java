package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by gaojianqun on 2018/6/7.
 */
public class InventoriesWhMatParam {

    @ApiModelProperty(value = "仓库Id")
    private String whId;

    @ApiModelProperty(value = "仓库名称")
    private String whName;

    @ApiModelProperty(value = "盘点物料实体列表")
    private List<InventoiresMatParamNew> inventoriesMatList;

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

    public List<InventoiresMatParamNew> getInventoriesMatList() {
        return inventoriesMatList;
    }

    public void setInventoriesMatList(List<InventoiresMatParamNew> inventoriesMatList) {
        this.inventoriesMatList = inventoriesMatList;
    }

}
