package com.sgai.property.notice.web;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.entity.ApprovalParam;
import com.sgai.property.notice.entity.NoticeInfoParam;
import com.sgai.property.notice.entity.NoticeResponseInfo;
import com.sgai.property.notice.service.NoticeInfoOperationServiceImpl;
import com.sgai.property.notice.vo.SendDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
@Api(description = "公告操作相关接口")
public class NoticeInfoOperationController extends BaseController {

	@Autowired
	NoticeInfoOperationServiceImpl noticeInfoOperationService;

	@RequestMapping(value="/infoApproval", method = RequestMethod.POST)
	@ApiOperation(value = "审批公告", httpMethod = "POST", notes = "审批公告")
	public Response<Boolean> infoApproval(

			@RequestBody ApprovalParam approvalParam) {
		Response<Boolean> result = new Response<>();

		Boolean bool = noticeInfoOperationService.infoApproval(approvalParam);
		result.setData(bool);
		return result;
	}

	@RequestMapping(value="/infoPublish", method = RequestMethod.POST)
	@ApiOperation(value = "发布公告", httpMethod = "POST", notes = "发布公告")
	public Response<String> infoPublish(

			@RequestParam String id) {

		String bool = noticeInfoOperationService.infoPublish(id);
		Response<String> result = new Response<>();
		result.setData(bool);
		return result;
	}

	@RequestMapping(value="/infoRetract", method = RequestMethod.POST)
	@ApiOperation(value = "撤回公告", httpMethod = "POST", notes = "撤回公告")
	public Response<Boolean> infoRetract(

			@RequestParam String id) {

		Boolean bool = noticeInfoOperationService.infoRetract(id);
		Response<Boolean> result = new Response<>();
		result.setData(bool);
		return result;
	}

	@RequestMapping(value="/infoIsTop", method = RequestMethod.POST)
	@ApiOperation(value = "公告置顶/取消", httpMethod = "POST", notes = "公告置顶/取消")
	public Response<Boolean> infoIsTop(

			@RequestBody ApprovalParam approvalParam) {
		Response<Boolean> result = new Response<>();

		Boolean bool = noticeInfoOperationService.infoIsTop(approvalParam);
		result.setData(bool);
		return result;
	}

	@RequestMapping(value="/selectInfo", method = RequestMethod.POST)
	@ApiOperation(value = "搜索接口", httpMethod = "POST", notes = "搜索接口")
	public Response<List<NoticeResponseInfo>> selectInfo(

			 @RequestBody NoticeInfoParam noticeInfoParam
			, int pageNum
			, int pageSize
	) {

		Response<List<NoticeResponseInfo>> page=noticeInfoOperationService.selectInfo(noticeInfoParam,UserServletContext.getUserInfo().getUserNo(),pageNum,pageSize);
		return page;
	}

	@RequestMapping(value="/sendDetail", method = RequestMethod.POST)
	@ApiOperation(value = "发送明细", httpMethod = "POST", notes = "发送明细")
	public Response<SendDetailVo> sendDetail(

			@RequestParam("infoId") String infoId) {

		SendDetailVo sendVo = noticeInfoOperationService.sendDetail(infoId);
		Response<SendDetailVo> result = new Response<>();
		result.setData(sendVo);
		return result;
	}
}