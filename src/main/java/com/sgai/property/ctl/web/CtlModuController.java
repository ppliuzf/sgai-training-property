package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlComRule;
import com.sgai.property.ctl.entity.CtlModu;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.CtlModuService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;

/**
 * 模块维护Controller
 * @author guanze
 * @version 2017-11-07
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlModu")
public class CtlModuController extends BaseController {

	@Autowired
	private CtlModuService ctlModuService;
	
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	
	/**
	 * get:获取单条数据
	 * @param id
	 * @return :CtlModu 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ModelAttribute
	public CtlModu get(@RequestParam(required=false) String id) {
		CtlModu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlModuService.get(id);
		}
		if (entity == null){
			entity = new CtlModu();
		}
		return entity;
	}
	
	/**
	 * list:获取列表
	 * @param ctlModu
	 * @param request
	 * @param response
	 * @param model
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = {"list", ""})
	public String list(CtlModu ctlModu, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlModu==null){
			ctlModu=new CtlModu();
		}
		Page<CtlModu> page = ctlModuService.findPage(new Page<CtlModu>(request, response), ctlModu); 
		model.addAttribute("page", page);
		return "ctl/ctlModuList";
	}

	@RequestMapping(value = "form")
	public String form(CtlModu ctlModu, Model model) {
		model.addAttribute("ctlModu", ctlModu);
		return "ctl/ctlModuForm";
	}

	/**
	 * save:单条数据新增保存，根据获取的对象的sbs_code，构建新对象进行唯一性校验，执行保存的同时返回执行信息
	 * @param ctlModu
	 * @param model
	 * @param redirectAttributes
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "save")
	public Map<String,String> save(CtlModu ctlModu, Model model,LoginUser user, RedirectAttributes redirectAttributes) {
		Map<String,String> data = new HashMap<String,String>();
		try {
			if (StringUtils.isBlank(ctlModu.getId())) {
			String moduCode = ctlComRuleService.getNext(user.getComCode(),"CTL_MODU");
			ctlModu.setSbsCode(moduCode);
			ctlModu.setEnabledFlag("Y");
			ctlModuService.save(ctlModu);
			}else {
				CtlModu ctlModu2 = get(ctlModu.getId());
				ctlModu.setModuCode(ctlModu2.getModuCode());
				ctlModuService.save(ctlModu);
			}
			data.put("msg", "success");
		} catch (Exception e){
			e.printStackTrace();
			data.put("msg", "faild");
			data.put("saveResult", "保存失败："+e.getMessage());
		}
		return data;
	}
	

	/**
	 * delete:批量删除，通过sql和id参数列表删除数据
	 * @param ids
	 * @param redirectAttributes
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "delete")
	public Map<String, String> delete(String ids, RedirectAttributes redirectAttributes) {
		String[] idArr = ids.split(",");
		List<String> idList = new ArrayList<String>();
		List<CtlModu> cdcList = new ArrayList<CtlModu>();
		for (String id : idArr) {
			if (id != null && !id.equals("")) {
				idList.add(id);
				CtlModu ctlModu = ctlModuService.get(id);
				cdcList.add(ctlModu);
			}
		}

		// 检查是否满足删除条件
		Map<String, String> data = deleteRulesUtils.checkBeforeDelete(CtlModu.class, idList);
		if ("true".equals(data.get("value"))) {
			List<CtlModu> finalList = ctlModuService.batchDelete(cdcList);
			if (finalList.size() > 0) {
				data.put("result", "删除成功");
			} else {
				data.put("result", "删除失败");
			}
		}
		// addMessage(redirectAttributes, "删除模块成功");
		// return "redirect:"+Global.getAdminPath()+"/ctlModu/?repage"+"删除模块成功";
		return data;
	}
//	
//	/**
//	 * updateSubmit:单条数据更新，根据获取的对象的sbs_code，构建新对象进行唯一性校验，执行更新的同时返回执行信息
//	 * @param ctlModu
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws IOException :Map<String,String> 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@RequestMapping(value = "/updateSubmit")
//	public Map<String,String> updateSubmit(CtlModu ctlModu,HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Map<String,String> data = new HashMap<String,String>();
//		data.put("checkResult", this.checkModuUpdate(ctlModu));
//		if (data.get("checkResult") == "dataOk") {
//			try {
//				ctlModuService.save(ctlModu);
//				data.put("updateResult", "更新完毕");
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				data.put("updateResult", "更新失败："+e.getMessage());
//			}
//		}
//		return data;
//	}
	
	/**
	 * findModuById:根据获取的id，得到一个CtlModu对象信息，只适用单条数据查询，id来源为url后缀的第一个参数
	 * @param qId
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/findModuById")
	public CtlModu findModuById(String qId,HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("qId:"+qId);
		return ctlModuService.findModuById(qId);
	}
	
	/**
	 * getListModu:获取列表
	 * @param ctlmodu
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/getListModu")
	public Page<CtlModu> getListModu(CtlModu ctlmodu,HttpServletRequest request, HttpServletResponse response) {
		Page<CtlModu> page = ctlModuService.findPage(new Page<CtlModu>(request, response), ctlmodu);
		return page;
	}
	
//	/**
//	 * insertSubmit:数据新增
//	 * @param ctlModu
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws IOException :Map<String,String> 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@RequestMapping(value = "/insertSubmit")
//	public Map<String, String> insertSubmit(CtlModu ctlModu,HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Map<String,String> data = new HashMap<String,String>();
//		data.put("checkResult", this.checkModuInsert(ctlModu));
//		if (data.get("checkResult") == "dataOk") {
//			try {
//				ctlModuService.save(ctlModu);
//				data.put("insertResult", "新增完毕");
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				data.put("insertResult", "新增失败："+e.getMessage());
//			}
//		}
//		return data;
//	}
//	

//	/**
//	 * checkModuUpdate:更新前检查数据
//	 * @param ctlModu
//	 * @return :String 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@Transactional(readOnly = false)
//	public String checkModuUpdate(CtlModu ctlModu) {
//		CtlModu qcm = new CtlModu();
//		qcm.setId(ctlModu.getId());
//		qcm.setSbsCode(ctlModu.getSbsCode());
//		
//		//字段非空判断
//		if(this.isNull(ctlModu.getSbsCode())) {return "\"模块代码\"为空";}
//		if(this.isNull(ctlModu.getSbsName())) {return "\"模块名称\"为空";}
//		if(this.isZero(ctlModu.getDisplayOrder())) {return "\"显示序号\"不可为0";}
//		if(this.isNull(ctlModu.getEnabledFlag())) {return "\"可用\"为空";}
//
//		//记录是否存在判断
//		if(this.existModu(qcm)) {return "dataOk";}
//		else {return "不存在的数据";}
//	}
//	
//	/**
//	 * checkModuInsert:插入数据前检查
//	 * @param ctlModu
//	 * @return :String 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	@Transactional(readOnly = false)
//	private String checkModuInsert(CtlModu ctlModu) {
//		CtlModu qcm = new CtlModu();
//		qcm.setSbsCode(ctlModu.getSbsCode());
//		CtlModu qcm2 = new CtlModu();
//		qcm2.setDisplayOrder(ctlModu.getDisplayOrder());
//		
//		//字段非空判断
//		if(this.isNull(ctlModu.getSbsCode())) {return "\"模块代码\"为空";}
//		if(this.isNull(ctlModu.getSbsName())) {return "\"模块名称\"为空";}
//		if(this.isZero(ctlModu.getDisplayOrder())) {return "\"显示序号\"不可为0";}
//		if(this.isNull(ctlModu.getEnabledFlag())) {return "\"可用\"为空";}
//
//		//记录是否存在判断
//		if(this.existModu(qcm)) {return "已存在的数据";}
//		else if (this.existModu(qcm2)) {return "序号不可重复";}
//		else {return "dataOk";}
//		
//	}
//	
//	/**
//	 * existModu:检查数据是否存在
//	 * @param qcm
//	 * @return :boolean 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	private boolean existModu(CtlModu qcm) {
//		List<CtlModu> qList = ctlModuService.findList(qcm);
//		if (qList.size() != 0) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * isNull:判断数据是否为空
//	 * @param str
//	 * @return :boolean 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	private boolean isNull(String str) {
//		if(str == null || str.equals("")) {
//			return true;
//		}
//		return false;
//	}
//	/**
//	 * isZero:判断数字是否为0
//	 * @param numL
//	 * @return :boolean 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	private boolean isZero(Long numL) {
//		if(numL == 0) {
//			return true;
//		}
//		return false;
//	}
//	/**
//	 * isNumeric:判断输入字符串是否为数字
//	 * @param str
//	 * @return :boolean 
//	 * @since JDK 1.8
//	 * @author guanze
//	 */
//	public boolean isNumeric(String str) {
//		Pattern pattern = Pattern.compile("[0-9]*");
//		Matcher isNum = pattern.matcher(str);
//		if (!isNum.matches()) {
//			return false;
//		}
//		return true;
//	}
}