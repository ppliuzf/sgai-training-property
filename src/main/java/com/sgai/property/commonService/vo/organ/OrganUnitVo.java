package com.sgai.property.commonService.vo.organ;

import io.swagger.annotations.ApiModelProperty;

/**
 * description:
 * Created by llh on 2017/4/17.
 */
public class OrganUnitVo {
    @ApiModelProperty(value = "部门ID")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String organUnitName;

    @ApiModelProperty(value = "父级部门")
    private Long parentId;

    @ApiModelProperty(value = "排序序号")
    private Integer ordinal;

    @ApiModelProperty(value = "岗位关系")
    private String relationType ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganUnitName() {
        return organUnitName;
    }

    public void setOrganUnitName(String organUnitName) {
        this.organUnitName = organUnitName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

}
