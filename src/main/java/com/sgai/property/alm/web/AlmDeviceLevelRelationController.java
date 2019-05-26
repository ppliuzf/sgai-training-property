package com.sgai.property.alm.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.alm.entity.AlmDeviceLevelRelation;
import com.sgai.property.alm.service.AlmDeviceLevelRelationService;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.service.MdmDeviceClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
    * ClassName: AlmDeviceLevelRelationController  
    * com.sgai.property.commonService.vo;(设备与报警等级关系Controller)
    * @author 王天尧  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "设备与报警等级关系Controller")
@RequestMapping(value = "${adminPath}/alm/almDeviceLevelRelation")
public class AlmDeviceLevelRelationController extends BaseController {

	@Autowired
	private AlmDeviceLevelRelationService almDeviceLevelRelationService;
	@Autowired
	private MdmDeviceClassService mdmDeviceClassService;
	

	/**
	 * 
	 * list:(获取设备与报警等级关系列表).
	 * @param request
	 * @param response
	 * @param model
	 * @return :Page<AlmAlermClass> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	 @ApiImplicitParams({
         @ApiImplicitParam(name = "levelCode", value = "报警等级代码", required = false, dataType = "Array"),
         @ApiImplicitParam(name = "alermTypeCode", value = "报警分类代码", required = false, dataType = "Array"),
	 })
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	public CommonResponse list(
			String levelCode,
			String alermTypeCode,
			HttpServletRequest request, 
			HttpServletResponse response,

			Model model) throws IOException {
		AlmDeviceLevelRelation almDeviceLevelRelation = new AlmDeviceLevelRelation();
		almDeviceLevelRelation.setAlermTypeCode(alermTypeCode);
		almDeviceLevelRelation.setLevelCode(levelCode);
		almDeviceLevelRelation.setEnabledFlag("Y");
		Page<AlmDeviceLevelRelation> page = almDeviceLevelRelationService.findPage(new Page<AlmDeviceLevelRelation>(request, response), almDeviceLevelRelation); 
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * save:(保存设备与报警等级关系，包括新增和修改).
	 * @return :String
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存设备与报警等级关系，包括新增和修改", notes = "保存设备与报警等级关系，包括新增和修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "设备数组", required = false, dataType = "Array"),
    })
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	public CommonResponse save(
			@RequestParam(required=true)String[]ids,

			AlmDeviceLevelRelation almDeviceLevelRelation) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		try {
			result=almDeviceLevelRelationService.saveAlmDeviceLevelRelation(almDeviceLevelRelation, result,ids);
		} catch (Exception e) {

			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
		
	}
	/**
	 * 
	 * delete:(删除设备与报警等级关系).
	 * @param ids 设备与报警等级关系id集合（字符串拼而成）
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "删除设备与报警等级关系", notes = "删除设备与报警等级关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "设备与报警等级关系id集合(拼接成的字符串)", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> result = new HashMap<String,String>();
		AlmDeviceLevelRelation almDeviceLevelRelation = new AlmDeviceLevelRelation();
		String idArr[]=ids.split(",");
		try {
			for(String id:idArr){
				if(StringUtils.isNotEmpty(id)){
					almDeviceLevelRelation.setId(id);
					almDeviceLevelRelationService.delete(almDeviceLevelRelation);
				}
			}
			result.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "faild");
		}
		return ResponseUtil.successResponse(result);
		
	}
	/**
	 * 
	 * getById:(根据id获取报警信息关联对象).
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return :AlmDeviceLevelRelation 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "根据id获取报警信息关联对象", notes = "根据id获取报警信息关联对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getById",method=RequestMethod.POST)
	public CommonResponse getById(
			String id, 

			HttpServletRequest request,
			HttpServletResponse response, 
			Model model) throws IOException {
		return ResponseUtil.successResponse(almDeviceLevelRelationService.get(id));
	}
	@ApiOperation(value = "获得设备分类分页列表信息", httpMethod = "POST", notes = "获得设备专业分页列表信息")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "className", value = "设备分类名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListDeviceClass", method=RequestMethod.POST)
	public CommonResponse getListDeviceClass(
			LoginUser user,
			String className,
			String profCode,
			String classCode,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		MdmDeviceClass mdmDeviceClass = new MdmDeviceClass();
		mdmDeviceClass.setClassName(className);
		mdmDeviceClass.setClassCode(classCode);
		mdmDeviceClass.setProfCode(profCode);
		String sql ="";
		Map<String,String> sqlMap = new HashMap<String,String>();
		if(StringUtils.isBlank(user.getModuCode())) {
			 sql = "NOT EXISTS(select * FROM ALM_DEVICE_LEVEL_RELATION b WHERE a.CLASS_CODE=b.CLASS_CODE AND b.COM_CODE='"+user.getComCode()+"' AND b.MODU_CODE IS NULL)";
		}else {
			sql = "NOT EXISTS(select * FROM ALM_DEVICE_LEVEL_RELATION b WHERE a.CLASS_CODE=b.CLASS_CODE AND b.COM_CODE='"+user.getComCode()+"' AND b.MODU_CODE="+user.getModuCode()+")";
		}
		sqlMap.put("sql", sql);
		mdmDeviceClass.setSqlMap(sqlMap);
		Page<MdmDeviceClass> page = mdmDeviceClassService.findPage(new Page<MdmDeviceClass>(pageNo, pageSize), mdmDeviceClass);
		return ResponseUtil.successResponse(page);
	}
}