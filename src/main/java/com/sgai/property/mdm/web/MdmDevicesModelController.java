package com.sgai.property.mdm.web;

import java.io.IOException;
import java.util.HashMap;
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
import com.sgai.property.mdm.entity.MdmDevicesModel;
import com.sgai.property.mdm.service.MdmDevicesModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.sgai.property.mdm.dto.MdmDevicesModelVo;


/**
 * 
    * ClassName: MdmDevicesModelController  
    * com.sgai.property.commonService.vo;(设备型号维护)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/devicesmodel/mdmDevicesModel")
@Api(description = "设备型号接口")
public class MdmDevicesModelController extends BaseController {

	@Autowired
	private MdmDevicesModelService mdmDevicesModelService;

	/**
	 * 
	 * getListDeviceClass:(查询设备型号信息列表).
	 * @param mdmDevicesModel
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmDevicesModel> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备型号分页列表信息", httpMethod = "POST", notes = "获得设备型号分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "classCode", value = "设备分类代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "brandCode", value = "品牌代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "devicesModel", value = "设备型号名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDevicesModel",method=RequestMethod.POST)
	public CommonResponse getListDevicesModel(
			LoginUser user,
			String classCode,
			String brandCode,
			String devicesModel,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {	
		MdmDevicesModel mdmDevicesModel = new MdmDevicesModel();
		mdmDevicesModel.setClassCode(classCode);
		mdmDevicesModel.setBrandCode(brandCode);
		mdmDevicesModel.setDevicesModel(devicesModel);
		Page<MdmDevicesModel> page = mdmDevicesModelService.findPage(new Page<MdmDevicesModel>(pageNo, pageSize), mdmDevicesModel);
		//page.setOrderBy("CREATED_DT");
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getDevicesModel:(查询一条设备型号数据).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :MdmDevicesModel 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备型号信息", httpMethod = "POST", notes = "获得设备型号信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getDevicesModel",method=RequestMethod.POST)
	public CommonResponse getDevicesModel(
			String id,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDevicesModelVo devicesModelVo = mdmDevicesModelService.getGevicesModelVo(id);
		
		return ResponseUtil.successResponse(devicesModelVo);
	}
	
	/**
	 * 
	 * save:(新增或修改保存设备型号信息).
	 * @param mdmDeviceClass
	 * @param oldModelCode
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存设备型号信息", httpMethod = "POST", notes = "保存设备型号信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			MdmDevicesModel mdmDevicesModel, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = mdmDevicesModelService.saveDevicesModel(mdmDevicesModel);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除设备型号数据).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author lenovo
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除设备型号信息", httpMethod = "POST", notes = "删除设备型号信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "多个主键，逗号分隔", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try {
			map = mdmDevicesModelService.deleteDevicesModel(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
}