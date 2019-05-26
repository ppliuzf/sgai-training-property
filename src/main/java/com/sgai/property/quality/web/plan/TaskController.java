package com.sgai.property.quality.web.plan;

import com.alibaba.fastjson.JSON;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.service.plan.RecordTaskServiceImpl;
import com.sgai.property.quality.service.plan.TaskServiceImpl;
import com.sgai.property.common.util.plan.PlanConstants;
import com.sgai.property.quality.vo.CategoryTypeVo;
import com.sgai.property.quality.vo.PlanVo;
import com.sgai.property.quality.vo.TaskParam;
import com.sgai.property.quality.vo.plan.*;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 145811 on 2017/11/16.
 */
@RestController
@RequestMapping("/task")
@Api(description = "任务")
public class TaskController extends BaseController {

    @Autowired
    RecordTaskServiceImpl recordTaskService;
    @Autowired
    TaskServiceImpl taskService;

    @ApiOperation(value = "任务详情", notes = "任务详情")
    @RequestMapping(value = "/taskDetail", method = RequestMethod.POST)
    public Response<PlanTaskVo> taskDetail(
            @RequestParam("taskId") String taskId) {
        Response<PlanTaskVo> result = new Response<PlanTaskVo>();

        PlanTaskVo taskVo = recordTaskService.getDetail(taskId);
        result.setData(taskVo);
        return result;
    }

    @ApiOperation(value = "月任务状态列表", notes = "月任务状态列表")
    @RequestMapping(value = "/taskStatMonth", method = RequestMethod.POST)
    public Response<List<TaskTPStatVo>> taskStatMonth(
            @ApiParam(value = "type 任务类型标示 0：检验类 1：安保类", required = true) @RequestParam("type") Integer type,
            @ApiParam(value = "开始日期", required = true) @RequestParam("startTime") Long startTime,
            @ApiParam(value = "结束日期", required = true) @RequestParam("endTime") Long endTime) {
        Response<List<TaskTPStatVo>> result = new Response<List<TaskTPStatVo>>();
        List<TaskTPStatVo> taskStatVoList = recordTaskService.taskStateMonth(UserServletContext.getUserInfo().getEmCode(), startTime, endTime, type);
        result.setData(taskStatVoList);
        return result;
    }

    @ApiOperation(value = "今天任务状态列表", notes = "今天任务状态列表")
    @RequestMapping(value = "/taskStatMonthToday", method = RequestMethod.POST)
    public synchronized List<ExecuteTaskDetailVo> taskStatMonthToday() {
//        Long eiId = 0L;
//
//		String sgaiToken = this.getSgaiToken(accessToken);
//        eiId = UserServletContext.getUserInfo().getEmCode();
        List<ExecuteTaskDetailVo> executeTaskDetailVoList = recordTaskService.taskStateMonthToday();
        return executeTaskDetailVoList;
    }

    @ApiOperation(value = "执行人月任务状态列表", notes = "执行人月任务状态列表")
    @RequestMapping(value = "/taskStatMonthByExecutor", method = RequestMethod.POST)
    public Response<List<TaskTPStatVo>> taskStatMonthByExecutor(Long startTime, Long endTime, Integer type) {
        Response<List<TaskTPStatVo>> result = new Response<List<TaskTPStatVo>>();
        String eiId = UserServletContext.getUserInfo().getEmCode();
        List<TaskTPStatVo> taskStatVoList = recordTaskService.taskStateMonthByExecutor(eiId, startTime, endTime, type);
        result.setData(taskStatVoList);
        return result;
    }

    @ApiOperation(value = "日任务列表", notes = "日任务列表")
    @RequestMapping(value = "/taskDayList", method = RequestMethod.POST)
    public Response<List<DayTaskVo>> taskDayList(
            @ApiParam(value = "日期", required = true) @RequestParam("dateTime") Long dateTime) {
        Response<List<DayTaskVo>> result = new Response<List<DayTaskVo>>();

        Long eiId = Long.valueOf(UserServletContext.getUserInfo().getEmCode());
        List<DayTaskVo> taskDayList = recordTaskService.taskDay(eiId, dateTime);
        result.setData(taskDayList);
        return result;
    }


    /**
     * 新增任务接口
     * 任务管理者有权限保存
     *
     * @param task
     * @return
     */
    @ApiOperation(value = "新增任务", notes = "新增任务")
    @RequestMapping(value = "/taskSave", method = RequestMethod.POST)
    public Response<Boolean> taskSave(
            @RequestBody TaskParam task) {
        Response<Boolean> result = new Response<Boolean>();
        boolean bool = recordTaskService.saveOrUpdate(task);
        result.setData(bool);
        return result;
    }


    @ApiOperation(value = "任务责任人填写完成情况", notes = "任务责任人填写完成情况")
    @RequestMapping(value = "/taskComplete", method = RequestMethod.POST)
    public Response<Boolean> taskComplete(
            @RequestParam(value = "taskId", required = true) String taskId,
            @RequestParam(value = "taskResult", required = true) String taskResult) {
        Response<Boolean> result = new Response<Boolean>();
        boolean stat = recordTaskService.completeTask(taskId, taskResult);
        result.setData(stat);
        return result;
    }

    @ApiOperation(value = "任务审核人审核", notes = "任务审核人审核")
    @RequestMapping(value = "/taskAppr", method = RequestMethod.POST)
    public Response<Boolean> taskAppr(
            @RequestParam(value = "taskId", required = true) String taskId,
            @ApiParam(name = "apprState", value = "审核；1：同意，0：拒绝", required = true) @RequestParam(value = "apprState", required = true) Long apprState,
            @RequestParam(value = "reason", required = false) String reason) {
        Response<Boolean> result = new Response<Boolean>();

        boolean stat = recordTaskService.apprTask(taskId, apprState, reason);
        result.setData(stat);
        return result;
    }

    @ApiOperation(value = "删除任务", notes = "删除任务")
    @RequestMapping(value = "/taskDelete", method = RequestMethod.POST)
    public Response<Boolean> taskDelete(
            @RequestParam(value = "taskId", required = true) String taskId) {
        Response<Boolean> result = new Response<Boolean>();


        boolean stat = recordTaskService.taskDelete(taskId);
        result.setData(stat);
        return result;
    }

    @ApiOperation(value = "任务上下移动接口", httpMethod = "POST", notes = "任务上下移动接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sId", value = "当前的任务记录id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gId", value = "被交换的任务记录id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/taskMove", method = RequestMethod.POST)
    public Response<String> taskMove(
            @RequestParam("sId") String sId, @RequestParam("gId") String gId) {
        return recordTaskService.taskMove(sId, gId);
    }

    @ApiOperation(value = "将任务移动至其他的任务阶段", httpMethod = "POST", notes = "将任务移动至其他的任务阶段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务记录id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "periodId", value = "阶段id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "periodName", value = "阶段名称", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/taskMoveTo", method = RequestMethod.POST)
    public Response<String> taskMoveTo(
            @RequestParam("taskId") String taskId,
            @RequestParam("periodId") String periodId,
            @RequestParam("periodName") String periodName) {
        return recordTaskService.taskMoveTo(taskId, periodId, periodName);
    }

    @ApiOperation(value = "专业范畴列表", notes = "专业范畴列表")
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.POST)
    public Response<List<Map<String, Object>>> getAllCategory() {
        Response<List<Map<String, Object>>> result = new Response<>();

        List<Map<String, Object>> list = recordTaskService.getAllCategory();
        result.setData(list);
        return result;
    }

    @ApiOperation(value = "专业范畴关联类型列表", notes = "专业范畴关联类型列表")
    @RequestMapping(value = "/getCategoryTypeByCategoryId", method = RequestMethod.POST)
    public Response<List<CategoryTypeVo>> getCategoryTypeByCategoryId(
            String categoryId) {
        Response<List<CategoryTypeVo>> result = new Response<>();

        List<CategoryTypeVo> list = recordTaskService.getCategoryTypeByCategoryId(categoryId);
        result.setData(list);
        return result;
    }

    @ApiOperation(value = "检验方案列表", notes = "检验方案列表")
    @RequestMapping(value = "/getPlanList", method = RequestMethod.POST)
    public Response<List<PlanVo>> getPlanList(

            String categoryId) {
        Response<List<PlanVo>> result = new Response<>();

        List<PlanVo> list = recordTaskService.getPlanList(categoryId);
        result.setData(list);
        return result;
    }

    @ApiOperation(value = "关联房产数据接口", notes = "关联房产数据接口")
    @RequestMapping(value = "/getSpaceData", method = RequestMethod.POST)
    public Response<List<TreeNodeVo>> getSpaceData(

            String id) {
        Response<List<TreeNodeVo>> result = new Response<>();

        List<TreeNodeVo> spaceList = recordTaskService.getSpaceData(id);
        result.setData(spaceList);
        return result;
    }

    @ApiOperation(value = "关联物料数据接口", notes = "关联物料数据接口")
    @RequestMapping(value = "/getMaterielData", method = RequestMethod.POST)
    public Response<List<TreeNodeVo>> getMaterielData(

            String id) {
        Response<List<TreeNodeVo>> result = new Response<>();

        List<TreeNodeVo> spaceList = recordTaskService.getMaterielData(id);
        result.setData(spaceList);
        return result;
    }

    @ApiOperation(value = "关联设备数据接口", notes = "关联设备数据接口")
    @RequestMapping(value = "/getDevicesData", method = RequestMethod.POST)
    public Response<List<TreeNodeVo>> getDevicesData(
            String id,
            String className) {
        Response<List<TreeNodeVo>> result = new Response<>();

        List<TreeNodeVo> spaceList = recordTaskService.getDevicesData(id, className);
        result.setData(spaceList);
        return result;
    }

    /**
     * 新增任务接口(只提供品质管理项目使用)
     * 任务管理者有权限保存
     *
     * @param task
     * @return
     */
    @ApiOperation(value = "新增任务", notes = "新增任务")
    @RequestMapping(value = "/taskQualitySave", method = RequestMethod.POST)
    public Response<String> taskQualitySave(
            @RequestBody TaskParam task) {
        Response<String> result = new Response<>();

        String taskId = recordTaskService.saveTaskQuality(task);
        result.setData(taskId);
        return result;
    }

    /**
     * 更新任务状态接口(只提供品质管理项目使用)
     * 任务管理者有权限保存
     *
     * @return
     */
    @ApiOperation(value = "更新任务状态接口,并根据条件判断是否增加审核功能", notes = "更新任务状态接口,并根据条件判断是否增加审核功能")
    @RequestMapping(value = "/updateTaskQualityStatus", method = RequestMethod.POST)
    public Response<Boolean> updateTaskQualityStatus(
            String taskId, Long status, String eiIds) {
        Response<Boolean> result = new Response<>();
        System.out.println("updateTaskQualityStatus taskId==>:" + taskId + "====eiIds==>" + JSON.toJSONString(eiIds));
        Task task = taskService.getById(taskId);
        if (task == null) {
            //非计划的任务直接返回成功
            result.setData(true);
            return result;
        }
        task.setId(taskId);
        task.setTaskApprState(status);
        if (status == PlanConstants.MT_STATUS_3.longValue()) {
            task.setTaskIsComplete(1L); //已完成
        }
        try {

            Long comId = UserServletContext.getUserInfo().getCompanyId();
            //出错不能影响主体流程
            if (StringUtils.isNotEmpty(eiIds)) {
                List<String> eiIdList = JSON.parseArray(eiIds, String.class);
                recordTaskService.addTaskQualityApproval(taskId, comId, eiIdList);
            }
        } catch (Exception e) {
        }
        result.setData(taskService.updateById(task));
        return result;
    }

}
