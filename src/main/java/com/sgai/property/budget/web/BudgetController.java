package com.sgai.property.budget.web;

import com.alibaba.fastjson.JSONObject;
import com.sgai.common.persistence.Page;
import com.sgai.property.budget.service.BudgetServiceImpl;
import com.sgai.property.budget.vo.*;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 145811 on 2017/11/16.
 */
@RestController
@RequestMapping("/budget")
@Api(description = "预算计划")
public class BudgetController extends BaseController {

    @Autowired
    private BudgetServiceImpl budgetService;

    @ApiOperation(value = "计划列表", notes = "计划列表")
    @RequestMapping(value = "/budgetRecordList", method = RequestMethod.POST)
    public Response<Page<RecordVo>> budgetRecordList(


    		@ApiParam(value = "pageNum", required = true) 
    		@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "pageSize", required = true) 
    		@RequestParam("pageSize") int pageSize) {
        Response<Page<RecordVo>> result = new Response<Page<RecordVo>>();

        Page<RecordVo> voList = budgetService.budgetRecordList(pageNum, pageSize);
        result.setData(voList);
        return result;
    }
    
    @ApiOperation(value = "计划列表搜索", notes = "计划列表搜索")
    @RequestMapping(value = "/budgetRecordSearchList", method = RequestMethod.POST)
    public Response<Page<RecordVo>> budgetRecordSearchList(


    		@ApiParam(value = "pageNum", required = true) 
    		@RequestParam("pageNum") int pageNum,
            @ApiParam(value = "pageSize", required = true) 
    		@RequestParam("pageSize") int pageSize,
    		@RequestBody RecordSearchParam searchParam) {
        Response<Page<RecordVo>> result = new Response<Page<RecordVo>>();

        Page<RecordVo> voList = budgetService.budgetRecordSearchList(pageNum, pageSize, searchParam);
        result.setData(voList);
        return result;
    }
    
    @ApiOperation(value = "根据周期获取模板列表", notes = "根据周期获取模板列表")
    @RequestMapping(value = "/getTemplateByCycle", method = RequestMethod.POST)
    public Response<List<TemplateResponseByCycle>> getTemplateByCycle(


    		@ApiParam(value = "周期(1:全年2:半年3季度4:月)", required = true) 
    		@RequestParam("cycle") Long cycle) {
        Response<List<TemplateResponseByCycle>> result = new Response<>();

        List<TemplateResponseByCycle> voList = budgetService.getTemplateByCycle(cycle);
        result.setData(voList);
        return result;
    }
    
    @ApiOperation(value = "新增计划", notes = "新增计划")
    @RequestMapping(value = "/recordAdd", method = RequestMethod.POST)
    public Response<String> recordAdd(

    		@RequestBody RecordSaveParam param) {
        Response<String> result = new Response<String>();

        String recordId = budgetService.recordAdd(param);
        result.setData(recordId);
        return result;
    }
    
    @ApiOperation(value = "编辑计划", notes = "编辑计划")
    @RequestMapping(value = "/recordEdit", method = RequestMethod.POST)
    public Response<Boolean> recordEdit(

    		@RequestBody RecordEditParam param) {
        Response<Boolean> result = new Response<Boolean>();

        boolean bool = budgetService.recordEdit(param);
        result.setData(bool);
        return result;
    }
    
    @ApiOperation(value = "预算详情里基础信息", notes = "预算详情基础信息")
    @RequestMapping(value = "/budgetDetail", method = RequestMethod.POST)
    public Response<RecordVo> budgetDetail(

    		@ApiParam(value = "计划id", required = true) 
    		@RequestParam("recordId") String recordId) {
        Response<RecordVo> result = new Response<RecordVo>();

        RecordVo vo = budgetService.budgetDetail(recordId);
        result.setData(vo);
        return result;
    }
    
    @ApiOperation(value = "预算表格头(模板json串)", notes = "表格头(模板json串)")
    @RequestMapping(value = "/budgetTableHeader", method = RequestMethod.POST)
    public Response<JSONObject> budgetTableHeader(

    		@ApiParam(value = "计划id", required = true) 
    		@RequestParam("recordId") String recordId) {
        Response<JSONObject> result = new Response<>();

        JSONObject vo = budgetService.budgetTableHeader(recordId);
        result.setData(vo);
        return result;
    }
    
    @ApiOperation(value = "录入预算", notes = "录入预算")
    @RequestMapping(value = "/inputBudget", method = RequestMethod.POST)
    public Response<Boolean> inputBudget(

    		@RequestBody InputBudgetParam param) {
        Response<Boolean> result = new Response<Boolean>();

        boolean bool = budgetService.inputBudget(param);
        result.setData(bool);
        return result;
    }
    
    @ApiOperation(value = "录入支出", notes = "录入支出")
    @RequestMapping(value = "/inputExpend", method = RequestMethod.POST)
    public Response<Boolean> inputExpend(

    		@RequestBody InputExpendParam param) {
        Response<Boolean> result = new Response<Boolean>();

        boolean bool = budgetService.inputExpend(param);
        result.setData(bool);
        return result;
    }
    
    @ApiOperation(value = "预算表格记录数据（表格中记录内容）", notes = "预算表格记录数据（表格中记录内容）")
    @RequestMapping(value = "/budgetTableContent", method = RequestMethod.POST)
    public Response<List<RowVo>> budgetTableContent(

    		@ApiParam(value = "计划id", required = true) 
    		@RequestParam("recordId") String recordId) {
        Response<List<RowVo>> result = new Response<>();

        List<RowVo> vo = budgetService.budgetTableContent(recordId);
        result.setData(vo);
        return result;
    }
    
    @ApiOperation(value = "录入详情", notes = "录入详情")
    @RequestMapping(value = "/inputDetail", method = RequestMethod.POST)
    public Response<List<RowVo>> inputDetail(

    		@ApiParam(value = "计划id", required = true) 
    		@RequestParam("recordId") String recordId,
    		@ApiParam(value = "录入id", required = true) 
    		@RequestParam("inputId") String inputId) {
        Response<List<RowVo>> result = new Response<>();

        List<RowVo> vo = budgetService.inputDetail(recordId, inputId);
        result.setData(vo);
        return result;
    }

}
