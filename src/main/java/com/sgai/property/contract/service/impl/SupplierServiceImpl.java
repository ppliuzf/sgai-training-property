package com.sgai.property.contract.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.contract.dao.ICGysSupplierDaoMapper;
import com.sgai.property.contract.entity.GysSupplier;
import com.sgai.property.contract.service.inteface.SupplierService;
import com.sgai.property.contract.vo.SupplierParams;
import com.sgai.property.contract.vo.SupplierVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private ICGysSupplierDaoMapper supplierDaoMapper;

    /**
     * f分页查询供应商信息
     * @param supplierParams
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<SupplierVO> findList(SupplierParams supplierParams, int pageNum, int pageSize) {
        GysSupplier gysSupplier = new GysSupplier();
        if (!"".equals(supplierParams.getName()) && supplierParams.getName() !=null){
            gysSupplier.setName(supplierParams.getName());
        }
            gysSupplier.setComCode(UserServletContext.getUserInfo().getComCode());
            gysSupplier.setModuCode(UserServletContext.getUserInfo().getModuCode());

        Page<GysSupplier> page = new Page<>(pageNum,pageSize);
        page.setOrderBy("CREATED_DT desc");
        gysSupplier.setPage(page);
        List<GysSupplier> supplierVOS = supplierDaoMapper.findList(gysSupplier);
        int count = supplierDaoMapper.getCount(gysSupplier);
        Page<SupplierVO> pageInfo = new Page<>(pageNum,pageSize);
        List<SupplierVO> supplierVOList = new ArrayList<>();
        if (supplierVOS.size()>0) {
            for (GysSupplier supplier : supplierVOS) {
                SupplierVO supplierVO = new SupplierVO();
                BeanUtils.copyProperties(supplier,supplierVO);
                supplierVOList.add(supplierVO);
            }
        }
        pageInfo.setCount(count);
        pageInfo.setList(supplierVOList);
        return pageInfo;
    }

}
