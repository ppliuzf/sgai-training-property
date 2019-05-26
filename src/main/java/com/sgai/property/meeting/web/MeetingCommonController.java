package com.sgai.property.meeting.web;

import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.meeting.service.CommonServicelmpl;
import com.sgai.property.meeting.service.MailConfigureServiceImpl;
import com.sgai.property.meeting.vo.MailConfigurationDto;
import com.sgai.property.meeting.vo.MailConfigureDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/common")
@Api(description = "通用工具类")
public class MeetingCommonController extends BaseController {

	@Autowired
	private MailConfigureServiceImpl mailConfigureService;
	@Autowired
	private CommonServicelmpl commonServicelmpl;

	@ApiOperation(value = "修改邮件配置信息,byMcId", httpMethod = "POST", notes = "修改邮件配置信息,byMcId")
	@RequestMapping(value = "/updateEmailConfigInfo", method = RequestMethod.POST)
	public Response<MailConfigureDto> updateById( @RequestBody MailConfigurationDto mailConfigureDto) {
		Response<MailConfigureDto> result = new Response<>();

		result.setData(mailConfigureService.updateMailConfigureById(mailConfigureDto));
		return result;
	}

	@ApiOperation(value = "修改邮件开启关闭项:isSend 1开启,0关闭", httpMethod = "POST", notes = "修改邮件开启关闭项:isSend 1开启,0关闭")
	@RequestMapping(value = "/openOrCloseEmail", method = RequestMethod.POST)
	public Response<MailConfigureDto> openOrCloseEmail(
													   @RequestBody MailConfigureDto mailConfigureDto) {
		Response<MailConfigureDto> result = new Response<>();

		result.setData(mailConfigureService.openOrCloseEmail(mailConfigureDto));
		return result;
	}


	@ApiOperation(value = "获取邮件配置信息", httpMethod = "POST", notes = "获取邮件配置信息")
	@RequestMapping(value = "/getEmailConfigInfo", method = RequestMethod.POST)
	public Response<MailConfigureDto> getEmailConfigInfo() {
		Response<MailConfigureDto> result = new Response<>();
		result.setData(mailConfigureService.getEmailConfigInfo());
		return result;
	}

	@ApiOperation(value = "获取系统时间", httpMethod = "POST", notes = "获取系统时间")
	@RequestMapping(value = "/getSystemTime", method = RequestMethod.POST)
	public Response<Long> getSystemTime() {
		Response<Long> result = new Response<>();
		result.setData(System.currentTimeMillis());
		return result;
	}

	@ApiOperation(value = "上传图片", httpMethod = "POST", notes = "上传图片,成功返回url")
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public Response<String> uploadImage() {
		return commonServicelmpl.uploadImage(request);

	}

	@ApiOperation(value = "测试邮件配置是否可用", httpMethod = "POST", notes = "测试邮件配置是否可用")
	@RequestMapping(value = "/testSendEmail", method = RequestMethod.POST)
	public Response<Boolean> testSendEmail(@RequestBody MailConfigureDto mailConfigureDto) {
		Response<Boolean> result = new Response<>();
		Boolean str = commonServicelmpl.testSendEmail(mailConfigureDto);
		result.setData(str);
		return result;

	}

}