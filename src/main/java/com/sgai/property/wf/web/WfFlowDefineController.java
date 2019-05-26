package com.sgai.property.wf.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.excel.ExportExcel;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.service.WfFlowDefineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: WfFlowDefineController  
    * com.sgai.property.commonService.vo;(事件流程定义维护)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/wf/wfflowdefine/wfFlowDefine")
@Api(description = "事件流程定义接口")
public class WfFlowDefineController extends BaseController {

	@Autowired
	private WfFlowDefineService wfFlowDefineService;
	
	/**
	 * 
	 * getListFlowDefine:(查询事件流程定义列表).
	 * @param wfFlowDefine
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<WfFlowDefine> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得事件流程分页列表信息", httpMethod = "POST", notes = "获得事件流程分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "stepName", value = "步骤名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListFlowDefine", method=RequestMethod.POST)
	public CommonResponse getListFlowDefine(
			LoginUser user,
			String stepName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		WfFlowDefine wfFlowDefine = new WfFlowDefine();
		wfFlowDefine.setStepName(stepName);
		Page<WfFlowDefine> page = wfFlowDefineService.findPage(new Page<WfFlowDefine>(pageNo, pageSize), wfFlowDefine);
		return ResponseUtil.successResponse(page);
	}

	/**
	 * 
	 * getFlowDefine:(查询一条事件流程定义信息).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :WfFlowDefine 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得事件流程信息", httpMethod = "POST", notes = "获得事件流程信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getFlowDefine", method=RequestMethod.POST)
	public CommonResponse getFlowDefine(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		WfFlowDefine wfFlowDefine = wfFlowDefineService.get(id);
		return ResponseUtil.successResponse(wfFlowDefine);
	}
	
	/**
	 * 
	 * save:(保存事件流程定义).
	 * @param wfFlowDefine
	 * @param oldStepCode
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "保存事件流程信息", httpMethod = "POST", notes = "保存事件流程信息")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			WfFlowDefine wfFlowDefine, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = wfFlowDefineService.saveFlowDefine(wfFlowDefine);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除事件流程定义数据).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除事件流程信息", httpMethod = "POST", notes = "删除事件流程信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = wfFlowDefineService.deleteFlowDefine(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * importExcel:(导入事件流程定义数据).
	 * @param file
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "导入事件流程定义数据", httpMethod = "POST", notes = "导入事件流程定义数据")
	@RequestMapping(value = "/importExcel", method=RequestMethod.POST)
    public CommonResponse importExcel(
    		@RequestParam("upfile") MultipartFile file, 

    		RedirectAttributes redirectAttributes
    		) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = wfFlowDefineService.importFlowDefines(file);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
    }
	
}