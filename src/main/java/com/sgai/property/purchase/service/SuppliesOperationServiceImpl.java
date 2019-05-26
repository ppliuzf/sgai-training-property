package com.sgai.property.purchase.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.dao.IMatOrderDao;
import com.sgai.property.depot.dao.IWarehouseOutDao;
import com.sgai.property.depot.dao.IWarehouseOutMatDao;
import com.sgai.property.depot.entity.WarehouseOut;
import com.sgai.property.depot.entity.WarehouseOutMat;
import com.sgai.property.purchase.constants.Constants;
import com.sgai.property.purchase.dao.*;
import com.sgai.property.purchase.entity.*;
import com.sgai.property.purchase.vo.ApprovalParam;
import com.sgai.property.purchase.vo.SuppliesApproveParam;
import com.sgai.property.purchase.vo.SuppliesApproveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用料操作相关 on 2018/1/11.
 */
@Service
@Transactional
public class SuppliesOperationServiceImpl {
    @Autowired
    SuppliesOperationVoDao suppliesOperationVoDao;
    @Autowired
    IMatApplyDetailExtDao iMatApplyDetailExtDao;
    @Autowired
    IMatApplyDetailImgDao iMatApplyDetailImgDao;
    @Autowired
    IMatDetailDao iMatDetailDao;
    @Autowired
    IMatApplyDetailDao iMatApplyDetailDao;
    @Autowired
    IMatOrderExtDao matOrderExtDao;
    @Autowired
    IMatOrderDao matOrderDao;
    @Autowired
    IPlanDao iPlanDao;
    @Autowired
    IPlanTaskDao iPlanTaskDao;
    @Autowired
    IPlanDetailDao iPlanDetailDao;
    @Autowired
    IDepotWarehouseDao depotWareHouseDao;
    @Autowired
    IWarehouseOutDao iWarehouseOutDao;
    @Autowired
    IWarehouseOutMatDao iWarehouseOutMatDao;
    @Autowired
    IWarehouseMatDao iWarehouseMatDao;
    @Autowired
    IPlanDao planDao;
    @Autowired
    private BaseCodeService baseCodeService;

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 新增用料申请单
     * @MethodParameters
     */
    public Response<Boolean> infoApproval(SuppliesParam suppliesParam) {
        Response<Boolean> result = new Response<>();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATEFORMAT_YYYYMMDDHHMMSS);
        //用料申请单
        MatApplyDetail mad = new MatApplyDetail();
        mad.setId(UUID.randomUUID().toString());
        mad.setApplyEmpId(UserServletContext.getUserInfo().getEmCode());
        mad.setApplyEmpName(UserServletContext.getUserInfo().getUserName());
        mad.setApplyDeptName(UserServletContext.getUserInfo().getComName());
        mad.setApplyReason(suppliesParam.getApplyReason());
        mad.setSupplyDate(suppliesParam.getSupplyDate());
        mad.setApplyDate(new Date());
        mad.setMatStat(Constants.MT_STAT_1);
        mad.setApplyNo(getApplyNo());
        mad.setComCode(UserServletContext.getUserInfo().getComCode());
        mad.setModuCode(UserServletContext.getUserInfo().getModuCode());
        mad.setHasOrder(0L);
        int it = iMatApplyDetailDao.insert(mad);
        //图片
        int ins = -1;
        if (suppliesParam.getImgUrl() != null && !suppliesParam.getImgUrl().equals("")) {

            MatApplyDetailImg mimg = new MatApplyDetailImg();
            mimg.setId(UUID.randomUUID().toString());
            mimg.setImgUrl(suppliesParam.getImgUrl());
            mimg.setApplyId(mad.getId());
            ins = iMatApplyDetailImgDao.insert(mimg);
        }
        //物料明细
        MatDetail ma = new MatDetail();
        int inse = -1;
        if (suppliesParam.getSuppliesDetails().size() > 0) {

            ma.setApplyNo(mad.getApplyNo());

            for (SuppliesDetail su : suppliesParam.getSuppliesDetails()) {
                ma.setId(UUID.randomUUID().toString());
                ma.setMatName(su.getMatName());
                ma.setMatSpec(su.getMatSpec());
                ma.setMatTypeCode(su.getMatTypeCode());
                ma.setApplyCount(su.getApplyCount());
                ma.setMatTypeId(su.getMatTypeId());
                ma.setComCode(UserServletContext.getUserInfo().getComCode());
                ma.setModuCode(UserServletContext.getUserInfo().getModuCode());
                inse = iMatDetailDao.insert(ma);
            }
        }

        if (it == 0) {
            result.setData(false);
            result.setMessage("用料申请失败，稍后再试；");
        } else if (ins == 0) {
            result.setData(false);
            result.setMessage("用料申请失败，稍后再试；");
        } else if (inse == 0) {
            result.setData(false);
            result.setMessage("用料申请失败，稍后再试；");
        } else {
            result.setData(true);
        }
        return result;
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 生成ApplyNo
     * @MethodParameters
     */
    private String getApplyNo() {
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_iMatApplyDetailExt);
        String seqStr = Long.toString(seq);
        for (int i = seqStr.length(); i < 8; i++) {
            seqStr = "0" + seqStr;
        }
        return "CL-" + seqStr;
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 用料申请单数据列表
     * @MethodParameters
     */
    public Response<Page<MatApplyDetail>> suppliesList(int pageNum, int pageSize, String applyEmpName) {
        Response<Page<MatApplyDetail>> result = new Response<>();
        MatApplyDetail mat = new MatApplyDetail();
        if (applyEmpName != null && !applyEmpName.equals("")) {
            applyEmpName = "'%" + applyEmpName + "%'";
            mat.setApplyEmpName(applyEmpName);
        }
        mat.setComCode(UserServletContext.getUserInfo().getComCode());
        mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<MatApplyDetail> matdetail = new Page<>(pageNum, pageSize);
        mat.setPage(matdetail);

        matdetail.setList(suppliesOperationVoDao.findDetailList(mat));

        result.setData(matdetail);
        return result;
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 用料申请单详情
     * @MethodParameters
     */
    public Response<SuppliesParam> suppliesDetil(String id) {
        Response<SuppliesParam> result = new Response<>();
        SuppliesParam sp = new SuppliesParam();
        //主表数据
        MatApplyDetail madl = iMatApplyDetailDao.selectByPrimaryKey(id);
        if (madl != null) {
            BeanUtils.copyProperties(madl, sp);
        } else {
            throw new BusinessException(ReturnType.DB, "查询不到此申请单！");
        }

        //图片
        MatApplyDetailImg mimg = new MatApplyDetailImg();
        mimg.setApplyId(id);
        MatApplyDetailImg madImg = iMatApplyDetailImgDao.get(mimg);

        if (madImg != null && StringUtils.isNotBlank(madImg.getImgUrl())) {
            sp.setImgUrl(madImg.getImgUrl());
            sp.setImgID(madImg.getId());
        }

        //物料数据集合
        MatDetail ma = new MatDetail();
        ma.setApplyNo(madl.getApplyNo());
        List<MatDetail> maLists = iMatDetailDao.select(ma);
        if (maLists.size() > 0) {
            //数据拷贝
            List<SuppliesDetail> detail = getNoticeResponseInfos(maLists);
            if (detail.size() > 0) {
                sp.setSuppliesDetails(detail);
            }
        }

        result.setData(sp);
        return result;
    }

    //拷贝数据到response对象中
    private List<SuppliesDetail> getNoticeResponseInfos(List<MatDetail> infoList) {
        List<SuppliesDetail> lists = new ArrayList<SuppliesDetail>();
        if (infoList != null && infoList.size() > 0) {
            for (MatDetail info : infoList) {
                SuppliesDetail responseInfo = new SuppliesDetail();
                BeanUtils.copyProperties(info, responseInfo);
                lists.add(responseInfo);
            }
        }
        return lists;
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 修改用料申请单
     * @MethodParameters
     */
    @Transactional
    public Response<Boolean> updateSupplies(SuppliesParam suppliesParam) {
        Response<Boolean> result = new Response<>();
        //用料申请单
        MatApplyDetail mad = new MatApplyDetail();
        mad.setId(suppliesParam.getId());
        mad.setApplyReason(suppliesParam.getApplyReason());
        mad.setSupplyDate(suppliesParam.getSupplyDate());
        mad.setApplyDate(new Date());
        int it = iMatApplyDetailDao.updateByPrimaryKeySelective(mad);
        //图片
        int ins = -1;
        MatApplyDetailImg mimg = new MatApplyDetailImg();
        if (StringUtils.isNotBlank(suppliesParam.getImgID())) {
            ins = iMatApplyDetailImgDao.deleteById(suppliesParam.getImgID());
        }
        if (StringUtils.isNotBlank(suppliesParam.getImgUrl())) {
            mimg.setId(UUID.randomUUID().toString());
            mimg.setImgUrl(suppliesParam.getImgUrl());
            mimg.setApplyId(suppliesParam.getId());
            ins = iMatApplyDetailImgDao.insert(mimg);
        }
        //物料明细
        int inse = -1;
        if (suppliesParam.getSuppliesDetails().size() > 0) {
            //查询申请单编号
            MatApplyDetail applyDetail = iMatApplyDetailDao.selectByPrimaryKey(suppliesParam.getId());
            //根据编号查询物料信息
            if(applyDetail.getApplyNo() != null && !"".equals(applyDetail.getApplyNo())) {
            	 Example matDelExample = new Example(MatDetail.class);
                 matDelExample.createCriteria().andEqualTo("applyNo", applyDetail.getApplyNo());
                 iMatDetailDao.deleteByExample(matDelExample);
            }
           
            //插入改动后的数据
            if (suppliesParam.getSuppliesDetails().size() > 0) {
                for (SuppliesDetail su : suppliesParam.getSuppliesDetails()) {
                    MatDetail ma = new MatDetail();
                    ma.setId(UUID.randomUUID().toString());
                    ma.setMatName(su.getMatName());
                    ma.setMatSpec(su.getMatSpec());
                    ma.setApplyCount(su.getApplyCount());
                    ma.setMatTypeCode(su.getMatTypeCode());
                    ma.setMatTypeId(su.getMatTypeId());
                    ma.setApplyNo(suppliesParam.getApplyNo());
                    ma.setComCode(UserServletContext.getUserInfo().getComCode());
                    ma.setModuCode(UserServletContext.getUserInfo().getModuCode());
                    inse = iMatDetailDao.insert(ma);
                }
            }
        }

        if (it == 0) {
            result.setData(false);
            result.setMessage("修改用料申请单失败，请联系管理员！");
        } else if (ins == 0) {
            result.setData(false);
            result.setMessage("操作用料图片失败，请联系管理员！");
        } else if (inse == 0) {
            result.setData(false);
            result.setMessage("修改物料明细失败，请联系管理员！");
        } else {
            result.setData(true);
        }
        return result;
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 用料审批列表
     * @MethodParameters
     */
    public Response<Page<SuppliesApproveVo>> suppliesApproves(int pageNum, int pageSize, SuppliesApproveParam suppliesApproveParam) {
        Response<Page<SuppliesApproveVo>> result = new Response<>();

        MatApplyDetail mat = new MatApplyDetail();
        String applyEmpName = suppliesApproveParam.getApplyEmpName();
        if (applyEmpName != null && !applyEmpName.equals("")) {
            applyEmpName = "'%" + applyEmpName + "%'";
            mat.setApplyEmpName(applyEmpName);
        }
        if (suppliesApproveParam.getMatStat() > 0) {
            mat.setMatStat(suppliesApproveParam.getMatStat());
        }
        mat.setModuCode(UserServletContext.getUserInfo().getModuCode());
        mat.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<MatApplyDetail> matdetail = new Page<>(pageNum, pageSize);
        mat.setPage(matdetail);
        if (suppliesApproveParam.getMatStat().intValue() == 2) {
            matdetail.setList(suppliesOperationVoDao.suppliesApprovest(mat));
        } else {

            matdetail.setList(suppliesOperationVoDao.suppliesApproves(mat));
        }
        Page<SuppliesApproveVo> matdetailvo = new Page<SuppliesApproveVo>();
        if (matdetail.getList().size() > 0) {

            BeanUtils.copyProperties(matdetail, matdetailvo);
        }
        result.setData(matdetailvo);
        return result;

    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 撤回
     * @MethodParameters
     */
    public Boolean suppliesRetract(String id) {
        MatApplyDetail detail = iMatApplyDetailDao.selectByPrimaryKey(id);
        if (detail != null) {
            if (detail.getMatStat() == Constants.MT_STAT_4) {
                throw new BusinessException(ReturnType.DB, "申请单已撤回");
            } else {
                //更新撤回状态
                MatApplyDetail mat = new MatApplyDetail();
                mat.setId(id);
                mat.setMatStat(Constants.MT_STAT_4);
                int count = iMatApplyDetailDao.updateByPrimaryKeySelective(mat);
                return count == 1;
            }
        } else {
            throw new BusinessException(ReturnType.DB, "没有查到申请单信息信息");
        }
    }

    /**
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 审批用料
     * @MethodParameters
     */
    public Boolean suppliesApproval(ApprovalParam approvalParam) {
        MatApplyDetail detail = iMatApplyDetailDao.selectByPrimaryKey(approvalParam.getId());
        if (detail == null) {
            return false;
        }
        if (detail.getMatStat() != Constants.MT_STAT_1) {
            throw new BusinessException(ReturnType.DB, "申请单不是待审核状态！");
        }
        MatApplyDetail matd = new MatApplyDetail();
        if (approvalParam.getApprovalStatus().equals("Y")) {
            matd.setMatStat(Constants.MT_STAT_2);
        } else {
            matd.setMatStat(Constants.MT_STAT_3);
        }
        matd.setId(approvalParam.getId());
        matd.setApproveEmpId(UserServletContext.getUserInfo().getEmCode());
        matd.setApproveEmpName(UserServletContext.getUserInfo().getUserName());
        matd.setApproveDate(new Date());
        matd.setApproveOption(approvalParam.getApprovalOpinition());
        int count = iMatApplyDetailDao.updateByPrimaryKeySelective(matd);//更新审核状态信息
        if (!approvalParam.getApprovalStatus().equals("Y") || count == 0) {// 更新失败或审核未通过
            return true;
        }

        //:MRP算法
        // 获取最新的用料申请信息
        MatApplyDetail mapApplyDetail = iMatApplyDetailDao.selectByPrimaryKey(approvalParam.getId());
        MatDetail matDetail = new MatDetail();
        matDetail.setApplyNo(mapApplyDetail.getApplyNo());
        List<MatDetail> matDetailList = iMatDetailDao.select(matDetail);// 根据需求编号查询所需的全部物品
        // 库存统计与采购申请
        purchaseApply(matDetailList, approvalParam);
        List<MatDetail> matDetailListPlus = plusMatDetailList(matDetailList);
        if (matDetailListPlus == null || matDetailListPlus.size() == 0) {
            return true;
        }
        return true;
    }


    /**
     * 剩余物料
     *
     * @param matDetailList
     * @return
     */
    private List<MatDetail> plusMatDetailList(List<MatDetail> matDetailList) {
        List<MatDetail> result = new ArrayList<MatDetail>();
        for (MatDetail one : matDetailList) {
            if (one.getApplyCount() > 0) {
                result.add(one);
            }
        }
        return result;
    }

    /**
     * 库存统计与采购申请
     *  @param matDetailList 物料信息
     * @param approvalParam 审批信息
     */
    private boolean purchaseApply(List<MatDetail> matDetailList, ApprovalParam approvalParam) {
        ArrayList<PlanDetail> pdls = new ArrayList<>();
        MatApplyDetail matApplyDetail = new MatApplyDetail();
        matApplyDetail.setPurchasetype("0");// 全部采购
        //查询物料明细，统计需采购的物料
        for (MatDetail oneMat : matDetailList) {
            WarehouseOutMat outMat = new WarehouseOutMat();
            // 对仓库分组，查询仓库中物料信息
//            List<WarehouseMat> warehouseMatList = iWarehouseMatDao.getDepotWareHouse(oneMat.getMatTypeId());
//            for (WarehouseMat whMat : warehouseMatList) {
           Long wmSum = iWarehouseMatDao.getDepotWareHouse(oneMat.getMatTypeId());
                //计算出库数量
                Long outCount = 0L;
//            if (oneMat.getApplyCount().compareTo(whMat.getMatNum()) > 0)
                if (oneMat.getApplyCount().compareTo(wmSum) > 0) {
                    outCount = wmSum;
                    oneMat.setApplyCount(oneMat.getApplyCount() - outCount);
                    matApplyDetail.setPurchasetype("1");// 部分采购
                } else {
                    outCount = oneMat.getApplyCount();
                    oneMat.setApplyCount(0L);
                }
                outMat.setMatRealNum(outCount);// 实际数量
                outMat.setMatNeetNum(oneMat.getApplyCount());// 所需数量
                if (oneMat.getApplyCount().intValue() == 0) {
//                    return true;
                    continue ;
                }
//            }

            //:采购申请
            // 更新采购单类型
            matApplyDetail.setId(approvalParam.getId());
            int count = iMatApplyDetailDao.updateByPrimaryKeySelective(matApplyDetail);
            if (count == 0) {
                return false;
            }

            outMat.setMatName(oneMat.getMatName());
            outMat.setMatSpec(oneMat.getMatSpec());
            outMat.setMatTypeCode(oneMat.getMatTypeCode());
            oneMat.setApplyCount(oneMat.getApplyCount());// 所需数量（申请输入的数量）
            outMat.setMatNeetNum(oneMat.getApplyCount());// 实际需要数量（库存-所需）直接走采购，所需数量与实际需要输入一致。
                    //将物料明细数据导入到计划用料表
                    PlanDetail pd = new PlanDetail();
                    pd.setId(UUID.randomUUID().toString());
//                    pd.setTaskId(planTask.getId());
                    pd.setMatName(outMat.getMatName());
                    pd.setMatSpec(outMat.getMatSpec());
                    pd.setMatTypeCode(outMat.getMatTypeCode());
                    pd.setApplyCount(outMat.getMatNeetNum());
                    pd.setMatTypeId(oneMat.getMatTypeId());
                    pd.setBuyCount(oneMat.getApplyCount());
                    pdls.add(pd);
//                    iPlanDetailDao.insert(pd);// 插入采购计划详情信息
//                }
            }
            // 采购申请
        if (pdls.size() > 0){
            MatApplyDetail dl = iMatApplyDetailDao.selectByPrimaryKey(approvalParam.getId());// 获取用料申请信息
            if (dl != null && dl.getMatStat().intValue() == 2) {
                //审核成功将数据拷贝到任务申请表
                PlanTask planTask = new PlanTask();
                planTask.setId(UUID.randomUUID().toString());
                planTask.setTaskNo(dl.getApplyNo());//申请编号
                planTask.setTaskEmpId(dl.getApplyEmpId());//申请人ID
                planTask.setTaskEmpName(dl.getApplyEmpName());//申请人姓名
                planTask.setTaskDept(getDeptInfo(UserServletContext.getUserInfo().getDeptCode(), dl.getApplyDeptName()));//所属部门
                planTask.setTaskOpinion(dl.getApplyReason());//申请理由
                planTask.setTaskNeedStatus(Constants.MT_STAT_2);//需求状态
                planTask.setPlanMatStat(Constants.MT_STAT_1);//是否审批通过
                planTask.setTaskPlanMat(Constants.MT_STAT_1);//数据来源：1.用料2.计划
//                planTask.setTaskPuchaseName(outMat.getMatName());
                planTask.setModuCode(UserServletContext.getUserInfo().getModuCode());
                planTask.setComCode(UserServletContext.getUserInfo().getComCode());
                int ins = iPlanTaskDao.insert(planTask);// 插入采购计划信息
                if (ins == 1) {
                    for (PlanDetail pdl : pdls) {
                        pdl.setTaskId(planTask.getId());
                        iPlanDetailDao.insert(pdl);// 插入采购计划详情信息
                    }
                }
            }
        }
        return true;
    }

    /**
     * 单库出库物料
     * 减少matDetailList出库物料数值
     *  @param one
     * @param matDetailList
     */
    private void matOutOnDepot(DepotWarehouse one, List<MatDetail> matDetailList, String matApplyId) {
        WarehouseOut whOut = new WarehouseOut();
//        BeanUtils.copyProperties(addWhOutParam,whOut);
        whOut.setWhId(one.getId());
        whOut.setWhName(one.getWhName());
        whOut.setWhType(one.getWhType());
        whOut.setMatApplyId(matApplyId);
        whOut.setMatApplyName("MRP出库");
        whOut.setWhOutType(2L);
        whOut.setWhStat(3L);

        whOut.setId(UUID.randomUUID().toString());
        whOut.setWhOutNo(getWhNo());
        whOut.setOutDatetime(new Date());
        whOut.setOutEmpId(UserServletContext.getUserInfo().getEmCode());
        whOut.setOutEmpName(UserServletContext.getUserInfo().getUserName());
        int inst = iWarehouseOutDao.insert(whOut);
        if (inst == 0) {
            throw new BusinessException(ReturnType.DB, "新建出库单失败！");
        }
        //出库物料明细
        for (MatDetail oneMat : matDetailList) {
            WarehouseOutMat outMat = new WarehouseOutMat();
            outMat.setWhOutId(whOut.getId());
            outMat.setWhId(one.getId());
            outMat.setId(UUID.randomUUID().toString());
            outMat.setMatTypeId(oneMat.getMatTypeId());
            outMat.setMatName(oneMat.getMatName());
            //计算出库数量
            WarehouseMat whMat = new WarehouseMat();
            whMat.setWhId(one.getId());
            whMat.setMatTypeId(oneMat.getMatTypeId());
            whMat = iWarehouseMatDao.get(whMat);
            Long outCount = 0L;
            if (whMat == null)
                continue;
            if (oneMat.getApplyCount().compareTo(whMat.getMatNum()) > 0) {
                outCount = whMat.getMatNum();
                oneMat.setApplyCount(oneMat.getApplyCount() - outCount);
            } else {
                outCount = oneMat.getApplyCount();
                oneMat.setApplyCount(0L);
            }
            outMat.setMatRealNum(outCount);// 实际数量
            outMat.setMatNeetNum(oneMat.getApplyCount());// 所需数量

            outMat.setMatSpec(oneMat.getMatSpec());// 物料规格
            int inser = iWarehouseOutMatDao.insert(outMat);// 插入仓库与用料关联

            if (inser == 0) {
                throw new BusinessException(ReturnType.DB, "请联系管理员，物料关联失败！");
            }
        }
        // 仓库物料处理
        WarehouseOutMat outMat = new WarehouseOutMat();
        outMat.setWhOutId(whOut.getId());
        List<WarehouseOutMat> mats = iWarehouseOutMatDao.findList(outMat);
        if (mats != null && mats.size() > 0) {
            // 更新库存
            updateWhMat(mats);
        }
    }

    //修改库存
    private void updateWhMat(List<WarehouseOutMat> mats) {
        if (mats.size() > 0) {
            for (WarehouseOutMat whOutm : mats) {
                //根据物料类型Id查询物料表相关数据
                WarehouseMat wa = new WarehouseMat();
                wa.setMatTypeId(whOutm.getMatTypeId());
                wa.setWhId(whOutm.getWhId());
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
     * @Author 杨鹏伟 【syswin.600111】
     * @Date 2018/1/11 10:36
     * @ClassName SuppliesOperationServiceImpl
     * @MethodName 领取
     * @MethodParameters
     */
    public Boolean getSupplies(String id) {
        MatApplyDetail detail = iMatApplyDetailDao.selectByPrimaryKey(id);
        if (detail != null) {
            if (detail.getMatStat() == Constants.MT_STAT_5) {
                throw new BusinessException(ReturnType.DB, "申请单已领取");
            } else {
                //更新领取状态
                MatApplyDetail mat = new MatApplyDetail();
                mat.setId(id);
                mat.setMatStat(Constants.MT_STAT_5);
                int count = iMatApplyDetailDao.updateByPrimaryKeySelective(mat);
                return count == 1;
            }
        } else {
            throw new BusinessException(ReturnType.DB, "没有查到申请单信息信息");
        }
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

    /**
     * 整理部门信息与组织树同样数据格式
     *
     * @param deptCode 部门ID
     * @param deptName 部门名称
     * @return 组织树格式部门信息
     */
    private String getDeptInfo(String deptCode, String deptName) {
        // 拼接与组织数同样格式部门数据
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        json.put("isDept", true);
        json.put("id", deptCode == null || deptCode.equals("") ? "" : deptCode);
        json.put("title", deptName == null || deptName.equals("") ? "" : deptName);
        json.put("avatar", "");
        json.put("empNum", 1);
        array.add(json);

        return array.toString();
    }
}
