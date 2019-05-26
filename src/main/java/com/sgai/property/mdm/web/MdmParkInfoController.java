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
import com.sgai.property.mdm.entity.MdmParkInfo;
import com.sgai.property.mdm.service.MdmParkInfoService;

/**
 * 园区描述 ---空间Controller
 * @author zhb
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmParkInfo")
public class MdmParkInfoController extends BaseController {

	@Autowired
	private MdmParkInfoService mdmParkInfoService;
	
	@ModelAttribute
	public MdmParkInfo get(@RequestParam(required=false) String id) {
		MdmParkInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmParkInfoService.get(id);
		}
		if (entity == null){
			entity = new MdmParkInfo();
		}
		return entity;
	}
	
	//@RequiresPermissions("mdm:mdmParkInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmParkInfo mdmParkInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmParkInfo==null){
			mdmParkInfo=new MdmParkInfo();
		}
		Page<MdmParkInfo> page = mdmParkInfoService.findPage(new Page<MdmParkInfo>(request, response), mdmParkInfo); 
		model.addAttribute("page", page);
		return "modules/mdm/mdmParkInfoList";
	}

	//@RequiresPermissions("mdm:mdmParkInfo:view")
	@RequestMapping(value = "form")
	public String form(MdmParkInfo mdmParkInfo, Model model) {
		model.addAttribute("mdmParkInfo", mdmParkInfo);
		return "modules/mdm/mdmParkInfoForm";
	}

	//@RequiresPermissions("mdm:mdmParkInfo:edit")
	@RequestMapping(value = "save")
	public String save(MdmParkInfo mdmParkInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mdmParkInfo)){
			return form(mdmParkInfo, model);
		}
		mdmParkInfoService.save(mdmParkInfo);
		addMessage(redirectAttributes, "保存园区描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmParkInfo/?repage";
	}
	
	//@RequiresPermissions("mdm:mdmParkInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MdmParkInfo mdmParkInfo, RedirectAttributes redirectAttributes) {
		String idMerge=mdmParkInfo.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				mdmParkInfo=new MdmParkInfo();
				mdmParkInfo.setId(id);
				mdmParkInfoService.delete(mdmParkInfo);
			}
		}
		addMessage(redirectAttributes, "删除园区描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmParkInfo/?repage";
	}

}