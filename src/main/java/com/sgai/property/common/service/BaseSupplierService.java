package com.sgai.property.common.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.mdm.entity.MdmSupplierInfo;

/**
 * 基础供应商数据.
 *
 * @author ppliu
 * created in 2018/12/21 14:03
 */
public interface BaseSupplierService {

    /**
     * 获取所有供应商数据
     */
    Page<MdmSupplierInfo> getListSupplierInfo(String sgaiToken, int pageNo, int pageSize, String comType, String supplierName, String id);
}
