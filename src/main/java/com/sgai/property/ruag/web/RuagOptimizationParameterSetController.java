package com.sgai.property.ruag.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginContext;
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

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagOptimizationParameterSet;
import com.sgai.property.ruag.service.RuagModelTemplateService;
import com.sgai.property.ruag.service.RuagOptimizationParameterSetService;
import com.sgai.property.ruag.service.RuagOptimizedRecordService;
import com.sgai.property.ruag.service.RuagWorkModelDatailService;

/**
 * 优化参数配置Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagOptimizationParameterSet")
public class RuagOptimizationParameterSetController extends BaseController {

	@Autowired
	private RuagOptimizationParameterSetService ruagOptimizationParameterSetService;
	@Autowired
	private RuagOptimizedRecordService ruagOptimizedRecordService;
	
	@RequestMapping(value = "/updateParamter",method=RequestMethod.POST)
	
	public CommonResponse updateParamter(
			
			String objStr,
			String targetStr,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map=new HashMap<>();
		try {
			//执行优化
			ruagOptimizationParameterSetService.updateParamter(objStr, targetStr);
			//添加优化记录
			LoginUser user=UserServletContext.getUserInfo();
			ruagOptimizedRecordService.saveRecord(objStr,user);
			//
			map.put("msg","success");
			
		} catch (Exception e) {
			map.put("msg","fail");
			e.printStackTrace();
			
		}
		return ResponseUtil.successResponse(map);
	}
	
	
	
@RequestMapping(value = "/getnewListParamter",method=RequestMethod.POST)
	
	public CommonResponse getnewListParamter(
			String id,

			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map=new HashMap<>();
		
			RuagOptimizationParameterSet ruagOptimizationParameterSet = new RuagOptimizationParameterSet();
			String arr[]=id.split("\\*");
			ruagOptimizationParameterSet.setWorkModelId(arr[0]);
			//判断参数是目标优化(profCode*targetsss)  或是  一键优化(参数只有profCode)
			if(arr.length>1) {
				ruagOptimizationParameterSet.setTargets(arr[1]);
			}else {
				ruagOptimizationParameterSet.setTargets("avg");
			}
			
			List<RuagOptimizationParameterSet> list= ruagOptimizationParameterSetService.findList(ruagOptimizationParameterSet);
			return ResponseUtil.successResponse(list);
	}
	
	
	
	//@RequiresPermissions("mon:ruagOptimizationParameterSet:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagOptimizationParameterSet ruagOptimizationParameterSet, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagOptimizationParameterSet==null){
			ruagOptimizationParameterSet=new RuagOptimizationParameterSet();
		}
		Page<RuagOptimizationParameterSet> page = ruagOptimizationParameterSetService.findPage(new Page<RuagOptimizationParameterSet>(request, response), ruagOptimizationParameterSet); 
		model.addAttribute("page", page);
		return "think/mon/ruagOptimizationParameterSetList";
	}

	//@RequiresPermissions("mon:ruagOptimizationParameterSet:view")
	@RequestMapping(value = "form")
	public String form(RuagOptimizationParameterSet ruagOptimizationParameterSet, Model model) {
		model.addAttribute("ruagOptimizationParameterSet", ruagOptimizationParameterSet);
		return "think/mon/ruagOptimizationParameterSetForm";
	}

	//@RequiresPermissions("mon:ruagOptimizationParameterSet:edit")
	@RequestMapping(value = "save")
	public String save(RuagOptimizationParameterSet ruagOptimizationParameterSet, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagOptimizationParameterSet)){
			return form(ruagOptimizationParameterSet, model);
		}
		ruagOptimizationParameterSetService.save(ruagOptimizationParameterSet);
		addMessage(redirectAttributes, "保存优化参数配置成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptimizationParameterSet/?repage";
	}
	
	//@RequiresPermissions("mon:ruagOptimizationParameterSet:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagOptimizationParameterSet ruagOptimizationParameterSet, RedirectAttributes redirectAttributes) {
		String idMerge=ruagOptimizationParameterSet.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagOptimizationParameterSet=new RuagOptimizationParameterSet();
				ruagOptimizationParameterSet.setId(id);
				ruagOptimizationParameterSetService.delete(ruagOptimizationParameterSet);
			}
		}
		addMessage(redirectAttributes, "删除优化参数配置成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptimizationParameterSet/?repage";
	}

}