package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.constants.Constants;
import com.sgai.property.depot.dao.*;
import com.sgai.property.depot.entity.*;
import com.sgai.property.depot.param.UpdateWhInParam;
import com.sgai.property.depot.param.WarehousRecordParam;
import com.sgai.property.depot.vo.*;
import com.sgai.property.purchase.dao.IWarehouseMatDao;
import com.sgai.property.purchase.entity.WarehouseMat;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class DepotInManageServiceImpl {
    @Autowired
    IWarehouseInDao iWarehouseInDao;
    @Autowired
    IWarehouseInMatDao iWarehouseInMatDao;
    @Autowired
    IWarehouseMatDao iWarehouseMatDao;
    @Autowired
    IWarehousRecordDao iWarehousRecordDao;
    @Autowired
    IMatOrderDao matOrderDao;
    @Autowired
    IWarehouseAllotDao allotDao;
    @Autowired
    IOperationMatDao iOperationMatDao;
    @Autowired
    IOperationDao iOperationDao;
    @Autowired
    IWarehouseAllotDao iAllotDao;
    @Autowired
    private BaseCodeService baseCodeService;


    /**
     * describe: 这个人很懒也很帅，但是什么都没写~列表
     * className: DepotInManageServiceImpl
     * methodName: depotInLists
     * methodParam: [whInParam, pageNo, pageSize]
     * return: com.sgai.property.depot.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.depot.entity.WarehouseIn>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/25 10:13
     **/
    public Response<Page<WarehouseIn>> depotInLists(WhInParam whInParam, int pageNo, int pageSize) {
        Response<Page<WarehouseIn>> result = new Response<>();
        WarehouseIn whIn = new WarehouseIn();
        if (StringUtils.isNotBlank(whInParam.getWhInNo())) {
            whIn.setWhInNo(whInParam.getWhInNo());
        }
        whIn.setWhInType(whInParam.getWhInType());
        whIn.setWhInStat(whInParam.getWhInStat());
        whIn.setComCode(UserServletContext.getUserInfo().getComCode());
        whIn.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<WarehouseIn> page = new Page<>(pageNo, pageSize);
        page.setOrderBy("OUT_DATETIME DESC");
        whIn.setPage(page);
        List<WarehouseIn> list = iWarehouseInDao.findList(whIn);
        int count = iWarehouseInDao.getCount(whIn);

        /*for (WarehouseIn warehouseIn:list) {
            Long whInType = warehouseIn.getWhInType();//1、挑拨入库
            if(whInType == 1){
                WarehouseAllot warehouseAllot = allotDao.get(warehouseIn.getAllotId());
                if(warehouseAllot != null){
                    String allotNo = warehouseAllot.getAllotNo();
                    warehouseIn.setAllotId(allotNo);
                }
            }
        }*/
        whIn.setPage(page);
        page.setCount(count);
        page.setList(list);
        result.setData(page);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~新增入库单
     * className: DepotInManageServiceImpl
     * methodName: addDepot
     * methodParam: [toonCode, addWhInParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/25 11:46
     **/
    public Response<Boolean> addDepot(AddWhInParam addWhInParam) {
        Response<Boolean> result = new Response<>();

        //根据虚拟订单id查询出来订单号所对应的物料信息
        WarehousRecord warehousRecord = new WarehousRecord();
        warehousRecord.setOrderId(addWhInParam.getSuppliesDetailsId());
        List<WarehousRecord> list = iWarehousRecordDao.findList(warehousRecord);


        int yeindex = 0;
        int noIndex = 0;
        for (WarehousRecord wr : list) {
            Long neetNum = wr.getMatNeetNum();//所需数量
            Long realNum = wr.getMatRealNum();//实际数量

            if (realNum != null) {
                if (neetNum.intValue() == realNum.intValue()) {
                    yeindex++;//全部出库
                } else if (realNum.intValue() == 0) {
                    noIndex++;
                }
            } else {
                noIndex++;
            }
        }

        WarehouseIn whIn = new WarehouseIn();
        BeanUtils.copyProperties(addWhInParam, whIn);
        String wh = UUID.randomUUID().toString();
        whIn.setId(wh);
        String whNo = getWhNo();
        whIn.setWhInNo(whNo);
        whIn.setOutDatetime(new Date());
        whIn.setOutEmpId(UserServletContext.getUserInfo().getUserNo());
        whIn.setOutEmpName(UserServletContext.getUserInfo().getUserName());
        whIn.preInsert();
        whIn.setComCode(UserServletContext.getUserInfo().getComCode());
        whIn.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Long whInType = whIn.getWhInType();
        if (whInType == 1) {
            WarehouseAllot warehouseAllot = allotDao.getById(whIn.getAllotId());
            if (warehouseAllot != null) {
                String allotNo = warehouseAllot.getAllotNo();
                whIn.setAllotName(allotNo);
            }
        }

        if (yeindex == list.size()) {
            whIn.setWhInStat(Constants.MT_STAT_3);
        } else if (noIndex == list.size()) {
            whIn.setWhInStat(Constants.MT_STAT_1);
        } else {
            whIn.setWhInStat(Constants.MT_STAT_2);
        }
        int inst = iWarehouseInDao.insert(whIn);
        if (inst == 0) {
            throw new BusinessException(ReturnType.DB, "新建入库单失败！");
        }

        //入库物料明细
        if (list.size() > 0) {
            WarehouseInMat InMat = new WarehouseInMat();
            InMat.setWhInId(whIn.getId());
            InMat.setWhId(addWhInParam.getWhId());


            String opMatId = UUID.randomUUID().toString();
            for (WarehousRecord su : list) {
                InMat.setId(UUID.randomUUID().toString());
                InMat.setMatTypeId(su.getMatTypeId());
                InMat.setMatName(su.getMatName());
                InMat.setMatRealNum(su.getMatRealNum());
                InMat.setMatNeetNum(su.getMatNeetNum());
                InMat.setMatSpec(su.getMatSpec());
                InMat.setComCode(UserServletContext.getUserInfo().getComCode());
                InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                InMat.preInsert();
                int inser = iWarehouseInMatDao.insert(InMat);
                if (inser == 0) {
                    throw new BusinessException(ReturnType.DB, "请联系管理员，物料关联失败！");
                }

                //添加到操作人员记录表
                OperationMat operationMat = new OperationMat();
                operationMat.setOperationId(opMatId);//与操作人员表关联的唯一标识
                operationMat.setMatName(su.getMatName());//材料名称
                Long a = su.getMatRealNum();
                operationMat.setMatCount(su.getMatRealNum());//物料数量
                operationMat.setOderNumber(whNo);//入库单号
                operationMat.setOperationTime(new Date());
                operationMat.setComCode(UserServletContext.getUserInfo().getComCode());
                operationMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                operationMat.preInsert();
                iOperationMatDao.insert(operationMat);
            }
            //添加到操作人员表
            Operation operation = new Operation();
            operation.setId(UUID.randomUUID().toString());
            operation.setOrderNumber(wh);//入库单id
            operation.setComCode(UserServletContext.getUserInfo().getComCode());
            operation.setModuCode(UserServletContext.getUserInfo().getModuCode());
            operation.setOperationName(UserServletContext.getUserInfo().getUserName());//操作人名称
            operation.setOperationTime(new Date());
            operation.setOperationType(whIn.getWhInStat().toString());
            operation.setOpeationMatId(opMatId);////与操作人员记录表关联的唯一标识
            if (yeindex == list.size()) {
                operation.setOperationType(Constants.MT_STAT_3.toString());
            } else if (noIndex == list.size()) {
                operation.setOperationType(Constants.MT_STAT_1.toString());
            } else {
                operation.setOperationType(Constants.MT_STAT_2.toString());
            }
            operation.preInsert();
            iOperationDao.insert(operation);
        }
/*
        //入库物料明细
        if (addWhInParam.getSuppliesDetails().size() > 0 ){
            WarehouseInMat InMat = new WarehouseInMat();
            InMat.setWhInId(whIn.getId());
            InMat.setWhId(addWhInParam.getWhId().toString());
            for (SuppliesDetail su: addWhInParam.getSuppliesDetails()) {
                InMat.setId(UUID.randomUUID().toString());
                InMat.setMatTypeId(su.getMatTypeId());
                InMat.setMatName(su.getMatName());
                InMat.setMatRealNum(su.getMatRealNum());
                InMat.setMatNeetNum(su.getMatNeetNum());
                InMat.setMatSpec(su.getMatSpec());
                InMat.setComCode(UserServletContext.getUserInfo().getComCode());
                InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                InMat.preInsert();
                int inser = iWarehouseInMatDao.insert(InMat);
                if (inser == 0 ){
                    throw new BusinessException(ReturnType.DB,"请联系管理员，物料关联失败！");
                }
            }
        }*/
        //仓库物料处理
        WarehouseInMat InMat = new WarehouseInMat();
        InMat.setWhInId(wh);
        InMat.setComCode(UserServletContext.getUserInfo().getComCode());
        InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehouseInMat> mats = iWarehouseInMatDao.findList(InMat);
        updateWhMat(mats);
        if ("".equals(addWhInParam.getAllotId()) || addWhInParam.getAllotId() == null) {
            //采购单  变成已生成订单
            MatOrder order = new MatOrder();
            order.setOrderNo(addWhInParam.getOrderId());
            order.setComCode(UserServletContext.getUserInfo().getComCode());
            order.setModuCode(UserServletContext.getUserInfo().getModuCode());
            MatOrder matOrder1 = matOrderDao.get(order);//采购单的编号
            matOrder1.setHasOrder(1L);
            matOrderDao.updateById(matOrder1);
        }
        if (addWhInParam.getWhInType() == Constants.MT_STAT_1) {
            WarehouseAllot wa = new WarehouseAllot();
            wa.setId(addWhInParam.getAllotId());
            wa.setHasOrder(Constants.MT_STAT_2);
            iAllotDao.updateById(wa);
        }
        result.setData(true);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~修改
     * className: DepotInManageServiceImpl
     * methodName: updateDepot
     * methodParam: [toonCode, addWhInParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/25 18:19
     **/
    public Response<Boolean> updateDepot(UpdateWhInParam addWhInParam) {
        Response<Boolean> result = new Response<>();
        String opMatId = UUID.randomUUID().toString();
        List<SuppliesDetail> suppliesDetails = addWhInParam.getSuppliesDetails();
        for (SuppliesDetail suppliesDetail : suppliesDetails) {

            //根据物料类型Id查询物料表相关数据
            WarehouseInMat inMat = iWarehouseInMatDao.getById(suppliesDetail.getId());

            WarehouseMat wa = new WarehouseMat();
            wa.setMatTypeId(inMat.getMatTypeId());
            wa.setWhId(inMat.getWhId());
            WarehouseMat whmat = iWarehouseMatDao.get(wa);
            //修改库存数量
            whmat.setMatNum(whmat.getMatNum() + suppliesDetail.getMatRealNum());
            iWarehouseMatDao.updateById(whmat);
            //回滚入库物料表相关的数量

            WarehouseInMat byId = iWarehouseInMatDao.getById(suppliesDetail.getId());

            WarehouseInMat warehouseInMat = new WarehouseInMat();
            warehouseInMat.setId(suppliesDetail.getId());
            warehouseInMat.setMatRealNum(suppliesDetail.getMatRealNum() + byId.getMatRealNum());
            int ins = iWarehouseInMatDao.updateById(warehouseInMat);

            if (byId != null) {
                //添加到操作人员记录表
                OperationMat operationMat = new OperationMat();
                operationMat.setOperationId(opMatId);//与操作人员表关联的唯一标识
                operationMat.setMatName(byId.getMatName());//材料名称
                operationMat.setMatCount(suppliesDetail.getMatRealNum());//物料数量
                operationMat.setOderNumber(addWhInParam.getWhInNo());//入库单号
                operationMat.setOperationTime(new Date());
                operationMat.setComCode(UserServletContext.getUserInfo().getComCode());
                operationMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                operationMat.preInsert();
                iOperationMatDao.insert(operationMat);
            }
        }
        //查询物料记录单
        WarehouseInMat InMat = new WarehouseInMat();
        InMat.setWhInId(addWhInParam.getId());
        InMat.setComCode(UserServletContext.getUserInfo().getComCode());
        InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        List<WarehouseInMat> InMatList = iWarehouseInMatDao.findList(InMat);
        int yeindex = 0;
        int noIndex = 0;
        //修改入库状态
        if (InMatList.size() > 0) {
            for (WarehouseInMat wr : InMatList) {

                Long neetNum = wr.getMatNeetNum();//所需数量
                Long realNum = wr.getMatRealNum();//实际数量
                if (realNum != null) {
                    if (neetNum.intValue() == realNum.intValue()) {
                        yeindex++;//全部出库
                    } else if (realNum.intValue() == 0) {
                        noIndex++;
                    }
                } else {
                    noIndex++;
                }


                /*//查询仓库物料表有则回滚入库数量
                WarehouseMat mat = new WarehouseMat();
                mat.setWhId(wm.getWhId());
                mat.setMatTypeId(wm.getMatTypeId());
                mat.setComCode(UserServletContext.getUserInfo().getComCode());
                mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<WarehouseMat> warehouseMatList = iWarehouseMatDao.findList(mat);
                WarehouseMat warehouseMat = null; ;
                if(warehouseMatList != null && warehouseMatList.size() > 0){
                    warehouseMat = warehouseMatList.get(0);
                }

                if (warehouseMat != null){
                    warehouseMat.setMatNum(warehouseMat.getMatNum() - wm.getMatRealNum());
                    int ins = iWarehouseMatDao.updateById(warehouseMat);
                    if (ins == 0){
                        throw new BusinessException(ReturnType.DB , "库存修改失败,联系管理员");
                    }
                }
                //删除物料记录表随后增加新数据
                iWarehouseInMatDao.deleteById(wm.getId());*/
            }
        }
        //新增物料记录表数据
        /*if (addWhInParam.getSuppliesDetails().size() > 0){
            WarehouseInMat InMats = new WarehouseInMat();
            InMats.setWhInId(addWhInParam.getId());
            InMats.setWhId(addWhInParam.getWhId());
            for (SuppliesDetail su: addWhInParam.getSuppliesDetails()) {
                InMats.setId(UUID.randomUUID().toString());
                InMats.setMatName(su.getMatName());
                InMats.setMatTypeId(su.getMatTypeId());
                InMats.setMatNeetNum(su.getMatNeetNum());
                InMats.setMatRealNum(su.getMatRealNum());
                InMats.setMatSpec(su.getMatSpec());
                InMats.setComCode(UserServletContext.getUserInfo().getComCode());
                InMats.setModuCode(UserServletContext.getUserInfo().getModuCode());
                InMats.preInsert();
                int inser = iWarehouseInMatDao.insert(InMats);
                if (inser == 0 ){
                    throw new BusinessException(ReturnType.DB,"请联系管理员，物料关联失败！");
                }
            }
        }*/

        //修改入库单状态
        WarehouseIn win = new WarehouseIn();
        win.setId(addWhInParam.getId());
        //win.setWhInStat(addWhInParam.getWhInStat());
        if (yeindex == InMatList.size()) {
            win.setWhInStat(Constants.MT_STAT_3);
        } else if (noIndex == InMatList.size()) {
            win.setWhInStat(Constants.MT_STAT_1);
        } else {
            win.setWhInStat(Constants.MT_STAT_2);
        }
        iWarehouseInDao.updateById(win);

        //添加到操作人员表
        Operation operation = new Operation();
        operation.setId(UUID.randomUUID().toString());
        operation.setOrderNumber(addWhInParam.getId());//入库单id
        operation.setComCode(UserServletContext.getUserInfo().getComCode());
        operation.setModuCode(UserServletContext.getUserInfo().getModuCode());
        operation.setOperationName(UserServletContext.getUserInfo().getUserName());//操作人名称
        operation.setOperationTime(new Date());
        operation.setOperationType(null);
        operation.setOpeationMatId(opMatId);////与操作人员记录表关联的唯一标识
        if (yeindex == InMatList.size()) {
            operation.setOperationType(Constants.MT_STAT_3.toString());
        } else if (noIndex == InMatList.size()) {
            operation.setOperationType(Constants.MT_STAT_1.toString());
        } else {
            operation.setOperationType(Constants.MT_STAT_2.toString());
        }
        operation.preInsert();
        iOperationDao.insert(operation);

        //入库物料处理-更新仓库物料库存
//        WarehouseInMat inMatss = new WarehouseInMat();
//        inMatss.setWhInId(addWhInParam.getId());
//        inMatss.setComCode(UserServletContext.getUserInfo().getComCode());
//        inMatss.setModuCode(UserServletContext.getUserInfo().getModuCode());
//        List<SuppliesDetail> inDetails = addWhInParam.getSuppliesDetails();
//        String[] ids = new String[inDetails.size()];
//        for (int i = 0; i < inDetails.size(); i++) {
//            ids[i] = inDetails.get(i).getId();
//        }
//
//        List<WarehouseInMat> mats = iWarehouseInMatDao.getByIds(ids);
//        //List<WarehouseInMat> mats = iWarehouseInMatDao.findList(inMatss);
//        if (addWhInParam.getWhInStat().intValue() != Constants.MT_INVITE_1){
//            updateWhMat(mats);
//        }

        result.setData(true);
        return result;
    }

    //修改库存
    private void updateWhMat(List<WarehouseInMat> mats) {
        if (mats.size() > 0) {
            for (WarehouseInMat whInm : mats) {
                //根据物料类型Id查询物料表相关数据
                WarehouseMat wa = new WarehouseMat();
                wa.setMatTypeId(whInm.getMatTypeId());
                wa.setWhId(whInm.getWhId());
                WarehouseMat whmat = iWarehouseMatDao.get(wa);
                if (whmat != null) {//修改库存数量
                    whmat.setMatNum(whmat.getMatNum() + (whInm.getMatRealNum() == null ? 0 : whInm.getMatRealNum()));
                    int ins = iWarehouseMatDao.updateById(whmat);
                    if (ins == 0) {
                        throw new BusinessException(ReturnType.DB, "库存修改失败,联系管理员");
                    }
                } else {
                    WarehouseMat was = new WarehouseMat();
                    was.setId(UUID.randomUUID().toString());
                    was.setWhId(whInm.getWhId());
                    was.setMatNum(whInm.getMatRealNum());
                    was.setMatTypeId(whInm.getMatTypeId());
                    was.setMatSpec(whInm.getMatSpec());
                    was.setMatName(whInm.getMatName());
                    was.setComCode(whInm.getComCode());
                    was.setModuCode(whInm.getModuCode());
                    was.preInsert();
                    int inse = iWarehouseMatDao.insert(was);
                    if (inse == 0) {
                        throw new BusinessException(ReturnType.DB, "库存修改失败，联系管理员");
                    }
                }
            }
        }
    }


    /**
     * describe: 这个人很懒也很帅，但是什么都没写~详情
     * className: DepotInManageServiceImpl
     * methodName: depotDetil
     * methodParam: [toonCode, id]
     * return: com.sgai.property.depot.vo.Response<com.sgai.property.depot.vo.WarehouseInMatVo>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 17:32
     **/
    public Response<WarehouseInMatVo> depotDetil(String id) {
        Response<WarehouseInMatVo> result = new Response<>();
        WarehouseInMatVo wom = new WarehouseInMatVo();
        WarehouseIn wIn = new WarehouseIn();
        wIn.setId(id);
        wIn.setComCode(UserServletContext.getUserInfo().getComCode());
        wIn.setModuCode(UserServletContext.getUserInfo().getModuCode());
        WarehouseIn whInMat = iWarehouseInDao.get(wIn);
        if (whInMat != null) {
            BeanUtils.copyProperties(whInMat, wom);
            WarehouseInMat InMat = new WarehouseInMat();
            InMat.setWhInId(id);
            InMat.setComCode(UserServletContext.getUserInfo().getComCode());
            InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<WarehouseInMat> Inlist = iWarehouseInMatDao.findList(InMat);
            wom.setWarehouseInMats(Inlist);
            result.setData(wom);
        } else {
            throw new BusinessException(ReturnType.DB, "出库单走丢了！");
        }
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~撤销
     * className: DepotOutManageServiceImpl
     * methodName: deleteDepot
     * methodParam: [toonCode, id]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/25 9:19
     **/
    public Response<Boolean> deleteDepot(String id) {
        Response<Boolean> result = new Response<>();
        WarehouseIn warehouseIn = iWarehouseInDao.getById(id);
        WarehouseInMat outMat = new WarehouseInMat();
        outMat.setWhInId(id);
        if (warehouseIn.getWhInType().intValue() == Constants.MT_STAT_1) {
            //修改调拨单状态
            WarehouseAllot wa = new WarehouseAllot();
            wa.setId(warehouseIn.getAllotId());
            wa.setHasOrder(1L);
            iAllotDao.updateById(wa);
        } else if (warehouseIn.getWhInType().intValue() == Constants.MT_STAT_2) {

            MatOrder order = new MatOrder();
            order.setId(warehouseIn.getOrderId());
            order.setHasOrder(0L);
            matOrderDao.updateById(order);
        }

        int del = iWarehouseInMatDao.delete(outMat);
        int dele = iWarehouseInDao.deleteById(id);
        if (dele == 0) {
            throw new BusinessException(ReturnType.DB, "撤销失败！");
        }
        result.setData(true);
        return result;
    }

    /*
     * 出库单号
     * */
    private String getWhNo() {
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_iWarehouseIn_);
        String seqStr = Long.toString(seq);
        for (int i = seqStr.length(); i <= 6; i++) {
            seqStr = "0" + seqStr;
        }
        return "RK-" + seqStr;
    }

    @Autowired
    IWarehouseInOutExtDao inOutDao;

    /**
     * 出入库分页查询
     *
     * @param dtBegin
     * @param dtEnd
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<WarehouseInOutVo> findInOutPage(Date dtBegin, Date dtEnd, int pageNum,
                                                int pageSize) {
        Page<WarehouseInOut> pageInfo = new Page<WarehouseInOut>(pageNum, pageSize);
        WarehouseInOut inOut = new WarehouseInOut();
        if (dtBegin != null) {
            inOut.setDtBegin(dtBegin);
        }
        if (dtEnd != null) {
            inOut.setDtEnd(dtEnd);
        }
        inOut.setPage(pageInfo);
        inOut.setComCode(UserServletContext.getUserInfo().getComCode());
        inOut.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<WarehouseInOut> page = pageInfo.setList(inOutDao.findInOutList(inOut));
        Page<WarehouseInOutVo> pageVo = new Page<WarehouseInOutVo>();
        CopyHelper.copyObj(page, pageVo);
        if (page.getList() != null) {
            List<WarehouseInOutVo> voList = new ArrayList<WarehouseInOutVo>();
            CopyHelper.copyList(page.getList(), voList, WarehouseInOutVo.class);
            pageVo.setList(voList);
        }
        return pageVo;
    }

    public WarehousRecordParam addDepotMatInfo(WarehousRecordParam warehousRecordParam) {

        Response<String> s = new Response<String>();
        WarehousRecord warehousRecord = new WarehousRecord();
        BeanUtils.copyProperties(warehousRecordParam, warehousRecord);
        iWarehousRecordDao.updateById(warehousRecord);
        return warehousRecordParam;
    }


    /**
     * 入库单详情--用料明细分页
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<WarehouseInMat> depotDetilMat(String id, int pageNum, int pageSize) {
        Page<WarehouseInMat> page = new Page<WarehouseInMat>();
        Page<WarehouseInMat> pageInfo = new Page<WarehouseInMat>(pageNum, pageSize);

        WarehouseInMat InMat = new WarehouseInMat();
        InMat.setPage(pageInfo);
        //d入库单i
        InMat.setWhInId(id);
        InMat.setComCode(UserServletContext.getUserInfo().getComCode());
        InMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //入库单详情--用料明细分页
        List<WarehouseInMat> list = iWarehouseInMatDao.findList(InMat);
        int count = iWarehouseInMatDao.getCount(InMat);
        page.setList(list);
        page.setPageSize(pageSize);
        page.setPageNo(pageNum);
        page.setCount(count);
        return page;
    }

    /**
     * 入库单详情--入库操作员记录
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<OperationVo> depotDetilOperation(String id, int pageNum, int pageSize) {
        Page<OperationVo> page = new Page<OperationVo>();
        Page<Operation> pageInfo = new Page<Operation>(pageNum, pageSize);

        Operation operation = new Operation();
        operation.setPage(pageInfo);
        //入库单id
        operation.setOrderNumber(id);
        operation.setComCode(UserServletContext.getUserInfo().getComCode());
        operation.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //入库单详情--用料明细分页
        pageInfo.setOrderBy("OPERATION_TIME");
        List<Operation> list = iOperationDao.findList(operation);
        List<OperationVo> listInfo = new ArrayList<OperationVo>();
        int count = iOperationDao.getCount(operation);
        CopyHelper.copyList(list, listInfo, OperationVo.class);
        page.setList(listInfo);
        page.setPageSize(pageSize);
        page.setPageNo(pageNum);
        page.setCount(count);
        return page;
    }

    /**
     * 入库单详情--入库操作员物料分页记录
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<WarehouseOpterationMatVo> depotDetilOperationMatPag(String id, int pageNum, int pageSize) {
        Page<WarehouseOpterationMatVo> page = new Page<WarehouseOpterationMatVo>();
        Page<OperationMat> pageInfo = new Page<OperationMat>(pageNum, pageSize);

        OperationMat operationMat = new OperationMat();
        operationMat.setPage(pageInfo);
        //操作人员表中的opeation_mat_id
        operationMat.setOperationId(id);
        operationMat.setComCode(UserServletContext.getUserInfo().getComCode());
        operationMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        //入库单详情--用料明细分页
        List<OperationMat> list = iOperationMatDao.findList(operationMat);
        for (OperationMat l : list) {
            Long matCount = l.getMatCount();
            if (matCount == null) {
                l.setMatCount(0L);
            }
        }
        List<WarehouseOpterationMatVo> listInfo = new ArrayList<WarehouseOpterationMatVo>();
        int count = iOperationMatDao.getCount(operationMat);
        CopyHelper.copyList(list, listInfo, WarehouseOpterationMatVo.class);
        page.setList(listInfo);
        page.setPageSize(pageSize);
        page.setPageNo(pageNum);
        page.setCount(count);
        return page;
    }

    public Response<String> updateWhInfo(WarehousRecord record) {
        Response<String> reslut = new Response<String>();

        WarehousRecord warehousRecord = new WarehousRecord();
        warehousRecord.setOrderId(record.getOrderId());
        List<WarehousRecord> list = iWarehousRecordDao.findList(warehousRecord);
        try {
            for (WarehousRecord warehousRecord1 : list) {
                warehousRecord1.setWhType(record.getWhType());
                warehousRecord1.setWhId(record.getWhId());
                warehousRecord1.setWhName(record.getWhName());
                int i = iWarehousRecordDao.updateById(warehousRecord1);
            }
            reslut.setMessage("操作成功！");
            reslut.setCode(ReturnType.Success.getCode());

        } catch (Exception e) {
            reslut.setMessage("操作失败！");
            reslut.setCode(ReturnType.Error.getCode());
        }
        return reslut;
    }
}
