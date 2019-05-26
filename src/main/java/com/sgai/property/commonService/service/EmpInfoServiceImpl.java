package com.sgai.property.commonService.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.commonService.dao.IEmpInfoDao;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.ctl.dao.CtlEmpDao;
import com.sgai.property.ctl.entity.CtlEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpInfoServiceImpl extends MoreDataSourceCrudServiceImpl<IEmpInfoDao, EmpInfo> {
    @Autowired
    private IEmpInfoDao empInfoDao;
    @Autowired
    private CtlEmpDao ctlEmpDao;

    public Page<EmpInfo> findListPage(EmpInfo entity, int pageNumber, int pageSize) {
        return super.findPage(new Page<>(pageNumber, pageSize), entity);
    }

    public Page<EmpInfo> searchEmpInfoList(EmpInfo empInfo, int pageNumber, int pageSize) {
        Page<EmpInfo> page = new Page<EmpInfo>(pageNumber, pageSize);
        page.setList(empInfoDao.searchEmpInfoList(empInfo));
        return page;
    }

    public CtlEmp getByCode(String code) {
        EmpInfo empInfo = new EmpInfo();
        empInfo.setEiEmpNo(code);
        return ctlEmpDao.getByCode(code);
    }


}
