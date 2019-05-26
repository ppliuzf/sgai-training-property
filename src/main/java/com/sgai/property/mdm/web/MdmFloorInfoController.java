package com.sgai.property.mdm.web;

import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.mdm.entity.MdmFloorInfo;
import com.sgai.property.mdm.service.MdmFloorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 楼层描述 ---空间Controller
 * @author zhb
 * @version 2017-11-24
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmFloorInfo")
public class MdmFloorInfoController extends BaseController {

	@Autowired
	private MdmFloorInfoService mdmFloorInfoService;
	
	@ModelAttribute
	public MdmFloorInfo get(@RequestParam(required=false) String id) {
		MdmFloorInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mdmFloorInfoService.get(id);
		}
		if (entity == null){
			entity = new MdmFloorInfo();
		}
		return entity;
	}
	
	//@RequiresPermissions("mdm:mdmFloorInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(MdmFloorInfo mdmFloorInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(mdmFloorInfo==null){
			mdmFloorInfo=new MdmFloorInfo();
		}
		Page<MdmFloorInfo> page = mdmFloorInfoService.findPage(new Page<MdmFloorInfo>(request, response), mdmFloorInfo); 
		model.addAttribute("page", page);
		return "modules/mdm/mdmFloorInfoList";
	}

	//@RequiresPermissions("mdm:mdmFloorInfo:view")
	@RequestMapping(value = "form")
	public String form(MdmFloorInfo mdmFloorInfo, Model model) {
		model.addAttribute("mdmFloorInfo", mdmFloorInfo);
		return "modules/mdm/mdmFloorInfoForm";
	}

	//@RequiresPermissions("mdm:mdmFloorInfo:edit")
	@RequestMapping(value = "save")
	public String save(MdmFloorInfo mdmFloorInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, mdmFloorInfo)){
			return form(mdmFloorInfo, model);
		}
		mdmFloorInfoService.save(mdmFloorInfo);
		addMessage(redirectAttributes, "保存楼层描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmFloorInfo/?repage";
	}
	
	//@RequiresPermissions("mdm:mdmFloorInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(MdmFloorInfo mdmFloorInfo, RedirectAttributes redirectAttributes) {
		String idMerge=mdmFloorInfo.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				mdmFloorInfo=new MdmFloorInfo();
				mdmFloorInfo.setId(id);
				mdmFloorInfoService.delete(mdmFloorInfo);
			}
		}
		addMessage(redirectAttributes, "删除楼层描述 ---空间成功");
		return "redirect:"+Global.getAdminPath()+"/mdm/mdmFloorInfo/?repage";
	}
	
	@RequestMapping(value = "/getFloorList", method=RequestMethod.POST)
	public List<MdmFloorInfo>  getFloorList(
			HttpServletRequest request,
			HttpServletResponse response
			) {
		MdmFloorInfo mdmFloorInfo=new MdmFloorInfo();
		mdmFloorInfo.setComCode(UserServletContext.getUserInfo().getComCode());
		return mdmFloorInfoService.findList(mdmFloorInfo);
	}
	
	
	@RequestMapping(value = "/getFloorListbyBuild", method=RequestMethod.POST)
	public List<MdmFloorInfo>  getFloorListByBuild(
			String buildingCode,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		MdmFloorInfo mdmFloorInfo=new MdmFloorInfo();
		mdmFloorInfo.setComCode(UserServletContext.getUserInfo().getComCode());
		mdmFloorInfo.setBuildingCode(buildingCode);
		List<MdmFloorInfo>  list=mdmFloorInfoService.findList(mdmFloorInfo);
		return list;
	}
	
	
	
	
	

}