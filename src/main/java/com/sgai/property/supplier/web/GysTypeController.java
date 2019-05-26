package com.sgai.property.supplier.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.supplier.entity.GysSupplier;
import com.sgai.property.supplier.entity.GysType;
import com.sgai.property.supplier.service.GysSupplierServiceImpl;
import com.sgai.property.supplier.service.GysTypeServiceImpl;
import com.sgai.property.supplier.vo.GysTypeParams;
import com.sgai.property.supplier.vo.GysTypeVO;
import com.sgai.property.supplier.vo.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@Api(description = "供应商类型")
public class GysTypeController extends BaseController {
    @Autowired
	private GysTypeServiceImpl gysTypeService;
	@Autowired
	private GysSupplierServiceImpl gysSupplierService;


	@ApiOperation(value = "获取所有供应商类型列表", httpMethod = "POST", notes = "获取所有供应商类型列表")
	@PostMapping(value="/findAllTypeList")
	public Response<List<GysType>> list() {

		List<GysType> gysTypeList=gysTypeService.findAllList();
		Response<List<GysType>> result = new Response<>();
		result.setData(gysTypeList);
		return result;
	}

	@ApiOperation(value = "获取供应商类型列表", httpMethod = "POST", notes = "获取供应商类型列表")
	@PostMapping(value="/findGysTypeList")
	public Response<Page<GysTypeVO>> findGysTypeList(
													 @RequestParam(value = "pageNum",required = true) int pageNum,
													 @RequestParam(value = "pageSize",required = true) int pageSize) {


		Response<Page<GysTypeVO>> result = new Response<>();
    	//传入已删除的标识，在后台判断不等于该标识
		GysType gysType = new GysType();
    	gysType.setIsDelete(1L);
    	gysType.setComCode(UserServletContext.getUserInfo().getComCode());
		if(UserServletContext.getUserInfo().getModuCode()!=null && !"".equals(UserServletContext.getUserInfo().getModuCode())){
			gysType.setModuCode(UserServletContext.getUserInfo().getModuCode());
		}
    	Page<GysTypeVO> page = gysTypeService.findGysTypeList(gysType,pageNum,pageSize);
		result.setData(page);
		return result;
	}

	@ApiOperation(value = "新增供应商类型", httpMethod = "POST", notes = "新增供应商类型")
	@PostMapping(value = "/saveGysType")
	public Response<ResultMessage> saveGysType( @RequestBody GysTypeParams gysTypeParams) {
		Response<ResultMessage> result = new Response<>();
		ResultMessage dataResult = new ResultMessage();
		GysType gysType = new GysType();
		gysType.setName(gysTypeParams.getName());
		GysType type = gysTypeService.get(gysType);
		//如果供应商类型不为空，则返回提示名称重复
		if (type != null) {
			dataResult.setCode("3");
			dataResult.setMessage("类型名称重复,请重新输入!");
		} else {
			//如果供应商类型为空，则添加
			gysType.setDescription(gysTypeParams.getDescription());
			gysTypeService.save(gysType);
			dataResult.setCode("0");
			dataResult.setMessage("保存成功!");
		}
		result.setData(dataResult);
		return result;
	}

	@ApiOperation(value = "根据唯一标识获取供应商类型", httpMethod = "POST", notes = "根据唯一标识获取供应商类型")
	@PostMapping(value = "/getGysTypeById")
	public Response<GysType> getGysTypeById(
											@RequestParam(value = "id",required = true) String id){
		Response<GysType> result = new Response<>();

		if(id == null || "".equals(id)){
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		GysType type = gysTypeService.getById(id);
		result.setData(type);
		return result;
	}

	@ApiOperation(value = "编辑供应商类型", httpMethod = "POST", notes = "编辑供应商类型")
	@PostMapping(value = "/updateGysType")
	public Response<ResultMessage> updateGysType(
											  @RequestParam(value = "id",required = true) String id,
											  @RequestBody GysTypeParams gysTypeParams){

		Response<ResultMessage> result = new Response<ResultMessage>();
		ResultMessage dataResult = new ResultMessage();
		if(id == null || "".equals(id)){
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		GysType gysType = new GysType();
		gysType.setName(gysTypeParams.getName());
		//保证编辑的类型名称不能重复
		GysType type = gysTypeService.get(gysType);
		if(type!=null && !id.equals(type.getId())){
			dataResult.setCode("3");
			dataResult.setMessage("编辑名称重复!");
		}else{
			//插入描述
			gysType.setDescription(gysTypeParams.getDescription());
			boolean flag = gysTypeService.updateById(id, gysType);
			if(flag == true){
				dataResult.setCode("0");
				dataResult.setMessage("编辑成功!");
			}else{
				dataResult.setCode("-1");
				dataResult.setMessage("编辑失败!");
			}
		}
		result.setData(dataResult);
		return result;
	}

	@ApiOperation(value = "删除供应商类型", httpMethod = "POST", notes = "删除供应商类型")
	@PostMapping(value = "/deleteGysTypeById")
	public Response<ResultMessage> deleteGysTypeById(
													 @RequestParam(value = "id",required = true) String id){


		Response<ResultMessage> result = new Response<>();
		ResultMessage dataResult = new ResultMessage();
		if(id == null || "".equals(id)){
			throw new BusinessException(ReturnType.ParamIllegal,"参数错误!");
		}
		GysSupplier gysSupplier = new GysSupplier();
		gysSupplier.setTypeId(id);
		boolean flag = gysTypeService.deleteById(id);
		if(flag == true){
			dataResult.setCode("0");
			dataResult.setMessage("删除成功!");
		}else{
			dataResult.setCode("-1");
			dataResult.setMessage("删除失败!");
		}
		result.setData(dataResult);
		return result;
	}


}