package com.sgai.property.purchase.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.depot.dao.IMatOrderDao;
import com.sgai.property.depot.entity.MatOrder;
import com.sgai.property.purchase.constants.Constants;
import com.sgai.property.purchase.dao.*;
import com.sgai.property.purchase.entity.*;
import com.sgai.property.purchase.param.ConReceParam;
import com.sgai.property.purchase.param.InvoiceParam;
import com.sgai.property.purchase.param.PlanParam;
import com.sgai.property.purchase.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/2/27.
 */
@Service
public class PlanListOrDetailServiceImpl {
    @Autowired
    PlanListOrDetailDao planListOrDetailDao;
    @Autowired
    IPlanDao planDao;
    @Autowired
    IPlanStageDao iPlanStageDao;
    @Autowired
    IPlanTaskDao iPlanTaskDao;
    @Autowired
    IPlanDetailDao iPlanDetailDao;
    @Autowired
    IPlanDetailCompanyDao iPlanDetailCompanyDao;
    @Autowired
    IMatOrderDao iMatOrderDao;
    @Autowired
    IMatOrderDao matOrderDao;
    @Autowired
    IMatDetailDao matDetailDao;
    @Autowired
    IMatOrderTakeDao iMatOrderTakeDao;
    @Autowired
    IDepotWarehouseDao iDepotWarehouseDao;
    @Autowired
    IMatOrderInvoiceDao iMatOrderInvoiceDao;
    @Autowired
    IMatApplyDetailImgDao iMatApplyDetailImgDao;
    @Autowired
    IMatApplyDetailDao iMatApplyDetailDao;
    /**
     * describe: 这个人很懒也很帅，但是什么都没写~列表
     * className: PlanListOrDetailServiceImpl
     * methodName: getPlanList
     * methodParam: [toonCode, planParam, pageNum, pageSize]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.purchase.entity.Plan>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/28 9:36
     **/
    public Response<Page<Plan>> getPlanList(PlanParam planParam, int pageNum, int pageSize) {
        Response<Page<Plan>> result = new Response<>();
        Plan plan = new Plan();
        if(StringUtils.isNotBlank(planParam.getPlanName())){
            String planName = "'%"+planParam.getPlanName()+"%'";
            plan.setPlanName(planName);
        }
        if (planParam.getPlanStat().intValue() > 0){
            plan.setPlanStat(planParam.getPlanStat());
        }
        plan.setComCode(UserServletContext.getUserInfo().getComCode());
        plan.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<Plan> page = new Page<>(pageNum, pageSize);
        plan.setPage(page);
        if (planParam.getApproveIsYup().equals("Y")){
            page.setList(planListOrDetailDao.getPlanApproveList(plan));
        } else {

            page.setList(planListOrDetailDao.getPlanList(plan));
        }
        result.setData(page);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~详情
     * className: PlanListOrDetailServiceImpl
     * methodName: getPlanDetail
     * methodParam: [toonCode, id]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.property.purchase.entity.PlanDetailResponse>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/28 14:12
     **/
    public Response<PlanDetailResponse> getPlanDetail(String id) {

        Response<PlanDetailResponse> result = new Response<>();
        PlanDetailResponse response = new PlanDetailResponse();
        //查询采购计划基础信息
        Plan plan = new Plan();
        plan.setId(id);
        Plan pl = planDao.get(plan);
        if (pl == null ){
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        BeanUtils.copyProperties(pl, response);
        
        response.setPlanStageVoList(new ArrayList<PlanStageVo>());

        //根据计划Id 查询 所属阶段List
        Page<PlanStage> p = new Page<PlanStage>();
        p.setOrderBy("SORT asc");
        PlanStage planStage = new PlanStage();
        planStage.setPage(p);
        planStage.setPlanId(id);
        List<PlanStage> stageList = iPlanStageDao.findList(planStage);
        if (stageList.size() > 0){
            List<PlanStageVo> stageVos = getCobyList(stageList);
            response.setPlanStageVoList(stageVos);
            //遍历阶段List 根据阶段Id查询任务集合，将任务集合coby到所需的List中 并复制给当前计划的当前阶段的任务List中
            List<PlanStageVo> planStageVoList = response.getPlanStageVoList();
            for (int i = 0; i < planStageVoList.size(); i++) {
                PlanTask planTask = new PlanTask();
                planTask.setStageId(planStageVoList.get(i).getId());
                Page<PlanTask> page = new Page<PlanTask>();
                page.setOrderBy("SORT");
                planTask.setPage(page);
                List<PlanTask> taskList = iPlanTaskDao.findList(planTask);
                if (taskList.size() > 0){
                    List<PlanTaskVo> planTaskVoList = getCobyLists(taskList);
                    response.getPlanStageVoList().get(i).setPlanTaskVoList(planTaskVoList);
                }
            }
        }
        
        //无阶段阶段及任务列表
        PlanTask planTask = new PlanTask();
        planTask.setStageId(id);
        Page<PlanTask> page = new Page<PlanTask>();
        page.setOrderBy("SORT");
        planTask.setPage(page);
        List<PlanTask> taskList = iPlanTaskDao.findList(planTask);
        if(taskList != null && taskList.size() > 0){
        	List<PlanTaskVo> planTaskVoList = getCobyLists(taskList);
            planStage = new PlanStage();
            planStage.setId(id);
            planStage.setStageName("无阶段");
            PlanStageVo stageVo = new PlanStageVo();
            BeanUtils.copyProperties(planStage,stageVo);
            stageVo.setPlanTaskVoList(planTaskVoList);
            response.getPlanStageVoList().add(stageVo);
        }else{
        	planStage = new PlanStage();
            planStage.setId(id);
            planStage.setStageName("无阶段");
            PlanStageVo stageVo = new PlanStageVo();
            BeanUtils.copyProperties(planStage,stageVo);
            response.getPlanStageVoList().add(stageVo);
        }

        result.setData(response);
        return result;
    }
    //考取阶段数据到所需的List中
    private List<PlanStageVo> getCobyList(List<PlanStage> stageList){
        List<PlanStageVo> stageVoList = new ArrayList<>();
        for (PlanStage ps : stageList) {
            PlanStageVo stageVo = new PlanStageVo();
            BeanUtils.copyProperties(ps,stageVo);
            stageVoList.add(stageVo);
        }
        return stageVoList;
    }
    //考取阶段数据到所需的List中
    private List<PlanTaskVo> getCobyLists(List<PlanTask> taskList){
        List<PlanTaskVo> planTaskVoList = new ArrayList<>();
        for (PlanTask pt : taskList) {
            PlanTaskVo planTaskVo = new PlanTaskVo();
            BeanUtils.copyProperties(pt,planTaskVo);
            planTaskVoList.add(planTaskVo);
        }
        return planTaskVoList;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~任务详情
     * className: PlanListOrDetailServiceImpl
     * methodName: planTaskDetail
     * methodParam: [toonCode, id]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.property.purchase.entity.PlanTaskDetailVo>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/28 14:48
     **/
    public Response<PlanTaskDetailVo> planTaskDetail(String id) {

        Response<PlanTaskDetailVo> result = new Response<>();
        PlanTaskDetailVo taskDetailVo = new PlanTaskDetailVo();
        //查询任务相关信息
        PlanTask task = new PlanTask();
        task.setId(id);
        PlanTask planTask = iPlanTaskDao.get(task);
        if (planTask != null ){
            BeanUtils.copyProperties(planTask,taskDetailVo);
            if(planTask.getTaskPlanMat().intValue() == 2){
                //处理阶段名称
                if (planTask.getStageId().equals(planTask.getPlanId())){
                    taskDetailVo.setStageName("无阶段");
                    taskDetailVo.setStageId(planTask.getStageId());
                } else {
                    PlanStage planStage = new PlanStage();
                    planStage.setId(planTask.getStageId());
                    planStage.setPlanId(planTask.getPlanId());
                    PlanStage stage = iPlanStageDao.get(planStage);
                    taskDetailVo.setStageName(stage.getStageName());
                    taskDetailVo.setStageId(stage.getId());
                }
                //根据任务Id查询计划相关信息
                Plan p = new Plan();
                p.setId(planTask.getPlanId());
                Plan plan = planDao.get(p);
                if (plan != null){
                    taskDetailVo.setPlanEmpName(plan.getPlanEmpName());
                    taskDetailVo.setTaskDept(plan.getPlanDept());
                }
            } else {
                //查询用料来源的图片信息
                MatApplyDetail applyDetail = new MatApplyDetail();
                applyDetail.setApplyNo(planTask.getTaskNo());
                MatApplyDetail matdetail = iMatApplyDetailDao.selectOne(applyDetail);
                MatApplyDetailImg applyDetailImg = new MatApplyDetailImg();
                applyDetailImg.setApplyId(matdetail.getId());
                List<MatApplyDetailImg> imgs = iMatApplyDetailImgDao.findList(applyDetailImg);
                taskDetailVo.setImgUrls(imgs);
                taskDetailVo.setPlanEmpName(planTask.getTaskEmpName());
            }
        }


        //根据任务ID查询物料List
        PlanDetail pd = new PlanDetail();
        pd.setTaskId(id);
        List<PlanDetail> detailList = iPlanDetailDao.findList(pd);
        if (detailList.size() > 0){
            taskDetailVo.setPlanDetailList(detailList);
        }
        result.setData(taskDetailVo);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~阶段列表
     * className: PlanListOrDetailServiceImpl
     * methodName: planTaskDetail
     * methodParam: [toonCode, id]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.property.purchase.entity.PlanTaskDetailVo>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/28 14:48
     **/
    public Response<List<PlanStage>> getStageList(String id) {
        Response<List<PlanStage>> result = new Response<>();
        Page<PlanStage> p = new Page<PlanStage>();
        p.setOrderBy("SORT asc");
        PlanStage stage = new PlanStage();
        stage.setPage(p);
        stage.setPlanId(id);
        List<PlanStage> stageList = iPlanStageDao.findList(stage);
        stage = new PlanStage();
        stage.setStageName("无阶段");
        stage.setId(id);
        stageList.add(0, stage);
        result.setData(stageList);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~计划审批
     * className: PlanListOrDetailServiceImpl
     * methodName: planApproval
     * methodParam: [toonCode, approvalParam]
     * return: java.lang.Boolean
     * creatUser: 600111@syswin
     * creatDateTime: 2018/2/28 18:06
     **/
    public Boolean planApproval(ApprovalParam approvalParam) {
        Boolean flag =false;
        Plan plan = planDao.getById(approvalParam.getId());
        if (plan == null ){
            throw new BusinessException(ReturnType.DB,"该计划已被删除。");
        }
        if (plan.getPlanStat().intValue() != Constants.MT_STAT_2){
            throw new BusinessException(ReturnType.DB,"不是待审核状态！");
        } else {
            Plan p = new Plan();
            if (approvalParam.getApprovalStatus().equals("Y")){
                p.setPlanStat(Constants.MT_STAT_4);
            } else {
                p.setPlanStat(Constants.MT_STAT_3);
            }
            if (StringUtils.isNotBlank(approvalParam.getApprovalOpinition())){
                p.setApproveOption(approvalParam.getApprovalOpinition());
            }
            p.setApproveDate(new Date());
            p.setBuyerEmpName(UserServletContext.getUserInfo().getUserName());
            p.setId(approvalParam.getId());
            int i = planDao.updateById(p);
            if (i == 1){
                if (p.getPlanStat().intValue() == 4){
                    //将计划审批已通过的任务List中每条数据的planMatStat 修改为1
                    PlanTask planTask = new PlanTask();
                    planTask.setPlanId(plan.getId());
                    List<PlanTask> taskList = iPlanTaskDao.findList(planTask);
                    for (PlanTask pt : taskList) {
                        //修改任务数据中的planMatStat值为1
                        pt.setPlanMatStat(Constants.MT_STAT_1);//是否审批通过
                        pt.setTaskPlanMat(Constants.MT_STAT_2);//数据来源：1.用料2.计划
                        iPlanTaskDao.updateById(pt);
                    }

                }
                flag = true;
            }
        }

        return flag;

    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~采购申请列表
     * className: PlanListOrDetailServiceImpl
     * methodName: getApplyList
     * methodParam: [toonCode, taskEmpName, pageNum, pageSize]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.common.persistence.Page<com.sgai.property.purchase.entity.PlanTask>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 9:24
     **/
    public Response<Page<PlanTask>> getApplyList(String taskEmpName, int pageNum, int pageSize) {

        Response<Page<PlanTask>> result = new Response<>();

        PlanTask planTask = new PlanTask();
        if (StringUtils.isNotBlank(taskEmpName)){
            taskEmpName = "'%" + taskEmpName + "%'";
            planTask.setTaskEmpName(taskEmpName);
        }

        planTask.setPlanMatStat(Constants.MT_STAT_1);
        planTask.setComCode(UserServletContext.getUserInfo().getComCode());
        planTask.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<PlanTask> page = new Page<>(pageNum, pageSize);
        page.setOrderBy("TASK_NO desc");
        planTask.setPage(page);
        List<PlanTask> planTasks = iPlanTaskDao.findList(planTask);
        for (PlanTask pt : planTasks) {
            if (pt.getTaskPlanMat().intValue() == Constants.MT_INVITE_2){
                Plan plan = planDao.getById(pt.getPlanId());
                if (StringUtils.isNotBlank(pt.getPlanId())){
                    if (plan != null && StringUtils.isNotBlank(plan.getPlanDept())){
                        pt.setTaskDept(plan.getPlanDept());
                    }
                }
            }
        }
        page.setList(planTasks);
        result.setData(page);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~申请详情已处理
     * className: PlanListOrDetailServiceImpl
     * methodName: tetailProcessed
     * methodParam: [toonCode, id]
     * return: com.sgai.property.purchase.vo.Response<com.sgai.property.purchase.vo.TetailProcessedVo>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 10:59
     **/
    public Response<TetailProcessedVo> tetailProcessed(String id) {
        Response<TetailProcessedVo> result = new Response<>();
        TetailProcessedVo processedVo = new TetailProcessedVo();
        //根据任务Id查询订单相关信息相关信息
        MatOrder matOrder = new MatOrder();
        matOrder.setTaskId(id);
        List<MatOrder> orderList = iMatOrderDao.findList(matOrder);
        if (orderList != null && orderList.size() > 0){
            BeanUtils.copyProperties(orderList.get(0),processedVo);
        }
        //根据任务Id查询Task相关信息
        PlanTask pt = new PlanTask();
        pt.setId(id);
        PlanTask planTask = iPlanTaskDao.get(pt);
        if (planTask != null) {
            BeanUtils.copyProperties(planTask,processedVo);
        }
        //根据任务Id查询计划相关信息
        Plan p = new Plan();
        p.setId(planTask.getPlanId());
        Plan plan = planDao.get(p);
        if (plan != null){
            BeanUtils.copyProperties(plan,processedVo);
        }
        //根据任务Id 查询处理后的物料信息List
        PlanDetailCompany pdc = new PlanDetailCompany();
        pdc.setTaskId(id);
        List<PlanDetailCompany> companyList = iPlanDetailCompanyDao.findList(pdc);
        if (companyList != null){
            processedVo.setPdcList(companyList);
        }

        result.setData(processedVo);
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~提交
     * className: PlanListOrDetailServiceImpl
     * methodName: planSubmit
     * methodParam: [toonCode, id]
     * return: java.lang.Boolean
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 13:44
     **/
    public Boolean planSubmit(String id) {
        Boolean flag =false;
        Plan plan = planDao.getById(id);
        if (plan.getPlanStat().intValue() != Constants.MT_STAT_1){
            throw new BusinessException(ReturnType.DB,"不是待提交状态！");
        } else {
            Plan p = new Plan();
            p.setPlanStat(Constants.MT_STAT_2);
            p.setId(id);
            int i = planDao.updateById(p);
            if (i == 1){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~撤销
     * className: PlanListOrDetailServiceImpl
     * methodName: planRevocation
     * methodParam: [toonCode, id]
     * return: java.lang.Boolean
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 13:49
     **/
    public Boolean planRevocation(String id) {
        Boolean flag =false;
        Plan plan = planDao.getById(id);
        if (plan.getPlanStat().intValue() != Constants.MT_STAT_2){
            throw new BusinessException(ReturnType.DB,"不是待审核状态！");
        } else {
            Plan p = new Plan();
            p.setPlanStat(Constants.MT_STAT_1);
            p.setId(id);
            int i = planDao.updateById(p);
            if (i == 1){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~订单详情
     * className: PlanListOrDetailServiceImpl
     * methodName: getOrderDetail
     * methodParam: [id]
     * return: com.sgai.property.purchase.vo.MatOrderVo
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 14:41
     **/
    public MatOrderVo getOrderDetail(String id) {
        MatOrder matOrder = matOrderDao.getById(id);
        if(matOrder == null){
            return null;
        }
        MatOrderVo vo = new MatOrderVo();
        CopyHelper.copyObj(matOrder, vo);
        MatDetail matDetail = new MatDetail();
        matDetail.setOrderNo(matOrder.getOrderNo());
        List<MatDetail> matDetailList = matDetailDao.select(matDetail);
        if(matDetailList != null && matDetailList.size() > 0 ){
            List<MatDetailVo> matVoList = new ArrayList<MatDetailVo>() ;
            CopyHelper.copyList(matDetailList, matVoList, MatDetailVo.class);
            vo.setMatDetailVoList(matVoList);
        }
        return vo;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~确认收货
     * className: PlanListOrDetailServiceImpl
     * methodName: confirmReceipt
     * methodParam: [toonCode, conReceParam]
     * return: java.lang.Boolean
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 15:33
     **/
    public Boolean confirmReceipt(ConReceParam conReceParam) {
        MatOrder order = matOrderDao.getById(conReceParam.getId());
        if(order == null){
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        order.setOrderStat(Constants.MT_STAT_3);
        // 采购人 采购时间
        order.setBuyerEmpId(UserServletContext.getUserInfo().getEmCode());
        order.setBuyerEmpName(UserServletContext.getUserInfo().getUserName());
        matOrderDao.updateById(order);
        MatOrderTake orderTake = new MatOrderTake();
        orderTake.setOrderId(conReceParam.getId());
        orderTake.setWhId(conReceParam.getWhId());
        orderTake.setWhName(conReceParam.getWarName());
        orderTake.setTakeCargoName(conReceParam.getTakeCargoName());
        orderTake.setId(UUID.randomUUID().toString());
        orderTake.setCreatedDt(new Date());
        iMatOrderTakeDao.insert(orderTake);
        return true;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~仓库列表
     * className: PlanListOrDetailServiceImpl
     * methodName: getWhList
     * methodParam: [toonCode]
     * return: com.sgai.property.purchase.vo.Response<java.util.List<com.sgai.property.purchase.entity.DepotWarehouse>>
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 16:36
     **/
    public Response<List<DepotWarehouse>> getWhList() {
        Response<List<DepotWarehouse>> result = new Response<>();
        DepotWarehouse warehouse = new DepotWarehouse();
        warehouse.setComCode(UserServletContext.getUserInfo().getComCode());
        warehouse.setModuCode(UserServletContext.getUserInfo().getModuCode());
        result.setData(iDepotWarehouseDao.findList(warehouse));
        return result;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~开具发票
     * className: PlanListOrDetailServiceImpl
     * methodName: invoice
     * methodParam: [toonCode, invoiceParam]
     * return: java.lang.Boolean
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 16:50
     **/
    public Boolean invoice(InvoiceParam invoiceParam) {
        Boolean flag = false;
        MatOrder order = matOrderDao.getById(invoiceParam.getOrderId());
        if(order == null){
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        order.setInvoiceState(Constants.MT_INVITE_2.toString());
        matOrderDao.updateById(order);

        MatOrderInvoice invoice = new MatOrderInvoice();
        invoice.setId(UUID.randomUUID().toString());
        invoice.setInvoiceMoney(invoiceParam.getInvoiceMoney());
        invoice.setInvoiceName(invoiceParam.getInvoiceName());
        invoice.setInvoicePhone(invoiceParam.getInvoicePhone());
        invoice.setOrderId(invoiceParam.getOrderId());
        invoice.setTallageNumber(invoiceParam.getTallageNumber());
        invoice.setInvoiceSite(invoiceParam.getInvoiceSite());
        int i = iMatOrderInvoiceDao.insert(invoice);
        if (i == 1){
            flag = true;
        }
        return flag;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~收货信息详情
     * className: PlanListOrDetailServiceImpl
     * methodName: reivgDetail
     * methodParam: [id]
     * return: com.sgai.property.purchase.entity.MatOrderTake
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 17:53
     **/
    public MatOrderTake reivgDetail(String id) {
        MatOrderTake take = new MatOrderTake();
        take.setOrderId(id);
        MatOrderTake matOrderTake = iMatOrderTakeDao.get(take);
        if (matOrderTake == null ){
            throw new BusinessException(ReturnType.ParamIllegal, "暂时没有收货信息");
        }else{
            Date createdDt = matOrderTake.getCreatedDt();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(createdDt);
            matOrderTake.setCreatedTime(format);

        }
        return matOrderTake;
    }

    /**
     * describe: 这个人很懒也很帅，但是什么都没写~发票信息详情
     * className: PlanListOrDetailServiceImpl
     * methodName: reivgDetail
     * methodParam: [id]
     * return: com.sgai.property.purchase.entity.MatOrderTake
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 17:53
     **/
    public MatOrderInvoice invoiceDetail(String id) {
        MatOrderInvoice invoice = new MatOrderInvoice();
        invoice.setOrderId(id);
        MatOrderInvoice orderInvoice = iMatOrderInvoiceDao.get(invoice);
        if (orderInvoice == null ){
            throw new BusinessException(ReturnType.ParamIllegal, "暂时没有开具发票");
        }
        return orderInvoice;
    }


    /**
     * describe: 这个人很懒也很帅，但是什么都没写~发票信息详情
     * className: PlanListOrDetailServiceImpl
     * methodName: reivgDetail
     * methodParam: [id]
     * return: com.sgai.property.purchase.entity.MatOrderTake
     * creatUser: 600111@syswin
     * creatDateTime: 2018/3/1 17:53
     **/
    public Boolean planDelete(String id) {
        Boolean flag = false;
        Plan plan = planDao.getById(id);
        if (plan == null ){
            throw new BusinessException(ReturnType.ParamIllegal, "查无记录");
        }
        int i = planDao.deleteById(id);
        if (i == 1){
            flag = true;
        }
        return flag;
    }

	public Response<List<PlanDetailCompanyVo>> detailCompList(String detailId) {
		Response<List<PlanDetailCompanyVo>> result = new Response<List<PlanDetailCompanyVo>>();
		PlanDetailCompany pdc = new PlanDetailCompany();
		pdc.setDetailId(detailId);
		List<PlanDetailCompany> pdcList = iPlanDetailCompanyDao.findList(pdc);
		if(pdcList != null && pdcList.size() > 0){
			List<PlanDetailCompanyVo> voList = new ArrayList<PlanDetailCompanyVo>();
			CopyHelper.copyList(pdcList, voList, PlanDetailCompanyVo.class);
			result.setData(voList);
		}
		
		return result;
	}

    public Response<Page<TaskCompanyVo>> taskMatDetail(String taskId, String matTypeId) {
        Response<Page<TaskCompanyVo>> response = new Response<>();
        Page<TaskCompanyVo> page = new Page<TaskCompanyVo>();
        PlanDetailCompany pdc = new PlanDetailCompany();
        pdc.setTaskId(taskId);
        pdc.setMatTypeId(matTypeId);
        List<PlanDetailCompany> pdcList = iPlanDetailCompanyDao.findList(pdc);
        List<TaskCompanyVo> voList = new ArrayList<>();
        for (PlanDetailCompany pl:pdcList) {
            TaskCompanyVo vo = new TaskCompanyVo();
            BeanUtils.copyProperties(pl,vo);
            voList.add(vo);
        }
        response.setData(page.setList(voList));
        return response;
    }
}
