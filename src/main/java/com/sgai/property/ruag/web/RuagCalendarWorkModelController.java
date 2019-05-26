package com.sgai.property.ruag.web;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ruag.entity.RuagCalendarWorkModel;
import com.sgai.property.ruag.service.RuagCalendarWorkModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
    * @ClassName: RuagCalendarWorkModelController  
    * @com.sgai.property.commonService.vo;(策略日程与策略配置之间的关系Controller)
    * @author 王天尧  
    * @date 2018年1月9日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagCalendarWorkModel")
public class RuagCalendarWorkModelController extends BaseController {

	@Autowired
	private RuagCalendarWorkModelService ruagCalendarWorkModelService;
	
	@ModelAttribute
	public RuagCalendarWorkModel get(@RequestParam(required=false) String id) {
		RuagCalendarWorkModel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagCalendarWorkModelService.get(id);
		}
		if (entity == null){
			entity = new RuagCalendarWorkModel();
		}
		return entity;
	}
	
	//@RequiresPermissions("ruag:ruagCalendarWorkModel:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagCalendarWorkModel ruagCalendarWorkModel, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagCalendarWorkModel==null){
			ruagCalendarWorkModel=new RuagCalendarWorkModel();
		}
		Page<RuagCalendarWorkModel> page = ruagCalendarWorkModelService.findPage(new Page<RuagCalendarWorkModel>(request, response), ruagCalendarWorkModel); 
		model.addAttribute("page", page);
		return "modules/ruag/ruagCalendarWorkModelList";
	}

	//@RequiresPermissions("ruag:ruagCalendarWorkModel:view")
	@RequestMapping(value = "form")
	public String form(RuagCalendarWorkModel ruagCalendarWorkModel, Model model) {
		model.addAttribute("ruagCalendarWorkModel", ruagCalendarWorkModel);
		return "modules/ruag/ruagCalendarWorkModelForm";
	}

	//@RequiresPermissions("ruag:ruagCalendarWorkModel:edit")
	@RequestMapping(value = "save")
	public String save(RuagCalendarWorkModel ruagCalendarWorkModel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagCalendarWorkModel)){
			return form(ruagCalendarWorkModel, model);
		}
		ruagCalendarWorkModelService.save(ruagCalendarWorkModel);
		addMessage(redirectAttributes, "保存策略日程与策略配置之间的关系成功");
		return "redirect:"+Global.getAdminPath()+"/ruag/ruagCalendarWorkModel/?repage";
	}
	
	//@RequiresPermissions("ruag:ruagCalendarWorkModel:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagCalendarWorkModel ruagCalendarWorkModel, RedirectAttributes redirectAttributes) {
		String idMerge=ruagCalendarWorkModel.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagCalendarWorkModel=new RuagCalendarWorkModel();
				ruagCalendarWorkModel.setId(id);
				ruagCalendarWorkModelService.delete(ruagCalendarWorkModel);
			}
		}
		addMessage(redirectAttributes, "删除策略日程与策略配置之间的关系成功");
		return "redirect:"+Global.getAdminPath()+"/ruag/ruagCalendarWorkModel/?repage";
	}

}