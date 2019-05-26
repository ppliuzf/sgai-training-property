package com.sgai.property.commonService.vo.organ;

import java.util.List;

/**
 * description:人跟部门的关系
 * Created by llh on 2017/4/18.
 */
public class OrganUnitAndUserVo {

    //归属组织信息，应一个人有多个岗位，每个岗位对应一个部门
    private List<OrganUnitVo> organVoList;

    //用户信息
    private OrganUserVo organUserVo;

    //岗位信息
    private OrganRoleVo organRoleVo;

    public List<OrganUnitVo> getOrganVoList() {
        return organVoList;
    }

    public void setOrganVoList(List<OrganUnitVo> organVoList) {
        this.organVoList = organVoList;
    }

    public OrganUserVo getOrganUserVo() {
        return organUserVo;
    }

    public void setOrganUserVo(OrganUserVo organUserVo) {
        this.organUserVo = organUserVo;
    }

    public OrganRoleVo getOrganRoleVo() {
        return organRoleVo;
    }

    public void setOrganRoleVo(OrganRoleVo organRoleVo) {
        this.organRoleVo = organRoleVo;
    }
}
