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
import com.sgai.property.mdm.entity.MdmRoomInfo;
import com.sgai.property.mdm.service.MdmRoomInfoService;

/**
 * 房间描述 ---空间Controller
 * @author zhb
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmRoomInfo")
public class MdmRoomInfoController extends BaseController {

	@Autowired
	private MdmRoomInfoService mdmRoomInfoService;
	
	@ModelAttribute
	public MdmRoomInfo get(@RequestParam(required=false) String id) {
		MdmRoomInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmRoomInfoService.get(id);
		}
		if (entity == null){
			entity = new MdmRoomInfo();
		}
		return entity;
	}
	
	//@RequiresPermissions("mdm:mdmRoomInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmRoomInfo mdmRoomInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmRoomInfo==null){
			mdmRoomInfo=new MdmRoomInfo();
		}
		Page<MdmRoomInfo> page = mdmRoomInfoService.findPage(new Page<MdmRoomInfo>(request, response), mdmRoomInfo); 
		model.addAttribute("page", page);
		return "modules/mdm/mdmRoomInfoList";
	}

	//@RequiresPermissions("mdm:mdmRoomInfo:view")
	@RequestMapping(value = "form")
	public String form(MdmRoomInfo mdmRoomInfo, Model model) {
		model.addAttribute("mdmRoomInfo", mdmRoomInfo);
		return "modules/mdm/mdmRoomInfoForm";
	}

	//@RequiresPermissions("mdm:mdmRoomInfo:edit")
	@RequestMapping(value = "save")
	public String save(MdmRoomInfo mdmRoomInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mdmRoomInfo)){
			return form(mdmRoomInfo, model);
		}
		mdmRoomInfoService.save(mdmRoomInfo);
		addMessage(redirectAttributes, "保存房间描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmRoomInfo/?repage";
	}
	
	//@RequiresPermissions("mdm:mdmRoomInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MdmRoomInfo mdmRoomInfo, RedirectAttributes redirectAttributes) {
		String idMerge=mdmRoomInfo.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(StringUtils.isNotBlank(id)){
				mdmRoomInfo=new MdmRoomInfo();
				mdmRoomInfo.setId(id);
				mdmRoomInfoService.delete(mdmRoomInfo);
			}
		}
		addMessage(redirectAttributes, "删除房间描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmRoomInfo/?repage";
	}

}