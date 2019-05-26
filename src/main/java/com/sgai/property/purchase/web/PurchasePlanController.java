package com.sgai.property.purchase.web;

import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.purchase.param.PlanDetailCompanyParam;
import com.sgai.property.purchase.param.PlanParam;
import com.sgai.property.purchase.param.PlanStageParam;
import com.sgai.property.purchase.param.PlanTaskParam;
import com.sgai.property.purchase.service.PlanTaskServiceImpl;
import com.sgai.property.purchase.service.PurchasePlanServiceImpl;
import com.sgai.property.purchase.service.PurchasePlanStageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@Api(description = "采购计划")
public class PurchasePlanController extends BaseController {

	@Autowired
	PurchasePlanServiceImpl planService;
	
	@Autowired
	PurchasePlanStageServiceImpl stageService;
	
	@Autowired
	PlanTaskServiceImpl taskService;
	
    @RequestMapping(value = "/saveOrUpdatePlan", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新计划", httpMethod = "POST", notes = "新增或更新")
    public Response<String> saveOrUpdatePlan(
                                      @RequestBody PlanParam planParam){

        Response<String> result = new Response<String>();
        result.setData(planService.saveOrUpdatePlan(planParam));
        return result;
    }
    
    @RequestMapping(value = "/saveOrUpdateStage", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新阶段", httpMethod = "POST", notes = "新增或更新阶段")
    public Response<Boolean> saveOrUpdateStage(
                                      @RequestBody List<PlanStageParam> stageParamList){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(stageService.saveOrUpdate(stageParamList));
        return result;
    }
    
    @RequestMapping(value = "/clearStage", method = RequestMethod.POST)
    @ApiOperation(value = "完成任务", httpMethod = "POST", notes = "完成任务")
    public Response<Boolean> clearStage(
                                      @RequestParam String planId){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.clearStage(planId));
        return result;
    }
    
    @RequestMapping(value = "/saveOrUpdateTask", method = RequestMethod.POST)
    @ApiOperation(value = "新增或更新任务", httpMethod = "POST", notes = "新增或更新任务")
    public Response<Boolean> saveOrUpdateTask(
                                      @RequestBody PlanTaskParam planTaskParam){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.saveOrUpdate(planTaskParam));
        return result;
    }
    
    @RequestMapping(value = "/complateTask", method = RequestMethod.POST)
    @ApiOperation(value = "完成任务", httpMethod = "POST", notes = "完成任务")
    public Response<Boolean> complateTask(
                                      @RequestBody List<PlanDetailCompanyParam> paramList){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.complateTask(paramList));
        return result;
    }
    
    @RequestMapping(value = "/taskMove", method = RequestMethod.POST)
    @ApiOperation(value = "任务上移下移", httpMethod = "POST", notes = "任务上移下移")
    public Response<Boolean> taskMove(
    		@ApiParam(value="任务id", required = true) @RequestParam(name="taskId", required = true) String taskId,
    		@ApiParam(value="1:上移，2:下移", required = true) @RequestParam(name = "upDown", required = true) Integer upDown){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.taskMove(taskId, upDown));
        return result;
    }
    
    @RequestMapping(value = "/taskMoveTo", method = RequestMethod.POST)
    @ApiOperation(value = "任务移动至", httpMethod = "POST", notes = "任务移动至")
    public Response<Boolean> taskMoveTo(
    		@ApiParam(value="任务id", required = true) @RequestParam(name="taskId", required = true) String taskId,
    		@ApiParam(value="阶段id", required = true) @RequestParam(name = "stageId", required = true) String stageId){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.taskMoveTo(taskId, stageId));
        return result;
    }
    
    @RequestMapping(value = "/taskDelete", method = RequestMethod.POST)
    @ApiOperation(value = "任务移动至", httpMethod = "POST", notes = "任务移动至")
    public Response<Boolean> taskDelete(
    		@ApiParam(value="任务id", required = true) @RequestParam(name="taskId", required = true) String taskId){

        Response<Boolean> result = new Response<Boolean>();
        result.setData(taskService.taskDelete(taskId));
        return result;
    }
    
    

}
