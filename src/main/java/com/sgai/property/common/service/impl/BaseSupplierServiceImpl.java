package com.sgai.property.common.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseSupplierService;
import com.sgai.property.mdm.entity.MdmSupplierInfo;
import com.sgai.property.mdm.service.MdmSupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础供应商数据.
 *
 * @author ppliu
 * created in 2018/12/21 14:03
 */
@Service
public class BaseSupplierServiceImpl implements BaseSupplierService {
    @Autowired
    private MdmSupplierInfoService mdmSupplierInfoService;

    @Override
    public Page<MdmSupplierInfo> getListSupplierInfo(String sgaiToken, int pageNo, int pageSize, String comType, String supplierName, String id) {
        MdmSupplierInfo mdmSupplierInfo = new MdmSupplierInfo();
        if (id != null && !"".equals(id)) {
            mdmSupplierInfo.setId(id);
        }
        if (comType != null && !"".equals(comType)) {
            mdmSupplierInfo.setComType(comType);
        }
        if (supplierName != null && !"".equals(supplierName)) {
            mdmSupplierInfo.setSupplierName(supplierName);
        }
        return mdmSupplierInfoService.findPage(new Page<>(pageNo, pageSize), mdmSupplierInfo);
    }
}
