package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;

public class ResultMessage {
    @ApiModelProperty(value = "code标示")
    private int code;
    @ApiModelProperty(value = "返回信息")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
