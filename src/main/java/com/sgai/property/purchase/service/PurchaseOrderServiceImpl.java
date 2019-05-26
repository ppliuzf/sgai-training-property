package com.sgai.property.purchase.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.depot.dao.IMatOrderDao;
import com.sgai.property.depot.entity.MatOrder;
import com.sgai.property.purchase.dao.IMatApplyDetailDao;
import com.sgai.property.purchase.dao.IMatDetailDao;
import com.sgai.property.purchase.dao.IMatOrderExtDao;
import com.sgai.property.purchase.entity.MatApplyDetail;
import com.sgai.property.purchase.entity.MatDetail;
import com.sgai.property.purchase.param.MatOrderParam;
import com.sgai.property.purchase.vo.MatApplyDetailVo;
import com.sgai.property.purchase.vo.MatDetailVo;
import com.sgai.property.purchase.vo.MatOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 145811 on 2018/1/11.
 */
@Service
public class PurchaseOrderServiceImpl {

    @Autowired
    IMatOrderExtDao matOrderExtDao;

    @Autowired
    IMatOrderDao matOrderDao;

    @Autowired
    IMatDetailDao matDetailDao;
    @Autowired
    IMatApplyDetailDao matApplyDetailDao;
    @Autowired
    private BaseCodeService baseCodeService;


    private String genOrderNo() {
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_matOrderExt);
        String seqStr = Long.toString(seq);
        for (int i = seqStr.length(); i < 8; i++) {
            seqStr = "0" + seqStr;
        }
        return "CA-" + seqStr;
    }

    public Page<MatOrderVo> searchList(String orderNo, Long orderType, Long orderStat, String invoiceState, int pageNum, int pageSize) {
        MatOrder matOrder = new MatOrder();
        if (StringUtils.isNotBlank(orderNo)) {
            matOrder.setOrderNo(orderNo);
        }
        if (orderType != null && orderType > 0) {
            matOrder.setOrderType(orderType);
        }
        if (orderStat != null && orderStat > 0) {
            matOrder.setOrderStat(orderStat);
        }
        if (StringUtils.isNoneBlank(invoiceState)) {
            matOrder.setInvoiceState(invoiceState);
        }
        matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
        matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<MatOrder> pageInfo = new Page<MatOrder>(pageNum, pageSize);
        pageInfo.setOrderBy("ORDER_NO desc");
        matOrder.setPage(pageInfo);

        Page<MatOrder> page = pageInfo.setList(matOrderDao.findList(matOrder));
        List<MatOrderVo> voList = new ArrayList<MatOrderVo>();
        CopyHelper.copyList(page.getList(), voList, MatOrderVo.class);

        Page<MatOrderVo> pageVo = new Page<MatOrderVo>();
        BeanCopier copier = BeanCopier.create(page.getClass(), pageVo.getClass(), false);
        copier.copy(page, pageVo, null);
        pageVo.setList(voList);

        return pageVo;
    }

    public Boolean addOrder(MatOrderParam matOrderParam) {
        if (matOrderParam == null || matOrderParam.getOrderType() == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "");
        }
        //待审核
        matOrderParam.setOrderStat(1L);
        if (matOrderParam.getOrderType() == 1) {
            //需求订单
            saveOrUpdateOrderType1st(matOrderParam);

        } else if (matOrderParam.getOrderType() == 2) {
            //主动采购
            saveOrUpdateOrderType2nd(matOrderParam);
        } else {
            throw new BusinessException(ReturnType.ParamIllegal, "");
        }

        return true;
    }

    //需求订单
    private void saveOrUpdateOrderType1st(MatOrderParam matOrderParam) {
        if (StringUtils.isBlank(matOrderParam.getApplyNo())) {
            throw new BusinessException(ReturnType.ParamIllegal, "需求编号为空");
        }
        MatOrder matOrder = null;
        String orderNo = null;
        Date dt = new Date();
        if (matOrderParam.getId() == null) { //new
            matOrder = new MatOrder();
            CopyHelper.copyObj(matOrderParam, matOrder);
            matOrder.setId(UUID.randomUUID().toString());
            //生成订单号
            orderNo = genOrderNo();
            matOrder.setOrderNo(orderNo);
            //
            matOrder.setUpdatedDt(dt);
            //制单人
            matOrder.setOrderEmpId(UserServletContext.getUserInfo().getEmCode());
            matOrder.setOrderEmpName(UserServletContext.getUserInfo().getUserName());
            matOrder.setApplyDeptName(UserServletContext.getUserInfo().getComName());
            matOrder.setOrderDate(dt);
            matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
            matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
            matOrderDao.insert(matOrder);
            //更新无聊申请，设置订单标识位
            MatApplyDetail applyDetail = new MatApplyDetail();
            applyDetail.setApplyNo(matOrderParam.getApplyNo());
            List<MatApplyDetail> applyDetailList = matApplyDetailDao.select(applyDetail);
            if (applyDetailList != null && applyDetailList.size() > 0) {
                MatApplyDetail detail = applyDetailList.get(0);
                detail.setHasOrder(1L);
                matApplyDetailDao.updateByPrimaryKeySelective(detail);
            }
        } else { //udpate
            matOrder = matOrderDao.getById(matOrderParam.getId());
            orderNo = matOrder.getOrderNo();
            if (matOrder == null) {
                throw new BusinessException(ReturnType.DB, "数据异常");
            }
            CopyHelper.copyObj(matOrderParam, matOrder);
            //
            matOrder.setUpdatedDt(dt);
            //制单人
            matOrder.setOrderEmpId(UserServletContext.getUserInfo().getEmCode());
            matOrder.setOrderEmpName(UserServletContext.getUserInfo().getUserName());
            matOrder.setApplyDeptName(UserServletContext.getUserInfo().getComName());
            matOrder.setUpdatedDt(dt);
            matOrder.setOrderDate(dt);
            matOrderDao.updateById(matOrder);
        }
        //update sub table
        if (matOrderParam.getMatDetailParamList() == null || matOrderParam.getMatDetailParamList().size() == 0) {
            return;
        }
        for (int i = 0; i < matOrderParam.getMatDetailParamList().size(); i++) {
            MatDetail matDetail = matDetailDao.selectByPrimaryKey(matOrderParam.getMatDetailParamList().get(i).getId());
            CopyHelper.copyObj(matOrderParam.getMatDetailParamList().get(i), matDetail);
            matDetail.setOrderNo(orderNo);
            matDetail.setUpdatedDt(dt);
            matDetailDao.updateByPrimaryKeySelective(matDetail);
        }

    }

    //主动采购
    private void saveOrUpdateOrderType2nd(MatOrderParam matOrderParam) {
        MatOrder matOrder = null;
        String orderNo = "";
        Date dt = new Date();
        if (matOrderParam.getId() == null) { //new
            matOrder = new MatOrder();
            CopyHelper.copyObj(matOrderParam, matOrder);
            matOrder.setId(UUID.randomUUID().toString());
            //生成订单号
            orderNo = genOrderNo();
            matOrder.setOrderNo(orderNo);
            //
            matOrder.setUpdatedDt(dt);
            //制单人
            matOrder.setOrderEmpId(UserServletContext.getUserInfo().getEmCode());
            matOrder.setOrderEmpName(UserServletContext.getUserInfo().getUserName());
            matOrder.setApplyDeptName(UserServletContext.getUserInfo().getComName());
            matOrder.setOrderDate(dt);
            matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
            matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
            matOrderDao.insert(matOrder);
        } else { //udpate
            matOrder = matOrderDao.getById(matOrderParam.getId());
            orderNo = matOrder.getOrderNo();
            if (matOrder == null) {
                throw new BusinessException(ReturnType.DB, "数据异常");
            }
            CopyHelper.copyObj(matOrderParam, matOrder);
            //
            matOrder.setUpdatedDt(dt);
            //制单人
            matOrder.setOrderEmpId(UserServletContext.getUserInfo().getEmCode());
            matOrder.setOrderEmpName(UserServletContext.getUserInfo().getUserName());
            matOrder.setApplyDeptName(UserServletContext.getUserInfo().getComName());
            matOrder.setUpdatedDt(dt);
            matOrder.setOrderDate(dt);
            matOrderDao.updateById(matOrder);
        }

        //子表处理
        if (matOrderParam.getId() == null || matOrderParam.getOrderType() == 2) {
            MatDetail matDetail = new MatDetail();
            if (StringUtils.isBlank(orderNo)) {
                throw new BusinessException(ReturnType.Error, "订单编号为空，请调试");
            }
            matDetail.setOrderNo(orderNo);
            matDetailDao.delete(matDetail);
        }
        // insert subTable
        if (matOrderParam.getMatDetailParamList() == null || matOrderParam.getMatDetailParamList().size() == 0) {
            return;
        }
        for (int i = 0; i < matOrderParam.getMatDetailParamList().size(); i++) {
            MatDetail matDetail = matDetailDao.selectByPrimaryKey(matOrderParam.getMatDetailParamList().get(i).getId());
            if (matDetail == null) {
                matDetail = new MatDetail();
            }
            CopyHelper.copyObj(matOrderParam.getMatDetailParamList().get(i), matDetail);
            matDetail.setOrderNo(orderNo);
            matDetail.setUpdatedDt(dt);

            if (matOrderParam.getOrderType() == 1) {
                matDetailDao.updateByPrimaryKeySelective(matDetail);
            } else {
                matDetail.setId(UUID.randomUUID().toString());
                matDetailDao.insert(matDetail);
            }

        }

    }

    public MatOrderVo getOrderDetail(String id) {
        MatOrder matOrder = matOrderDao.getById(id);
        if (matOrder == null) {
            return null;
        }
        MatOrderVo vo = new MatOrderVo();
        CopyHelper.copyObj(matOrder, vo);
        MatDetail matDetail = new MatDetail();
        matDetail.setOrderNo(matOrder.getOrderNo());
        List<MatDetail> matDetailList = matDetailDao.select(matDetail);
        if (matDetailList != null && matDetailList.size() > 0) {
            List<MatDetailVo> matVoList = new ArrayList<MatDetailVo>();
            CopyHelper.copyList(matDetailList, matVoList, MatDetailVo.class);
            vo.setMatDetailVoList(matVoList);
        }
        if (vo.getOrderType() == 1) {
            MatApplyDetail matApplyDetail = new MatApplyDetail();
            matApplyDetail.setApplyNo(vo.getApplyNo());
            List<MatApplyDetail> applyList = matApplyDetailDao.select(matApplyDetail);
            if (applyList != null && applyList.size() > 0) {
                MatApplyDetailVo applyDetailVo = new MatApplyDetailVo();
                CopyHelper.copyObj(applyList.get(0), applyDetailVo);
                vo.setMatApplyDetailVo(applyDetailVo);
            }

        }

        return vo;
    }

    /**
     * revocation
     * 撤回
     *
     * @param id
     * @return
     */
    public Boolean revocation(String id) {
        MatOrder order = matOrderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        order.setOrderStat(6L);
        matOrderDao.updateById(order);
        return true;
    }

    public Boolean approve(String id, int option, String reason) {
        MatOrder order = matOrderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        if (option == 1) {
            order.setOrderStat(3L);
        } else {
            order.setOrderStat(2L);
        }
        if (StringUtils.isNoneBlank(reason)) {
            order.setApproveOption(reason);
        }
        //审核人 审核时间
        order.setBuyerApproveEmpId(UserServletContext.getUserInfo().getEmCode());
        order.setBuyerApproveEmpName(UserServletContext.getUserInfo().getUserName());
        order.setApproveDate(new Date());
        matOrderDao.updateById(order);
        return true;
    }

    public Boolean beginPurchase(String id) {
        MatOrder order = matOrderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        order.setOrderStat(2L);
        // 采购人 采购时间
        order.setBuyerEmpId(UserServletContext.getUserInfo().getEmCode());
        order.setBuyerEmpName(UserServletContext.getUserInfo().getUserName());
        matOrderDao.updateById(order);
        return true;
    }

    public Boolean complatePurchase(String id) {
        MatOrder order = matOrderDao.getById(id);
        if (order == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        order.setOrderStat(5L);
        // 采购人 采购时间
        order.setBuyerEmpId(UserServletContext.getUserInfo().getEmCode());
        order.setBuyerEmpName(UserServletContext.getUserInfo().getUserName());
        matOrderDao.updateById(order);
        return true;
    }

    public Integer demondCount() {
        MatApplyDetail entity = new MatApplyDetail();
        entity.setHasOrder(0L);
        entity.setMatStat(2L);
        return matApplyDetailDao.selectCount(entity);
    }


    public Page<MatApplyDetailVo> demondList(String empName, int pageNum, int pageSize) {
        MatApplyDetail entity = new MatApplyDetail();
        entity.setHasOrder(0L);
        if (StringUtils.isNotBlank(empName)) {
            entity.setApplyEmpName(empName);
        }

        Page<MatApplyDetail> pageInfo = new Page<MatApplyDetail>();
        pageInfo.setList(matApplyDetailDao.selectByRowBounds(entity, new RowBounds(pageNum, pageSize)));
        Page<MatApplyDetailVo> pageVoInfo = new Page<MatApplyDetailVo>();
        if (pageInfo.getList().size() > 0) {
            BeanUtils.copyProperties(pageInfo, pageVoInfo);
        }

        return pageVoInfo;
    }

    public Page<MatOrderVo> searchApproveList(String orderNo, Long orderType, Long orderStat, int pageNum, int pageSize) {
        MatOrder matOrder = new MatOrder();
        if (StringUtils.isNotBlank(orderNo)) {
            matOrder.setOrderNo(orderNo);
        }
        if (orderType != null && orderType > 0) {
            matOrder.setOrderType(orderType);
        }
        if (orderStat != null && orderStat > 0) {
            matOrder.setOrderStat(orderStat);
        }
        matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
        matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<MatOrder> pageInfo = new Page<MatOrder>(pageNum, pageSize);
        matOrder.setPage(pageInfo);

        Page<MatOrder> page = pageInfo.setList(matOrderExtDao.findApproveList(matOrder));
        List<MatOrderVo> voList = new ArrayList<MatOrderVo>();
        CopyHelper.copyList(page.getList(), voList, MatOrderVo.class);

        Page<MatOrderVo> pageVo = new Page<MatOrderVo>();
        BeanCopier copier = BeanCopier.create(page.getClass(), pageVo.getClass(), false);
        copier.copy(page, pageVo, null);
        pageVo.setList(voList);

        return pageVo;
    }

    public Page<MatOrderVo> searchList(int pageNum, int pageSize, int outOrIn) {
        MatOrder matOrder = new MatOrder();
        Page<MatOrder> pageInfo = new Page<>(pageNum, pageSize);
        pageInfo.setOrderBy("created_dt desc");
        matOrder.setPage(pageInfo);
        //已完成状态
        matOrder.setOrderStat(3L);
        if (outOrIn == 0) {
            matOrder.setHasOrder(0L);
        } else if (outOrIn == 1) {
            matOrder.setHasOrder(1L);
        }
        Page<MatOrder> page = pageInfo.setList(matOrderDao.findList(matOrder));
        Page<MatOrderVo> pageVo = new Page<>();

        if (page != null) {
            CopyHelper.copyObj(page, pageVo);
            if (page.getList() != null) {
                List<MatOrder> matOrderList = page.getList();
                List<MatOrderVo> matOrderVoList = new ArrayList<MatOrderVo>();
                CopyHelper.copyList(matOrderList, matOrderVoList, MatOrderVo.class);
                for (MatOrderVo matOrderVo : matOrderVoList) {
                    MatDetail detail = new MatDetail();
                    detail.setOrderNo(matOrderVo.getOrderNo());
                    List<MatDetail> detailList = matDetailDao.select(detail);
                    if (detailList != null) {
                        List<MatDetailVo> detailVoList = new ArrayList<>();
                        CopyHelper.copyList(detailList, detailVoList, MatDetailVo.class);
                        matOrderVo.setMatDetailVoList(detailVoList);
                    }
                }
                pageVo.setList(matOrderVoList);
            }
        }
        return pageVo;
    }
}
