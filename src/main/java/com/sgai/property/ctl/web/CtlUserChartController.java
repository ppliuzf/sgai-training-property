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
import com.sgai.property.ctl.entity.CtlUserChart;
import com.sgai.property.ctl.service.CtlUserChartService;

/**
 * 用户图表关系表Controller
 * @author admin
 * @version 2018-01-04
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlUserChart")
public class CtlUserChartController extends BaseController {

	@Autowired
	private CtlUserChartService ctlUserChartService;
	
	
	//@RequiresPermissions("ctl:ctlUserChart:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtlUserChart ctlUserChart, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlUserChart==null){
			ctlUserChart=new CtlUserChart();
		}
		Page<CtlUserChart> page = ctlUserChartService.findPage(new Page<CtlUserChart>(request, response), ctlUserChart); 
		model.addAttribute("page", page);
		return "modules/ctl/ctlUserChartList";
	}

	//@RequiresPermissions("ctl:ctlUserChart:view")
	@RequestMapping(value = "form")
	public String form(CtlUserChart ctlUserChart, Model model) {
		model.addAttribute("ctlUserChart", ctlUserChart);
		return "modules/ctl/ctlUserChartForm";
	}

	//@RequiresPermissions("ctl:ctlUserChart:edit")
	@RequestMapping(value = "save")
	public String save(CtlUserChart ctlUserChart, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlUserChart)){
			return form(ctlUserChart, model);
		}
		ctlUserChartService.save(ctlUserChart);
		addMessage(redirectAttributes, "保存用户图表关系表成功");
		return "redirect:"+Global.getAdminPath()+"/ctl/ctlUserChart/?repage";
	}
	
	//@RequiresPermissions("ctl:ctlUserChart:edit")
	@RequestMapping(value = "delete")
	public String delete(CtlUserChart ctlUserChart, RedirectAttributes redirectAttributes) {
		String idMerge=ctlUserChart.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ctlUserChart=new CtlUserChart();
				ctlUserChart.setId(id);
				ctlUserChartService.delete(ctlUserChart);
			}
		}
		addMessage(redirectAttributes, "删除用户图表关系表成功");
		return "redirect:"+Global.getAdminPath()+"/ctl/ctlUserChart/?repage";
	}

}