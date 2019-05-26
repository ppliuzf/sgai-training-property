package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.constants.Constants;
import com.sgai.property.depot.dao.*;
import com.sgai.property.depot.entity.*;
import com.sgai.property.depot.param.InstantSavParam;
import com.sgai.property.depot.param.RecordParam;
import com.sgai.property.depot.param.UpdateWhOtParam;
import com.sgai.property.depot.vo.*;
import com.sgai.property.purchase.dao.IMatApplyDetailDao;
import com.sgai.property.purchase.dao.IMatDetailDao;
import com.sgai.property.purchase.dao.IWarehouseMatDao;
import com.sgai.property.purchase.entity.MatApplyDetail;
import com.sgai.property.purchase.entity.MatDetail;
import com.sgai.property.purchase.entity.WarehouseMat;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/1/24.
 */
@Service
public class DepotOutManageServiceImpl {
    @Autowired
    IWarehouseOutDao iWarehouseOutDao;
    @Autowired
    IMatApplyDetailDao iMatApplyDetailDao;
    @Autowired
    IWarehouseAllotDao iAllotDao;
    @Autowired
    IWarehouseOutMatDao iWarehouseOutMatDao;
    @Autowired
    IWarehouseMatDao iWarehouseMatDao;
    @Autowired
    IMatDetailDao iMatDetailDao;
    @Autowired
    IWarehousRecordDao iWarehousRecordDao;
    @Autowired
    IWarehouseAllotMatDao iWarehouseAllotMatDao;
    @Autowired
    IOperationDao iOperationDao;
    @Autowired
    IOperationMatDao iOperationMatDao;
    @Autowired
    private BaseCodeService baseCodeService;

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~列表
     * className: DepotOutManageServiceImpl
     * methodName: depotOutLists
     * methodParam: [whOutParam, pageNo, pageSize]
     * return: com.sgai.property.depot.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.depot.entity.WarehouseOut>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 10:06
     **/
    public Response<Page<WarehouseOut>> depotOutLists(WhOutParam whOutParam, int pageNo, int pageSize) {
        Response<Page<WarehouseOut>> result = new Response<>();
        WarehouseOut whOut = new WarehouseOut();
        if (StringUtils.isNotBlank(whOutParam.getWhOutNo())) {
            whOut.setWhOutNo(whOutParam.getWhOutNo());
        }
        whOut.setWhOutType(whOutParam.getWhOutType());
        whOut.setWhStat(whOutParam.getWhStat());
        Page<WarehouseOut> page = new Page<>(pageNo, pageSize);
        page.setOrderBy("OUT_DATETIME DESC");
        whOut.setPage(page);
        whOut.setComCode(UserServletContext.getUserInfo().getComCode());
        whOut.setModuCode(UserServletContext.getUserInfo().getModuCode());
        page.setList(iWarehouseOutDao.findList(whOut));
        result.setData(page);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~用料申请单
     * className: DepotManageServiceImpl
     * methodName: suppliesList
     * methodParam: [toonCode, pageNum, pageSize]
     * return: com.sgai.property.depot.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.depot.entity.MatApplyDetail>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 10:51
     **/
    public Response<Page<MatApplyDetailVo>> suppliesList(int pageNum, int pageSize) {
        Response<Page<MatApplyDetailVo>> result = new Response<>();

        MatApplyDetail mat = new MatApplyDetail();
        mat.setMatStat(Constants.MT_STAT_2);
        mat.setHasOrder(0L);
        //物料采购申请
        List<MatApplyDetail> matPageList = iMatApplyDetailDao.selectByRowBounds(mat, new RowBounds(pageNum, pageSize));


        if (matPageList.size() == 0) {
            throw new BusinessException(ReturnType.DB, "暂无用料申请单！");
        }
        List<MatApplyDetailVo> pageList = getNoticeResponseInfos(matPageList);

        for (MatApplyDetailVo mo : pageList) {
            MatDetail mdl = new MatDetail();
            mdl.setApplyNo(mo.getApplyNo());
            mdl.setComCode(UserServletContext.getUserInfo().getComCode());
            mdl.setModuCode(UserServletContext.getUserInfo().getModuCode());
            List<MatDetail> matDetail = iMatDetailDao.select(mdl);
            mo.setMatDetail(matDetail);
        }
        Page<MatApplyDetailVo> page = new Page<>(pageNum, pageSize);
        page.setList(pageList);
        result.setData(page);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~新增出库单
     * className: DepotOutManageServiceImpl
     * methodName: addDepot
     * methodParam: [toonCode, addWhOutParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 11:46
     **/
    @Transactional
    public Response<StringBuffer> addDepot(AddWhOutParam addWhOutParam) {
        Response<StringBuffer> result = new Response<>();
        String uuids = UUID.randomUUID().toString();
        StringBuffer logs = new StringBuffer();
        WarehouseOut whOut = new WarehouseOut();
        BeanUtils.copyProperties(addWhOutParam, whOut);
        whOut.setId(uuids);

        //出库物料明细  中间表获取
        if (StringUtils.isNotBlank(addWhOutParam.getOrderId())) {
            WarehousRecord record = new WarehousRecord();
            record.setOrderId(addWhOutParam.getOrderId());
            List<WarehousRecord> recordList = iWarehousRecordDao.findList(record);
            if (recordList.size() > 0) {
                WarehouseOutMat outMat = new WarehouseOutMat();
                outMat.setWhOutId(whOut.getId());
                outMat.setWhId(addWhOutParam.getWhId());

                for (WarehousRecord rd : recordList) {
                    //实际出库数量计算
                    WarehouseMat wt = new WarehouseMat();
                    wt.setWhId(rd.getWhId());
                    wt.setMatTypeId(rd.getMatTypeId());
                    WarehouseMat mat = iWarehouseMatDao.get(wt);
                    if (mat != null) {
                        Long matNum = mat.getMatNum();//库存数量
                        if (matNum.compareTo(rd.getMatRealNum()) <= 0) {
                            outMat.setMatRealNum(matNum);
                            logs.append("物料：" + rd.getMatName() + "库存不足;");
                        } else if (matNum.compareTo(rd.getMatRealNum()) == 0) {
                            outMat.setMatRealNum(matNum);
                        } else {
                            outMat.setMatRealNum(rd.getMatRealNum());
                        }
                    } else {
                        logs.append("物料：" + rd.getMatName() + "暂时缺货;");
                        outMat.setMatRealNum(0L);
                    }
                    outMat.setId(UUID.randomUUID().toString());
                    outMat.setMatTypeId(rd.getMatTypeId());
                    outMat.setMatName(rd.getMatName());
                    outMat.setMatNeetNum(rd.getMatNeetNum());
                    outMat.setMatSpec(rd.getMatSpec());
                    outMat.setComCode(UserServletContext.getUserInfo().getComCode());
                    outMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    outMat.preInsert();
                    //出库物料表
                    iWarehouseOutMatDao.insert(outMat);
                }
            }

        } else {
            throw new BusinessException(ReturnType.Error, "凭证丢失！");
        }
        //判断出库状态
        WarehouseOutMat ot = new WarehouseOutMat();
        ot.setWhOutId(whOut.getId());
        List<WarehouseOutMat> outMats = iWarehouseOutMatDao.findList(ot);
        int yeindex = 0;
        int noIndex = 0;
        for (WarehouseOutMat wo : outMats) {
            Long realNum = wo.getMatRealNum();//实际数量
            Long neetNum = wo.getMatNeetNum();//所需数量
            if (neetNum.intValue() == realNum.intValue()) {
                yeindex++;//全部出库
            } else if (realNum.intValue() == 0) {
                noIndex++;
            }
        }
        if (yeindex == outMats.size()) {
            whOut.setWhStat(Constants.MT_STAT_3);
        } else if (noIndex == outMats.size()) {
            whOut.setWhStat(Constants.MT_STAT_1);
        } else {
            whOut.setWhStat(Constants.MT_STAT_2);
        }
        whOut.setWhOutNo(getWhNo());
        whOut.setOutDatetime(new Date());
        whOut.setOutEmpId(UserServletContext.getUserInfo().getUserNo());
        whOut.setOutEmpName(UserServletContext.getUserInfo().getUserName());
        whOut.setComCode(UserServletContext.getUserInfo().getComCode());
        whOut.setModuCode(UserServletContext.getUserInfo().getModuCode());
        whOut.preInsert();
        //出库单表
        iWarehouseOutDao.insert(whOut);
        //修改库存
        if (whOut.getWhStat() != 1) {
            updateWhMat(outMats);
        }


        //操作人记录
        Operation op = new Operation();
        String opId = UUID.randomUUID().toString();
        op.setId(opId);
        op.setOrderNumber(whOut.getId());
        op.setOperationType(whOut.getWhStat() + "");
        op.setOperationTime(new Date());
        op.setOperationName(UserServletContext.getUserInfo().getUserName());
        op.setComCode(UserServletContext.getUserInfo().getComCode());
        op.setModuCode(UserServletContext.getUserInfo().getModuCode());
        iOperationDao.insert(op);

        //插入物料表记录；
        for (WarehouseOutMat wm : outMats) {
            OperationMat operationMat = new OperationMat();
            operationMat.setId(UUID.randomUUID().toString());
            operationMat.setMatCount(wm.getMatRealNum());
            operationMat.setMatName(wm.getMatName());
            operationMat.setOderNumber(whOut.getWhOutNo());
            operationMat.setOperationId(opId);
            operationMat.setOperationTime(wm.getCreatedDt());
            operationMat.setComCode(UserServletContext.getUserInfo().getComCode());
            operationMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            iOperationMatDao.insert(operationMat);
        }
        //修改单据状态，已经出过库的就不能再次选择出库 （用料出库单）
        if (whOut.getWhOutType() == Constants.MT_STAT_2) {
            MatApplyDetail md = new MatApplyDetail();
            md.setHasOrder(Constants.MT_STAT_1);
            md.setId(whOut.getMatApplyId());
            iMatApplyDetailDao.updateByPrimaryKeySelective(md);
        } else if (whOut.getWhOutType() == Constants.MT_STAT_1) {
            WarehouseAllot wa = new WarehouseAllot();
            wa.setId(whOut.getAllotId());
            if (whOut.getWhStat().intValue() == Constants.MT_STAT_1) {
                wa.setHasOrder(Constants.MT_STAT_3);//启用并且未出库
            } else {
                wa.setHasOrder(Constants.MT_STAT_1);
            }
            iAllotDao.updateById(wa);
        }
        result.setData(logs);
        return result;
    }

    //修改库存
    private void updateWhMat(List<WarehouseOutMat> mats) {
        if (mats.size() > 0) {
            for (WarehouseOutMat whOutm : mats) {
                //根据物料类型Id查询物料表相关数据
                WarehouseMat wa = new WarehouseMat();
                wa.setWhId(whOutm.getWhId());
                wa.setMatTypeId(whOutm.getMatTypeId());
                WarehouseMat whmat = iWarehouseMatDao.get(wa);
                if (whmat != null) {//修改库存数量
                    if (whmat.getMatNum() >= whOutm.getMatRealNum()) {
                        whmat.setMatNum(whmat.getMatNum() - whOutm.getMatRealNum());
                        int ins = iWarehouseMatDao.updateById(whmat);
                        if (ins == 0) {
                            throw new BusinessException(ReturnType.DB, "库存修改失败,联系管理员");
                        }
                    } else {
                        throw new BusinessException(ReturnType.DB, "仓库库存不足！");
                    }
                } else {
                    if (whOutm.getMatRealNum() > 0) {
                        throw new BusinessException(ReturnType.DB, "仓库库存不足！");
                    }
                }
            }
        }
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~修改
     * className: DepotOutManageServiceImpl
     * methodName: updateDepot
     * methodParam: [toonCode, addWhOutParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 18:19
     **/
    @Transactional
    public Response<StringBuffer> updateDepot(UpdateWhOtParam updateWhOtParam) {
        Response<StringBuffer> result = new Response<>();
        StringBuffer logs = new StringBuffer();
        String whId = updateWhOtParam.getWhId();
        String whOutId = updateWhOtParam.getWhOutId();
        String opId = UUID.randomUUID().toString();
        Date dt = new Date();
        List<SuppliesDetail> details = updateWhOtParam.getSuppliesDetails();
        //回滚仓库物料表相关的数量
        if (details.size() > 0) {
            for (SuppliesDetail wm : details) {
                //查询仓库物料表有则回滚入库数量
                WarehouseMat mat = new WarehouseMat();
                mat.setMatTypeId(wm.getMatTypeId());
                mat.setWhId(whId);
                mat.setComCode(UserServletContext.getUserInfo().getComCode());
                mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                WarehouseMat warehouseMat = iWarehouseMatDao.get(mat);
                //查询出库物料表原始数据
                WarehouseOutMat bdot = iWarehouseOutMatDao.getById(wm.getId());
                Long realNum = bdot.getMatRealNum();//原始实际数量
                Long matRealNum = wm.getMatRealNum();//现今实际数量
//                if (warehouseMat != null){
//                    warehouseMat.setMatNum(warehouseMat.getMatNum() - matRealNum);
//                    int ins = iWarehouseMatDao.updateById(warehouseMat);
//                    if (ins == 0){
//                        throw new BusinessException(ReturnType.DB , "库存修改失败,联系管理员");
//                    }
//                }

                //增加新数据
                WarehouseOutMat outMats = new WarehouseOutMat();
                if (warehouseMat != null) {
                    Long matNum = warehouseMat.getMatNum();//库存数量
                    if (matNum.compareTo(wm.getMatRealNum()) < 0) {
                        logs.append("物料：" + wm.getMatName() + "库存不足;");
                        outMats.setMatRealNum(matNum + realNum);
                        warehouseMat.setMatNum(0L);
                    } else if (matNum.compareTo(wm.getMatRealNum()) == 0) {
                        outMats.setMatRealNum(matNum + realNum);
                        warehouseMat.setMatNum(0L);
                    } else {
                        outMats.setMatRealNum(wm.getMatRealNum() + realNum);
                        warehouseMat.setMatNum(matNum - wm.getMatRealNum());
                    }
                } else {
                    logs.append("物料：" + wm.getMatName() + "暂时缺货;");
                    outMats.setMatRealNum(0L);
                }
                outMats.setMatSpec(wm.getMatSpec());
                outMats.setId(wm.getId());
                outMats.setComCode(UserServletContext.getUserInfo().getComCode());
                outMats.setModuCode(UserServletContext.getUserInfo().getModuCode());
                outMats.setUpdatedDt(new Date());
                //outMats.preInsert();
                iWarehouseOutMatDao.updateById(outMats);
                iWarehouseMatDao.updateById(warehouseMat);
                //插入物料操作记录；
//                Long realnumber = 0l;
//                if (realNum.compareTo(matRealNum) > 0){
//                    realnumber = realNum - matRealNum;
//                } else if (realNum.compareTo(matRealNum) < 0 ){
//                    realnumber = matRealNum - realNum;
//                }else {
//                    realnumber = matRealNum;
//                }
                OperationMat operationMat = new OperationMat();
                operationMat.setId(UUID.randomUUID().toString());
                operationMat.setMatCount(matRealNum);
                operationMat.setMatName(warehouseMat.getMatName());
                operationMat.setOderNumber(whOutId);
                operationMat.setOperationId(opId);
                operationMat.setOperationTime(dt);
                operationMat.setComCode(UserServletContext.getUserInfo().getComCode());
                operationMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                iOperationMatDao.insert(operationMat);

            }
        }
        //仓库物料库存处理
        WarehouseOutMat outMatss = new WarehouseOutMat();
        outMatss.setWhOutId(updateWhOtParam.getId());
        List<WarehouseOutMat> mats = iWarehouseOutMatDao.findList(outMatss);

        //修改出库单状态
        WarehouseOut out = new WarehouseOut();
        int yeindex = 0;
        int noIndex = 0;
        for (WarehouseOutMat wo : mats) {
            Long neetNum = wo.getMatNeetNum();//所需数量
            Long realNum = wo.getMatRealNum();//实际数量
            if (neetNum.intValue() == realNum.intValue()) {
                yeindex++;//全部出库
            } else if (realNum.intValue() == 0) {
                noIndex++;
            }
        }
        if (yeindex == mats.size()) {
            out.setWhStat(Constants.MT_STAT_3);
        } else if (noIndex == mats.size()) {
            out.setWhStat(Constants.MT_STAT_1);
        } else {
            out.setWhStat(Constants.MT_STAT_2);
        }
        out.setId(updateWhOtParam.getId());

//        if (out.getWhStat().intValue() != Constants.MT_INVITE_1){
//            updateWhMat(details,toonCode);
//        }
        iWarehouseOutDao.updateById(out);
        //物料操作人员记录
        Operation op = new Operation();
        op.setId(opId);
        op.setOrderNumber(whOutId);
        op.setOperationType(out.getWhStat() + "");
        op.setOperationTime(new Date());
        op.setOperationName(UserServletContext.getUserInfo().getUserName());
        op.setComCode(UserServletContext.getUserInfo().getComCode());
        op.setModuCode(UserServletContext.getUserInfo().getModuCode());
        op.setCreatedDt(dt);
        iOperationDao.insert(op);

        //修改调拨状态
        WarehouseOut wo = iWarehouseOutDao.getById(updateWhOtParam.getId());
        if (wo.getWhStat().intValue() != Constants.MT_STAT_1) {
            WarehouseAllot wa = new WarehouseAllot();
            wa.setId(wo.getAllotId());
            wa.setHasOrder(Constants.MT_STAT_1);
            iAllotDao.updateById(wa);
        }
        result.setData(logs);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~详情
     * className: DepotOutManageServiceImpl
     * methodName: depotDetil
     * methodParam: [toonCode, id]
     * return: com.sgai.property.depot.vo.Response<com.sgai.property.depot.vo.WarehouseOutMatVo>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/1/24 17:32
     **/
    public Response<WarehouseOutMatVo> depotDetil(String id, int pageNo, int pageSize) {
        Response<WarehouseOutMatVo> result = new Response<>();
        WarehouseOutMatVo wom = new WarehouseOutMatVo();
//        WarehouseOut wout = new WarehouseOut();
//        wout.setId(id);
        WarehouseOut whoutMat = iWarehouseOutDao.getById(id);
        if (whoutMat != null) {
            BeanUtils.copyProperties(whoutMat, wom);
            WarehouseOutMat outMat = new WarehouseOutMat();
            outMat.setWhOutId(id);
            outMat.setComCode(UserServletContext.getUserInfo().getComCode());
            outMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
            Page<WarehouseOutMat> matPage = new Page<>(pageNo, pageSize);
            matPage.setOrderBy("CREATED_DT DESC");
            outMat.setPage(matPage);
            matPage.setList(iWarehouseOutMatDao.findList(outMat));
            Page<OutDetilVo> page = new Page<>();
//            BeanUtils.copyProperties(matPage,page);
            //库存数量处理
            ArrayList<OutDetilVo> outDetilVos = new ArrayList<>();
            for (WarehouseOutMat wv : matPage.getList()) {
                OutDetilVo vo = new OutDetilVo();
                BeanUtils.copyProperties(wv, vo);
                WarehouseMat wt = new WarehouseMat();
                wt.setMatTypeId(wv.getMatTypeId());
                wt.setWhId(wv.getWhId());
                WarehouseMat mat = iWarehouseMatDao.get(wt);
                if (mat != null) {
                    vo.setMatNum(mat.getMatNum());
                } else {
                    vo.setMatNum(0L);
                }
                outDetilVos.add(vo);
            }
            page.setList(outDetilVos);
            page.setCount(iWarehouseOutMatDao.getCount(outMat));
            wom.setWarehouseOutMats(page);
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
        WarehouseOutMat outMat = new WarehouseOutMat();
        outMat.setWhOutId(id);
        outMat.setComCode(UserServletContext.getUserInfo().getComCode());
        outMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        WarehouseOut wo = iWarehouseOutDao.getById(id);
        if (wo.getWhOutType().intValue() == Constants.MT_STAT_1) {
            //修改调拨单状态
            WarehouseAllot wa = new WarehouseAllot();
            wa.setId(wo.getAllotId());
            wa.setHasOrder(0L);
            iAllotDao.updateById(wa);
        } else if (wo.getWhOutType().intValue() == Constants.MT_STAT_2) {
            MatApplyDetail applyDetail = new MatApplyDetail();
            applyDetail.setId(wo.getMatApplyId());
            applyDetail.setHasOrder(0L);
            iMatApplyDetailDao.updateByPrimaryKeySelective(applyDetail);
        }
        int del = iWarehouseOutMatDao.delete(outMat);
        int dele = iWarehouseOutDao.deleteById(id);
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
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_iWarehouseOut);
        String seqStr = Long.toString(seq);
        for (int i = seqStr.length(); i <= 6; i++) {
            seqStr = "0" + seqStr;
        }
        return "CK-" + seqStr;
    }

    //拷贝数据到response对象中
    private List<MatApplyDetailVo> getNoticeResponseInfos(List<MatApplyDetail> infoList) {
        List<MatApplyDetailVo> noticeList = new ArrayList<>();
        if (infoList != null && infoList.size() > 0) {
            for (MatApplyDetail info : infoList) {
                MatApplyDetailVo responseInfo = new MatApplyDetailVo();
                BeanUtils.copyProperties(info, responseInfo);
                noticeList.add(responseInfo);
            }
        }
        return noticeList;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~出库物料详情分页
     * className: DepotOutManageServiceImpl
     * methodName:
     * methodParam:
     * return:
     * creatUser: 600111@syswin
     * creatDateTime: 2018/6/9 13:57
     **/
    @Transactional
    public Response<Page<WhRecordVo>> detilList(RecordParam recordParam, int pageNo, int pageSize) {
        Response<Page<WhRecordVo>> result = new Response<>();
        Page<WhRecordVo> voPage = new Page<>();
        String orderId = recordParam.getOrderId();
        String orderNumber = recordParam.getOrderNumber();
        Integer isAllot = recordParam.getIsAllot();
        String whId = recordParam.getWhId();
        if (StringUtils.isNotBlank(orderId)) {//返回分页数据
            //根据orderId在虚拟表查询数据
            recordList(orderId, pageNo, pageSize, voPage);
        } else { //新增到虚拟表并返回分页数据
            //根据单号查询物料信息
            String uuid = UUID.randomUUID().toString();
            if (isAllot == 0) {
                //查询调拨用料
                WarehouseAllotMat allotMat = new WarehouseAllotMat();
                allotMat.setAllotId(orderNumber);
                allotMat.setComCode(UserServletContext.getUserInfo().getComCode());
                allotMat.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<WarehouseAllotMat> allotMatList = iWarehouseAllotMatDao.findList(allotMat);
                for (WarehouseAllotMat matDetail : allotMatList) {
//                    insertRecords(toonCode, orderNumber, uuid, matDetail ,whId);
                    insertRecord(orderNumber, uuid, null, matDetail, whId);
                }

            } else if (isAllot == 1) {
                //查询用料申请
                MatDetail mdl = new MatDetail();
                mdl.setApplyNo(orderNumber);
                mdl.setComCode(UserServletContext.getUserInfo().getComCode());
                mdl.setModuCode(UserServletContext.getUserInfo().getModuCode());
                List<MatDetail> matDetailList = iMatDetailDao.select(mdl);
                for (MatDetail matDetail : matDetailList) {
                    insertRecord(orderNumber, uuid, matDetail, null, whId);
                }
            } else if (isAllot == 6) {
                //查询计划单据物料申请
                MatDetail detail = new MatDetail();
                detail.setOrderNo(orderNumber);
                // 先根据单号进行用料明细查询，
                List<MatDetail> detailList = iMatDetailDao.select(detail);
                for (MatDetail matDetail : detailList) {
                    insertRecord(orderNumber, uuid, matDetail, null, whId);
                }
            }
            //根据uuid在虚拟表查询数据
            try {

                recordList(uuid, pageNo, pageSize, voPage);
            } catch (Exception ex) {
                throw new BusinessException(ReturnType.DB, "数据回滚");
            }
        }
        result.setData(voPage);
        return result;
    }

    private void recordList(String orderId, int pageNo, int pageSize, Page<WhRecordVo> voPage) {
        WarehousRecord record = new WarehousRecord();
        record.setComCode(UserServletContext.getUserInfo().getComCode());
        record.setModuCode(UserServletContext.getUserInfo().getModuCode());
        record.setOrderId(orderId);
        Page<WarehousRecord> page = new Page<>(pageNo, pageSize);
        page.setOrderBy("CREATED_DT DESC");
        record.setPage(page);
        page.setList(iWarehousRecordDao.findList(record));
        page.setCount(iWarehousRecordDao.getCount(record));
        for (WarehousRecord wv : page.getList()) {
            WarehouseMat wt = new WarehouseMat();
            wt.setWhId(wv.getWhId());
            wt.setMatTypeId(wv.getMatTypeId());
            WarehouseMat mat = iWarehouseMatDao.get(wt);
            if (mat != null) {
                wv.setMatNum(mat.getMatNum());
            } else {
                wv.setMatNum(0L);
            }
        }
        BeanUtils.copyProperties(page, voPage);
    }

    private void insertRecord(String orderNumber, String uuid, MatDetail matDetail, WarehouseAllotMat wMatDetail, String whId) {
        String matName = null;//物料名称
        String matTypeId = null;//物料id
        String matSpec = null;//物料规格
        String matTypeCode = null;//物料型号
        Long applyCount;//需求数量
        if (wMatDetail != null) {
            matName = wMatDetail.getMatName();//物料名称
            matTypeId = wMatDetail.getMatTypeId();//物料id
            matSpec = wMatDetail.getMatSpec();//物料规格
            matTypeCode = wMatDetail.getMatTypeCode();//物料型号
            //applyCount = wMatDetail.getMatNeetNum();//需求数量
            applyCount = wMatDetail.getMatRealNum();//需求数量
        } else {

            matName = matDetail.getMatName();//物料名称
            matTypeId = matDetail.getMatTypeId();//物料id
            matSpec = matDetail.getMatSpec();//物料规格
            matTypeCode = matDetail.getMatTypeCode();//物料型号
            applyCount = matDetail.getApplyCount() == null ? matDetail.getBuyCount() : matDetail.getApplyCount();//需求数量
        }
        WarehousRecord warehousRecord = new WarehousRecord();
        warehousRecord.setMatTypeId(matTypeId);
        warehousRecord.setMatSpec(matSpec);
        warehousRecord.setMatName(matName);
        warehousRecord.setMatTypeCode(matTypeCode);
        warehousRecord.setOrderId(uuid);
        warehousRecord.setComCode(UserServletContext.getUserInfo().getComCode());
        warehousRecord.setModuCode(UserServletContext.getUserInfo().getModuCode());
        warehousRecord.setMatNeetNum(applyCount);
        warehousRecord.setMatRealNum(0L);
        warehousRecord.setOrderNumber(orderNumber);
        warehousRecord.setWhId(whId);
        String uuId = UUID.randomUUID().toString();
        warehousRecord.setId(uuId);
        warehousRecord.setUpdatedBy(UserServletContext.getUserInfo().getUserName());
        warehousRecord.setUpdatedDt(new Date());
        warehousRecord.setCreatedBy(UserServletContext.getUserInfo().getUserName());
        warehousRecord.setCreatedDt(new Date());
        // 然后把用料明细添加到中间表中，
        iWarehousRecordDao.insert(warehousRecord);
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~即时保存
     * className: DepotOutManageServiceImpl
     * methodName: instantSaving
     * methodParam: [toonCode, instantSavParam]
     * return: com.sgai.property.depot.vo.Response<java.lang.Boolean>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/6/11 10:44
     **/
    public Response<Boolean> instantSaving(InstantSavParam instantSavParam) {
        Response<Boolean> response = new Response<>();
        WarehousRecord record = new WarehousRecord();
        record.setId(instantSavParam.getId());
        record.setMatRealNum(instantSavParam.getMatRealNum());
        iWarehousRecordDao.updateById(record);
        response.setData(true);
        return response;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~批量新增接口
     * className: DepotOutManageServiceImpl
     * methodName: addListDepot
     * methodParam: [toonCode, whOutParamList]
     * return: com.sgai.property.depot.vo.Response<java.lang.StringBuffer>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/6/11 15:01
     **/
    public Response<StringBuffer> addListDepot(List<AddWhOutParam> whOutParamList) {
        Response<StringBuffer> result = new Response<>();
        StringBuffer buffer = new StringBuffer();
        Response<StringBuffer> addLogs = null;
        for (AddWhOutParam wp : whOutParamList) {
            //判断是调拨还是用料
            if (wp.getWhOutType().intValue() == Constants.MT_INVITE_2.intValue()) {
                //用料申请单是否已经被占用
                MatApplyDetail applyDetail = iMatApplyDetailDao.selectByPrimaryKey(wp.getMatApplyId());
                Long hasOrder = applyDetail.getHasOrder();//是否生成订单？0：没有，1：有
                if (hasOrder.intValue() == 1) {
                    buffer.append(applyDetail.getApplyNo() + "已被别人生成订单;");
                } else {
                    //新增出库单据
                    addLogs = addDepot(wp);
                    buffer.append(addLogs.getData());
                }
            } else if (wp.getWhOutType().intValue() == Constants.MT_STAT_1.intValue()) {
                WarehouseAllot byId = iAllotDao.getById(wp.getAllotId());
                if (byId.getHasOrder().intValue() == Constants.MT_INVITE_1) {
                    buffer.append(byId.getAllotNo() + "已被别人生成订单;");
                } else {
                    addLogs = addDepot(wp);
                    buffer.append(addLogs.getData());
                }
            }

        }
        result.setData(buffer);
        return result;

    }
}
