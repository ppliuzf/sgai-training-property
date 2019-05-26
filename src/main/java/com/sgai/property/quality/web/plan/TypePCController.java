package com.sgai.property.quality.web.plan;


import com.sgai.common.persistence.Page;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.quality.entity.plan.Type;
import com.sgai.property.quality.service.plan.TypePCServiceImpl;
import com.sgai.property.quality.vo.plan.TypeParam;
import com.sgai.property.quality.vo.plan.TypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@author 严浩淼
 *@date 2018年1月5日--下午4:36:27
 */
@RestController
@RequestMapping("/pc/type")
@Api(description = "类型管理-PC端")
public class TypePCController extends BaseController {
 
	@Autowired
	private TypePCServiceImpl typeService;
	
	@PostMapping(value="/typePageList")
    @ApiOperation(value = "分页获取类型列表", httpMethod = "POST", notes = "分页获取类型列表")
	public Response<Page<TypeVo>> PageList( int pageNum, int pageSize) {
        Response<Page<TypeVo>> result = new Response<>();
		try {
			Page<TypeVo> pageVo = typeService.getListPage(pageNum, pageSize);
			result.setData(pageVo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("查询数据库异常！");
		}
		return result;
	}
	
	@PostMapping(value="/getList")
    @ApiOperation(value = "获取类型列表", httpMethod = "POST", notes = "获取类型列表")
	public Response<List<Type>> getList() {
        Response<List<Type>> result = new Response<>();
		try {
			List<Type> types= typeService.getList();
			result.setData(types);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("查询数据库异常！");
		}
		return result;
	}
	
	@PostMapping(value="/getById")
	@ApiOperation(value = "获取类型详情", httpMethod = "POST", notes = "获取类型详情")
	public Response<TypeVo> getById(String id) {
		Response<TypeVo> result = new Response<>();
		TypeVo typeVo =typeService.getTypeById(id);
		result.setData(typeVo);
		return result;
	}

	@ApiOperation(value = "新增类型", httpMethod = "POST", notes = "新增类型")
	@PostMapping(value = "/insert")
	public Response<Type> insert(@RequestBody TypeParam typeParam){
        Response<Type> result = new Response<>();
		try {
			if (StringUtils.isEmpty(typeParam.getTypeName())) {
				throw new Exception("类型名称不能为空");
			}

	        Type type= typeService.saveType(typeParam);
	        result.setData(type);
		} catch (Exception e) {
			result.setCode("-100");
			result.setMessage(e.getMessage());
		}
        return result;
    }

	@ApiOperation(value = "更新类型", httpMethod = "POST", notes = "更新类型")
	@PostMapping(value = "/updateById")
	public Response<Type> updateById(@RequestBody Type type){

        Response<Type> result = new Response<>();
        type.setUpdateTime(System.currentTimeMillis());
        typeService.updateById(type);
		result.setData(type);
		return result;
	 }

	@ApiOperation(value = "删除类型", httpMethod = "POST", notes = "删除类型")
	@PostMapping(value = "/deleteById")
    public Response<Boolean> deleteById(String id){
        Response<Boolean> result = new Response<>();
		try {

	        Type type =typeService.getById(id);
	        if (type!=null) {
	        	if (0==type.getTypeCode()) {
					throw new Exception("预算类为默认类型，不可删除!");
				}
		        type.setIsDelete(1L);
		        type.setUpdateTime(System.currentTimeMillis());
		        result.setData(typeService.updateById(type));
			}else {
				throw new Exception("数据库操作失败！");
			}
		} catch (Exception e) {
			result.setCode("-101");
			result.setMessage(e.getMessage());
		}
        return result;
	}

}
