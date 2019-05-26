package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

public class SupplierParams {

    @ApiModelProperty(value = "关键字")
    private String keyWord;//关键字
    @ApiModelProperty(value = "类型ID")
    private String typeId;//类型ID
    @ApiModelProperty(value = "服务内容Id")
    private String contentId;//服务内容Id
    @ApiModelProperty(value = "供应商等级Id")
    private String levelId;

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

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
