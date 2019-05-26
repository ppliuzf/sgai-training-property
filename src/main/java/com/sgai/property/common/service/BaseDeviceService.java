package com.sgai.property.common.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceClass;

/**
 * 设备基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:51
 */
public interface BaseDeviceService {

    /**
     * 获取所有设备分类数据
     */
    Page<MdmDeviceClass> getListDeviceClass(String sgaiToken, String profCode, String classCode, String className,
                                            Integer pageNo, Integer pageSize);

    /**
     * 获得设备主数据分页列表信息
     */
    Page<MdmDevicesUseInfoVo> getListDevicesUseInfo(String sgaiToken, String className,
                                                    Integer pageNo, Integer pageSize);

}
