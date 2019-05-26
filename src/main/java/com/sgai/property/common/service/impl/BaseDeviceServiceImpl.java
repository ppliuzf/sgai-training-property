package com.sgai.property.common.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseDeviceService;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.service.MdmDeviceClassService;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 设备基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:51
 */
@Service
public class BaseDeviceServiceImpl implements BaseDeviceService {
    @Autowired
    private MdmDeviceClassService mdmDeviceClassService;
    @Autowired
    private MdmDevicesUseInfoService mdmDevicesUseInfoService;

    @Override
    public Page<MdmDeviceClass> getListDeviceClass(String sgaiToken, String profCode, String classCode, String className, Integer pageNo, Integer pageSize) {
        MdmDeviceClass mdmDeviceClass = new MdmDeviceClass();
        mdmDeviceClass.setClassName(className);
        mdmDeviceClass.setClassCode(classCode);
        mdmDeviceClass.setProfCode(profCode);
        return mdmDeviceClassService.findPage(new Page<>(pageNo, pageSize), mdmDeviceClass);
    }

    @Override
    public Page<MdmDevicesUseInfoVo> getListDevicesUseInfo(String sgaiToken, String className, Integer pageNo, Integer pageSize) {
        MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
        mdmDevicesUseInfoVo.setClassCode(className);
        return mdmDevicesUseInfoService.findPageVoByModel(new Page<>(pageNo, pageSize), mdmDevicesUseInfoVo);
    }
}
