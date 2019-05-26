package com.sgai.property.depot.vo;

import com.sgai.common.persistence.Page;
import com.sgai.property.depot.entity.WarehouseOutMat;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */
public class WarehouseOutMatVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "用料申请单id")
    private String matApplyId; //用料申请单id
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "用料申请单名称")
    private String matApplyName; //用料申请单名称
    @ApiModelProperty(value = "出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库")
    private Long whOutType; //出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库
    @ApiModelProperty(value = "出库单号")
    private String whOutNo; //出库单号
    @ApiModelProperty(value = "出库状态?1:未出库;2:部分出库;3:全部出库")
    private Long whStat; //出库状态?1:未出库;2:部分出库;3:全部出库
    @ApiModelProperty(value = "调拨单名称")
    private String allotName; //调拨单名称
    @ApiModelProperty(value = "物料明细")
    private Page<OutDetilVo> warehouseOutMats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public Long getWhType() {
        return whType;
    }

    public void setWhType(Long whType) {
        this.whType = whType;
    }

    public String getMatApplyId() {
        return matApplyId;
    }

    public void setMatApplyId(String matApplyId) {
        this.matApplyId = matApplyId;
    }

    public String getAllotId() {
        return allotId;
    }

    public void setAllotId(String allotId) {
        this.allotId = allotId;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getMatApplyName() {
        return matApplyName;
    }

    public void setMatApplyName(String matApplyName) {
        this.matApplyName = matApplyName;
    }

    public Long getWhOutType() {
        return whOutType;
    }

    public void setWhOutType(Long whOutType) {
        this.whOutType = whOutType;
    }

    public String getWhOutNo() {
        return whOutNo;
    }

    public void setWhOutNo(String whOutNo) {
        this.whOutNo = whOutNo;
    }

    public Long getWhStat() {
        return whStat;
    }

    public void setWhStat(Long whStat) {
        this.whStat = whStat;
    }

    public String getAllotName() {
        return allotName;
    }

    public void setAllotName(String allotName) {
        this.allotName = allotName;
    }

    public Page<OutDetilVo> getWarehouseOutMats() {
        return warehouseOutMats;
    }

    public void setWarehouseOutMats(Page<OutDetilVo> warehouseOutMats) {
        this.warehouseOutMats = warehouseOutMats;
    }
}
