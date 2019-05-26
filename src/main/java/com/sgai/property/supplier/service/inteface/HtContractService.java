package com.sgai.property.supplier.service.inteface;

import com.sgai.common.persistence.Page;
import com.sgai.property.supplier.vo.ContractDetailsVO;
import com.sgai.property.supplier.vo.ContractVO;

public interface HtContractService {

    /**
     * 根据供应商ID分页查询合同
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<ContractVO> findPageList(String id, int pageNum, int pageSize);

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    ContractDetailsVO getById(String id);
}
