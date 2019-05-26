package com.sgai.property.contract.service.inteface;

import com.sgai.common.persistence.Page;
import com.sgai.property.contract.entity.HtType;
import com.sgai.property.contract.vo.HtTypeVO;

import java.util.List;

public interface ITypeService {
    /**
     * 添加
     * @param typeVO
     */
    void save(HtTypeVO typeVO);

    /**
     * 查询对象
     * @param id
     * @return
     */
    HtTypeVO getById(String id);

    /**
     * 更新
     * @param typeVO
     * @return
     */
    boolean updateById(HtTypeVO typeVO);
    /**
     * 逻辑删除
     * 更新is_delete =0 未删除  1 删除
     * @param id
     * @return
     */
    boolean updateById(String id);


    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<HtTypeVO> findListPage(int pageNum, int pageSize);

    /**
     * 不分页查询
     * @param type
     * @return
     */
    List<HtType> findList(HtType type);

    /**
     * 查询对象
     * @param type
     * @return
     */
    HtType get(HtType type);
}
