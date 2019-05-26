package com.sgai.property.budget.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.budget.entity.Template;
import com.sgai.property.budget.service.TemplateNewServiceImpl;
import com.sgai.property.budget.vo.RelationDataParam;
import com.sgai.property.budget.vo.SubjectItemsVo;
import com.sgai.property.budget.vo.TemplateParam;
import com.sgai.property.budget.vo.TemplateVo;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@author 严浩淼
 *@date 2018年1月17日--下午4:55:32
 */
@Api(description="模板管理")
@RestController
@RequestMapping(value="/template")
public class TemplateController extends BaseController {

	@Autowired
	private TemplateNewServiceImpl templateNewService;
	
	@ApiOperation(value="分页获取模板列表",httpMethod="POST",notes="分页获取模板列表")
	@PostMapping(value="getPageList")
	public Response<Page<Template>> getPageList(
			Integer pageNum,Integer pageSize) {
		Response<Page<Template>> result = new Response<>();
		try {

			Page<Template> page =templateNewService.getPageList(pageNum,pageSize);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="获取模板详情",httpMethod="POST",notes="获取模板详情")
	@PostMapping(value="getById")
	public Response<TemplateVo> getById(String id) {
		Response<TemplateVo> result = new Response<>();
		try {
			TemplateVo templateVo =templateNewService.getDetailById(id);
			result.setData(templateVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="保存模板",httpMethod="POST",notes="保存模板")
	@PostMapping(value="save")
	public Response<Template> save(@RequestBody TemplateParam param) {
		Response<Template> result = new Response<>();
		try {

			Template template =templateNewService.saveTemplate(param);
			result.setData(template);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="科目关联费项数据详情",httpMethod="POST",notes="科目关联费项数据详情")
	@PostMapping(value="getRelationToItems")
	public Response<List<SubjectItemsVo>> getRelationToItems(String templateId) {
		Response<List<SubjectItemsVo>> result = new Response<>();
		try {

			List<SubjectItemsVo> subjectItemsVos =templateNewService.getSubjectItemList(templateId);
			result.setData(subjectItemsVos);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="保存科目费项关联数据",httpMethod="POST",notes="保存科目费项关联数据")
	@PostMapping(value="saveRelationData")
	public Response<Boolean> saveRelationData(@RequestBody RelationDataParam param) {
		Response<Boolean> result = new Response<>();
		try {

			result.setData(templateNewService.saveRelationData(param));
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(false);
			result.setCode("-100");
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value="模板启用/禁用",httpMethod="POST",notes="模板启用/禁用")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id",value="模版id",required=true,paramType="query",dataType="String"),
		@ApiImplicitParam(name="state",value="状态：0、启用；1、禁用",required=true,paramType="query",dataType="String")
	})
	@PostMapping(value="enableOrDisable")
	public Response<Boolean> enableOrDisable(String id,Long state) {
		Response<Boolean> result = new Response<>();
		try {

			result.setData(templateNewService.updateState(state,id));
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(false);
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
}
