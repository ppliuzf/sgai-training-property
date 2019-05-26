package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

public class ResultMessage {
    @ApiModelProperty(value = "标示")
    private String code;
    @ApiModelProperty(value = "返回信息")
    private String message;

    private String url;//附件地址

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
