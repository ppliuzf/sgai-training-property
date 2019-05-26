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
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceParameter;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.service.MdmDeviceParameterService;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: MdmDevicesUseInfoController  
    * com.sgai.property.commonService.vo;(设备主数据维护)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/devicesuseinfo/mdmDevicesUseInfo")
@Api(description = "设备主数据接口")
public class MdmDevicesUseInfoController extends BaseController {

	@Autowired
	private MdmDevicesUseInfoService mdmDevicesUseInfoService;
	@Autowired
	private MdmDeviceParameterService mdmDeviceParameterService;
	
	
	
	/**
	 * 
	 * getListDevicesUseInfo:(查询设备主数据列表).
	 * @param mdmDevicesUseInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmDevicesUseInfo> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备主数据分页列表信息", httpMethod = "POST", notes = "获得设备主数据分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "profName", value = "设备专业", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "className", value = "设备类型", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "brandName", value = "设备品牌", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "devicesModel", value = "设备型号", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "devicesCode", value = "设备代码", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "devicesName", value = "设备名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDevicesUseInfo",method=RequestMethod.POST)
	public CommonResponse getListDevicesUseInfo(
			LoginUser user,
			String profName,
			String className,
			String brandName,
			String devicesModel,
			String devicesCode,
			String devicesName,
			String supplierName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
		mdmDevicesUseInfoVo.setProfName(profName);
		mdmDevicesUseInfoVo.setClassCode(className);
		mdmDevicesUseInfoVo.setBrandName(brandName);
		mdmDevicesUseInfoVo.setDevicesModel(devicesModel);
		mdmDevicesUseInfoVo.setDevicesCode(devicesCode);
		mdmDevicesUseInfoVo.setDevicesName(devicesName);
		mdmDevicesUseInfoVo.setSupplierName(supplierName);
		Page<MdmDevicesUseInfoVo> page = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(pageNo, pageSize), mdmDevicesUseInfoVo);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	 * getListDevicesUseInfo:(查询设备主数据列表).
	 * @param mdmDevicesUseInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<MdmDevicesUseInfo> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@RequestMapping(value = "/getListDevicesUseInfoAttr",method=RequestMethod.POST)
	public CommonResponse getListDevicesUseInfoAttr(
			LoginUser user,
			String devicesCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
		mdmDevicesUseInfoVo.setDevicesCode(devicesCode);
		Page<MdmDevicesUseInfoVo> page = mdmDevicesUseInfoService.findAttrPageVoByDevices(new Page<MdmDevicesUseInfoVo>(pageNo, pageSize), mdmDevicesUseInfoVo);
		return ResponseUtil.successResponse(page);
	}
	
	
	
	
	/**
	 * 
	 * getDeviceClass:(查询一条设备主数据).
	 * @param request
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException :MdmDevicesUseInfo 
	 * @since JDK 1.8
	 * @author yangyz
	 */
	@ApiOperation(value = "获得设备主数据信息", httpMethod = "POST", notes = "获得设备主数据信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getDevicesUseInfo",method=RequestMethod.POST)
	public CommonResponse getDevicesUseInfo(
			LoginUser user,
			String id, 
			String classCode,
			String token,
			HttpServletRequest request, 
			HttpServletResponse response//classCode
			) throws IOException {
		MdmDevicesUseInfoVo devicesUseInfoVo = mdmDevicesUseInfoService.getpro(id);
		return ResponseUtil.successResponse(devicesUseInfoVo);
	}
	
	/**
	 * 
	 * save:(新增或修改保存设备主数据).
	 * @param mdmDevicesUseInfo
	 * @param oldDevicesCode
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存设备主数据信息", httpMethod = "POST", notes = "保存设备主数据信息")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			MdmDevicesUseInfo mdmDevicesUseInfo, 

			RedirectAttributes redirectAttributes
			) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			MdmDeviceParameter mdmDeviceParameter = new MdmDeviceParameter();
			mdmDeviceParameter.setDeviceClassCode(mdmDevicesUseInfo.getClassCode());
			List<MdmDeviceParameter> list = mdmDeviceParameterService.findList(mdmDeviceParameter);
			if (list != null && list.size() > 0) {
				map = mdmDevicesUseInfoService.saveDevicesUseInfo(mdmDevicesUseInfo);
			}else {
				map.put("msg", "noParams");
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	 * delete:(批量删除设备主数据).
	 * @param ids
	 * @param redirectAttributes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除设备主数据信息", httpMethod = "POST", notes = "删除设备主数据信息")
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
			map = mdmDevicesUseInfoService.deleteDevicesUseInfo(ids);
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "出错了！");
			map.put("result", "fail");
		}
		return ResponseUtil.successResponse(map);
	}
	/**
	 * 
	 * getLackList:(查找某个运行策略未拥有的设备).
	 * @param id
	 * @param mdmDevicesUseInfo
	 * @param token
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :CommonResponse 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@RequestMapping(value = "getLackList",method=RequestMethod.POST)
	public CommonResponse getLackList(
			String id, 
			String spaceCodes,
			MdmDevicesUseInfo mdmDevicesUseInfo,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		Map<String,String> sqlMap = new HashMap<String,String>();
		String sql = "NOT EXISTS (SELECT * FROM RUAG_WORK_MODEL_DATAIL D WHERE A .DEVICES_CODE = D .DEVICE_CODE AND D .MODEL_TEMPLATE_ID ='"+id+"')";
		if(!("".equals(spaceCodes))&&!(spaceCodes.equals(null))) {
			sqlMap.put("sql", sql+" AND a.space_code in("+spaceCodes.substring(0, spaceCodes.length()-1)+")");
			mdmDevicesUseInfo.setSqlMap(sqlMap);
		}else {
			sqlMap.put("sql", sql);
		}
		mdmDevicesUseInfo.setSqlMap(sqlMap);
	    Page<MdmDevicesUseInfo> page = mdmDevicesUseInfoService.findPage(new Page<MdmDevicesUseInfo>(request, response), mdmDevicesUseInfo);
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	    * @Title: findProfBySpace  
	    * @com.sgai.property.commonService.vo;(根据联动规则区域获取专业)
	    * @param @param linkCode
	    * @param @param user
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws JsonProcessingException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "findProfBySpace",method=RequestMethod.POST)
	public CommonResponse findProfBySpace(
			String linkCode, 
			LoginUser user,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("linkCode", linkCode);
		param.put("comCode", user.getComCode());
		param.put("moduCode", user.getModuCode());
		return ResponseUtil.successResponse(mdmDevicesUseInfoService.findProBySpace(param));
	}
	/**
	 * 
	    * @Title: findListByProf  
	    * @com.sgai.property.commonService.vo;(根据专业查询设备)
	    * @param @param profCode
	    * @param @param user
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws JsonProcessingException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "按照专业获得设备主数据分页列表信息", httpMethod = "POST", notes = "获得设备主数据分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "profCode", value = "设备专业", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "spaceCode", value = "设备类型", required = false,paramType = "query", dataType = "String"),
	})
	@RequestMapping(value = "findListByProf",method=RequestMethod.POST)
	public CommonResponse findListByProf(
			String profCode,
			String spaceCode,
			String floorCode,
			String classCode,
			LoginUser user,

			@RequestParam(value = "pageNo", required = true,defaultValue="1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
	    Page<MdmDevicesUseInfoVo> page = new Page<MdmDevicesUseInfoVo>(pageNo, pageSize) ;
	    mdmDevicesUseInfoVo.setProfCode(profCode);
	    mdmDevicesUseInfoVo.setSpaceName(spaceCode);
	    mdmDevicesUseInfoVo.setModuCode(user.getModuCode());
	    mdmDevicesUseInfoVo.setComCode(user.getComCode());
	    mdmDevicesUseInfoVo.setClassCode(classCode);
		mdmDevicesUseInfoVo.setPage(page);
		page.setList(mdmDevicesUseInfoService.findListByProf(mdmDevicesUseInfoVo));
		return ResponseUtil.successResponse(page);
	}
	@ApiOperation(value = "获取视频位置树", httpMethod = "POST", notes = "获取视频位置树")
	@RequestMapping(value = "getVideoTree",method=RequestMethod.POST)
	public CommonResponse getVideoTree(

			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		return ResponseUtil.successResponse(mdmDevicesUseInfoService.getVideoTree());
	}
}