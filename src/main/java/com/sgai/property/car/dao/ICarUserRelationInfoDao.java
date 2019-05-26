package com.sgai.property.car.dao;
import com.sgai.common.persistence.Page;
import com.sgai.property.car.entity.CarUserRelationInfo;
import com.sgai.property.car.vo.CarUserRelationInfoVo;
import com.sgai.property.commonService.dao.MoreDataSourceDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface ICarUserRelationInfoDao extends MoreDataSourceDao<CarUserRelationInfo>{

    /**
     * 查询现在某段时间内车辆是否被预约
     * @param entity
     * @return
     */
    List<CarUserRelationInfo> findRelation(CarUserRelationInfo entity);
    /**
     * 分页查询登录管理员(申请人)下的车辆预约信息
     *
     * @param entity
     * @return
     */
    List<CarUserRelationInfoVo> findListInnerCarInfo(CarUserRelationInfoVo entity);
    /**
     * 分页查询登录管理员(申请人)下的车辆预约信息
     *
     * @param entity
     * @return
     */
    List<CarUserRelationInfoVo> findListInnerCarInfoForAPP(CarUserRelationInfoVo entity);

    /**
     * 登录管理员(申请人)下的车辆预约详情
     * @param id
     * @return
     */
    CarUserRelationInfoVo detailCarUserRelationInfo(String id);

    /**
     * 查询预约记录根据审核状态
     * @param entity
     * @return
     */
    List<CarUserRelationInfoVo> findListPageByAuditStatus(CarUserRelationInfoVo entity);
    /**
     * 查询预约记录根据审核状态
     * @param entity
     * @return
     */
    List<CarUserRelationInfo> findListNew(CarUserRelationInfo entity);

    /**
     * 查询预约记录根据审核状态 app端
     * @param entity
     * @return
     */
    List<CarUserRelationInfoVo> findListPageByAuditStatusForApp(CarUserRelationInfoVo entity);

    /**
     * 我已提交状态的预约申请个数
     * @param entity
     * @return
     */
    Integer getApplyCount(CarUserRelationInfo entity);

    /**
     * 需要我审核的预约申请个数
     * @param entity
     * @return
     */
    Integer getAuditCount(CarUserRelationInfo entity);


    /**
     * 获取预约车辆信息 根据carId
     * @param carId
     * @return
     */
    CarUserRelationInfo getRelationInfoByCarId(@Param("carId") String carId);
}