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
import com.sgai.property.ruag.entity.RuagOptTarget;
import com.sgai.property.ruag.service.RuagOptTargetService;

/**
 * 优化目标Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/mon/ruagOptTarget")
public class RuagOptTargetController extends BaseController {

	@Autowired
	private RuagOptTargetService ruagOptTargetService;
	
	@ModelAttribute
	public RuagOptTarget get(@RequestParam(required=false) String id) {
		RuagOptTarget entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagOptTargetService.get(id);
		}
		if (entity == null){
			entity = new RuagOptTarget();
		}
		return entity;
	}
	
	//@RequiresPermissions("mon:ruagOptTarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagOptTarget ruagOptTarget, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagOptTarget==null){
			ruagOptTarget=new RuagOptTarget();
		}
		Page<RuagOptTarget> page = ruagOptTargetService.findPage(new Page<RuagOptTarget>(request, response), ruagOptTarget); 
		model.addAttribute("page", page);
		return "think/mon/ruagOptTargetList";
	}

	//@RequiresPermissions("mon:ruagOptTarget:view")
	@RequestMapping(value = "form")
	public String form(RuagOptTarget ruagOptTarget, Model model) {
		model.addAttribute("ruagOptTarget", ruagOptTarget);
		return "think/mon/ruagOptTargetForm";
	}

	//@RequiresPermissions("mon:ruagOptTarget:edit")
	@RequestMapping(value = "save")
	public String save(RuagOptTarget ruagOptTarget, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagOptTarget)){
			return form(ruagOptTarget, model);
		}
		ruagOptTargetService.save(ruagOptTarget);
		addMessage(redirectAttributes, "保存优化目标成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptTarget/?repage";
	}
	
	//@RequiresPermissions("mon:ruagOptTarget:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagOptTarget ruagOptTarget, RedirectAttributes redirectAttributes) {
		String idMerge=ruagOptTarget.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagOptTarget=new RuagOptTarget();
				ruagOptTarget.setId(id);
				ruagOptTargetService.delete(ruagOptTarget);
			}
		}
		addMessage(redirectAttributes, "删除优化目标成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptTarget/?repage";
	}

}