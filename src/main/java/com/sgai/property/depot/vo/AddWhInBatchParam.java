package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */
public class AddWhInBatchParam {
    @ApiModelProperty(value = "批量入库数据")
    private List<AddWhInParam> whInParamList;

    public List<AddWhInParam> getWhInParamList() {
        return whInParamList;
    }

    public void setWhInParamList(List<AddWhInParam> whInParamList) {
        this.whInParamList = whInParamList;
    }
}
