package com.sgai.property.car.web;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.entity.TypeInfo;
import com.sgai.property.car.service.TypeInfoServiceImpl;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/typeInfo")
@Api(description ="车辆类型")
public class TypeInfoController extends BaseController {
    @Autowired
	private TypeInfoServiceImpl typeInfoService;

  
	@PostMapping(value="/typeInfoPageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<TypeInfo>> PageList(int pageNum,int pageSize) {
		TypeInfo typeInfo=new TypeInfo();

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		typeInfo.setCtIsDelete(Constants.NO);
		//typeInfo.setComCode("bailu");
		//typeInfo.setModuCode(moduCode);
        Page<TypeInfo> page=typeInfoService.findListPage(typeInfo,pageNum,pageSize);
        Response<Page<TypeInfo>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/typeInfoList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<TypeInfo>> list() {
		TypeInfo typeInfo=new TypeInfo();

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		typeInfo.setCtIsDelete(Constants.NO);
		typeInfo.setModuCode(moduCode);
		typeInfo.setComCode(comCode);
		Page<TypeInfo> page=typeInfoService.findListPage(typeInfo,Constants.carGearBoxType.PAGENUM,Integer.MAX_VALUE);
		Response<List<TypeInfo>> result = new Response<>();
		result.setData(page.getList());
		return result;
	}

	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<TypeInfo> getById(String id) {
		Response<TypeInfo> result = new Response<>();
		result.setData(typeInfoService.getById(id));
		return result;
	}


	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
	@PostMapping(value = "/save")
	public Response<TypeInfo> save(@RequestBody TypeInfo typeInfo){
        Response<TypeInfo> result = new Response<>();

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		typeInfo.setComCode(comCode);
		typeInfo.setModuCode(moduCode);
		typeInfo.setUpdatedDt(new Date());
		typeInfo.setCreatedDt(new Date());
		typeInfo.setCtIsDelete(Constants.NO);
        typeInfoService.save(typeInfo);
        result.setData(typeInfo);
        return result;
    }
//
//	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
//	@PostMapping(value = "/updateById")
//	public Response<TypeInfo> updateById(@RequestBody TypeInfo typeInfo){
//        Response<TypeInfo> result = new Response<>();
//		typeInfoService.updateById(typeInfo);
//		result.setData(typeInfo);
//		return result;
//	 }
//
//	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
//	@PostMapping(value = "/deleteById")
//    public Response<Boolean> deleteById(String id){
//        Response<Boolean> result = new Response<>();
//        result.setData(typeInfoService.deleteById(id));
//        return result;
//	}

}