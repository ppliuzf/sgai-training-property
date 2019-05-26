package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

public class HtImageVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "图片A")
    private String urlA; //图片A
    @ApiModelProperty(value = "URL_C")
    private String urlB; //URL_C
    @ApiModelProperty(value = "")
    private String urlC; //
    @ApiModelProperty(value = "合同编码")
    private String contractNo; //合同编码


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlA() {
        return urlA;
    }

    public void setUrlA(String urlA) {
        this.urlA = urlA;
    }

    public String getUrlB() {
        return urlB;
    }

    public void setUrlB(String urlB) {
        this.urlB = urlB;
    }

    public String getUrlC() {
        return urlC;
    }

    public void setUrlC(String urlC) {
        this.urlC = urlC;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

}