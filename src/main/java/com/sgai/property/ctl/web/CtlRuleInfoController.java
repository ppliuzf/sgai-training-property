package com.sgai.property.ctl.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.entity.CtlRuleInfo;
import com.sgai.property.ctl.entity.CtlRuleItem;
import com.sgai.property.ctl.service.CtlRuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 编号规则Controller
 * @author chenxing
 * @version 2017-11-20
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlRuleInfo")
public class CtlRuleInfoController extends BaseController {

	@Autowired
	private CtlRuleInfoService ctlRuleInfoService;
	
	@RequestMapping(value = "getRuleInfoPage")
	public Page<CtlRuleInfo> getRuleInfoPage(CtlRuleInfo ctlRuleInfo,

			HttpServletRequest request, HttpServletResponse response) {
		Page<CtlRuleInfo> page = ctlRuleInfoService.findPage(new Page<CtlRuleInfo>(request, response), ctlRuleInfo); 
		return page;
	}
	
	@RequestMapping(value = "getRuleInfo")
	public CtlRuleInfo getRuleInfo(CtlRuleInfo ctlRuleInfo) {
		return ctlRuleInfoService.getRuleInfo(ctlRuleInfo); 
	}
	
	@RequestMapping(value = "getRuleItemList")
	public List<CtlRuleItem> getRuleItemList(CtlRuleItem ctlRuleItem) {
		return ctlRuleInfoService.getRuleItemList(ctlRuleItem);
	}
	
	@RequestMapping(value = "save")
	public Map<String,Object> save(CtlRuleInfo ctlRuleInfo,Model model) {
		Map<String,Object> result = Maps.newHashMap();
		ctlRuleInfo.setEnabledFlag("Y");
		for (CtlRuleItem ctlRuleItem : ctlRuleInfo.getCtlRuleItemList()) {
			ctlRuleItem.setEnabledFlag("Y");
		}
		try {
			if (!beanValidator(model, ctlRuleInfo)){
				result.put("state", false);
				result.put("msg", "保存失败!"+model.asMap().get("message"));
				return result;
			}
			if(!particularBeanValidator(ctlRuleInfo,result)) {
				return result;
			}
			String comCode=null;
			 try {
				 LoginUser user = UserServletContext.getUserInfo();
				 comCode= user.getComCode();

			} catch (Exception e) {
				e.printStackTrace();
			}
			 ctlRuleInfo.setComCode(comCode);
			ctlRuleInfoService.save(ctlRuleInfo);		
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	
	public boolean particularBeanValidator(CtlRuleInfo entity, Map<String,Object> result){
		int count = ctlRuleInfoService.countSequCodeExceptSelf(entity);
		if(count >= 1){
			result.put("state", false);
			result.put("msg", "单据规则CODE不能重复!");
			return false;
		}	
		
		Set<String> set = new HashSet<String>();
		List<CtlRuleItem> list = entity.getCtlRuleItemList();
		for(CtlRuleItem item : list) {
			if(set.contains(item.getRuleCode())) {
				result.put("state", false);
				result.put("msg", "编码规则CODE不能重复!");
				return false;
			}else {
				set.add(item.getRuleCode());
			}
		}
		
		return true;
	}


	@RequestMapping(value = "deleteRuleInfo")
	public Map<String,Object> deleteRuleInfo(CtlRuleInfo ctlRuleInfo) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			String ids = ctlRuleInfo.getId();
			String[] idArray = ids.split(",");
			List<String> idList = Lists.newArrayList();
			for(String id : idArray) {
				if(id!=null && !id.equals("")) {
					idList.add(id);
				}
			}
			ctlRuleInfoService.batchDeleteRuleInfo(idList);		
			result.put("state", true);
			result.put("msg", "删除成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "删除失败!");
		}
		return result;
	}
	
}