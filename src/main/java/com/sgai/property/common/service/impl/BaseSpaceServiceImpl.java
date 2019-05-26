package com.sgai.property.common.service.impl;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseSpaceService;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmSpaceTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基础空间数据.
 *
 * @author ppliu
 * created in 2018/12/21 14:02
 */
@Service
public class BaseSpaceServiceImpl implements BaseSpaceService {
    @Autowired
    private MdmSpaceTreeService mdmSpaceTreeService;
    @Override
    public List<SpaceDto> registAndCallBack() {
        LoginUser user =UserServletContext.getUserInfo();
        MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
        mdmSpaceTree.setComCode(user.getComCode());
        if(org.apache.commons.lang.StringUtils.isNotBlank(user.getModuCode())) {
            mdmSpaceTree.setModuCode(user.getModuCode());
        }
        return mdmSpaceTreeService.getSpaceList(mdmSpaceTree);
    }

    @Override
    public List<String> getAuthSpaceList(String userCode) {
        return null;
    }
}
