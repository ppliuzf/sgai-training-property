package com.sgai.property.quality.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.DefectOrderServiceImpl;
import com.sgai.property.quality.vo.DefectOrderDetailVo;
import com.sgai.property.quality.vo.DefectOrderVo;
import com.sgai.property.quality.vo.dto.DealOrderDto;
import com.sgai.property.quality.vo.dto.DefectOrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description = "缺陷工单接口")
@RestController
@RequestMapping("/defect")
public class DefectOrderController extends BaseController {

	@Autowired
	DefectOrderServiceImpl defectOrderService;

	@PostMapping(value = "/createOrder")
	@ApiOperation(value = "新增缺陷工单", notes = "新增缺陷工单")
	public Response<Boolean> createOrder(
										 @RequestBody DefectOrderDto defectOrderDto) {
		Response<Boolean> response = new Response<>();

		response.setData(defectOrderService.createOrder(defectOrderDto));
		return response;
	}

	@PostMapping(value = "/redistributeOrder")
	@ApiOperation(value = "改派工单", notes = "改派工单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId", value = "工单ID", required = true, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "feedId", value = "执行人feedId", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "改派内容", required = true, paramType = "query", dataType = "String") })
	public Response<Boolean> redistributeOrder( String orderId,
			String feedId, String content) {
		Response<Boolean> response = new Response<>();

		response.setData(defectOrderService.redistributeOrder(orderId, feedId, content));
		return response;
	}

	@ApiOperation(value = "反馈工单", notes = "反馈工单")
	@PostMapping(value = "/feedbackOrder")
	public Response<Boolean> feedbackOrder(
			@RequestBody DealOrderDto dealOrderDto) {
		Response<Boolean> response = new Response<>();
		response.setData(defectOrderService.feedbackOrder(dealOrderDto));
		return response;
	}

	@ApiOperation(value = "评价工单", notes = "评价工单")
	@PostMapping(value = "/evaluateOrder")
	public Response<Boolean> evaluateOrder(
			@RequestBody DealOrderDto dealOrderDto) {
		Response<Boolean> response = new Response<>();

		response.setData(defectOrderService.evaluateOrder(dealOrderDto));
		return response;
	}

	@PostMapping(value = "/cancelOrder")
	@ApiOperation(value = "撤消工单", notes = "撤消工单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "reason", value = "撤消原因", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "orderId", value = "工单ID", required = true, paramType = "query", dataType = "String")
		})
	public Response<Boolean> cancelOrder(String reason,String orderId) {
		Response<Boolean> response = new Response<>();

		response.setData(defectOrderService.cancelOrder(reason,orderId));
		return response;
	}

	@PostMapping(value = "/listOrder")
	@ApiOperation(value = "工单列表", notes = "工单列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "categoryId", value = "专业范畴ID", required = true, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "status", value = "工单状态 0未完成，1已完成", required = true, paramType = "query", dataType = "Integer")
		})
	public Response<Page<DefectOrderVo>> listOrder(
												   String categoryId, Integer pageNum, Integer pageSize, Integer status) {
		Response<Page<DefectOrderVo>> response = new Response<>();
		response.setData(defectOrderService.listOrder(categoryId, pageNum, pageSize, status));
		return response;
	}

	@PostMapping(value = "/orderDetail")
	@ApiOperation(value = "工单详情", notes = "工单详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderId", value = "工单ID", required = true, paramType = "query", dataType = "String")
		})
	public Response<DefectOrderDetailVo> orderDetail( String orderId) {
		Response<DefectOrderDetailVo> response = new Response<>();
		response.setData(defectOrderService.orderDetail(orderId));
		return response;
	}

	@PostMapping(value = "/getUserLabels")
	@ApiOperation(value = "获取评价标签", notes = "获取评价标签(dlName标签名称，dlId标签ID)")
	public Response<List<Map<String, Object>>> getUserLabels() {
		Response<List<Map<String, Object>>> response = new Response<>();
		response.setData(defectOrderService.getUserLabels());
		return response;
	}

	@PostMapping(value = "/saveUserLabel")
	@ApiOperation(value = "保存评价标签", notes = "保存评价标签")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "label", value = "用户输入的标签内容", required = true, paramType = "query", dataType = "String") })
	public Response<Boolean> saveUserLabel(String label) {
		Response<Boolean> response = new Response<>();
		response.setData(defectOrderService.saveUserLabel(label));
		return response;
	}

	@PostMapping(value = "/getCalcelReasons")
	@ApiOperation(value = "获取整改工单撤消原因", notes = "获取整改工单撤消原因(reason撤消原因，reasonId撤消原因ID)")
	public Response<List<Map<String, Object>>> getCalcelReasons() {
		Response<List<Map<String, Object>>> response = new Response<>();
		response.setData(defectOrderService.getCancelReasons());
		return response;
	}
}
