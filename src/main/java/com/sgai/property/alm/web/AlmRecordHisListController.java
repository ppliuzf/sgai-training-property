package com.sgai.property.alm.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.alm.entity.AlmRecordHisList;
import com.sgai.property.alm.service.AlmRecordHisListService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: AlmRecordHisListController  
    * com.sgai.property.commonService.vo;(报警记录历史表Controller)
    * @author 王天尧 
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "报警记录历史表Controller")
@RequestMapping(value = "${adminPath}/alm/almRecordHisList")
public class AlmRecordHisListController extends BaseController {

	@Autowired
	private AlmRecordHisListService almRecordHisListService;
	
	/**
	 * 
	 * list:(获取报警历史记录列表).
	 * @param almRecordHisList
	 * @param request
	 * @param response
	 * @param model
	 * @return :String 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获取设备与报警等级关系列表", notes = "获取设备与报警等级关系列表")
	 @ApiImplicitParams({
         @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
         @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
         @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
         @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
         @ApiImplicitParam(name = "id", value = "设备与报警等级关系id集合", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public CommonResponse list(
			String alermTypeCode,
			String levelCode,
			String profCode,
			String states,
			String recordNum,

			HttpServletRequest request,
			HttpServletResponse response,
			Model model) throws IOException {
		AlmRecordHisList almRecordHisList = new AlmRecordHisList();
		almRecordHisList.setRecordNum(recordNum);
		almRecordHisList.setAlermTypeCode(alermTypeCode);
		almRecordHisList.setProfCode(profCode);
		almRecordHisList.setLevelCode(levelCode);
		almRecordHisList.setStates(states);
		almRecordHisList.setEnabledFlag("Y");
		Page<AlmRecordHisList> page = almRecordHisListService.findPage(new Page<AlmRecordHisList>(request, response), almRecordHisList); 
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	    * @Title: getMsgById  
	    * @com.sgai.property.commonService.vo;(获取处理详情)
	    * @param @param id
	    * @param @param request
	    * @param @param token
	    * @param @param response
	    * @param @param model
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "获取处理详情", notes = "获取处理详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "报警记录id", required = false, dataType = "String"),
   })
	@RequestMapping(value = "/getMsgById",method=RequestMethod.POST)
	public CommonResponse getMsgId(
			String id,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model) throws IOException {
		
		return ResponseUtil.successResponse(almRecordHisListService.getMsgById(id));
	}

}