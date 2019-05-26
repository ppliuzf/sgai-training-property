package com.sgai.property.car.service;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarInfoDao;
import com.sgai.property.car.entity.CarInfo;
import com.sgai.property.car.vo.MyCarInfoParam;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.commonService.vo.DeptEmpVo;
import com.sgai.property.commonService.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICarInfoDao, CarInfo> {
    @Autowired
    private ICarInfoDao carInfoDao;
    @Autowired
    private BaseDepartmentService baseDepartmentService;

    /**
     * 根据用户姓名和手机号 查询车辆信息
     *
     * @return
     */
    public Page<CarInfo> findByCustomer(CarInfo carInfo, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Page<CarInfo> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("updated_dt");
        carInfo.setCiIsDelete(Constants.NO);
        carInfo.setPage(page);
        List<CarInfo> list1 = carInfoDao.findByCustomer(carInfo);
        page.setList(list1);
        return page;
    }

    /**
     * 查询可预约的车辆ids
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<CarInfo> findListForCar(MyCarInfoParam myCarInfoParam, Integer pageNumber, Integer pageSize) {
        String comCode = UserServletContext.getUserInfo().getComCode();
        String moduCode = UserServletContext.getUserInfo().getModuCode();
        Page<CarInfo> page = new Page<>(pageNumber, pageSize);
        page.setOrderBy("updated_dt");
        CarInfo carInfo = new CarInfo();
        BeanUtils.copyProperties(myCarInfoParam, carInfo);
        carInfo.setPage(page);
        carInfo.setComCode(comCode);
        carInfo.setModuCode(moduCode);
        List<CarInfo> list1 = carInfoDao.findListForCar(carInfo);
        page.setList(list1);
        return page;
    }

    /**
     * 查询可预约的车辆ids
     *
     * @return /deptInfo/getDeptListById
     * Response<DeptEmpVo>
     */
    public Page<CarInfo> findListForCarApp(Long comId, Long riUseEnd, String ciDepartmentId, Integer pageNumber, Integer pageSize) {
        String comCode = UserServletContext.getUserInfo().getComCode();
        String moduCode = UserServletContext.getUserInfo().getModuCode();
        Page<CarInfo> page = new Page<>(pageNumber, pageSize);
        page.setOrderBy("updated_dt");
        Map<String, Object> map = new HashMap<>();
        DeptEmpVo deptEmpVo = baseDepartmentService.getDeptListById(comId, Long.parseLong(ciDepartmentId));
        ArrayList<String> list = new ArrayList<>();
        list.add(ciDepartmentId);
        if (deptEmpVo != null && deptEmpVo.getDeptVoList() != null && deptEmpVo.getDeptVoList().size() > 0) {
            for (DeptVo deptVo : deptEmpVo.getDeptVoList()) {
                list.add(deptVo.getDeptId().toString());
            }
        }
        CarInfo carInfo = new CarInfo();
        carInfo.setRiUseEnd(riUseEnd);
        carInfo.setCiDepartmentIds(list);
        carInfo.setPage(page);
		/*map.put("riUseEnd",riUseEnd);
		map.put("ciDepartmentIds",list);*/
        carInfo.setModuCode(moduCode);
        carInfo.setComCode(comCode);
        List<CarInfo> list1 = carInfoDao.findListForCar(carInfo);
        page.setList(list1);
        return page;
    }


    public void addCarInfo(CarInfo entity) {
        if (StringUtils.isBlank(entity.getId())) {
            entity.preInsert();
            this.dao.insert(entity);
        } else {
            entity.preUpdate();
            this.dao.update(entity);
        }

    }

}
