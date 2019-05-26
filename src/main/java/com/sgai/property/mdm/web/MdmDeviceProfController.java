package com.sgai.property.mdm.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.mdm.entity.MdmDeviceProf;
import com.sgai.property.mdm.service.MdmDeviceProfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: MdmDeviceProfController  
    * com.sgai.property.commonService.vo;(设备专业维护)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/deviceprof/mdmDeviceProf")
@Api(description = "设备专业接口")
public class MdmDeviceProfController extends BaseController {

	@Autowired
	private MdmDeviceProfService mdmDeviceProfService;
	
	/**
	 * 
	 * getListDeviceProf:(查询设备专业数据列表).
	 * @param mdmDeviceProf
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmDeviceProf> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备专业分页列表信息", httpMethod = "POST", notes = "获得设备专业分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "profCode", value = "设备专业代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "profName", value = "设备专业名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDeviceProf", method=RequestMethod.POST)
	public CommonResponse getListDeviceProf(
			LoginUser user,
			String profCode,
			String profName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceProf mdmDeviceProf = new MdmDeviceProf();
		mdmDeviceProf.setProfCode(profCode);
		mdmDeviceProf.setProfName(profName);
		mdmDeviceProf.setEnabledFlag("Y");
		Page<MdmDeviceProf> page = mdmDeviceProfService.findPage(new Page<MdmDeviceProf>(pageNo, pageSize), mdmDeviceProf);
		return ResponseUtil.successResponse(page);
	}
	
	
	
	/**
	 * 
	 * getListDeviceProf:(查询主数据下拉).
	 * @param mdmDeviceProf
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "/getListDeviceProfs", method=RequestMethod.POST)
	public List<MdmDeviceProf> getListDeviceProfs(
			LoginUser user,
			String profCode,
			String profName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		MdmDeviceProf mdmDeviceProf = new MdmDeviceProf();
		mdmDeviceProf.setProfCode(profCode);
		mdmDeviceProf.setProfName(profName);
		mdmDeviceProf.setEnabledFlag("Y");
		List<MdmDeviceProf> list = mdmDeviceProfService.findList(mdmDeviceProf);
		return list;
	}
	
	/**
	 * 
	 * getAllList:(获得所有的专业).
	 * @param mdmDeviceProf
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :List<MdmDeviceProf> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备专业列表信息", httpMethod = "POST", notes = "获得设备专业列表信息")
	@RequestMapping(value = "/getAllList", method=RequestMethod.POST)
	public CommonResponse getAllList(
			MdmDeviceProf mdmDeviceProf,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		mdmDeviceProf.setEnabledFlag("Y");
		List<MdmDeviceProf> list = mdmDeviceProfService.findList(mdmDeviceProf);
		return ResponseUtil.successResponse(list);
	}
	@ApiOperation(value = "获得可控设备专业列表信息", httpMethod = "POST", notes = "获得设备专业列表信息")
	@RequestMapping(value = "/getProfByControl", method=RequestMethod.POST)
	public CommonResponse getProfByControl(
			MdmDeviceProf mdmDeviceProf,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		mdmDeviceProf.setEnabledFlag("Y");
		mdmDeviceProf.setControlFlag("Y");
		List<MdmDeviceProf> list = mdmDeviceProfService.findList(mdmDeviceProf);
		return ResponseUtil.successResponse(list);
	}
	/**
	 * 
	 * getDeviceProf:(查询一条设备专业信息).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :MdmDeviceProf 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备专业信息", httpMethod = "POST", notes = "获得设备专业信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备专业主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getDeviceProf", method=RequestMethod.POST)
	public CommonResponse getDeviceProf(
			String id, 

			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		MdmDeviceProf prof = mdmDeviceProfService.get(id);
		return ResponseUtil.successResponse(prof);
	}
	
	/**
	 * 
	 * save:(新增或者修改保存设备专业信息).
	 * @param mdmDeviceProf
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存设备专业信息", httpMethod = "POST", notes = "保存设备专业信息")
	@RequestMapping(value = "save", method=RequestMethod.POST)
	public CommonResponse save(
			MdmDeviceProf mdmDeviceProf, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			mdmDeviceProf.setEnabledFlag("Y");
			map = mdmDeviceProfService.saveDeviceProf(mdmDeviceProf);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除设备专业数据).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除设备专业信息", httpMethod = "POST", notes = "删除设备专业信息")
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
			map = mdmDeviceProfService.deleteDeviceProfs(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}