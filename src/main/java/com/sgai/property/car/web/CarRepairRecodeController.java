package com.sgai.property.car.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.entity.CarRepairRecode;
import com.sgai.property.car.service.CarInfoServiceImpl;
import com.sgai.property.car.service.CarRepairRecodeServiceImpl;
import com.sgai.property.car.vo.CarRepairRecodeParam;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carRepairRecode")
@Api(description ="车辆维修记录信息")
public class CarRepairRecodeController extends BaseController {
    @Autowired
	private CarInfoServiceImpl carInfoService;
	@Autowired
	private CarRepairRecodeServiceImpl carRepairRecodeService;

	/**
	 * 分页查询所有的车辆维修记录信息

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "分页查询所有的车辆维修记录信息", httpMethod = "POST", notes = "分页查询所有的车辆维修记录信息")
	@PostMapping(value="/getCarRepairRecodePageList")
	public Response<Page<CarRepairRecode>> getCarRepairRecodePageList(String carId,int pageNum,int pageSize) {

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		CarRepairRecode carRepairRecode=new CarRepairRecode();
		carRepairRecode.setRrIsDelete(Constants.NO);
		if(StringUtils.isNotEmpty(carId)){
			carRepairRecode.setCarId(carId);
		}
		carRepairRecode.setComCode(comCode);
		carRepairRecode.setModuCode(moduCode);
		Page<CarRepairRecode> page=carRepairRecodeService.findListPage(carRepairRecode,pageNum,pageSize);
		Response<Page<CarRepairRecode>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 根据carId查询车辆维修信息

	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id查询车辆维修信息", httpMethod = "GET", notes = "getById")
	@GetMapping(value="/getCarRepairRecodeById")
	public Response<CarRepairRecode> getCarRepairRecodeById(String id) {
		Response<CarRepairRecode> result = new Response<>();
		result.setData(carRepairRecodeService.getById(id));
		return result;
	}

	/**
	 * 增加维修记录信息

	 * @param carRepairRecodeParam
	 * @return
	 */

	@ApiOperation(value = "增加维修记录信息", httpMethod = "POST", notes = "add")
	@PostMapping(value = "/addCarRepairRecode")
	public Response<CarRepairRecode> addCarRepairRecode(
														@RequestBody CarRepairRecodeParam carRepairRecodeParam){
        Response<CarRepairRecode> result = new Response<>();

		CarRepairRecode carRepairRecodeTarget=new CarRepairRecode();
		carRepairRecodeTarget.setRrDate(System.currentTimeMillis());
		carRepairRecodeTarget.setRrIsDelete(Constants.NO);
		BeanUtils.copyProperties(carRepairRecodeParam,carRepairRecodeTarget);

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		carRepairRecodeTarget.setModuCode(moduCode);
		carRepairRecodeTarget.setComCode(comCode);
		carRepairRecodeService.save(carRepairRecodeTarget);
        result.setData(carRepairRecodeTarget);
        return result;
    }

}