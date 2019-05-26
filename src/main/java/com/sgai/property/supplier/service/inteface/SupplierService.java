package com.sgai.property.supplier.service.inteface;

import com.sgai.common.persistence.Page;
import com.sgai.property.supplier.vo.SupplierDetailsVO;
import com.sgai.property.supplier.vo.SupplierParams;
import com.sgai.property.supplier.vo.SupplierVO;


public interface SupplierService {

    /**
     * 分页查询供应商
     * @param supplierParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<SupplierVO> findList(SupplierParams supplierParams, int pageNum, int pageSize);


    SupplierDetailsVO getSupplierDetails(String id);

    boolean updateById(SupplierDetailsVO supplierDetailsVO);


}
