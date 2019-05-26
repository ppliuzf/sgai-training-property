package com.sgai.property.contract.service.impl;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.contract.dao.ConITypeDao;
import com.sgai.property.contract.dao.ICHtContractDao;
import com.sgai.property.contract.dao.ICHtTypeDao;
import com.sgai.property.contract.entity.HtContract;
import com.sgai.property.contract.entity.HtType;
import com.sgai.property.contract.service.ContractHtContractServiceImpl;
import com.sgai.property.contract.service.HtTypeServiceImpl;
import com.sgai.property.contract.service.inteface.ITypeService;
import com.sgai.property.contract.vo.HtTypeVO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {
    private static final Logger logger = LogManager.getLogger(TypeServiceImpl.class);
    @Autowired
    private HtTypeServiceImpl htTypeService;
    @Autowired
    private ICHtTypeDao htTypeDao;
    @Autowired
    private ICHtContractDao htContractDao;
    @Autowired
    private ConITypeDao typeDao;

    @Autowired
    private ContractHtContractServiceImpl htContractService;

    /**
     * 新增
     *
     * @param typeVO
     */
    @Override
    public void save(HtTypeVO typeVO) {
        HtType type3 = new HtType();
        BeanUtils.copyProperties(typeVO, type3);
        type3.setComName(UserServletContext.getUserInfo().getComName());
        type3.setComCode(UserServletContext.getUserInfo().getComCode());
        type3.setModuCode(UserServletContext.getUserInfo().getModuCode());
        type3.setIsDelete(0L);
        htTypeService.save(type3);
    }

    /**
     * 查询对象
     *
     * @param id
     * @return
     */
    @Override
    public HtTypeVO getById(String id) {
        HtTypeVO typeVO = new HtTypeVO();
        HtType type = new HtType();
        type.setId(id);
//        type.setIsDelete(0L);
        HtType htType = htTypeService.getById(id);
        if (null != htType) {
            BeanUtils.copyProperties(htType, typeVO);
        }
        return typeVO;
    }

    /**
     * 更新
     *
     * @param typeVO
     * @return
     */
    @Override
    public boolean updateById(HtTypeVO typeVO) {
        HtType htType = new HtType();
        if (null != typeVO) {
            BeanUtils.copyProperties(typeVO, htType);
        }

        //更新关联该类型的合同类型
        HtContract htContract = new HtContract();
        htContract.setTypeId(typeVO.getId());
        List<HtContract> htContracts = htContractService.findList(htContract);
        List<HtContract> list = new ArrayList<>();
        if (htContracts.size() > 0) {
            for (HtContract ht : htContracts) {
                HtContract htContract1 = new HtContract();
                htContract1.setId(ht.getId());
                htContract1.setTypeId(htType.getId());
                htContract1.setTypeName(htType.getTypeName());
                list.add(htContract1);
            }
        }
        if (list.size() > 0) {
            for (HtContract contract : list) {
                htContractService.updateById(contract);
            }
        }
        return htTypeService.updateById(htType);
    }

    /**
     * 逻辑删除
     * 更新is_delete =0 未删除  1 删除 3只能编辑不能删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean updateById(String id) {
        HtType htType = new HtType();
        htType.setId(id);
        htType.setIsDelete(1L);
        return htTypeService.updateById(htType);
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<HtTypeVO> findListPage(int pageNum, int pageSize) {

        HtType type = new HtType();

        //分页
        Page<HtType> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("created_dt ASC");
        type.setPage(page);
        type.setIsDelete(1L);
        type.setComCode(UserServletContext.getUserInfo().getComCode());
        type.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<HtType> pageList = typeDao.findList(type);
        int count = typeDao.getCount(type);
        List<HtTypeVO> list = new ArrayList<>();
        Page<HtTypeVO> pageInfo = new Page<>();
        if (pageList.size() > 0) {
            for (HtType htType : pageList) {
                HtTypeVO htTypeVO = new HtTypeVO();
                HtContract contract = new HtContract();
                contract.setIsDelete(0L);
                contract.setTypeId(htType.getId());
                BeanUtils.copyProperties(htType, htTypeVO);
                int total = htContractDao.getCount(contract);
                htTypeVO.setTotal(total);
                list.add(htTypeVO);
            }
        }
        pageInfo.setList(list);
        pageInfo.setCount(count);
        return pageInfo;
    }

    /**
     * 不分页查询
     *
     * @param type
     * @return
     */
    @Override
    public List<HtType> findList(HtType type) {
        type.setModuCode(UserServletContext.getUserInfo().getModuCode());
        type.setComCode(UserServletContext.getUserInfo().getComCode());
        return typeDao.findList(type);
    }

    /**
     * 查询对象
     *
     * @param type
     * @return
     */
    @Override
    public HtType get(HtType type) {
        type.setComCode(UserServletContext.getUserInfo().getComCode());
        type.setModuCode(UserServletContext.getUserInfo().getModuCode());
        return typeDao.getByName(type);
    }
}
