package com.sgai.property.customer.web;


import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.customer.service.CommonServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/common")
@Api(description = "通用工具类")
public class CusCommonController extends BaseController {

	@Autowired
	private CommonServiceImpl commonService;


//
//	@ApiOperation(value = "上传图片", httpMethod = "POST", notes = "上传图片,成功返回url")
//	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
//	public Response<String> uploadImage() {
//
//	}
	

	@ApiOperation(value = "导入客户列表", httpMethod = "POST", notes = "导入客户列表")
	@RequestMapping(value = "/uploadCustomList", method = RequestMethod.POST)
	public Response<Boolean> uploadCustomList(
											  @RequestParam(value="file",required = false)MultipartFile file) {
		Response<Boolean> result = new Response<>();
		result.setData(commonService.uploadCustomList(file));
		return result;
	}

	@ApiOperation(value = "导出模板Excel", httpMethod = "GET", notes = "导出模板Excel")
	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public void downloadExcel() {
		try {
			commonService.downloadExcel(response);
		}catch (Exception e){
			e.printStackTrace();
			throw new BusinessException(ReturnType.ParamIllegal,"网络正忙,请稍后再试!");
		}

	}

}