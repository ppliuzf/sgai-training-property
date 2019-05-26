package com.sgai.property.ruag.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ruag.dao.RuagLinkageDeviceParamSetDao;
import com.sgai.property.ruag.entity.*;
import com.sgai.property.ruag.service.RuagLinkaageFrontDeviceService;
import com.sgai.property.ruag.service.RuagLinkaageNextDeviceService;
import com.sgai.property.ruag.service.RuagLinkageRuleService;
import com.sgai.property.ruag.service.RuagLinkageRuleSpaceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 联动规则定义Controller
 * @author yangyz
 * @version 2018-01-02
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagLinkageRule")
public class RuagLinkageRuleController extends BaseController {

	@Autowired
	private RuagLinkageRuleService ruagLinkageRuleService;
	@Autowired
	private RuagLinkaageFrontDeviceService ruagLinkaageFrontDeviceService;
	@Autowired
	private RuagLinkaageNextDeviceService ruagLinkaageNextDeviceService;
	@Autowired
	private RuagLinkageRuleSpaceService ruagLinkageRuleSpaceService;
	@Autowired
	private RuagLinkageDeviceParamSetDao ruagLinkageDeviceParamSetDao;
	
	/**
	 * 
	    * @Title: getListComp  
	    * @com.sgai.property.commonService.vo;(联动规则分页)
	    * @param @param spaceName
	    * @param @param linkageName
	    * @param @param token
	    * @param @param pageNo
	    * @param @param pageSize
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "联动规则分页", notes = "联动规则分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "spaceName", value = "区域名称", required = false,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "linkageName", value = "联动名称", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getListRuagLinkageRule",method=RequestMethod.POST)
	public CommonResponse getListComp(
			String spaceCode,
			String linkageName,

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			) throws IOException {
		RuagLinkageRule ruagLinkageRule = new RuagLinkageRule();
		Map<String,String> sqlMap = new HashMap<String,String>();
		if(StringUtils.isNotBlank(spaceCode)) {
			sqlMap.put("sql", "A.space_code IN("+spaceCode.substring(0, spaceCode.length()-1)+")");
			ruagLinkageRule.setSqlMap(sqlMap);
		}
		ruagLinkageRule.setLinkageName(linkageName);
		Page<RuagLinkageRule> page = ruagLinkageRuleService.findPage(new Page<RuagLinkageRule>(pageNo, pageSize), ruagLinkageRule);
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	    * @Title: getComp  
	    * @com.sgai.property.commonService.vo;(获得联动规则)
	    * @param @param id
	    * @param @param token
	    * @param @param request
	    * @param @param response
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "获得联动规则", notes = "获得联动规则")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getRuagLinkageRule",method=RequestMethod.POST)
	public CommonResponse getComp(
			String id, 

			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		RuagLinkageRule ruagLinkageRule = ruagLinkageRuleService.get(id);
		return ResponseUtil.successResponse(ruagLinkageRule);
	}
	
	/**
	 * 
	    * @Title: save  
	    * @com.sgai.property.commonService.vo;(保存联动规则)
	    * @param @param ruagLinkageRule
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "保存联动规则", notes = "保存联动规则")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public CommonResponse save(
			RuagLinkageRule ruagLinkageRule,
			String spaceCodes,
			String oldSpaceCode,

			RedirectAttributes redirectAttributes
			) throws IOException {
		
		Map<String,Object> data = new HashMap<String,Object>();
		ruagLinkageRule.setEnabledFlag("Y");
		ruagLinkageRule.setStatus("N");
		ruagLinkageRule.setRuleFlag("N");
		try {
			data = ruagLinkageRuleService.saveLinkageRule(ruagLinkageRule,spaceCodes,oldSpaceCode);
		} catch (Exception e) {
			data.put("msg", "failed");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(data);
	}
	
	/**
	 * 
	    * @Title: delete  
	    * @com.sgai.property.commonService.vo;(删除联动规则)
	    * @param @param ids
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "删除联动规则", notes = "删除联动规则")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "主键集合", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	public CommonResponse delete(
			String ids, 

			RedirectAttributes redirectAttributes
			) throws IOException {
		LoginUser user = UserServletContext.getUserInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		String[] arr = ids.split(",");
		for (String id : arr) {
			if(ruagLinkageRuleService.get(id).getStatus().equals("Y")) {
				map.put("msg", "open");
				break;
			}else {
				RuagLinkaageFrontDevice ruagLinkaageFrontDevice = new RuagLinkaageFrontDevice();
				ruagLinkaageFrontDevice.setLinkageCode(ruagLinkageRuleService.get(id).getLinkageCode());
				List<RuagLinkaageFrontDevice> frontList = ruagLinkaageFrontDeviceService.findList(ruagLinkaageFrontDevice);
				RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
  				ruagLinkaageNextDevice.setLinkageCode(ruagLinkageRuleService.get(id).getLinkageCode());
				List<RuagLinkaageNextDevice> nextList = ruagLinkaageNextDeviceService.findList(ruagLinkaageNextDevice);
					RuagLinkageRuleSpace ruagLinkageRuleSpace  = new RuagLinkageRuleSpace ();
					ruagLinkageRuleSpace.setLinkageCode(ruagLinkageRuleService.get(id).getLinkageCode());
					List<RuagLinkageRuleSpace> findList = ruagLinkageRuleSpaceService.findList(ruagLinkageRuleSpace);
					if(findList.size()>0) {
						for (RuagLinkageRuleSpace ruagLinkageRuleSpace2 : findList) {
							ruagLinkageRuleSpaceService.delete(ruagLinkageRuleSpace2);
						}
					}
					if(frontList.size()>0) {
						for (RuagLinkaageFrontDevice frontDevice  : frontList) {
							ruagLinkaageFrontDeviceService.delete(frontDevice);
							RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
							ruagLinkageDeviceParamSet.setMasterId(frontDevice.getId());
							ruagLinkageDeviceParamSet.setComCode(user.getComCode());
							ruagLinkageDeviceParamSet.setModuCode(user.getModuCode());
							ruagLinkageDeviceParamSetDao.deleteByMasterId(ruagLinkageDeviceParamSet);
						}
					}
					if(nextList.size()>0) {
						for (RuagLinkaageNextDevice nextDevice  : nextList) {
							ruagLinkaageNextDeviceService.delete(nextDevice);
							RuagLinkageDeviceParamSet ruagLinkageDeviceParamSet = new RuagLinkageDeviceParamSet();
							ruagLinkageDeviceParamSet.setMasterId(nextDevice.getId());
							ruagLinkageDeviceParamSet.setComCode(user.getComCode());
							ruagLinkageDeviceParamSet.setModuCode(user.getModuCode());
							ruagLinkageDeviceParamSetDao.deleteByMasterId(ruagLinkageDeviceParamSet);
						}
					}
					RuagLinkageRule ruagLinkageRule = new RuagLinkageRule();
					ruagLinkageRule.setId(id);
					ruagLinkageRuleService.delete(ruagLinkageRule);
					map.put("msg", "success");
			}
		}
			
			
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	    * @Title: enableRuagLinkageRule  
	    * @com.sgai.property.commonService.vo;(启用/关闭联动规则)
	    * @param @param ruagLinkageRule
	    * @param @param token
	    * @param @param redirectAttributes
	    * @param @return
	    * @param @throws ServletException
	    * @param @throws IOException    参数  
	    * @return CommonResponse    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "启用/关闭联动规则", notes = "启用/关闭联动规则")
	@RequestMapping(value = "enableRuagLinkageRule",method=RequestMethod.POST)
	public CommonResponse enableRuagLinkageRule(
			String id,
			String status,

			RedirectAttributes redirectAttributes
			) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RuagLinkageRule ruagLinkageRule = ruagLinkageRuleService.get(id);
			ruagLinkageRule.setStatus(status);
			if(status.equals("N")) {
				ruagLinkageRule.setRuleFlag("N");
				ruagLinkageRuleService.save(ruagLinkageRule);
				map.put("msg", "success");
			}else {
				RuagLinkaageFrontDevice ruagLinkaageFrontDevice = new RuagLinkaageFrontDevice();
				ruagLinkaageFrontDevice.setLinkageCode(ruagLinkageRule.getLinkageCode());
				List<RuagLinkaageFrontDevice> findList = ruagLinkaageFrontDeviceService.findList(ruagLinkaageFrontDevice);
				RuagLinkaageNextDevice ruagLinkaageNextDevice = new RuagLinkaageNextDevice();
				ruagLinkaageNextDevice.setLinkageCode(ruagLinkageRuleService.get(id).getLinkageCode());
				List<RuagLinkaageNextDevice> nextList = ruagLinkaageNextDeviceService.findList(ruagLinkaageNextDevice);
				if(findList!=null&&findList.size()>0&&nextList!=null&&nextList.size()>0) {
					ruagLinkageRuleService.save(ruagLinkageRule);
					//查看是否与其他运行策略有冲突并做处理
					ruagLinkaageNextDeviceService.SelectDevicesNextIns(ruagLinkageRule.getLinkageCode());
					map.put("msg", "success");
				}else {
					map.put("msg", "empty");
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return ResponseUtil.successResponse(map);
	}

}