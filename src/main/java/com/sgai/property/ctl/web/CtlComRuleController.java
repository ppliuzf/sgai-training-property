package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlComRule;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * user关联rule队列Controller
 * @author chenxing
 * @version 2017-11-20
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlComRule")
@Api(description = "编码规则接口")
public class CtlComRuleController extends BaseController {

	@Autowired
	private CtlComRuleService ctlComRuleService;

	@RequestMapping(value = "getComRulePage")
	public Page<CtlComRule> list(CtlComRule ctlComRule, HttpServletRequest request, HttpServletResponse response) {
		return ctlComRuleService.findPage(new Page<CtlComRule>(request, response), ctlComRule); 
	}
	
	@RequestMapping(value = "getComRule")
	public CtlComRule getComRule(@RequestParam(required=true) String id) {
		return ctlComRuleService.get(id); 
	}
	
	@RequestMapping(value = "save")
	public Map<String,Object> save(CtlComRule ctlComRule) {
		Map<String,Object> result = Maps.newHashMap();
		ctlComRule.setEnabledFlag("Y");
		try {
			if(!particularBeanValidator(ctlComRule,result)) {
				return result;
			}
			ctlComRuleService.save(ctlComRule);		
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	
	public boolean particularBeanValidator(CtlComRule entity, Map<String,Object> result){
		if(entity.getComCode()==null) {
			result.put("state", false);
			result.put("msg", "机构为空!");
			return false;
		}
		if(entity.getSequCode()==null) {
			result.put("state", false);
			result.put("msg", "规则为空!");
			return false;
		}
		return true;
	}


	@RequestMapping(value = "deleteComRule")
	public Map<String,Object> deleteComRule(CtlComRule ctlComRule) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			String ids = ctlComRule.getId();
			String[] idArray = ids.split(",");
			List<String> idList = Lists.newArrayList();
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					idList.add(id);
				}
			}
			ctlComRuleService.batchDeleteComRule(idList);		
			result.put("state", true);
			result.put("msg", "删除成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "删除失败!");
		}
		return result;
	}
	
	@RequestMapping(value = "getRuleList")
	public List<Map<String,Object>> getRuleList(@RequestHeader("token") String token){
		return ctlComRuleService.getRuleList();
	}
	
	@RequestMapping(value = "getComList")
	public List<Map<String,Object>> getComList(@RequestHeader("token") String token){
		return ctlComRuleService.getComList();
	}
	
	@ApiOperation(value = "获取下一个编码", httpMethod = "POST", notes = "下一个编码")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "comCode", value = "机构代码", required = false, dataType = "String"),
        @ApiImplicitParam(name = "sequCode", value = "规则代码", required = false, dataType = "String")
    })
	@RequestMapping(value = "getNext")
	public synchronized String getNext(String comCode, String sequCode) {
		return ctlComRuleService.getNext(comCode, sequCode);
	}
	
	
	@ApiOperation(value = "获取指定机构指定规则的下一个编码", httpMethod = "POST", notes = "下一个编码")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "comCode", value = "机构代码", required = true,paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "sequCode", value = "规则代码", required = true,paramType = "query",dataType = "String")
    })
	@RequestMapping(value = "getNextNum",method=RequestMethod.POST)
	public CommonResponse getNextNum(
			LoginUser user,
			String comCode, 
			String sequCode,
			@RequestHeader(value="token")String token,
			HttpServletRequest request
			) throws JsonProcessingException {
		String num = ctlComRuleService.getNext(comCode, sequCode);
		return ResponseUtil.successResponse(num);
	}

}