package com.sgai.property.ctl.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlParamCompSec;
import com.sgai.property.ctl.service.CtlParamCompSecService;
import com.sgai.modules.login.jwt.util.ResponseUtil;


   /** 
    * ClassName: CtlParamCompSecController  
    * Description: (这里用一句话描述这个类的作用)
    * @author admin  
    * Date 2017年11月18日  
    * Company 首自信--智慧城市创新中心
    */  
@RestController
@RequestMapping(value = "${adminPath}/paramcomp/ctlParamComp")
public class CtlParamCompSecController extends BaseController {

	@Autowired
	private CtlParamCompSecService ctlParamCompService;
	
	@ModelAttribute
	public CtlParamCompSec get(@RequestParam(required=false) String id) {
		CtlParamCompSec entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlParamCompService.get(id);
		}
		if (entity == null){
			entity = new CtlParamCompSec();
		}
		return entity;
	}
		
	@RequestMapping(value = {"list", ""})
	public ResponseEntity<String> list(CtlParamCompSec ctlParamComp, HttpServletRequest request, 
			HttpServletResponse response) throws JsonProcessingException {
		
		Page<CtlParamCompSec> page = ctlParamCompService.findPage(new Page<CtlParamCompSec>(request, response), ctlParamComp); 
		//return page;
		return ResponseUtil.success(page);
	}
	
	@RequestMapping(value = "form")
	public ResponseEntity<String> form(CtlParamCompSec ctlParamComp, Model model) throws JsonProcessingException {
		try {
			return ResponseUtil.success(ctlParamComp);
		} catch (JsonProcessingException e) {
			return ResponseUtil.unKonwException();
		}
	}
 
	/**
	 * update:(这里用一句话描述这个方法的作用).
	 * @param ctlParamComp
	 * @param redirectAttributes
	 * @return
	 * @throws JsonProcessingException :ResponseEntity<String> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "update")
	public ResponseEntity<String> update(CtlParamCompSec ctlParamComp, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		try {
			ctlParamComp.setUpdatedDt(new Date());
			ctlParamCompService.save(ctlParamComp);
			return ResponseUtil.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.unKonwException();
		}
	}

	
	/**
	 * getProgList:(这里用一句话描述这个方法的作用).
	 * @return :List<Map<String,Object>> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getProgList")
	public List<Map<String,Object>> getProgList(){
		return ctlParamCompService.getModuList();
	}
	
	//@RequiresPermissions("paramcomp:ctlParamComp:edit")
	@RequestMapping(value = "save")
	public String save(CtlParamCompSec ctlParamComp, Model model, RedirectAttributes redirectAttributes) {
		
		ctlParamCompService.save(ctlParamComp);
		addMessage(redirectAttributes, "保存子系统参数维护成功");
		return "redirect:"+Global.getAdminPath()+"/paramcomp/ctlParamComp/?repage";
	}
	
	//@RequiresPermissions("paramcomp:ctlParamComp:edit")
	@RequestMapping(value = "delete")
	public String delete(CtlParamCompSec ctlParamComp, RedirectAttributes redirectAttributes) {
		String idMerge=ctlParamComp.getId();
		String ids[]=idMerge.split("~");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				ctlParamComp=new CtlParamCompSec();
				ctlParamComp.setId(id);
				ctlParamCompService.delete(ctlParamComp);
			}
		}
		addMessage(redirectAttributes, "删除子系统参数维护成功");
		return "redirect:"+Global.getAdminPath()+"/paramcomp/ctlParamComp/?repage";
	}

}