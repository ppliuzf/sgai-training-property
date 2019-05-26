package com.sgai.property.budget.web;

import com.sgai.property.budget.service.ApprovalServiceImpl;
import com.sgai.property.budget.vo.ApprovalParam;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author 严浩淼
 *@date 2018年1月19日--上午10:11:12
 */
@Api(description="计划审核")
@RestController
@RequestMapping(value="/approval")
public class ApprovalController extends BaseController {

	@Autowired
	private ApprovalServiceImpl approvalService;
	
	@ApiOperation(value="审核通过/拒绝",httpMethod="POST",notes="审核通过/拒绝")
	@PostMapping(value="check")
	public Response<Boolean> approval(@RequestBody ApprovalParam param) {
		Response<Boolean> result = new Response<>();
		try {

			result.setData(approvalService.check(param));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="提交审核",httpMethod="POST",notes="提交审核")
	@PostMapping(value="submit")
	public Response<Boolean> submit(String id) {
		Response<Boolean> result = new Response<>();
		try {

			ApprovalParam param =new ApprovalParam();
			param.setId(id);
			param.setState(1L);
			result.setData(approvalService.submitOrRevoke(param));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="撤销提交",httpMethod="POST",notes="撤销提交")
	@PostMapping(value="revoke")
	public Response<Boolean> revoke(String id) {
		Response<Boolean> result = new Response<>();
		try {

			ApprovalParam param =new ApprovalParam();
			param.setId(id);
			param.setState(4L);
			result.setData(approvalService.submitOrRevoke(param));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
}
