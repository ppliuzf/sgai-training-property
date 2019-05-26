package com.sgai.property.purchase.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.purchase.param.MatOrderParam;
import com.sgai.property.purchase.service.PurchaseOrderServiceImpl;
import com.sgai.property.purchase.vo.MatApplyDetailVo;
import com.sgai.property.purchase.vo.MatOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 145811 on 2018/1/11.
 */
@RestController
@RequestMapping(value = "/order")
@Api(description = "采购订单")
public class PurchaseOrderController extends BaseController {

    @Autowired
    private PurchaseOrderServiceImpl orderService;

    @RequestMapping(value = "/searchList", method = RequestMethod.POST)
    @ApiOperation(value = "列表", httpMethod = "POST", notes = "列表")
    public Response<Page<MatOrderVo>> searchList(
            @ApiParam(value = "订单号", required = false) @RequestParam(required = false, name = "orderNo")String orderNo,
            @ApiParam(value = "订单类型?1:需求订单;2:主动采购", required = false) @RequestParam(required = false, name = "orderType") Long orderType,
            @ApiParam(value = "状态? 1:待审核；2:已拒绝；3:已通过；4:采购中；5:已完成；6:已撤回", required = false) @RequestParam(required = false, name = "orderStat") Long orderStat,
            @ApiParam(value = "发票状态? 1:未开具；2:已开具；", required = false) @RequestParam(required = false, name = "invoiceState") String invoiceState,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize){
        Response<Page<MatOrderVo>> result = new Response<Page<MatOrderVo>>();
        Page<MatOrderVo> page = orderService.searchList(orderNo, orderType, orderStat, invoiceState, pageNum, pageSize);
        result.setData(page);
        return result;
    }
    @RequestMapping(value = "/searchListForPlan", method = RequestMethod.POST)
    @ApiOperation(value = "列表-选择已完成的订单列表", httpMethod = "POST", notes = "列表")
    public Response<Page<MatOrderVo>> searchListForPlan(@RequestHeader("accessToken")
                                                 @ApiParam(value = "入库：0；出库：1")
                                                 @RequestParam(required = true , name = "outOrIn" ) int outOrIn,
                                                 @RequestParam(required = true, name = "pageNum") int pageNum,
                                                 @RequestParam(required = true, name = "pageSize") int pageSize ) {
        Response<Page<MatOrderVo>> result = new Response<>();
        Page<MatOrderVo> page = orderService.searchList(pageNum, pageSize,outOrIn);
        result.setData(page);
        return result;
    }
    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    @ApiOperation(value = "详情", httpMethod = "POST", notes = "列表")
    public Response<MatOrderVo> orderDetail(
            @RequestParam(required = false, name = "id")String id){

        Response<MatOrderVo> result = new Response<MatOrderVo>();
        result.setData(orderService.getOrderDetail(id));
        return result;
    }
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新", httpMethod = "POST", notes = "新增或更新")
    public Response<Boolean> addOrder(
            @RequestBody MatOrderParam matOrderParam){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(orderService.addOrder(matOrderParam));
        return result;
    }

    @RequestMapping(value = "/revocation", method = RequestMethod.POST)
    @ApiOperation(value = "撤回", httpMethod = "POST", notes = "撤回")
    public Response<Boolean> revocation(
            @RequestParam(required = false, name = "id")String id){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(orderService.revocation(id));
        return result;
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    @ApiOperation(value = "审批；0:拒绝，1:通过", httpMethod = "POST", notes = "审批；0:拒绝，1:通过")
    public Response<Boolean> approve(
            @RequestParam(required = true, name = "id")String id,
            @ApiParam(value = "0:拒绝，1:通过", required = true)@RequestParam(required = true, name = "option")int option,
            @ApiParam(value = "审核理由", required = false)@RequestParam(required = false, name = "reason")String reason){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(orderService.approve(id, option, reason));
        return result;
    }

    @RequestMapping(value = "/beginPurchase", method = RequestMethod.POST)
    @ApiOperation(value = "开始采购", httpMethod = "POST", notes = "开始采购")
    public Response<Boolean> beginPurchase(
            @RequestParam(required = false, name = "id")String id){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(orderService.beginPurchase(id));
        return result;
    }

    @RequestMapping(value = "/complatePurchase", method = RequestMethod.POST)
    @ApiOperation(value = "完成采购", httpMethod = "POST", notes = "完成采购")
    public Response<Boolean> complatePurchase(
            @RequestParam(required = false, name = "id")String id){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(orderService.complatePurchase(id));
        return result;
    }

    @RequestMapping(value = "/demondCount", method = RequestMethod.POST)
    @ApiOperation(value = "待处理的需求个数", httpMethod = "POST", notes = "待处理的需求个数")
    public Response<Integer> demondCount(){

        Response<Integer> result = new Response<Integer>();
        result.setData(orderService.demondCount());
        return result;
    }

    @RequestMapping(value = "/demondList", method = RequestMethod.POST)
    @ApiOperation(value = "待处理的需求列表", httpMethod = "POST", notes = "待处理的需求列表")
    public Response<Page<MatApplyDetailVo>> demondList(
            @RequestParam(required = false, name = "empName") String empName,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<MatApplyDetailVo>> result = new Response<Page<MatApplyDetailVo>>();
        result.setData(orderService.demondList(empName, pageNum, pageSize));
        return result;
    }

    @RequestMapping(value = "/searchApproveList", method = RequestMethod.POST)
    @ApiOperation(value = "列表", httpMethod = "POST", notes = "列表")
    public Response<Page<MatOrderVo>> searchApproveList(
            @ApiParam(value = "订单号", required = false) @RequestParam(required = false, name = "orderNo")String orderNo,
            @ApiParam(value = "订单类型?1:需求订单;2:主动采购", required = false) @RequestParam(required = false, name = "orderType") Long orderType,
            @ApiParam(value = "状态? 1:待审核；2:已拒绝；3:已通过；", required = false) @RequestParam(required = false, name = "orderStat") Long orderStat,
            @RequestParam(required = true, name = "pageNum") int pageNum,
            @RequestParam(required = true, name = "pageSize") int pageSize ){

        Response<Page<MatOrderVo>> result = new Response<Page<MatOrderVo>>();
        if(orderStat != null){
            if( orderStat != 1 && orderStat != 2 && orderStat != 3){
                throw new BusinessException(ReturnType.ParamIllegal, "");
            }
        }
        Page<MatOrderVo> page = orderService.searchApproveList(orderNo, orderType, orderStat, pageNum, pageSize);
        result.setData(page);
        return result;
    }

}
