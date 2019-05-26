package com.sgai.property.common.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseMaterielService;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;
import com.sgai.property.mdm.service.MdmMatClassService;
import com.sgai.property.mdm.service.MdmMatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:58
 */
@Service
public class BaseMaterielServiceImpl implements BaseMaterielService {
    @Autowired
    private MdmMatClassService mdmMatClassService;
    @Autowired
    private MdmMatInfoService mdmMatInfoService;

    @Override
    public Page<MdmMatClass> getListMdmMatClass(String sgaiToken, String matTypeName, String matTypeCode, Integer pageNo, Integer pageSize) {
        MdmMatClass mdmMatClass = new MdmMatClass();
        mdmMatClass.setMatTypeCode(matTypeCode);
        mdmMatClass.setMatTypeName(matTypeName);
        return mdmMatClassService.findPage(new Page<>(pageNo, pageSize), mdmMatClass);
    }

    @Override
    public Page<MdmMatInfo> getListMdmMatInfo(String matName, String matTypeCode, Integer pageNo, Integer pageSize) {
        MdmMatInfo mdmMatInfo = new MdmMatInfo();
        mdmMatInfo.setMatTypeCode(matTypeCode);
        mdmMatInfo.setMatName(matName);
        return mdmMatInfoService.findPage(new Page<MdmMatInfo>(pageNo, pageSize), mdmMatInfo);
    }
}
