package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

public class ContractVO {


    @ApiModelProperty(value = "主键ID")
    private String id; //合同主键ID
    @ApiModelProperty(value = "合同编号")
    private String no; //合同编号
    @ApiModelProperty(value = "合同名称")
    private String name; //合同名称
    @ApiModelProperty(value = "合同创建人")
    private String creater; //合同创建人
    @ApiModelProperty(value = "合同创建时间")
    private String creatTime; //合同创建时间
    @ApiModelProperty(value = "合同类型名称")
    private String typeName; //合同类型名称
    @ApiModelProperty(value = "合同状态 : 1 未签约  2 已签约")
    private Long status; //合同状态 : 1 未签约  2 已签约
    @ApiModelProperty(value = "供应商IDS")
    private String supplierIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }
}