package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;

public class ContractSearchParams {
    @ApiModelProperty(value = "关键字")
    private String keyWord;//关键字
    @ApiModelProperty(value = "类型ID")
    private String typeId;
    @ApiModelProperty(value = "状态")
    private Long statusId;


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
