package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * 即时保存 on 2018/6/11.
 */
public class InstantSavParam {
    @ApiModelProperty(value = "Id")
    private String id; //Id
//    @ApiModelProperty(value = "物料id")
//    private String matTypeId; //物料id
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getMatTypeId() {
//        return matTypeId;
//    }
//
//    public void setMatTypeId(String matTypeId) {
//        this.matTypeId = matTypeId;
//    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }
}
