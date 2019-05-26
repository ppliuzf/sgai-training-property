package com.sgai.property.ctl.web;

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
import com.sgai.property.ctl.entity.CtlRoleChart;
import com.sgai.property.ctl.service.CtlRoleChartService;
import com.sgai.modules.login.jwt.bean.LoginUser;

/**
 * 角色和图表关系Controller
 * @author admin
 * @version 2018-01-04
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlRoleChart")
public class CtlRoleChartController extends BaseController {

	@Autowired
	private CtlRoleChartService ctlRoleChartService;
		
	
	/*--===================================-*/
	//@RequiresPermissions("ctl:ctlRoleChart:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtlRoleChart ctlRoleChart, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlRoleChart==null){
			ctlRoleChart=new CtlRoleChart();
		}
		Page<CtlRoleChart> page = ctlRoleChartService.findPage(new Page<CtlRoleChart>(request, response), ctlRoleChart); 
		model.addAttribute("page", page);
		return "modules/ctl/ctlRoleChartList";
	}

	//@RequiresPermissions("ctl:ctlRoleChart:view")
	@RequestMapping(value = "form")
	public String form(CtlRoleChart ctlRoleChart, Model model) {
		model.addAttribute("ctlRoleChart", ctlRoleChart);
		return "modules/ctl/ctlRoleChartForm";
	}

	//@RequiresPermissions("ctl:ctlRoleChart:edit")
	@RequestMapping(value = "save")
	public String save(CtlRoleChart ctlRoleChart, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlRoleChart)){
			return form(ctlRoleChart, model);
		}
		ctlRoleChartService.save(ctlRoleChart);
		addMessage(redirectAttributes, "保存角色和图表关系成功");
		return "redirect:"+Global.getAdminPath()+"/ctl/ctlRoleChart/?repage";
	}
	
	//@RequiresPermissions("ctl:ctlRoleChart:edit")
	@RequestMapping(value = "delete")
	public String delete(CtlRoleChart ctlRoleChart, RedirectAttributes redirectAttributes) {
		String idMerge=ctlRoleChart.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ctlRoleChart=new CtlRoleChart();
				ctlRoleChart.setId(id);
				ctlRoleChartService.delete(ctlRoleChart);
			}
		}
		addMessage(redirectAttributes, "删除角色和图表关系成功");
		return "redirect:"+Global.getAdminPath()+"/ctl/ctlRoleChart/?repage";
	}

}