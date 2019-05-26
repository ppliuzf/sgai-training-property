package com.sgai.property.commonService.vo.organ;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * description:
 * Created by llh on 2017/4/18.
 */
public class OrganRoleVo {
    private Long id;

    private Long parentId;

    private String roleName;

    @ApiModelProperty(value = "depts",hidden = true)
    private List<OrganUnitVo> depts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<OrganUnitVo> getDepts() {
        return depts;
    }

    public void setDepts(List<OrganUnitVo> depts) {
        this.depts = depts;
    }
}
