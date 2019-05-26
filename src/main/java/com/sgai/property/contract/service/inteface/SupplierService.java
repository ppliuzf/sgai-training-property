package com.sgai.property.contract.service.inteface;


import com.sgai.common.persistence.Page;
import com.sgai.property.contract.vo.SupplierParams;
import com.sgai.property.contract.vo.SupplierVO;

public interface SupplierService {

    /**
     * 分页查询供应商
     * @param supplierParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<SupplierVO> findList(SupplierParams supplierParams, int pageNum, int pageSize);

}
