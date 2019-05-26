package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 根据物料品类,获取物料列表所使用的Vo
 *
 * @author hou
 * @date 2018-01-05 9:29
 */
public class MaterielInfoVo implements Serializable {

    @ApiModelProperty(value = "物料类型info")
    private List<MaterielTypeInfo> materielTypeInfoList;

    @ApiModelProperty(value = "物料信息list")
    private List<MaterielVo> materielVoList;

    public List<MaterielTypeInfo> getMaterielTypeInfoList() {
        return materielTypeInfoList;
    }

    public MaterielInfoVo setMaterielTypeInfoList(List<MaterielTypeInfo> materielTypeInfoList) {
        this.materielTypeInfoList = materielTypeInfoList;
        return this;
    }

    public List<MaterielVo> getMaterielVoList() {
        return materielVoList;
    }

    public MaterielInfoVo setMaterielVoList(List<MaterielVo> materielVoList) {
        this.materielVoList = materielVoList;
        return this;
    }
}
