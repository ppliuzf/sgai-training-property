package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2018/1/25.
 */
public class InventoriesMatVo {
    @ApiModelProperty(value = "id")
    private String id ;
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "差异量")
    private Long matDiffNum; //差异量
    @ApiModelProperty(value = "库存数量")
    private Long matNum; //库存数量
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "盘点id")
    private String ivtId; //盘点id
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "实存数量")
    private Long matRealNum; //实存数量
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Long getMatDiffNum() {
        return matDiffNum;
    }

    public void setMatDiffNum(Long matDiffNum) {
        this.matDiffNum = matDiffNum;
    }

    public Long getMatNum() {
        return matNum;
    }

    public void setMatNum(Long matNum) {
        this.matNum = matNum;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
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

    public String getIvtId() {
        return ivtId;
    }

    public void setIvtId(String ivtId) {
        this.ivtId = ivtId;
    }
}
