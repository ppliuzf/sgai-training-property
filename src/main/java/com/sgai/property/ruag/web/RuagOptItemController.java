package com.sgai.property.ruag.web;

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
import com.sgai.property.ruag.entity.RuagModelTemplate;
import com.sgai.property.ruag.entity.RuagOptItem;
import com.sgai.property.ruag.service.RuagOptItemService;

/**
 * 优化项Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagOptItem")
public class RuagOptItemController extends BaseController {

	@Autowired
	private RuagOptItemService ruagOptItemService;
	
	@ModelAttribute
	public RuagOptItem get(@RequestParam(required=false) String id) {
		RuagOptItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagOptItemService.get(id);
		}
		if (entity == null){
			entity = new RuagOptItem();
		}
		return entity;
	}
	
	@RequestMapping(value ="/getListYouHuaTarget",method=RequestMethod.POST)
	public CommonResponse getListYouHuaTarget(

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) throws JsonProcessingException {
			RuagOptItem ruagOptItem=new RuagOptItem();
			Page<RuagOptItem> page = ruagOptItemService.findPage(new Page<RuagOptItem>(pageNo, pageSize), ruagOptItem);
		return ResponseUtil.successResponse(page);
	}

	//@RequiresPermissions("mon:ruagOptItem:view")
	@RequestMapping(value = "form")
	public String form(RuagOptItem ruagOptItem, Model model) {
		model.addAttribute("ruagOptItem", ruagOptItem);
		return "think/mon/ruagOptItemForm";
	}

	//@RequiresPermissions("mon:ruagOptItem:edit")
	@RequestMapping(value = "save")
	public String save(RuagOptItem ruagOptItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagOptItem)){
			return form(ruagOptItem, model);
		}
		ruagOptItemService.save(ruagOptItem);
		addMessage(redirectAttributes, "保存优化项成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptItem/?repage";
	}
	
	//@RequiresPermissions("mon:ruagOptItem:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagOptItem ruagOptItem, RedirectAttributes redirectAttributes) {
		String idMerge=ruagOptItem.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagOptItem=new RuagOptItem();
				ruagOptItem.setId(id);
				ruagOptItemService.delete(ruagOptItem);
			}
		}
		addMessage(redirectAttributes, "删除优化项成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptItem/?repage";
	}

}