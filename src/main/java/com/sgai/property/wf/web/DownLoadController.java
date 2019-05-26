package com.sgai.property.wf.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.utils.excel.ExportExcel;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.service.WfFlowDefineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "${adminPath}/downLoad")
@Api(description = "下载接口")
public class DownLoadController {

	@Autowired
	private WfFlowDefineService wfFlowDefineService;
	
	@ApiOperation(value = "下载事件流程定义数据模板", httpMethod = "GET", notes = "下载事件流程定义数据模板")
	@RequestMapping(value = "/downLoadExcel", method=RequestMethod.GET)
	public CommonResponse downLoadExcel(
			HttpServletResponse response, 
			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
            String fileName = "事件流程定义.xlsx";
            WfFlowDefine  wfFlowDefine  = new  WfFlowDefine();
    		List<WfFlowDefine> list = wfFlowDefineService.findList(wfFlowDefine); 
    		new ExportExcel("事件流程定义数据", WfFlowDefine.class, 2).setDataList(list).write(response, fileName).dispose();
    		map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
}

