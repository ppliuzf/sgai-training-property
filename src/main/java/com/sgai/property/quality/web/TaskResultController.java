package com.sgai.property.quality.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.QtTaskResultServiceImpl;
import com.sgai.property.quality.service.TaskResultServiceImpl;
import com.sgai.property.quality.vo.TaskResultDetailVo;
import com.sgai.property.quality.vo.TaskResultVo;
import com.sgai.property.quality.vo.TaskStatusUncheckedVo;
import com.sgai.property.quality.vo.TestItemDetailVo;
import com.sgai.property.quality.vo.plan.ExecuteTaskDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "检验成果接口")
@RestController
@RequestMapping("/taskresult")
public class TaskResultController  extends BaseController {

	
	@Autowired
	TaskResultServiceImpl taskResultService;
	@Autowired
	QtTaskResultServiceImpl QttaskResultService;

	@PostMapping(value = "/listTaskResult")
	@ApiOperation(value = "检验成果列表", notes = "检验成果列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ttName", value = "任务名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "pcId", value = "专业范畴ID", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "startDate", value = "开始日期", required = false, paramType = "query", dataType = "long"),
		@ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query", dataType = "long"),
		@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "int")
		})
	public Response<Page<TaskResultVo>> listCategory(
													 String ttName,String pcId, Long startDate, Long endDate, Integer pageNum, Integer pageSize) {
		Response<Page<TaskResultVo>> response = new Response<>();
		response.setData(taskResultService.listTaskResult(ttName,pcId,startDate,endDate,pageNum,pageSize));
		return response;
	}

	@PostMapping(value = "/resultDetail")
	@ApiOperation(value = "检验成果详情", notes = "检验成果详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskId", value = "任务Id", required = true, paramType = "query", dataType = "String")
	})
	public Response<TaskResultDetailVo> resultDetail(String taskId) {
		Response<TaskResultDetailVo> response = new Response<>();
		response.setData(taskResultService.resultDetail(taskId));
		return response;
	}
	
	@ApiOperation(value = "计划关联任务项状态列表", httpMethod = "POST", notes = "任务项状态列表")
	@RequestMapping(value = "/taskItemStatusList", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tpId", value = "任务模板Id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "recordId", value = "计划Id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "dateTime", value = "日期时间", required = true, paramType = "query", dataType = "Long")
	})
	public Response<TaskStatusUncheckedVo> taskItemStatusList(
									String tpId ,String  recordId,Long dateTime) {
		Response<TaskStatusUncheckedVo> response = new Response<TaskStatusUncheckedVo>();
		response.setData(QttaskResultService.listByRecordIdAndTpId(tpId,recordId,dateTime));
		return response;
	}
	@PostMapping(value = "/detailTestItem")
	@ApiOperation(value = "任务项详情", notes = "任务项详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemId", value = "任务项ID", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "recordId", value = "计划ID", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "dateTime", value = "日期时间", required = true, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "tpId", value = "模板ID", required = true, paramType = "query", dataType = "String")

	})
	public Response<TestItemDetailVo> detailTestItem( String itemId, String recordId,Long dateTime,String tpId) {
		Response<TestItemDetailVo> response = new Response<>();
		response.setData(QttaskResultService.detailTestItem(itemId,"",recordId,dateTime,tpId));
		return response;
	}
	
	
	
	/****************************************************************************************************************/
	@PostMapping(value = "/executeTaskDetail")
	@ApiOperation(value = "任务详情", notes = "任务详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "recordId", value = "计划Id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "templateId", value = "模板Id", required = true, paramType = "query", dataType = "String")
	})
	public Response<ExecuteTaskDetailVo> executeTaskDetail(
														   String recordId, String templateId,Long dateTime) {
		Response<ExecuteTaskDetailVo> response = new Response<>();
		response.setData(taskResultService.ExecuteTaskDetail(recordId,templateId,dateTime));
		return response;
	}



}
