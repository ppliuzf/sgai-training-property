package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 物料类型vo
 *
 * @author hou
 * @date 2018-01-05 9:40
 */
public class MaterielInfoDto implements Serializable {

    @ApiModelProperty(value = "物料名称")
    private String matName;
    @ApiModelProperty(value = "物料类型code")
    private String matTypeCode;

    public String getMatName() {
        return matName;
    }

    public MaterielInfoDto setMatName(String matName) {
        this.matName = matName;
        return this;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public MaterielInfoDto setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
        return this;
    }
}
