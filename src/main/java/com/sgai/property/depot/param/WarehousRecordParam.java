package com.sgai.property.depot.param;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class WarehousRecordParam{


    @ApiModelProperty(value = "id")
    private String id; //id

    @ApiModelProperty(value = "实际数量入库数量")
    private Long matRealNum; //实际数量入库数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }
}