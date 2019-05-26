package com.sgai.property.quality.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.TestItemServiceImpl;
import com.sgai.property.quality.vo.TestItemDetailVo;
import com.sgai.property.quality.vo.TestItemVo;
import com.sgai.property.quality.vo.dto.TestItemDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(description = "任务项接口")
@Api(description = "任务项接口")
@RestController
@RequestMapping("/testitem")
public class TestItemController extends BaseController {

	@Autowired
	TestItemServiceImpl testItemService;
	

	@PostMapping(value = "/createTestItem")
	@ApiOperation(value = "新增任务项", notes = "新增任务项")
	public Response<Boolean> createTestItem(
											@RequestBody TestItemDto testItemDto) {
		Response<Boolean> response = new Response<>();
		response.setData(testItemService.createTestItem(testItemDto));
		return response;
	}

	@PostMapping(value = "/delTestItem")
	@ApiOperation(value = "删除任务项", notes = "删除任务项")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemId", value = "任务项ID", required = true, paramType = "query", dataType = "String") })
	public Response<Boolean> delTestItem(String itemId) {
		Response<Boolean> response = new Response<>();
		
		if(StringUtils.isEmpty(itemId)){
			throw new BusinessException(ReturnType.ParamIllegal, "请输入正确的任务项ID");
		}
		
		response.setData(testItemService.delTestItem(itemId));
		return response;
	}

	@PostMapping(value = "/listTestItem")
	@ApiOperation(value = "任务成果列表", notes = "任务成果列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemName", value = "任务名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "pcId", value = "专业范畴ID", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "startDate", value = "开始日期", required = false, paramType = "query", dataType = "long"),
		@ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query", dataType = "long"),
		@ApiImplicitParam(name = "type", value = "type 任务类型标示 0：检验类 1：安保类", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "int")
		})
	public Response<Page<TestItemVo>> listTestItem(String itemName, String pcId, Long startDate, Long endDate,Integer type, Integer pageNum, Integer pageSize) {
		Response<Page<TestItemVo>> response = new Response<>();
		response.setData(testItemService.listTestItem(itemName,pcId,startDate,endDate,type,pageNum,pageSize));
		return response;
	}

	@PostMapping(value = "/detailTestItem")
	@ApiOperation(value = "任务项详情", notes = "任务项详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemId", value = "任务项ID", required = true, paramType = "query", dataType = "String")
	})
	public Response<TestItemDetailVo> detailTestItem( String itemId) {
		Response<TestItemDetailVo> response = new Response<>();
		response.setData(testItemService.detailTestItem(itemId));
		return response;
	}

	
	@PostMapping(value = "/updateTestItem")
	@ApiOperation(value = "编辑任务项", notes = "编辑任务项")
	public Response<Boolean> updateTestItem(@RequestBody TestItemDto testItemDto) {
		Response<Boolean> response = new Response<>();

		response.setData(testItemService.updateTestItem(testItemDto));
		return response;
	}
	@PostMapping(value = "/saveExecuteTestItem")
	@ApiOperation(value = "保存执行任务项", notes = "保存执行任务项")
	public Response<Boolean> saveUpdateTestItem(@RequestBody TestItemDto testItemDto) {
		Response<Boolean> response = new Response<>();

		response.setData(testItemService.saveExecuteTestItem(testItemDto));
		return response;
	}
	
	
}
