package com.sgai.property.ruag.web;

import java.io.IOException;

import javax.servlet.ServletException;
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
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.property.ruag.entity.RuagOptimizedRecord;
import com.sgai.property.ruag.service.RuagOptimizedRecordService;

/**
 * 优化记录Controller
 * @author admin
 * @version 2018-08-17
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagOptimizedRecord")
public class RuagOptimizedRecordController extends BaseController {

	@Autowired
	private RuagOptimizedRecordService ruagOptimizedRecordService;
	
	
	
	@RequestMapping(value = "/getRecordlist", method=RequestMethod.POST)
	public CommonResponse getRecordlist(

			@RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			@RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,
			HttpServletRequest request, 
			HttpServletResponse response
			)throws IOException {
		RuagOptimizedRecord ruagOptimizedRecord=new RuagOptimizedRecord();
		
		Page<RuagOptimizedRecord> page = ruagOptimizedRecordService.findPage(new Page<RuagOptimizedRecord>(pageNo, pageSize), ruagOptimizedRecord);
		return ResponseUtil.successResponse(page);
	}
	
	
	
	
	
	
	@ModelAttribute
	public RuagOptimizedRecord get(@RequestParam(required=false) String id) {
		RuagOptimizedRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ruagOptimizedRecordService.get(id);
		}
		if (entity == null){
			entity = new RuagOptimizedRecord();
		}
		return entity;
	}
	
	//@RequiresPermissions("mon:ruagOptimizedRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(RuagOptimizedRecord ruagOptimizedRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ruagOptimizedRecord==null){
			ruagOptimizedRecord=new RuagOptimizedRecord();
		}
		Page<RuagOptimizedRecord> page = ruagOptimizedRecordService.findPage(new Page<RuagOptimizedRecord>(request, response), ruagOptimizedRecord); 
		model.addAttribute("page", page);
		return "think/mon/ruagOptimizedRecordList";
	}

	//@RequiresPermissions("mon:ruagOptimizedRecord:view")
	@RequestMapping(value = "form")
	public String form(RuagOptimizedRecord ruagOptimizedRecord, Model model) {
		model.addAttribute("ruagOptimizedRecord", ruagOptimizedRecord);
		return "think/mon/ruagOptimizedRecordForm";
	}

	//@RequiresPermissions("mon:ruagOptimizedRecord:edit")
	@RequestMapping(value = "save")
	public String save(RuagOptimizedRecord ruagOptimizedRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ruagOptimizedRecord)){
			return form(ruagOptimizedRecord, model);
		}
		ruagOptimizedRecordService.save(ruagOptimizedRecord);
		addMessage(redirectAttributes, "保存优化记录成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptimizedRecord/?repage";
	}
	
	//@RequiresPermissions("mon:ruagOptimizedRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(RuagOptimizedRecord ruagOptimizedRecord, RedirectAttributes redirectAttributes) {
		String idMerge=ruagOptimizedRecord.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ruagOptimizedRecord=new RuagOptimizedRecord();
				ruagOptimizedRecord.setId(id);
				ruagOptimizedRecordService.delete(ruagOptimizedRecord);
			}
		}
		addMessage(redirectAttributes, "删除优化记录成功");
		return "redirect:"+Global.getAdminPath()+"/mon/ruagOptimizedRecord/?repage";
	}

}