package com.sgai.property.purchase.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.purchase.dao.IMatSpuulyComDao;
import com.sgai.property.purchase.dao.IMdmSupplierInfoDao;
import com.sgai.property.purchase.entity.MatSpuulyCom;
import com.sgai.property.purchase.entity.MdmSupplierInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 145811 on 2018/1/12.
 */
@Service
public class PurchaseCommonServiceImpl {

    @Autowired
    private IMatSpuulyComDao mapSpuulyComDao;
    @Autowired
    private IMdmSupplierInfoDao infoDao;


    public List<MatSpuulyCom> getComList() {
        ArrayList<MatSpuulyCom> list = new ArrayList<>();
        MdmSupplierInfo info = new MdmSupplierInfo();
        info.setComCode(UserServletContext.getUserInfo().getComCode());
        info.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<MdmSupplierInfo> infoDaoList = infoDao.findList(info);
        for (MdmSupplierInfo mo : infoDaoList) {
            MatSpuulyCom spuulyCom = new MatSpuulyCom();
            spuulyCom.setComName(mo.getSupplierName());
            spuulyCom.setId(mo.getId());
            spuulyCom.setCreatedDt(mo.getCreateDate());
            spuulyCom.setUpdatedDt(mo.getUpdateDate());
            list.add(spuulyCom);
        }

        return list;
    }
}
