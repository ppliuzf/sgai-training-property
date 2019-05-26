package com.sgai.property.quality.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.service.IPlanServiceImpl;
import com.sgai.property.quality.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务模板pc
 * @author wuzhihui
 *
 */
@RestController
@RequestMapping("/planPc")
@Api(description = "任务模板设置接口")
public class PlanPcController extends BaseController {
	@Autowired
	private IPlanServiceImpl planService;

	@ApiOperation(value = "任务模板列表", httpMethod = "POST", notes = "任务模板列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "任务模板", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pcId", value = "任务专业id", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "startTime", value = "开始创建日期", required = false, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "endTime", value = "结束创建日期", required = false, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "type", value = "type 任务类型标示 0：检验类 1：安保类", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageNum", value = "pageSize", required = true, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "Long")
	})
	public Response<Page<PlanVo>> list(
									   String name, String pcId, Long startTime, Long endTime,Integer type, Integer pageNum, Integer pageSize) {
		Response<Page<PlanVo>> response = new Response<>();
		response.setData(planService.list(name,pcId,startTime,endTime,type,pageNum,pageSize));
		return response;
	}
	
	
	@ApiOperation(value = "任务项状态列表", httpMethod = "POST", notes = "任务项状态列表")
	@RequestMapping(value = "/taskItemStatusListById", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tpId", value = "任务模板Id", required = true, paramType = "query", dataType = "String"),
	})
	public Response<TaskStatusUncheckedVo> listById(
									String tpId ) {
		Response<TaskStatusUncheckedVo> response = new Response<TaskStatusUncheckedVo>();
		response.setData(planService.listByTpId(tpId));
		return response;
	}
	
	
	@ApiOperation(value = "删除任务模板", httpMethod = "POST", notes = "删除任务模板")
	@RequestMapping(value = "/deletePlanById", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "模板id", required = true, paramType = "query", dataType = "Long")
	})
	public Response<Boolean> deletePlanById( String id) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.deletePlanById(id));
		return response;
	}

	@ApiOperation(value = "新增任务模板", httpMethod = "POST", notes = "新增任务模板")
	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "任务模板名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pcId", value = "任务专业id", required = true, paramType = "query", dataType = "Long"),
		@ApiImplicitParam(name = "description", value = "模板说明", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "type", value = "type 任务类型标示 0：检验类 1：安保类", required = false, paramType = "query", dataType = "int")
	})
	public Response<String> addPlan(
			String name,String pcId,String description,Integer type) {
		Response<String> response = new Response<>();

		response.setData(planService.addPlan(name,pcId,description,type));
		return response;
	}

	@ApiOperation(value = "任务模板详细信息", httpMethod = "POST", notes = "任务模板详细信息")
	@RequestMapping(value = "/planDetail", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "模板id", required = true, paramType = "query", dataType = "String")
	})
	public Response<PlanItemVo> planDetail(String id) {
		Response<PlanItemVo> response = new Response<>();
		response.setData(planService.planDetail(id));
		return response;
	}

	@ApiOperation(value = "任务模板未关联任务项列表", httpMethod = "POST", notes = "任务模板未关联任务项列表")
	@RequestMapping(value = "/unlinkList", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "planId", value = "模板id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "搜索任务项名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "type 任务类型标示 0：检验类 1：安保类", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageNum", value = "pageSize", required = true, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, paramType = "query", dataType = "Long")
	})
	public Response<Page<UnLinkItemVo>> unlinkList(
													   String planId, String name,Integer type, Integer pageNum, Integer pageSize) {
		Response<Page<UnLinkItemVo>> response = new Response<>();
		response.setData(planService.unlinkList(planId,name,type,pageNum,pageSize));
		return response;
	}

	@ApiOperation(value = "任务项取消关联", httpMethod = "POST", notes = "任务项取消关联")
	@RequestMapping(value = "/unlink", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "任务项id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> unlink(String id) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.unlink(id));
		return response;
	}

//	@ApiOperation(value = "任务模板关联任务项", httpMethod = "POST", notes = "任务模板关联任务项")
//	@RequestMapping(value = "/link", method = RequestMethod.POST)
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "id", value = "任务项id", required = true, paramType = "query", dataType = "String"),
//		@ApiImplicitParam(name = "planId", value = "模板id", required = true, paramType = "query", dataType = "String")
//	})
//	public Response<Boolean> linkPlan(
//									  String id,String planId) {
//		Response<Boolean> response = new Response<>();
//
//		response.setData(planService.linkPlan(accessToken,id,planId));
//		return response;
//	}
	
	@ApiOperation(value = "任务模板关联多个任务项", httpMethod = "POST", notes = "任务模板关联多个任务项")
	@RequestMapping(value = "/links", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ids", value = "任务项ids", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "planId", value = "模板id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> linkPlanByIds(
									  String[] ids,String planId) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.batchLinksPlan(ids,planId));
		return response;
	}
	
	@ApiOperation(value = "任务模板新建组", httpMethod = "POST", notes = "任务模板新建组")
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "planId", value = "模板id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "name", value = "组名称", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> addGroup(
									  String planId,String name) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.addGroup(planId,name));
		return response;
	}

	@ApiOperation(value = "任务模板删除组", httpMethod = "POST", notes = "任务模板删除组")
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "groupId", value = "模板id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> deleteGroup(String groupId) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.deleteGroup(groupId));
		return response;
	}

	@ApiOperation(value = "组的上移下移", httpMethod = "POST", notes = "组的上移下移")
	@RequestMapping(value = "/groupUpOrDown", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "upId", value = "要上移的组id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "downId", value = "要下移的组id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> groupUpOrDown(
										   String upId,String downId) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.groupUpOrDown(upId,downId));
		return response;
	}

	@ApiOperation(value = "组内的检查项上移下移", httpMethod = "POST", notes = "组内的检查项上移下移")
	@RequestMapping(value = "/itemUpOrDown", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "upId", value = "要上移的组id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "downId", value = "要下移的组id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> itemUpOrDown(
										  String upId,String downId) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.itemUpOrDown(upId,downId));
		return response;
	}

	@ApiOperation(value = "移动检查项到其他组", httpMethod = "POST", notes = "移动检查项到其他组")
	@RequestMapping(value = "/moveToOtherGroup", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "itemId", value = "检查项id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "groupId", value = "组id", required = true, paramType = "query", dataType = "String")
	})
	public Response<Boolean> moveToOtherGroup(
											  String itemId,String groupId) {
		Response<Boolean> response = new Response<>();

		response.setData(planService.moveToOtherGroup(itemId,groupId));
		return response;
	}

	@ApiOperation(value = "移动到其他组的下拉列表", httpMethod = "POST", notes = "移动到其他组的下拉列表")
	@RequestMapping(value = "/groupList", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "检查项id", required = true, paramType = "query", dataType = "String"),
	})
	public Response<List<OptionVo>> groupList(String id) {
		Response<List<OptionVo>> response = new Response<>();
		response.setData(planService.groupList(id));
		return response;
	}

	@ApiOperation(value = "分组", httpMethod = "POST", notes = "分组")
	@RequestMapping(value = "/grouping", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "planId", value = "方案id", required = true, paramType = "query", dataType = "String"),
	})
	public Response<List<GroupingVo>> grouping(String planId) {
		Response<List<GroupingVo>> response = new Response<>();
		response.setData(planService.grouping(planId));
		return response;
	}

}
