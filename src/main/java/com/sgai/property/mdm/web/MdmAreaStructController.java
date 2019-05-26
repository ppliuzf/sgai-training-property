package com.sgai.property.mdm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.mdm.entity.MdmAreaStruct;
import com.sgai.property.mdm.service.MdmAreaStructService;

/**
 * 区域描述 ---空间Controller
 * @author zhb
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmAreaStruct")
public class MdmAreaStructController extends BaseController {

	@Autowired
	private MdmAreaStructService mdmAreaStructService;
	
	@ModelAttribute
	public MdmAreaStruct get(@RequestParam(required=false) String id) {
		MdmAreaStruct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmAreaStructService.get(id);
		}
		if (entity == null){
			entity = new MdmAreaStruct();
		}
		return entity;
	}
	
	//@RequiresPermissions("mdm:mdmAreaStruct:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmAreaStruct mdmAreaStruct, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmAreaStruct==null){
			mdmAreaStruct=new MdmAreaStruct();
		}
		Page<MdmAreaStruct> page = mdmAreaStructService.findPage(new Page<MdmAreaStruct>(request, response), mdmAreaStruct); 
		model.addAttribute("page", page);
		return "modules/mdm/mdmAreaStructList";
	}

	//@RequiresPermissions("mdm:mdmAreaStruct:view")
	@RequestMapping(value = "form")
	public String form(MdmAreaStruct mdmAreaStruct, Model model) {
		model.addAttribute("mdmAreaStruct", mdmAreaStruct);
		return "modules/mdm/mdmAreaStructForm";
	}

	//@RequiresPermissions("mdm:mdmAreaStruct:edit")
	@RequestMapping(value = "save")
	public String save(MdmAreaStruct mdmAreaStruct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mdmAreaStruct)){
			return form(mdmAreaStruct, model);
		}
		mdmAreaStructService.save(mdmAreaStruct);
		addMessage(redirectAttributes, "保存区域描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmAreaStruct/?repage";
	}
	
	//@RequiresPermissions("mdm:mdmAreaStruct:edit")
	@RequestMapping(value = "delete")
	public String delete(MdmAreaStruct mdmAreaStruct, RedirectAttributes redirectAttributes) {
		String idMerge=mdmAreaStruct.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				mdmAreaStruct=new MdmAreaStruct();
				mdmAreaStruct.setId(id);
				mdmAreaStructService.delete(mdmAreaStruct);
			}
		}
		addMessage(redirectAttributes, "删除区域描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmAreaStruct/?repage";
	}

}