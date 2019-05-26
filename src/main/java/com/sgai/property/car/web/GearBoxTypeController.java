package com.sgai.property.car.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.entity.GearBoxType;
import com.sgai.property.car.service.GearBoxTypeServiceImpl;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gearBoxType")
@Api(description ="变速箱类型")
public class GearBoxTypeController extends BaseController {
    @Autowired
	private GearBoxTypeServiceImpl gearBoxTypeService;

  
	@PostMapping(value="/gearBoxTypePageList")
    @ApiOperation(value = "分页查询列表", httpMethod = "POST", notes = "分页查询列表")
	public Response<Page<GearBoxType>> PageList(int pageNum,int pageSize) {
		GearBoxType gearBoxType=new GearBoxType();
		gearBoxType.setBtIsDelete(Constants.NO);
		Page<GearBoxType> page=gearBoxTypeService.findListPage(gearBoxType,pageNum,pageSize);
        Response<Page<GearBoxType>> result = new Response<>();
        result.setData(page);
		return result;
	}

 
	@PostMapping(value="/gearBoxTypeList")
	@ApiOperation(value = "查询列表", httpMethod = "POST", notes = "查询列表")
	public Response<List<GearBoxType>> list() {
		GearBoxType gearBoxType=new GearBoxType();
		gearBoxType.setBtIsDelete(Constants.NO);
		Page<GearBoxType> page=gearBoxTypeService.findListPage(gearBoxType,Constants.carGearBoxType.PAGENUM,Integer.MAX_VALUE);
		Response<List<GearBoxType>> result = new Response<>();
		result.setData(page.getList());
		return result;
	}

//	@PostMapping(value="/getGearBoxType")
//	@ApiOperation(value = "查询单个对象", httpMethod = "POST", notes = "查询单个对象")
//    public Response<GearBoxType> getGearBoxType(@RequestBody GearBoxType gearBoxType){
//		Response<GearBoxType> result = new Response<>();
//		result.setData(gearBoxTypeService.get(gearBoxType));
//		return result;
//    }



	@GetMapping(value="/getById")
	@ApiOperation(value = "getById", httpMethod = "GET", notes = "getById")
	public Response<GearBoxType> getById(String id) {
		Response<GearBoxType> result = new Response<>();
		result.setData(gearBoxTypeService.getById(id));
		return result;
	}
//
//
//	@ApiOperation(value = "save", httpMethod = "POST", notes = "保存对象")
//	@PostMapping(value = "/save")
//	public Response<GearBoxType> save(@RequestBody GearBoxType gearBoxType){
//        Response<GearBoxType> result = new Response<>();
//        gearBoxTypeService.save(gearBoxType);
//        result.setData(gearBoxType);
//        return result;
//    }
//
//	@ApiOperation(value = "updateById", httpMethod = "POST", notes = "更新对象")
//	@PostMapping(value = "/updateById")
//	public Response<GearBoxType> updateById(@RequestBody GearBoxType gearBoxType){
//        Response<GearBoxType> result = new Response<>();
//		gearBoxTypeService.updateById(gearBoxType);
//		result.setData(gearBoxType);
//		return result;
//	 }
//
//	@ApiOperation(value = "deleteById", httpMethod = "POST", notes = "deleteById")
//	@PostMapping(value = "/deleteById")
//    public Response<Boolean> deleteById(String id){
//        Response<Boolean> result = new Response<>();
//        result.setData(gearBoxTypeService.deleteById(id));
//        return result;
//	}

}