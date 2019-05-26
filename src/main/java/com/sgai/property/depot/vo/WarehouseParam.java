package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * 仓库管理入参 on 2018/1/23.
 */
public class WarehouseParam {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "备注")
    private String whDesc; //备注
    @ApiModelProperty(value = "仓库地址")
    private String whAddress; //仓库地址
    @ApiModelProperty(value = "关联组织")
    private String whDept; //关联组织
    @ApiModelProperty(value = "仓库经度")
    private String whLatitude; //仓库经度
    @ApiModelProperty(value = "仓库维度")
    private String whLongitude; //仓库维度
    @ApiModelProperty(value = "仓库编号")
    private String whNo; //仓库编号
    public Long getWhType() {
        return whType;
    }

    public void setWhType(Long whType) {
        this.whType = whType;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getWhDesc() {
        return whDesc;
    }

    public void setWhDesc(String whDesc) {
        this.whDesc = whDesc;
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

    public String getWhDept() {
        return whDept;
    }

    public void setWhDept(String whDept) {
        this.whDept = whDept;
    }

    public String getWhLatitude() {
        return whLatitude;
    }

    public void setWhLatitude(String whLatitude) {
        this.whLatitude = whLatitude;
    }

    public String getWhLongitude() {
        return whLongitude;
    }

    public void setWhLongitude(String whLongitude) {
        this.whLongitude = whLongitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhNo() {
        return whNo;
    }

    public void setWhNo(String whNo) {
        this.whNo = whNo;
    }
}
