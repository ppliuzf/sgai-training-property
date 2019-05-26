package com.sgai.property.car.web;

import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarInfoDao;
import com.sgai.property.car.dao.ICarUserRelationInfoDao;
import com.sgai.property.car.entity.CarInfo;
import com.sgai.property.car.entity.CarUserRelationInfo;
import com.sgai.property.car.service.CarInfoServiceImpl;
import com.sgai.property.car.service.CarUserRelationInfoServiceImpl;
import com.sgai.property.car.vo.CarInfoParam;
import com.sgai.property.car.vo.CarUserRelationInfoVo;
import com.sgai.property.car.vo.MaintainReocrdVo;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.StringUtil;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/carInfo")
@Api(description ="车辆信息")
public class CarInfoController extends BaseController {
	private static final Logger logger = LogManager.getLogger(CarInfoController.class);

    @Autowired
	private CarInfoServiceImpl carInfoService;
	@Autowired
	private CarUserRelationInfoServiceImpl carUserRelationInfoService;
    @Autowired
	private ICarInfoDao carInfoDao;
    @Autowired
	private ICarUserRelationInfoDao carUserRelationInfoDao;
	/**
	 * 分页查询所有车辆信息

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "分页查询所有的车辆信息列表", httpMethod = "POST", notes = "分页查询所有的车辆信息列表")
	@PostMapping(value="/getCarInfoPageList")
	public Response<Page<CarInfo>> getCarInfoPageList(
													  String ciNumber, String ciTypeId, Long ciStatus, int pageNum, int pageSize) {
		CarInfo carInfo=new CarInfo();

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		carInfo.setCiIsDelete(Constants.NO);
		if(StringUtils.isNotEmpty(ciNumber)){
			carInfo.setCiNumber(StringUtil.fuzzySearchStr(ciNumber));
		}
		if(StringUtils.isNotBlank(ciTypeId)){
			carInfo.setCiTypeId(ciTypeId);
		}
		if(null != ciStatus && ciStatus != -1){
			carInfo.setCiStatus(ciStatus);
		}
		carInfo.setModuCode(moduCode);
		carInfo.setComCode(comCode);
		Page<CarInfo> page = null;
		try {
			page = carInfoService.findListPage(carInfo,pageNum,pageSize);
		} catch (Exception e) {

			e.printStackTrace();
		}
		Response<Page<CarInfo>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 分页查询客户下的车辆信息列表

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "分页查询客户下的车辆信息列表(供用户使用)", httpMethod = "POST", notes = "分页查询客户下的车辆信息列表(供用户使用)")
	@PostMapping(value="/getCarInfoList")
	public Response<Page<CarInfo>> getCarInfoList(@RequestParam(value = "ciOwnerName",required = true)String ciOwnerName,Long ciOwnerPhone1,Long ciOwnerPhone2,@RequestParam(value = "pageNum",required = true)Integer pageNum,@RequestParam(value = "pageSize",required = true)Integer pageSize) {
		CarInfo carInfo=new CarInfo();

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		if(ciOwnerPhone1 == null && ciOwnerPhone2 == null){
			throw new BusinessException(ReturnType.Error, "手机号不能为空！");
		}
		if(StringUtils.isEmpty(ciOwnerName)){
			throw new BusinessException(ReturnType.Error, "姓名不能为空！");
		}
		carInfo.setCiOwnerName(ciOwnerName);
		List<Long> ciOwnerPhones = new ArrayList<>();
		if(ciOwnerPhone1 != null){
			ciOwnerPhones.add(ciOwnerPhone1);
		}
		if(ciOwnerPhone2 != null){
			ciOwnerPhones.add(ciOwnerPhone2);
		}
		carInfo.setCiOwnerPhones(ciOwnerPhones);
		carInfo.setComCode(comCode);
		carInfo.setModuCode(moduCode);
		Page<CarInfo> page=carInfoService.findByCustomer(carInfo,pageNum,pageSize);
		Response<Page<CarInfo>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 根据carId查询车辆信息

	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据carId查询车辆信息详情", httpMethod = "GET", notes = "根据carId查询车辆信息详情")
	@GetMapping(value="/getCarInfoById")
	public Response<CarInfo> getCarInfoById(String id) {
		Response<CarInfo> result = new Response<>();
		result.setData(carInfoService.getById(id));
		return result;
	}

	/**
	 * 增加车辆信息

	 * @param carInfo
	 * @return
	 */
	@ApiOperation(value = "新建车辆信息", httpMethod = "POST", notes = "新建车辆信息")
	@PostMapping(value = "/addCarInfo")
	public Response<CarInfo> addCarInfo(@RequestBody CarInfoParam carInfo){
        Response<CarInfo> result = new Response<>();
        CarInfo carInfoTarget=new CarInfo();
		carInfoTarget.setCiIsDelete(Constants.NO);
		carInfoTarget.setCiStatus(Constants.carStatus.USE);
		BeanUtils.copyProperties(carInfo,carInfoTarget);

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		carInfoTarget.setComCode(comCode);
		carInfoTarget.setModuCode(moduCode);
		carInfoService.addCarInfo(carInfoTarget);
        result.setData(carInfoTarget);
        return result;
    }

	/**
	 * 修改车辆信息

	 * @param carInfo
	 * @return
	 */
	@ApiOperation(value = "修改车辆信息", httpMethod = "POST", notes = "修改车辆信息")
	@PostMapping(value = "/updateCarInfoById")
	public Response<Object> updateCarInfoById(@RequestBody CarInfoParam carInfo){
		Response<Object> result = new Response<>();
       	if(StringUtils.isEmpty(carInfo.getId())){
			BusinessException exception=new BusinessException(ReturnType.ParamIllegal,"id不能为空");
			result.setData(exception);
			return result;
		}
		CarInfo carInfoTarget = new CarInfo();
       	BeanUtils.copyProperties(carInfo,carInfoTarget);
		carInfoTarget.setLastJourneyAmount(0L);
		carInfoService.updateById(carInfoTarget);
		result.setData(carInfoTarget);
		return result;
	}

	/**
	 * 根据车辆ID获取车辆预约人信息

	 * @param cardId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name="cardId",value = "车辆ID",dataType = "String",paramType = "query",required =true)
	})
	@ApiOperation(value = "根据车辆ID获取车辆预约人信息", httpMethod = "POST", notes = "根据车辆ID获取车辆预约人信息")
	@PostMapping(value="/getCarUserRelationByCardId")
	public Response<Page<CarUserRelationInfoVo>> getCarUserRelationByCardId( String cardId, int pageNum, int pageSize) {

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		CarUserRelationInfo carUserRelationInfo=new CarUserRelationInfo();
		carUserRelationInfo.setRiCarId(cardId);
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setComCode(comCode);
		carUserRelationInfo.setModuCode(moduCode);
		Page<CarUserRelationInfo> page=carUserRelationInfoService.findListNew(carUserRelationInfo,pageNum,pageSize);
		List<CarUserRelationInfo> infoList=page.getList();
		CarUserRelationInfoVo carUserRelationInfoVo=null;
		List<CarUserRelationInfoVo> voList=Lists.newArrayList();
		for (CarUserRelationInfo userRelationInfo : infoList) {
			carUserRelationInfoVo=new CarUserRelationInfoVo();
			BeanUtils.copyProperties(userRelationInfo,carUserRelationInfoVo);
			voList.add(carUserRelationInfoVo);
		}
		Page<CarUserRelationInfoVo> resultPage=new Page<>();
		BeanUtils.copyProperties(page,resultPage);
		resultPage.setList(voList);
		Response<Page<CarUserRelationInfoVo>> result = new Response<>();
		result.setData(resultPage);
		return result;
	}

	/**
	 * 根据车辆ID获取车辆保养记录

	 * @param cardId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name="cardId",value = "车辆ID",dataType = "String",paramType = "query",required =true)
	})
	@ApiOperation(value = "根据车辆ID获取车辆保养记录", httpMethod = "POST", notes = "根据车辆ID获取车辆保养记录")
	@PostMapping(value="/getMaintainReocrd")
	public Response<Page<MaintainReocrdVo>> getMaintainReocrd( String cardId, int pageNum, int pageSize) {
		MaintainReocrdVo maintainReocrdVo=new MaintainReocrdVo();
		maintainReocrdVo.setMrDate(1514194361L);
		maintainReocrdVo.setMrName("基础保养");
		maintainReocrdVo.setMrPerson("张三");
		maintainReocrdVo.setMrReMark("换机油机滤");
		maintainReocrdVo.setMrType("小保养");
		maintainReocrdVo.setMrUnit("首都汽车有限公司");
		List<MaintainReocrdVo> reocrdVoList= Lists.newArrayList();
		reocrdVoList.add(maintainReocrdVo);
		Page<MaintainReocrdVo> page=new Page<>(pageNum,pageSize);
		page.setList(reocrdVoList);
		Response<Page<MaintainReocrdVo>> result = new Response<>();
		result.setData(page);
		return result;
	}
	/**
	 * 根据id删除车辆信息

	 * @param id
	 * @return
	 */
	@ApiOperation(value = "deleteCarInfoById", httpMethod = "POST", notes = "deleteById")
	@PostMapping(value = "/deleteCarInfoById")
	@Transactional(rollbackFor = RuntimeException.class)
    public Response<Object> deleteCarInfoById(String id){
        Response<Object> result = new Response<>();
		if(StringUtils.isEmpty(id)){
			BusinessException exception=new BusinessException(ReturnType.ParamIllegal,"id不能为空");
			result.setData(exception);
			return result;
		}
		CarUserRelationInfo carUserRelationInfo = carUserRelationInfoDao.getRelationInfoByCarId(id);
		if(null != carUserRelationInfo){
			if(null != carUserRelationInfo.getRiAuditStatus() &&
					("1".equals(carUserRelationInfo.getRiAuditStatus().toString())
							|| "2".equals(carUserRelationInfo.getRiAuditStatus().toString()))
							|| System.currentTimeMillis() <= carUserRelationInfo.getRiUseEnd()){
				BusinessException exception=new BusinessException(ReturnType.ParamIllegal,"已经被预约中的车辆无法删除");
				result.setData(exception);
				result.setCode("20");
				result.setMessage("已经被预约中的车辆无法删除");
				return result;
			}
		}
		CarInfo carInfo = new CarInfo();
		carInfo.setId(id);
		carInfo.setCiIsDelete(1L);
        result.setData(carInfoService.updateById(carInfo));
        return result;
	}
	/**
	 * 根据id修改车辆状态(0:启用 1:停用)

	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据id修改车辆状态(0:启用 1:停用)", httpMethod = "POST", notes = "根据id修改车辆状态(0:启用 1:停用)")
	@PostMapping(value = "/updateCarStatus")
	public Response<Object> updateCarStatus(String id,Long ciStatus){
		Response<Object> result = new Response<>();
		if(StringUtils.isEmpty(id)){
			BusinessException exception=new BusinessException(ReturnType.ParamIllegal,"id不能为空");
			result.setData(exception);
			return result;
		}
		CarInfo carInfo = new CarInfo();
		carInfo.setCiStatus(ciStatus);
		carInfo.setId(id);
		carInfoService.updateById(carInfo);
		result.setData(carInfo);
		return result;
	}

	@ApiOperation(value = "根据carId,获取车辆行驶里程数", httpMethod = "POST", notes = "获取车辆行驶里程数")
	@PostMapping(value = "/getCarJourneyAmount")
	public Response<Long> getCarJourneyAmount(
											  @RequestParam("carId") String carId) {
		Response<Long> result = new Response<>();
		if(null != carId){
			CarInfo carInfo = carInfoDao.getById(carId);
			if(null != carInfo){
				result.setData(carInfo.getJourneyAmount() == null ? 0L : carInfo.getJourneyAmount());
			}
		}
		return result;
	}
}