package com.sgai.property.depot.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 库存管理 on 2018/2/5.
 */
public class InventoryManageVo extends BoEntity {
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库编号")
    private String whNo; //仓库编号
    @ApiModelProperty(value = "仓库纬度")
    private String whLongitude; //仓库纬度
    @ApiModelProperty(value = "仓库地址")
    private String whAddress; //仓库地址
    @ApiModelProperty(value = "仓库经度")
    private String whLatitude; //仓库经度
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "库存数量")
    private Long matNum; //库存数量
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id

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

    public String getWhNo() {
        return whNo;
    }

    public void setWhNo(String whNo) {
        this.whNo = whNo;
    }

    public String getWhLongitude() {
        return whLongitude;
    }

    public void setWhLongitude(String whLongitude) {
        this.whLongitude = whLongitude;
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress;
    }

    public String getWhLatitude() {
        return whLatitude;
    }

    public void setWhLatitude(String whLatitude) {
        this.whLatitude = whLatitude;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Long getMatNum() {
        return matNum;
    }

    public void setMatNum(Long matNum) {
        this.matNum = matNum;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    public String getMatTypeId() {
        return matTypeId;
    }

    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }
}
