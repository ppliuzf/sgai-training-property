package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusiModeRelation;
import com.sgai.property.ctl.service.CtlBusiModeRelationService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * ClassName: CtlBusiModeRelationController  
    * Description: (业务模块打包controller)
    * @author 王天尧  
    * Date 2017年11月21日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlBusiModeRelation")
@Api(description = "业务模块打包")
public class CtlBusiModeRelationController extends BaseController {

	@Autowired
	private CtlBusiModeRelationService ctlBusiModeRelationService;
	
	/*@ModelAttribute
	public CtlBusiModeRelation get(@RequestParam(required=false) String id) {
		CtlBusiModeRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlBusiModeRelationService.get(id);
		}
		if (entity == null){
			entity = new CtlBusiModeRelation();
		}
		return entity;
	}*/
	/**
	 * 
	 * getModuByBusiCode:(通过机构代码获取模块信息).
	 * @param ctlComBusiRelation
	 * @param busiCode 机构代码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :List<CtlModu> 
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@ApiOperation(value = "通过机构代码获取模块信息", notes = "通过机构代码获取模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busiCode", value = "机构代码", required = false,paramType = "query", dataType = "String"),
    })
	@RequestMapping(value = "/getModuByBusiCode",method=RequestMethod.POST)
	public CommonResponse getModuByBusiCode(
			String busiCode,
			HttpServletRequest request, 

			HttpServletResponse response) throws IOException {
		CtlBusiModeRelation ctlBusiModeRelation = new CtlBusiModeRelation();
		ctlBusiModeRelation.setBusiCode(busiCode);
		return ResponseUtil.successResponse(ctlBusiModeRelationService.getListByCode(ctlBusiModeRelation, busiCode));
	}
	/**
	 * 
	 * save:(保存子系统配置结果).
	 * @param ctlComBusiRelation
	 * @param busiCode 子系统代码
	 * @param sbsCodes 模块代码集合
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存子系统配置结果", notes = "保存子系统配置结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "busiCode", value = "机构代码", required = false,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sbsCodes", value = "模块代码集合", required = false,paramType = "query", dataType = "String"),
    })
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			String busiCode,
			String sbsCodes,
			Model model, 

			RedirectAttributes redirectAttributes) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		try {
			CtlBusiModeRelation ctlBusiModeRelation = new CtlBusiModeRelation();
			ctlBusiModeRelationService.saveMode(ctlBusiModeRelation, busiCode, sbsCodes);
			map.put("msg", "success");
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "faild");
		}
		
		return ResponseUtil.successResponse(map);
	}
}