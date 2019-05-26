package com.sgai.property.ruag.web;

import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ruag.entity.RuagEnergyCompare;
import com.sgai.property.ruag.service.RuagEnergyCompareService;


/**
 * 能耗对比Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagEnergyCompare")
public class RuagEnergyCompareController extends BaseController {

	@Autowired
	private RuagEnergyCompareService ruagEnergyCompareService;
	
	
	@RequestMapping(value = "getEnergyById", method=RequestMethod.POST)
	public CommonResponse getEnergyById(
			String  recordId,

			HttpServletRequest request, 
			HttpServletResponse response
			) throws JsonProcessingException {
		RuagEnergyCompare ruagEnergyCompare = new RuagEnergyCompare();
		ruagEnergyCompare.setRecordId(recordId);
		ruagEnergyCompare.setEnabledFlag("Y");
		List<RuagEnergyCompare>  list = ruagEnergyCompareService.findEnergyListById(ruagEnergyCompare);
		return ResponseUtil.successResponse(list);
	}
	
	
	
	
	
	@ModelAttribute
	public RuagEnergyCompare get(@RequestParam(required=false) String id) {
		RuagEnergyCompare entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagEnergyCompareService.get(id);
		}
		if (entity == null){
			entity = new RuagEnergyCompare();
		}
		return entity;
	}
	
	//@RequiresPermissions("mon:ruagEnergyCompare:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagEnergyCompare ruagEnergyCompare, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagEnergyCompare==null){
			ruagEnergyCompare=new RuagEnergyCompare();
		}
		Page<RuagEnergyCompare> page = ruagEnergyCompareService.findPage(new Page<RuagEnergyCompare>(request, response), ruagEnergyCompare); 
		model.addAttribute("page", page);
		return "think/mon/ruagEnergyCompareList";
	}

	//@RequiresPermissions("mon:ruagEnergyCompare:view")
	@RequestMapping(value = "form")
	public String form(RuagEnergyCompare ruagEnergyCompare, Model model) {
		model.addAttribute("ruagEnergyCompare", ruagEnergyCompare);
		return "think/mon/ruagEnergyCompareForm";
	}

	//@RequiresPermissions("mon:ruagEnergyCompare:edit")
	@RequestMapping(value = "save")
	public String save(RuagEnergyCompare ruagEnergyCompare, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagEnergyCompare)){
			return form(ruagEnergyCompare, model);
		}
		ruagEnergyCompareService.save(ruagEnergyCompare);
		addMessage(redirectAttributes, "保存能耗对比成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagEnergyCompare/?repage";
	}
	
	//@RequiresPermissions("mon:ruagEnergyCompare:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagEnergyCompare ruagEnergyCompare, RedirectAttributes redirectAttributes) {
		String idMerge=ruagEnergyCompare.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagEnergyCompare=new RuagEnergyCompare();
				ruagEnergyCompare.setId(id);
				ruagEnergyCompareService.delete(ruagEnergyCompare);
			}
		}
		addMessage(redirectAttributes, "删除能耗对比成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagEnergyCompare/?repage";
	}

}