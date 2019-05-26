package com.sgai.property.purchase.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.purchase.entity.*;
import com.sgai.property.purchase.param.ConReceParam;
import com.sgai.property.purchase.param.InvoiceParam;
import com.sgai.property.purchase.param.PlanParam;
import com.sgai.property.purchase.service.PlanListOrDetailServiceImpl;
import com.sgai.property.purchase.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 采购计划列表或者详情
 * on 2018/2/27.
 */
@RestController
@RequestMapping(value = "/listOrDetail")
@Api(description = "采购计划详情列表")
public class PlanListOrDetailController extends BaseController {
    @Autowired
    PlanListOrDetailServiceImpl planListOrDetailService;

    @RequestMapping(value = "/getPlanList", method = RequestMethod.POST)
    @ApiOperation(value = "采购计划列表", httpMethod = "POST", notes = "采购计划列表")
    public Response<Page<Plan>> getPlanList(

            @RequestBody PlanParam planParam,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ) {
        return planListOrDetailService.getPlanList(planParam, pageNum, pageSize);
    }

    @RequestMapping(value = "/getPlanDetail", method = RequestMethod.POST)
    @ApiOperation(value = "采购计划详情", httpMethod = "POST", notes = "采购计划详情")
    public Response<PlanDetailResponse> getPlanDetail(

            @RequestParam(required = true, name = "id") String id
    ) {

        return planListOrDetailService.getPlanDetail(id);
    }

    @RequestMapping(value = "/planTaskDetail", method = RequestMethod.POST)
    @ApiOperation(value = "采购任务详情", httpMethod = "POST", notes = "采购任务详情")
    public Response<PlanTaskDetailVo> planTaskDetail(

            @RequestParam(required = true, name = "id") String id
    ) {

        return planListOrDetailService.planTaskDetail(id);
    }

    @RequestMapping(value = "/taskMatDetail", method = RequestMethod.POST)
    @ApiOperation(value = "任务物料详情", httpMethod = "POST", notes = "采购任务详情")
    public Response<Page<TaskCompanyVo>> taskMatDetail(

            @RequestParam(required = true, name = "taskId") String taskId,
            @RequestParam(required = true, name = "matTypeId") String matTypeId
    ) {

        return planListOrDetailService.taskMatDetail(taskId, matTypeId);
    }


    @RequestMapping(value = "/getStageList", method = RequestMethod.POST)
    @ApiOperation(value = "阶段列表", httpMethod = "POST", notes = "阶段列表")
    public Response<List<PlanStage>> getStageList(

            @RequestParam(required = true, name = "id") String id
    ) {

        return planListOrDetailService.getStageList(id);
    }

    @RequestMapping(value = "/getWhList", method = RequestMethod.POST)
    @ApiOperation(value = "仓库列表", httpMethod = "POST", notes = "仓库列表")
    public Response<List<DepotWarehouse>> getWhList(

    ) {

        return planListOrDetailService.getWhList();
    }

    @RequestMapping(value = "/planApproval", method = RequestMethod.POST)
    @ApiOperation(value = "审批计划", httpMethod = "POST", notes = "审批计划")
    public Response<Boolean> planApproval(

            @RequestBody ApprovalParam approvalParam
    ) {
        Response<Boolean> result = new Response<>();

        Boolean bool = planListOrDetailService.planApproval(approvalParam);
        result.setData(bool);
        return result;
    }

    @RequestMapping(value = "/getApplyList", method = RequestMethod.POST)
    @ApiOperation(value = "采购申请列表", httpMethod = "POST", notes = "采购申请列表")
    public Response<Page<PlanTask>> getApplyList(

            @RequestParam(required = false, name = "taskEmpName") String taskEmpName,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize
    ) {

        return planListOrDetailService.getApplyList(taskEmpName, pageNum, pageSize);
    }

    @RequestMapping(value = "/tetailProcessed", method = RequestMethod.POST)
    @ApiOperation(value = "采购任务详情-已处理", httpMethod = "POST", notes = "采购任务详情-已处理")
    public Response<TetailProcessedVo> tetailProcessed(

            @RequestParam(required = true, name = "id") String id
    ) {

        return planListOrDetailService.tetailProcessed(id);
    }


    @RequestMapping(value = "/detailCompList", method = RequestMethod.POST)
    @ApiOperation(value = "采购详情物料记录的供应商拆分列表", httpMethod = "POST", notes = "采购任务详情-已处理")
    public Response<List<PlanDetailCompanyVo>> detailCompList(

            @RequestParam(required = true, name = "detailId") String detailId
    ) {

        return planListOrDetailService.detailCompList(detailId);
    }

    @RequestMapping(value = "/planSubmit", method = RequestMethod.POST)
    @ApiOperation(value = "提交计划", httpMethod = "POST", notes = "提交计划")
    public Response<Boolean> planSubmit(

            @RequestParam(required = true, name = "id") String id
    ) {
        Response<Boolean> result = new Response<>();

        Boolean bool = planListOrDetailService.planSubmit(id);
        result.setData(bool);
        return result;
    }

    @RequestMapping(value = "/planRevocation", method = RequestMethod.POST)
    @ApiOperation(value = "撤销计划", httpMethod = "POST", notes = "撤销计划")
    public Response<Boolean> planRevocation(

            @RequestParam(required = true, name = "id") String id
    ) {
        Response<Boolean> result = new Response<>();

        Boolean bool = planListOrDetailService.planRevocation(id);
        result.setData(bool);
        return result;
    }

    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    @ApiOperation(value = "订单详情", httpMethod = "POST", notes = "订单详情")
    public Response<MatOrderVo> orderDetail(
            @RequestParam(required = true, name = "id") String id) {

        Response<MatOrderVo> result = new Response<MatOrderVo>();
        result.setData(planListOrDetailService.getOrderDetail(id));
        return result;
    }

    @RequestMapping(value = "/confirmReceipt", method = RequestMethod.POST)
    @ApiOperation(value = "确认收货", httpMethod = "POST", notes = "确认收货")
    public Response<Boolean> confirmReceipt(
            @RequestBody ConReceParam conReceParam
    ) {
        Response<Boolean> result = new Response<Boolean>();
        result.setData(planListOrDetailService.confirmReceipt(conReceParam));
        return result;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ApiOperation(value = "开具发票", httpMethod = "POST", notes = "开具发票")
    public Response<Boolean> invoice(
            @RequestBody InvoiceParam invoiceParam
    ) {

        Response<Boolean> result = new Response<Boolean>();
        result.setData(planListOrDetailService.invoice(invoiceParam));
        return result;
    }

    @RequestMapping(value = "/reivgDetail", method = RequestMethod.POST)
    @ApiOperation(value = "收货单信息", httpMethod = "POST", notes = "收货单信息")
    public Response<MatOrderTake> reivgDetail(
            @RequestParam(required = true, name = "id") String id
    ) {

        Response<MatOrderTake> result = new Response<MatOrderTake>();
        result.setData(planListOrDetailService.reivgDetail(id));
        return result;
    }

    @RequestMapping(value = "/invoiceDetail", method = RequestMethod.POST)
    @ApiOperation(value = "发票信息", httpMethod = "POST", notes = "发票信息")
    public Response<MatOrderInvoice> invoiceDetail(
            @RequestParam(required = true, name = "id") String id
    ) {

        Response<MatOrderInvoice> result = new Response<MatOrderInvoice>();
        result.setData(planListOrDetailService.invoiceDetail(id));
        return result;
    }

    @RequestMapping(value = "/planDelete", method = RequestMethod.POST)
    @ApiOperation(value = "删除计划", httpMethod = "POST", notes = "删除计划")
    public Response<Boolean> planDelete(
            @RequestParam(required = true, name = "id") String id
    ) {
        Response<Boolean> result = new Response<Boolean>();
        Boolean boo = planListOrDetailService.planDelete(id);
        result.setData(boo);
        return result;
    }

}
