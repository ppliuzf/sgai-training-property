package com.sgai.property.ruag.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ruag.entity.RuagOptIndicator;
import com.sgai.property.ruag.service.RuagOptIndicatorService;

/**
 * 优化指标Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/mon/ruagOptIndicator")
public class RuagOptIndicatorController extends BaseController {

	@Autowired
	private RuagOptIndicatorService ruagOptIndicatorService;
	
	@ModelAttribute
	public RuagOptIndicator get(@RequestParam(required=false) String id) {
		RuagOptIndicator entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagOptIndicatorService.get(id);
		}
		if (entity == null){
			entity = new RuagOptIndicator();
		}
		return entity;
	}
	
	//@RequiresPermissions("mon:ruagOptIndicator:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagOptIndicator ruagOptIndicator, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagOptIndicator==null){
			ruagOptIndicator=new RuagOptIndicator();
		}
		Page<RuagOptIndicator> page = ruagOptIndicatorService.findPage(new Page<RuagOptIndicator>(request, response), ruagOptIndicator); 
		model.addAttribute("page", page);
		return "think/mon/ruagOptIndicatorList";
	}

	//@RequiresPermissions("mon:ruagOptIndicator:view")
	@RequestMapping(value = "form")
	public String form(RuagOptIndicator ruagOptIndicator, Model model) {
		model.addAttribute("ruagOptIndicator", ruagOptIndicator);
		return "think/mon/ruagOptIndicatorForm";
	}

	//@RequiresPermissions("mon:ruagOptIndicator:edit")
	@RequestMapping(value = "save")
	public String save(RuagOptIndicator ruagOptIndicator, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagOptIndicator)){
			return form(ruagOptIndicator, model);
		}
		ruagOptIndicatorService.save(ruagOptIndicator);
		addMessage(redirectAttributes, "保存优化指标成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptIndicator/?repage";
	}
	
	//@RequiresPermissions("mon:ruagOptIndicator:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagOptIndicator ruagOptIndicator, RedirectAttributes redirectAttributes) {
		String idMerge=ruagOptIndicator.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagOptIndicator=new RuagOptIndicator();
				ruagOptIndicator.setId(id);
				ruagOptIndicatorService.delete(ruagOptIndicator);
			}
		}
		addMessage(redirectAttributes, "删除优化指标成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptIndicator/?repage";
	}

}