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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlDelCheck;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.service.CtlDelCheckService;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 对象删除规则定义Controller
 * @author guanze
 * @version 2017-11-13
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlDelCheck")
@Api(description = "对象删除规则定义接口")
public class CtlDelCheckController extends BaseController {

	@Autowired
	private CtlDelCheckService ctlDelCheckService;
	
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	
	/**
	 * get:获取ctlDelCheck单条数据
	 * @param id
	 * @return :CtlDelCheck 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ModelAttribute
	public CtlDelCheck get(@RequestParam(required=false) String id) {
		CtlDelCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlDelCheckService.get(id);
		}
		if (entity == null){
			entity = new CtlDelCheck();
		}
		return entity;
	}
	
	/**
	 * list:获取ctlDelCheck列表
	 * @param ctlDelCheck
	 * @param request
	 * @param response
	 * @param model
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = {"list", ""})
	public String list(CtlDelCheck ctlDelCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlDelCheck==null){
			ctlDelCheck=new CtlDelCheck();
		}
		Page<CtlDelCheck> page = ctlDelCheckService.findPage(new Page<CtlDelCheck>(request, response), ctlDelCheck); 
		model.addAttribute("page", page);
		return "ctl/ctlDelCheckList";
	}

	/**
	 * form:(这里用一句话描述这个方法的作用).
	 * @param ctlDelCheck
	 * @param model
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "form")
	public String form(CtlDelCheck ctlDelCheck, Model model) {
		model.addAttribute("ctlDelCheck", ctlDelCheck);
		return "ctl/ctlDelCheckForm";
	}

	/**
	 * save:保存数据
	 * @param ctlDelCheck
	 * @return :Map<String,String> 
	 * @since JDK 1.8
	 * @author guanze
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "保存规则删除定义信息", notes = "保存规则删除定义信息")
	@RequestMapping(value = "save")
	public CommonResponse save(CtlDelCheck ctlDelCheck, Model model, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String,String> data = new HashMap<String,String>();
		try {
			if (ctlDelCheck.getId() !=null && !"".equals(ctlDelCheck.getId())) {
				ctlDelCheckService.save(ctlDelCheck);
				data.put("message", "success");
			}else {
				CtlDelCheck cdc = new CtlDelCheck();
				cdc.setSbsCode(ctlDelCheck.getSbsCode());
				cdc.setOprTableName(ctlDelCheck.getOprTableName());
				cdc.setTableName(ctlDelCheck.getTableName());
				cdc.setOprColumnName(ctlDelCheck.getOprColumnName());
				cdc.setColumnName(ctlDelCheck.getColumnName());
				cdc.setOprColumnComCode(ctlDelCheck.getColumnComCode());
				cdc.setColumnComCode(ctlDelCheck.getColumnComCode());
				cdc.setValidFlag("Y");
				List<CtlDelCheck> resultlist = ctlDelCheckService.findList(cdc);
				if (resultlist.size()==0) {
					ctlDelCheck.setValidFlag("Y");
					ctlDelCheckService.save(ctlDelCheck);
					data.put("message", "success");
				} else {
					data.put("message", "repeat");
				}
			}
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return ResponseUtil.successResponse(data);
	}
	
	/**
	 * delete:删除多条数据
	 * @param ids
	 * @return :String 
	 * @since JDK 1.8
	 * @author guanze
	 * @throws JsonProcessingException 
	 */
	@ApiOperation(value = "删除规则删除定义信息", notes = "删除规则删除定义信息")
	@RequestMapping(value = "delete")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String,String> data = new HashMap<String,String>();
		try {
			String[] idArr=ids.split(",");
			List<String> idList = new ArrayList<String>();
			List<CtlDelCheck> cdcList = new ArrayList<CtlDelCheck>();
			for(String id:idArr){
				if(id!=null&&!id.equals("")){
					idList.add(id);
					CtlDelCheck ctlDelCheck = ctlDelCheckService.get(id);
					cdcList.add(ctlDelCheck);
				}
			}		
			//检查是否满足删除条件 
			Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlDelCheck.class, idList);
			if("true".equals(resultMap.get("value"))) {
				List<CtlDelCheck> finalList = ctlDelCheckService.batchDelete(cdcList);
                if(finalList.size() > 0) {
					data.put("msg", "删除成功!");
				}else {
					data.put("msg", "删除失败！");
				}
				data.put("result", "success");
			}else {
				data.put("msg", resultMap.get("description"));
				data.put("result", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			data.put("msg", "出错了！");
			data.put("result", "fail");
		}
		
		return ResponseUtil.successResponse(data);	
	}

		/**
		 * update:单条数据更新，根据获取的对象的，构建新对象进行唯一性校验，执行更新的同时返回执行信息
		 * @param id
		 * @param sbsCode
		 * @param oprTable
		 * @param tableName
		 * @param columnName
		 * @param columnComCode
		 * @param promptDesc
		 * @param request
		 * @param response
		 * @return 
		 * @since JDK 1.8
		 * @author guanze
		 */
		@RequestMapping(value = "update")
	public Map<String,String> update(String id,String sbsCode,String oprTableName,String tableName,String oprColumnName,String columnName,String oprColumnComCode,String columnComCode,String promptDesc,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("####:"+id);
		Map<String,String> data = new HashMap<String,String>();
		CtlDelCheck cd = new CtlDelCheck();
		cd.setId(id);
		CtlDelCheck cdc = new CtlDelCheck();
		cdc.setId(id);
		cdc.setSbsCode(sbsCode);
		cdc.setOprTableName(oprTableName);
		cdc.setTableName(tableName);
		cdc.setOprColumnName(oprColumnName);
		cdc.setColumnName(columnName);
		cdc.setOprColumnComCode(oprColumnComCode);
		cdc.setColumnComCode(columnComCode);
		cdc.setPromptDesc(promptDesc);
		List<CtlDelCheck> resultlist = ctlDelCheckService.findList(cd);
		try {
			if (resultlist.size()==0) {
				data.put("message", "notexist");
			} else {
				ctlDelCheckService.save(cdc);
				data.put("message", "success");
			}
		} catch (Exception e) {
			data.put("message", "failed");
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * findByIdDelCheck:根据id获取CtlDelCheck实例对象单条数据
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :CtlDelCheck 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ApiOperation(value = "查询规则删除定义信息", notes = "查询规则删除定义信息")
	@RequestMapping(value = "/findByIdDelCheck")
	public CommonResponse findByIdDelCheck(String ids,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idList[]=ids.split(",");
		CtlDelCheck ctlDelCheck = ctlDelCheckService.get(idList[0]);
		return ResponseUtil.successResponse(ctlDelCheck);
	}
	
	/**
	 * getListDelCheck:获取列表
	 * @param ctlDelCheck
	 * @param sbsCode
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException :Page<CtlDelCheck> 
	 * @since JDK 1.8
	 * @author guanze
	 */
	@ApiOperation(value = "查询规则删除定义列表信息", notes = "查询规则删除定义列表信息")
	@RequestMapping(value = "/getListDelCheck")
	public CommonResponse getListDelCheck(CtlDelCheck ctlDelCheck,String sbsCode,HttpServletRequest request, HttpServletResponse response) throws IOException {
		ctlDelCheck.setSbsCode(sbsCode);
		Page<CtlDelCheck> page = ctlDelCheckService.findPage(new Page<CtlDelCheck>(request, response), ctlDelCheck);
		//System.out.println(page.getList().toString());
		
//		List<String> idList= new ArrayList<String>();
//		idList.add("8ea1dc648b3c4c2db9675fdb57527265");
//		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(CtlRole.class,idList);
//		System.out.println(resultMap.toString());
		
		
		return ResponseUtil.successResponse(page);
	}

	/**
	 * checkDelCheckInsert:检查插入的数据是否符合规范
	 * @param sbsCode
	 * @param oprTable
	 * @param tableName
	 * @param columnName
	 * @param columnComCode
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author guanze
	 */
	@RequestMapping(value = "/checkDelCheckInsert")
	public Map<String,String> checkDelCheckInsert(String sbsCode,String oprTableName,String tableName,String oprColumnName,String columnName,String oprColumnComCode,String columnComCode,HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> data = new HashMap<String,String>();
		if (sbsCode == null || sbsCode.equals("") || 
			oprTableName == null || oprTableName.equals("") ||
			tableName == null || tableName.equals("") ||
			oprColumnName == null || oprColumnName.equals("") ||
			columnName == null || columnName.equals("") ||
			oprColumnComCode == null || oprColumnComCode.equals("") ||
			columnComCode == null || columnComCode.equals("")) {
			data.put("message", "nullValue");
			return data;
		}
		CtlDelCheck cdc = new CtlDelCheck();
		cdc.setSbsCode(sbsCode);
		cdc.setOprTableName(oprTableName);
		cdc.setTableName(tableName);
		cdc.setOprColumnName(oprColumnName);
		cdc.setColumnName(columnName);
		cdc.setOprColumnComCode(oprColumnComCode);
		cdc.setColumnComCode(columnComCode);
		List<CtlDelCheck> resultlist = ctlDelCheckService.checkDelCheck(cdc);
		try {
			if (resultlist.size()==0) {
				data.put("message", "notexist");
			} else {
				data.put("message", "exist");
			}
		} catch (Exception e) {
			data.put("message", "queryFailed");
			e.printStackTrace();
		}
		return data;
	}
}