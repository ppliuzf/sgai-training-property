package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.File;

/**
 * Created by gaojianqun on 2018/2/23.
 */
public class TempletParams {

    @ApiModelProperty(value = "模板id")
    private String id;//模板id
    @ApiModelProperty(value = "模板名称")
    private String name;//模板名称
    @ApiModelProperty(value = "合同类型")
    private String typeId;
    @ApiModelProperty(value = "模板描述")
    private String description;
    @ApiModelProperty(value = "合同模板地址")
    private String url;
    @ApiModelProperty(value = "文件名称")
    private String fileName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
