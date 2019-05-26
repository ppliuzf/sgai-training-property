package com.sgai.property.common.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;

/**
 * 物料基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:58
 */
public interface BaseMaterielService {


    /**
     * 获取所有物料分类数据
     */
    Page<MdmMatClass> getListMdmMatClass(String sgaiToken, String matTypeName, String matTypeCode,
                                         Integer pageNo, Integer pageSize);

    /**
     * 根据物料分类编码查询物流主数据
     */
    Page<MdmMatInfo> getListMdmMatInfo(String matName, String matTypeCode,
                                       Integer pageNo, Integer pageSize);
}
