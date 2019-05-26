package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.depot.dao.IMatOrderDao;
import com.sgai.property.depot.dao.IWarehousRecordDao;
import com.sgai.property.depot.dao.IWarehouseAllotMatDao;
import com.sgai.property.depot.entity.MatOrder;
import com.sgai.property.depot.entity.WarehousRecord;
import com.sgai.property.depot.entity.WarehouseAllotMat;
import com.sgai.property.depot.vo.MatDetailVo;
import com.sgai.property.depot.vo.MatOrderVo;
import com.sgai.property.depot.vo.WarehousRecordVo;
import com.sgai.property.purchase.dao.IMatDetailDao;
import com.sgai.property.purchase.entity.MatDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 145811 on 2018/1/24.
 */
@Service
public class DoptPurchaseOrderServiceImpl {

    @Autowired
    IMatOrderDao matOrderDao;
    @Autowired
    IMatDetailDao matDetailDao;
    @Autowired
    IWarehousRecordDao iWarehousRecordDao;
    @Autowired
    IWarehouseAllotMatDao allotMatDao;


    /**
     * 分页获取已完成状态的采购单列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<MatOrderVo> searchList(int pageNum, int pageSize) {
        MatOrder matOrder = new MatOrder();
        Page<MatOrder> pageInfo = new Page<MatOrder>(pageNum, pageSize);
        pageInfo.setOrderBy("created_dt desc");
        matOrder.setPage(pageInfo);
        //已完成状态
        matOrder.setOrderStat(3L);
        matOrder.setHasOrder(0L);
        matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
        matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<MatOrder> page = pageInfo.setList(matOrderDao.findList(matOrder));
        Page<MatOrderVo> pageVo = new Page<MatOrderVo>();

        if (page != null) {
            CopyHelper.copyObj(page, pageVo);
            if (page.getList() != null) {
                List<MatOrder> matOrderList = page.getList();
                List<MatOrderVo> matOrderVoList = new ArrayList<MatOrderVo>();
                CopyHelper.copyList(matOrderList, matOrderVoList, MatOrderVo.class);
                for (MatOrderVo matOrderVo : matOrderVoList) {
                    MatDetail detail = new MatDetail();
                    detail.setOrderNo(matOrderVo.getOrderNo());
//                    detail.setComCode(UserServletContext.getUserInfo().getComCode());
//                    detail.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    List<MatDetail> detailList = matDetailDao.select(detail);
                    if (detailList != null) {
                        List<MatDetailVo> detailVoList = new ArrayList<MatDetailVo>();
                        CopyHelper.copyList(detailList, detailVoList, MatDetailVo.class);
                        matOrderVo.setMatDetailVoList(detailVoList);
                    }
                }
                pageVo.setList(matOrderVoList);
            }
        }
        return pageVo;
    }

    /**
     * 查询分页采购入库采购单用料明细
     * @param orderNo  订单号
     * @param orderId  虚拟订单id
     * @param pageNum
     * @param pageSize
     * @param isAllot  区分是调拨还是采购   0是调拨，1是采购
     * @return
     */
    public Page<WarehousRecordVo> searchWarehousMatList(String orderNo, String orderId, String whId,
                                                        String whName, String whType, int pageNum, int pageSize, Long isAllot) {
        Page<WarehousRecordVo> page = new Page<WarehousRecordVo>();
        String codeCode = UserServletContext.getUserInfo().getComCode();
         String moduCode =UserServletContext.getUserInfo().getModuCode();
        //1、如果虚拟id为空的话，查询调拨或者采购的物料信息表插入到中间表
        if(orderId == null || orderId == ""){
            String uuid = UUID.randomUUID().toString();
            //0是调拨  1是采购
            if(isAllot == 0){
                if(orderId == null || orderId == ""){
                    Page<WarehouseAllotMat> pageInfo = new Page<WarehouseAllotMat>(pageNum, Integer.MAX_VALUE);
                    WarehouseAllotMat allotMat = new WarehouseAllotMat();
                    allotMat.setPage(pageInfo);
                    allotMat.setAllotId(orderNo);
                    allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
                    allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    List<WarehouseAllotMat> allotMatList  = allotMatDao.findList(allotMat);
                    for (WarehouseAllotMat matDetail:allotMatList) {
                        String matName = matDetail.getMatName();//物料名称
                        String matTypeId = matDetail.getMatTypeId();//物料id
                        String matSpec = matDetail.getMatSpec();//物料规格
                        String matTypeCode = matDetail.getMatTypeCode();//物料型号
                        Long applyCount = matDetail.getMatRealNum();//需求数量
                        WarehousRecord warehousRecord = new WarehousRecord();
                        warehousRecord.setMatTypeId(matTypeId);
                        warehousRecord.setMatSpec(matSpec);
                        warehousRecord.setMatName(matName);
                        warehousRecord.setMatTypeCode(matTypeCode);
                        warehousRecord.setOrderId(uuid);
                        warehousRecord.setComCode(codeCode);
                        warehousRecord.setModuCode(moduCode);
                        warehousRecord.setMatNeetNum(applyCount);
                        warehousRecord.setOrderNumber(orderNo);
                        String uuId = UUID.randomUUID().toString();
                        warehousRecord.setId(uuId);
                        warehousRecord.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
                        warehousRecord.setUpdatedDt(new Date());
                        warehousRecord.setCreatedBy(UserServletContext.getUserInfo().getUserName());
                        warehousRecord.setCreatedDt(new Date());
                        warehousRecord.setWhId(whId);
                        warehousRecord.setWhType(whType);
                        warehousRecord.setWhName(whName);
                        // 然后把用料明细添加到中间表中，
                        iWarehousRecordDao.insert(warehousRecord);
                    }
                }
                //1是采购
            }else if(isAllot == 1){
                    MatDetail detail = new MatDetail();
                    Page<MatDetail> pageInfo = new Page<MatDetail>(pageNum, Integer.MAX_VALUE);
                    detail.setPage(pageInfo);
                    detail.setOrderNo(orderNo);
                    // 先根据单号进行用料明细查询，
                    List<MatDetail> detailList = matDetailDao.select(detail);
                    for (MatDetail matDetail:detailList) {
                        String matName = matDetail.getMatName();//物料名称
                        String matTypeId = matDetail.getMatTypeId();//物料id
                        String matSpec = matDetail.getMatSpec();//物料规格
                        String matTypeCode = matDetail.getMatTypeCode();//物料型号
                        Long applyCount = matDetail.getBuyCount();//需求数量
                        WarehousRecord warehousRecord = new WarehousRecord();
                        warehousRecord.setMatTypeId(matTypeId);
                        warehousRecord.setMatSpec(matSpec);
                        warehousRecord.setMatName(matName);
                        warehousRecord.setMatTypeCode(matTypeCode);
                        warehousRecord.setOrderId(uuid);
                        warehousRecord.setComCode(codeCode);
                        warehousRecord.setModuCode(moduCode);
                        warehousRecord.setMatNeetNum(applyCount);
                        warehousRecord.setOrderNumber(orderNo);
                        String uuId = UUID.randomUUID().toString();
                        warehousRecord.setId(uuId);
                        warehousRecord.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
                        warehousRecord.setUpdatedDt(new Date());
                        warehousRecord.setCreatedBy(UserServletContext.getUserInfo().getUserName());
                        warehousRecord.setCreatedDt(new Date());
                        warehousRecord.setWhId(whId);
                        warehousRecord.setWhType(whType);
                        warehousRecord.setWhName(whName);
                        // 然后把用料明细添加到中间表中，
                        iWarehousRecordDao.insert(warehousRecord);
                    }
                }

            // 分页查询（从中间表查询用料明细信息）
            WarehousRecord warehousRecord = new WarehousRecord();
            Page<WarehousRecord> pageInfo = new Page<WarehousRecord>(pageNum, pageSize);
            warehousRecord.setPage(pageInfo);
            warehousRecord.setOrderId(uuid);
            //warehousRecord.setOrderNumber(orderNo);
            List<WarehousRecord> list = iWarehousRecordDao.findList(warehousRecord);
            int count = iWarehousRecordDao.getCount(warehousRecord);

            List<WarehousRecordVo> warehousRecordVoList = new ArrayList<WarehousRecordVo>();
            CopyHelper.copyList(list, warehousRecordVoList, WarehousRecordVo.class);
            page.setList(warehousRecordVoList);
            page.setCount(count);
            page.setPageSize(pageSize);
            page.setPageNo(pageNum);

        }else{

            // 分页查询（从中间表查询用料明细信息）
            WarehousRecord warehousRecord = new WarehousRecord();
            Page<WarehousRecord> pageInfo = new Page<WarehousRecord>(pageNum, pageSize);
            warehousRecord.setPage(pageInfo);
            warehousRecord.setOrderId(orderId);
            //warehousRecord.setOrderNumber(orderNo);
            List<WarehousRecord> list = iWarehousRecordDao.findList(warehousRecord);
            int count = iWarehousRecordDao.getCount(warehousRecord);

            List<WarehousRecordVo> warehousRecordVoList = new ArrayList<WarehousRecordVo>();
            CopyHelper.copyList(list, warehousRecordVoList, WarehousRecordVo.class);
            page.setList(warehousRecordVoList);
            page.setCount(count);
            page.setPageSize(pageSize);
            page.setPageNo(pageNum);
        }


        return page;
    }
}