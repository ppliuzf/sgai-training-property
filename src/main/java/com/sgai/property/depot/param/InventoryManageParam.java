package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * 库存管理 on 2018/2/5.
 */
public class InventoryManageParam {
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }
}
