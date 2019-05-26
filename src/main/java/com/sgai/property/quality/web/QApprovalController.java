package com.sgai.property.quality.web;

import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.QApprovalServiceImpl;
import com.sgai.property.quality.vo.dto.TaskApprovalDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "审批接口")
@RestController
@RequestMapping("/qapproval")
public class QApprovalController  extends BaseController {

	@Autowired
	QApprovalServiceImpl approvalService;

	@PostMapping(value = "/createApproval")
	@ApiOperation(value = "发起审批", notes = "发起审批")
	public Response<Boolean> createApproval(
											@RequestBody TaskApprovalDto taskApprovalDto) {
		Response<Boolean> response = new Response<>();
		response.setData(approvalService.createApproval(taskApprovalDto));
		return response;
	}

	@ApiOperation(value = "任务审核", httpMethod = "POST", notes = "任务审核")
	@RequestMapping(value = "/taskApproval", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskId", value = "任务id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "审核状态(1:审核通过,2:审核未通过)", required = true, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "opinion", value = "拒绝理由", required = false, paramType = "query", dataType = "String")
	})
	public Response<Integer> taskApproval(
										  String taskId,Integer status,String opinion) {
		Response<Integer> response = new Response<>();
		response.setData(approvalService.taskApproval(taskId,status,opinion));
		return response;
	}


	@ApiOperation(value = "任务审核(提供给计划调用)", httpMethod = "POST", notes = "任务审核(提供给计划调用)")
	@RequestMapping(value = "/taskPlanApproval", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taskId", value = "任务id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "审核状态(1:审核通过,2:审核未通过)", required = true, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "opinion", value = "拒绝理由", required = false, paramType = "query", dataType = "String")
	})
	public Response<Boolean> taskPlanApproval(
										  String taskId,Integer status,String opinion) {
		Response<Boolean> response = new Response<>();
		response.setData(approvalService.taskPlanApproval(taskId,status,opinion));
		return response;
	}
}
