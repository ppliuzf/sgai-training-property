package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/24.
 */
public class SuppliesDetail {
    @ApiModelProperty(value = "Id必传")
    private String id; //id
    @ApiModelProperty(value = "所需数量")
    private Long matNeetNum; //所需数量
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id

    public Long getMatNeetNum() {
        return matNeetNum;
    }

    public void setMatNeetNum(Long matNeetNum) {
        this.matNeetNum = matNeetNum;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
