package com.sgai.property.quality.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.dao.IQtProfessionalCategoryDao;
import com.sgai.property.quality.dao.IQtTestPlanDao;
import com.sgai.property.quality.entity.QtProfessionalCategory;
import com.sgai.property.quality.entity.QtTestPlan;
import com.sgai.property.quality.service.CategoryServiceImpl;
import com.sgai.property.quality.vo.CategoryTypeVo;
import com.sgai.property.quality.vo.CategoryVo;
import com.sgai.property.quality.vo.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//@Api(description = "专业范畴接口")
@Api(description = "任务专业维护")

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
	
	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private IQtProfessionalCategoryDao categoryDao;
	@Autowired
	private IQtTestPlanDao qtTestPlanDao;


	@PostMapping(value = "/createCategory")
//	@ApiOperation(value = "新增专业范畴", notes = "新增专业范畴")
	@ApiOperation(value = "新增任务专业", notes = "新增任务专业")
	public Response<Boolean> createCategory(
											@RequestBody CategoryDto categoryDto) {
		Response<Boolean> response = new Response<>();

		response.setData(categoryService.createCategory(categoryDto));
		return response;
	}

	@PostMapping(value = "/delCategory")
	@ApiOperation(value = "删除任务专业", notes = "删除任务专业")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pcId", value = "专业范畴ID", required = true, paramType = "query", dataType = "Long") })
	public Response<Boolean> delCategory(String pcId) {
		Response<Boolean> response = new Response<>();
		if(StringUtils.isEmpty(pcId)){
			throw new BusinessException(ReturnType.ParamIllegal, "请输入正确的专业范畴ID");
		}
		response.setData(categoryService.delCategory(pcId));
		return response;
	}

	@PostMapping(value = "/updateCategory")
	@ApiOperation(value = "编辑任务专业", notes = "编辑任务专业")
	public Response<Boolean> updateCategory(@RequestBody CategoryDto categoryDto) {
		Response<Boolean> response = new Response<>();

		response.setData(categoryService.updateCategory(categoryDto));
		return response;
	}
	

	@PostMapping(value = "/listCategory")
	@ApiOperation(value = "任务专业维护", notes = "任务专业维护,type 任务类型标示 0：检验类 1：安保类")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, paramType = "query", dataType = "int")
		})
	public Response<Page<CategoryVo>> listCategory(
												   Integer pageNum,
												   Integer pageSize,
												   Integer type) {
		Response<Page<CategoryVo>> response = new Response<>();
		response.setData(categoryService.listCategory(pageNum,pageSize,type));
		return response;
	}

	@PostMapping(value = "/getAllCategory")
	@ApiOperation(value = "获取任务专业下拉列表数据", notes = "获取任务专业下拉列表数据,type 任务类型标示 0：检验类 1：安保类")
	public Response<List<Map<String, Object>>> getAllCategory(Integer type) {
		Response<List<Map<String, Object>>> response = new Response<>();

		response.setData(categoryService.getAllCategory(type));
		return response;
	}

	@PostMapping(value = "/detailCategory")
	@ApiOperation(value = "任务专业详情", notes = "任务专业详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pcId", value = "专业范畴ID", required = true, paramType = "query", dataType = "long")
	})
	public Response<CategoryVo> detailCategory( String pcId) {
		Response<CategoryVo> response = new Response<>();
		response.setData(categoryService.detailCategory(pcId));
		return response;
	}
	

	@PostMapping(value = "/getCategoryTypes")
	@ApiOperation(value = "从字典表获取关联类型下拉列表", notes = "从字典表获取关联类型下拉列表")
	public Response<List<Map<String, Object>> > getCategoryTypes() {
		Response<List<Map<String, Object>> > response = new Response<>();

		response.setData(categoryService.getCategoryTypes());
		return response;
	}

	@PostMapping(value = "/getCategoryIcons")
	@ApiOperation(value = "从字典表获取任务专业icon列表", notes = "从字典表获取任务专业icon列表")
	public Response<List<Map<String, Object>> > getCategoryIcons() {
		Response<List<Map<String, Object>> > response = new Response<>();

		response.setData(categoryService.getCategoryIcons());
		return response;
	}

	@PostMapping(value = "/getCategoryTypeByCategoryId")
	@ApiOperation(value = "根据任务专业ID查询关联设备", notes = "根据任务专业ID查询关联设备")
	public Response<List<CategoryTypeVo>> getCategoryTypeByCategoryId(
																	   String categoryId) {
		Response<List<CategoryTypeVo>> response = new Response<>();
		CategoryVo categoryVo=categoryService.detailCategory(categoryId);
		if(categoryVo==null){
			throw new BusinessException(ReturnType.ParamIllegal, "任务专业不存在！");
		}
		List<CategoryTypeVo> typeVoList= JSON.parseObject(categoryVo.getAsType(),new TypeReference<List<CategoryTypeVo>>() {});
		response.setData(typeVoList);
		return response;
	}

	@PostMapping(value = "/getCategoryByType")
	@ApiOperation(value = "根据任务类型名称查任务专业", notes = "根据任务类型名称查任务专业,type 任务类型标示 0：检验类 1：安保类")
	public Response<List<QtProfessionalCategory>> getCategoryByType(
																	String typeName,
																	Integer type) {
		Response<List<QtProfessionalCategory>> response = new Response<>();

		QtProfessionalCategory qtProfessionalCategory = new QtProfessionalCategory();
		qtProfessionalCategory.setTaskType(typeName);
		System.out.println("integer 没有值========="+type);
		if (type == null) {
			qtProfessionalCategory.setTypeFlag(0);
		}else {
			qtProfessionalCategory.setTypeFlag(type);
		}
		qtProfessionalCategory.setComCode(UserServletContext.getUserInfo().getComCode());
		List<QtProfessionalCategory> list = categoryDao.findListByTypeName(qtProfessionalCategory);
		response.setData(list);
		return response;
	}

	@PostMapping(value = "/getTempletes")
	@ApiOperation(value = "根据任务专业查模板", notes = "根据任务专业查模板")
	public Response<List<QtTestPlan>> getTempletes( String id) {
		Response<List<QtTestPlan>> response = new Response<>();
		QtTestPlan qtTestPlan = new QtTestPlan();
		qtTestPlan.setTiId(id);
		List<QtTestPlan> list = qtTestPlanDao.findListByTId(qtTestPlan);
		response.setData(list);
		return response;
	}


}
