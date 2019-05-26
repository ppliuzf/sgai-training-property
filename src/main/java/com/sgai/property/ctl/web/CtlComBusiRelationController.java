package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlComBusiRelation;
import com.sgai.property.ctl.entity.CtlCompBusi;
import com.sgai.property.ctl.service.CtlBusinessDefineService;
import com.sgai.property.ctl.service.CtlComBusiRelationService;
import com.sgai.property.ctl.service.CtlCompBusiService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * ClassName: CtlComBusiRelationController  
    * Description: (子系统配置控制层)
    * @author 王天尧  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
 */
@RestController
@Api(description = "子系统配置控制层")
@RequestMapping(value = "${adminPath}/combusi/ctlComBusiRelation")
public class CtlComBusiRelationController extends BaseController {

	@Autowired
	private CtlComBusiRelationService ctlComBusiRelationService;
	@Autowired
	private CtlBusinessDefineService ctlBusinessDefineService;
	@Autowired
	private CtlCompBusiService ctlCompBusiService;
	/*@ModelAttribute
	public CtlComBusiRelation get(@RequestParam(required=false) String id) {
		CtlComBusiRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlComBusiRelationService.get(id);
		}
		if (entity == null){
			entity = new CtlComBusiRelation();
		}
		return entity;
	}*/
	
	/**
	 * 
	 * getMenuListOfRole:(读取子系统树).
	 * @param comCode 机构代码
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "读取子系统树", notes = "读取子系统树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comCode", value = "机构代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码", required = false, dataType = "String")
    })
	@RequestMapping(value = "/getBusiListLack",method=RequestMethod.POST)
	public CommonResponse getBusiList(
			@RequestParam(required=true) String comCode,
			CtlBusinessDefine ctlBusinessDefine,
			Model model,
			/*String pageSize,
			String pageNo,*/

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page<CtlBusinessDefine> page = new Page<CtlBusinessDefine>(request,response);
		try {
			/*Map<String,String> param = new HashMap<String,String>();
			param.put("comCode", comCode);
			int pageSizeNew = Integer.parseInt(pageSize);
			int pageNumNew = Integer.parseInt(pageNo);
			String startNum = (pageNumNew-1)*pageSizeNew+1+"";
			String endNum = pageNumNew*pageSizeNew+"";
			param.put("startNum", startNum);
			param.put("endNum", endNum);
			List<CtlBusinessDefine> stepList0 = ctlComBusiRelationService.getBusiListLackPage(param);
			List<CtlBusinessDefine> allList= ctlComBusiRelationService.getBusiListLack(param);
			page.setList(stepList0);
			page.setCount(allList.size());
			page.setPageNo(pageNumNew);
			page.setPageSize(pageSizeNew);
			page.initialize();
			page.toString();*/
			Map<String,String> sqlMap = new HashMap<String,String>();
			String sql ="A.BUSI_CODE NOT IN (SELECT BUSI_CODE FROM CTL_COM_BUSI_RELATION WHERE COM_CODE = '"+comCode+"')";
			sqlMap.put("sql", sql);
			ctlBusinessDefine.setEnabledFlag('Y');
			ctlBusinessDefine.setSqlMap(sqlMap);
			page = ctlBusinessDefineService.findPage(page,ctlBusinessDefine);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(page);
	}
	/**
	 * 
	 * getBusiListOwn:(获得已拥有的子系统).
	 * @param comCode
	 * @param model
	 * @param redirectAttributes
	 * @return :List<CtlBusinessDefine> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "获得已拥有的子系统", notes = "获得已拥有的子系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comCode", value = "机构代码", required = false, dataType = "String"),
    })
	@RequestMapping(value = "/getBusiListOwn",method=RequestMethod.POST)
	public CommonResponse getBusiListOwn(
			@RequestParam(required=true) String comCode,
			Model model,

			RedirectAttributes redirectAttributes) throws IOException{
		Map<String,String> param = new HashMap<String,String>();
		param.put("comCode", comCode);
		return ResponseUtil.successResponse(ctlComBusiRelationService.getBusiListOwn(param));
	}
	/**
	 * 
	 * saveBusiTree:(保存该机构的子系统).
	 * @param busiCodes 子系统代码集合
	 * @param comCode   机构代码
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws IOException
     */
	@ApiOperation(value = "保存该机构的子系统", notes = "保存该机构的子系统")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comCode", value = "机构代码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "busiCodes", value = "子系统代码集合", required = false, dataType = "String")
    })
	@RequestMapping(value = "saveBusiTree",method=RequestMethod.POST)
	public CommonResponse saveBusiTree(
			@RequestParam(required=true) String busiCodes,
			@RequestParam(required=true) String comCode,

			Model model, RedirectAttributes redirectAttributes) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("comCode", comCode);
			ctlComBusiRelationService.deleteBusiTree(param);
			ctlCompBusiService.deleteByCompCode(comCode);
			List<String> busiCodeList = Lists.newArrayList();
			String[] busiCodeArray = busiCodes.split(",");
			for(String s : busiCodeArray) {
				if(s!=null && !s.equals("")) {
					busiCodeList.add(s);
				}
			}
			ctlComBusiRelationService.saveBusiTree(busiCodeList,comCode);
			for(String busiCode:busiCodeList) {
				CtlCompBusi ctlCompBusi = new CtlCompBusi();
				ctlCompBusi.setBusiCode(busiCode);
				ctlCompBusi.setComCode(comCode);
				ctlCompBusiService.save(ctlCompBusi);
			}
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return ResponseUtil.successResponse(result);
	}
}