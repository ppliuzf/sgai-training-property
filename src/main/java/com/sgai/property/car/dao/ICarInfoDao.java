package com.sgai.property.car.dao;
import com.sgai.property.car.entity.CarInfo;
import com.sgai.property.commonService.dao.MoreDataSourceDao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ICarInfoDao extends MoreDataSourceDao<CarInfo>{
    /**
     * 对外接口-根据客户名和客户电话查询车辆信息
     * @param carInfo
     * @return
     */
    List<CarInfo> findByCustomer(CarInfo carInfo);
    /**
     * 新增的查询可预约车辆信息1
     *
     * @param carInfo
     * @return
     */
    List<CarInfo> findListForCar(CarInfo carInfo);
    /**
     * 新增的查询可预约车辆信息2
     * @return
     */
    List<CarInfo> findListForCar2(Map<String, Object> map);
    /**
     * 根据id删除车辆信息
     * @param carInfo
     * @return
     */
    int deleteCarInfoById(CarInfo carInfo);
}