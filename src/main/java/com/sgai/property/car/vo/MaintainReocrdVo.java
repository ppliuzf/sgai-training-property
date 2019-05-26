package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

public class MaintainReocrdVo {
    @ApiModelProperty(value = "保养名称")
    private String mrName;
    @ApiModelProperty(value = "保养类型")
    private String mrType;
    @ApiModelProperty(value = "保养日期")
    private Long mrDate;
    @ApiModelProperty(value = "保养单位")
    private String mrUnit;
    @ApiModelProperty(value = "保养人")
    private String mrPerson;
    @ApiModelProperty(value = "备注")
    private String mrReMark;

    public String getMrName() {
        return mrName;
    }

    public void setMrName(String mrName) {
        this.mrName = mrName;
    }

    public String getMrType() {
        return mrType;
    }

    public void setMrType(String mrType) {
        this.mrType = mrType;
    }

    public Long getMrDate() {
        return mrDate;
    }

    public void setMrDate(Long mrDate) {
        this.mrDate = mrDate;
    }

    public String getMrUnit() {
        return mrUnit;
    }

    public void setMrUnit(String mrUnit) {
        this.mrUnit = mrUnit;
    }

    public String getMrPerson() {
        return mrPerson;
    }

    public void setMrPerson(String mrPerson) {
        this.mrPerson = mrPerson;
    }

    public String getMrReMark() {
        return mrReMark;
    }

    public void setMrReMark(String mrReMark) {
        this.mrReMark = mrReMark;
    }
}
