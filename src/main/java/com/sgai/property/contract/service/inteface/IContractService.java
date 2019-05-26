package com.sgai.property.contract.service.inteface;

import com.sgai.common.persistence.Page;
import com.sgai.property.contract.entity.HtContract;
import com.sgai.property.contract.vo.ContractSearchParams;
import com.sgai.property.contract.vo.HtContractVO;

public interface IContractService {

    /**
     * 新增
     * @param contractVO
     */
    void save(HtContractVO contractVO);

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    HtContractVO getById(String id);

    /**
     * 更新
     * @param htContractVO
     * @return
     */
    boolean updateById(HtContractVO htContractVO);

    /**
     * 逻辑删除
     * 更新is_delete =0 未删除  1 删除
     * @param id
     * @return
     */
    boolean updateById(String id);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<HtContractVO> findListPage(ContractSearchParams contractSearchParams, int pageNum, int pageSize);

    /**
     * 统计未删除
     * @return
     */
    int getCount();

    /**
     * 查询最新一条数据的编码
     * @return
     */
    String getNumber();
    /**
     * 根据名称统计
     * @param htContractVO
     * @return
     */
    HtContract getContractByName(HtContractVO htContractVO);


}
