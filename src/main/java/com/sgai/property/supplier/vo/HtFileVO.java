package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

public class HtFileVO {
    @ApiModelProperty(value = "附件id")
    private String id;
    @ApiModelProperty(value = "附件url")
    private String fileUrl; //附件url
    @ApiModelProperty(value = "附件名字")
    private String fileName; //附件名字
    @ApiModelProperty(value = "标示 1 合同副本，2 补充协议 ，3 其他附件")
    private Long mark; //标示 1 合同副本，2 补充协议 ，3 其他附件

    public Long getMark() {
        return mark;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}