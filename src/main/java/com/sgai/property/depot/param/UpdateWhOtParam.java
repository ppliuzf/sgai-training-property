package com.sgai.property.depot.param;

import com.sgai.property.depot.vo.SuppliesDetail;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 修改出库单 on 2018/6/10.
 */
public class UpdateWhOtParam {
    @ApiModelProperty(value = "id/")
    private String id;
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "出库单号")
    private String whOutId;
    @ApiModelProperty(value = "物料明细/仅需物料Id 和实际数量")
    private List<SuppliesDetail> suppliesDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SuppliesDetail> getSuppliesDetails() {
        return suppliesDetails;
    }

    public void setSuppliesDetails(List<SuppliesDetail> suppliesDetails) {
        this.suppliesDetails = suppliesDetails;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getWhOutId() {
        return whOutId;
    }

    public void setWhOutId(String whOutId) {
        this.whOutId = whOutId;
    }
}
