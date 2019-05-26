package com.sgai.property.customer.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.customer.service.WorkorderCostServiceImpl;
import com.sgai.property.customer.vo.WorkorderCostParam;
import com.sgai.property.customer.vo.WorkorderCostVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workorderCost")
@Api(description = "工单费用接口")
public class WorkorderCostController extends BaseController {
    @Autowired
	private WorkorderCostServiceImpl workorderCostService;

  
	@PostMapping(value="/workorderCostList")
    @ApiOperation(value = "工单费用列表", httpMethod = "POST", notes = "工单费用列表")
	public Response<Page<WorkorderCostVO>> workorderCostList(
                                                             @RequestBody(required=false) WorkorderCostParam workorderCostParam, int pageNum, int pageSize) {

		Page<WorkorderCostVO> page=workorderCostService.findWorkorderCostList(workorderCostParam,pageNum,pageSize);
        Response<Page<WorkorderCostVO>> result = new Response<>();
        result.setData(page);
		return result;
	}


	@PostMapping(value="/getWorkorderCostById")
	@ApiOperation(value = "获取工单费用详情", httpMethod = "POST", notes = "获取工单费用详情")
    public Response<WorkorderCostVO> getWorkorderCostById( @RequestParam(required=true) String id){

		Response<WorkorderCostVO> result = new Response<>();
		WorkorderCostVO workorderCostDetailVO = workorderCostService.getWorkorderCostById(id);
		result.setData(workorderCostDetailVO);
		return result;
    }


	@ApiOperation(value = "保存工单费用信息", httpMethod = "POST", notes = "保存工单费用信息")
	@PostMapping(value = "/saveWorkorderCost")
	public Response<Boolean> saveWorkorderCost( @RequestBody WorkorderCostParam workorderCost){

		Response<Boolean> result = new Response<>();
        Boolean flag = workorderCostService.saveWorkorderCost(workorderCost);
        result.setData(flag);
        return result;
    }

	@ApiOperation(value = "编辑更新工单费用信息", httpMethod = "POST", notes = "编辑更新工单费用信息")
	@PostMapping(value = "/updateWorkorderCostById")
	public Response<Boolean> updateWorkorderCost( @RequestBody(required=true) WorkorderCostVO workorderCostDetailVO){

		Response<Boolean> result = new Response<>();
		Boolean flag = workorderCostService.updateWorkorderCost(workorderCostDetailVO);
		result.setData(flag);
		return result;
	 }

	@ApiOperation(value = "删除工单费用", httpMethod = "POST", notes = "删除工单费用")
	@PostMapping(value = "/deleteWorkorderCostById")
    public Response<Boolean> deleteWorkorderCostById( String id){

        Response<Boolean> result = new Response<>();
        result.setData(workorderCostService.deleteWorkorderCostById(id));
        return result;
	}

}