package com.sgai.property.car.web;

import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.car.constants.Constants;
import com.sgai.property.car.dao.ICarInfoDao;
import com.sgai.property.car.entity.CarInfo;
import com.sgai.property.car.entity.CarUserRelationInfo;
import com.sgai.property.car.service.CarInfoServiceImpl;
import com.sgai.property.car.service.CarMaintainInfoServiceImpl;
import com.sgai.property.car.service.CarPunishInfoServiceImpl;
import com.sgai.property.car.service.CarUserRelationInfoServiceImpl;
import com.sgai.property.car.vo.*;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.StringUtil;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/carUserRelationInfo")
@Api(description ="车辆预约信息")
public class CarUserRelationInfoController extends BaseController {
	private static final Logger logger = LogManager.getLogger(CarUserRelationInfoController.class);

	@Autowired
	private CarUserRelationInfoServiceImpl carUserRelationInfoService;
	@Autowired
	private CarInfoServiceImpl carInfoService;
	@Autowired
	private CarMaintainInfoServiceImpl  carMaintainInfoService;
	@Autowired
	private CarPunishInfoServiceImpl carPunishInfoService;
	@Autowired
	private ICarInfoDao carInfoDao;


	/**
	 * 查询预约时间段下的可预约车辆信息
	 *

	 * @param carInfoParam
	 * @return
	 */
	@ApiOperation(value = "分页查询预约时间段下的可预约车辆信息(web端调)", httpMethod = "POST", notes = "分页查询预约时间段下的可预约车辆信息(web端调)")
	@PostMapping(value = "/getReservableCarInfoList")
	public Response<Page<CarInfo>> getReservableCarInfoList(
															   @RequestBody MyCarInfoParam carInfoParam,int pageNum, int pageSize) {

		Response<Page<CarInfo>> result = new Response<>();
		try {
			result.setData(carInfoService.findListForCar(carInfoParam,pageNum,pageSize));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 分页查询登录管理员(申请人)下的车辆预约信息
	 *

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "当前页", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页展示条数", required = true, paramType = "query", dataType = "Integer")

	})
	@ApiOperation(value = "我的预约 - PC 【分页查询申请人下的车辆预约信息列表】", httpMethod = "POST", notes = "我的预约 - PC 【分页查询申请人下的车辆预约信息列表(PC 端调此接口)】")
	@PostMapping(value = "/getPageListByApplyId")
	public Response<Page<CarUserRelationInfoVo>> getPageListByApplyId(
																	  @RequestBody CarRelationInfoParam carRelationInfoParam,
																	  int pageNum, int pageSize) {
		

		CarUserRelationInfoVo carUserRelationInfo = new CarUserRelationInfoVo();
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
		Page<CarUserRelationInfoVo> page = carUserRelationInfoService.findListInnerCarInfo(carUserRelationInfo,carRelationInfoParam, pageNum, pageSize);

		Response<Page<CarUserRelationInfoVo>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 分页查询登录管理员(申请人)下的车辆预约信息APP端
	 *

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "当前页", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页展示条数", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "riAuditStatus", value = "审核状态(1 已提交 2 已通过 3已拒绝 4已归还 5归还中)", required = false, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "riIsDelete", value = "已取消 (riIsDelete：1)", required = false, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "condition", value = "搜索条件(APP端的搜索)", required = false, paramType = "query", dataType = "String")

	})
	@ApiOperation(value = "我的预约【分页查询申请人下的车辆预约信息列表(APP端调此接口)】", httpMethod = "POST", notes = "我的预约【分页查询申请人下的车辆预约信息列表(APP端调此接口)】")
	@PostMapping(value = "/getPageListByApplyIdForApp")
	public Response<Page<CarUserRelationInfoVo>> getPageListByApplyIdForApp( int pageNum, int pageSize, Long riAuditStatus, Long riIsDelete,String condition) {

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		CarUserRelationInfoVo carUserRelationInfo = new CarUserRelationInfoVo();
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		if (riAuditStatus != null) {
			carUserRelationInfo.setRiAuditStatus(riAuditStatus);
		}
		if (riIsDelete != null) {
			carUserRelationInfo.setRiIsDelete(riIsDelete);
		}
		if(StringUtils.isNotEmpty(condition)){
			carUserRelationInfo.setCondition(StringUtil.fuzzySearchStr(condition));
		}
		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
		carUserRelationInfo.setModuCode(moduCode);
		carUserRelationInfo.setComCode(comCode);

		Page<CarUserRelationInfoVo> page = carUserRelationInfoService.findListInnerCarInfoForAPP(carUserRelationInfo, pageNum, pageSize);
		Response<Page<CarUserRelationInfoVo>> result = new Response<>();
		result.setData(page);
		return result;
	}
	/**
	 * 取消预约车辆信息
	 *

	 * @param id
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "取消预约车辆信息(WEB or APP 端 均可调)", httpMethod = "POST", notes = "updateById")
	@PostMapping(value = "/cancelCarUserRelationInfoById")
	public Response<Object> cancelCarUserRelationInfoById(HttpServletRequest request, String id) {
		Response<Object> result = new Response<>();
		if (StringUtils.isEmpty(id)) {
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "id不能为空"));
		}
		CarUserRelationInfo carUserRelationInfoNew =carUserRelationInfoService.getById(id);
		if(carUserRelationInfoNew !=null && Constants.AUDIT_STATUS_2.equals(carUserRelationInfoNew.getRiAuditStatus())){
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "审核通过后不能取消预约！"));
		}
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		carUserRelationInfo.setRiIsDelete(Constants.YES);
		carUserRelationInfo.setId(id);
		carUserRelationInfoService.updateById(carUserRelationInfo);
		result.setData(carUserRelationInfo);
		return result;
	}

	/**
	 * 归还车辆
	 *
	 * @param request 返回值

	 * @param id 车辆ID
	 * @param riUseEnd 归还时间
	 * @param endJourney 结束行程公里数
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "riUseEnd", value = "预计结束时间(时间戳类型)", required = true, paramType = "query", dataType = "Long")
	})
	@ApiOperation(value = "归还车辆", httpMethod = "POST", notes = "updateById")
	@PostMapping(value = "/returnCar")
	public Response<Object> returnCar(HttpServletRequest request,
                                      String id, Long riUseEnd, Long endJourney) {
		Response<Object> result = new Response<>();
		CarUserRelationInfo carRelationInfo = carUserRelationInfoService.getById(id);
		if(carRelationInfo !=null){
			if(Constants.AUDIT_STATUS_4.equals(carRelationInfo.getRiAuditStatus())
                    || Constants.AUDIT_STATUS_5.equals(carRelationInfo.getRiAuditStatus())){
				return expHandler(new BusinessException(ReturnType.ParamIllegal, "车辆已被归还！"));
			}
		}
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		carUserRelationInfo.setRiUseEnd(riUseEnd);
		carUserRelationInfo.setId(id);
		carUserRelationInfo.setEndJourney(endJourney);
		if(riUseEnd >= System.currentTimeMillis()){
			carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_5);
		}else{
			carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
		}
		if(null != carRelationInfo.getRiCarId()){
			CarInfo oldCarInfo = carInfoDao.getById(carRelationInfo.getRiCarId());
			CarInfo car = new CarInfo();
			car.setId(oldCarInfo.getId());
			car.setJourneyAmount(endJourney);
			car.setLastJourneyAmount(oldCarInfo.getJourneyAmount());

			//预约记录上一次使用时的里程数
            carUserRelationInfo.setLastJourney(oldCarInfo.getJourneyAmount());

			carInfoDao.updateById(car);
		}
		carUserRelationInfoService.updateById(carUserRelationInfo);
		result.setData(carUserRelationInfo);
		return result;
	}


	/**
	 * 增加车辆预约信息
	 *

	 * @param carUserRelationInfoParam
	 * @return
	 */
	@ApiOperation(value = "新建车辆预约信息", httpMethod = "POST", notes = "新建车辆预约信息")
	@PostMapping(value = "/addCarUserRelationInfo")
	public Response<Object> addCarUserRelationInfo(HttpServletRequest request, @RequestBody CarUserRelationInfoParam carUserRelationInfoParam) {
		//预约人 申请人

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		Response<Object> result = new Response<>();
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		BeanUtils.copyProperties(carUserRelationInfoParam, carUserRelationInfo);
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setRiAuditStatus(Constants.PASSING);
		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());

		//同一车辆 同一时间 只能被预约一次
		CarUserRelationInfo carUserRelationInfoNew=new CarUserRelationInfo();
		carUserRelationInfoNew.setRiCarId(carUserRelationInfo.getRiCarId());
		carUserRelationInfoNew.setRiUseStart(carUserRelationInfo.getRiUseStart());
		if(StringUtils.isNotEmpty(carUserRelationInfo.getRiUseTimes())){
			carUserRelationInfoNew.setRiUseEnd(carUserRelationInfo.getRiUseStart()+tranferSeconds(carUserRelationInfo.getRiUseTimes()));
		}
		List<CarUserRelationInfo> isRelation = carUserRelationInfoService.findRelation(carUserRelationInfoNew);

		if (isRelation != null && isRelation.size() > 0) {
			return expHandler(new BusinessException(ReturnType.Error, "车辆在此时间段内已被预约！"));

		}
		carUserRelationInfo.setModuCode(moduCode);
		carUserRelationInfo.setComCode(comCode);
		carUserRelationInfoService.addCarUserRelationInfo(carUserRelationInfo);//新建车辆预约信息
		result.setData(carUserRelationInfo);
		return result;
	}
	//===============我的审核=========================================================

	/**
	 * 分页查询登录管理员(申请人)下的审核车辆信息
	 *

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "当前页", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页展示条数", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "ciNumber", value = "车牌号", required = false, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "分页查询登录管理员(申请人)下的审核车辆信息(pc端)", httpMethod = "POST", notes = "分页查询登录管理员(申请人)下的审核车辆信息(pc端)")
	@PostMapping(value = "/findListPageByAuditStatus")
	public Response<Page<CarUserRelationInfoVo>> findListPageByAuditStatus( int pageNum, int pageSize, String ciNumber) {
		

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		CarUserRelationInfoVo carUserRelationInfo = new CarUserRelationInfoVo();
		if (StringUtils.isNotEmpty(ciNumber)) {
			carUserRelationInfo.setCiNumber(StringUtil.fuzzySearchStr(ciNumber));
		}
		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setComCode(comCode);
		carUserRelationInfo.setModuCode(moduCode);
		Page<CarUserRelationInfoVo> page = carUserRelationInfoService.findListPageByAuditStatus(carUserRelationInfo, pageNum, pageSize);
		Response<Page<CarUserRelationInfoVo>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 审核车辆预约信息
	 *

	 * @param id
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "riAuditStatus", value = "审核状态(2 审核通过 3审核不通过)", required = true, paramType = "query", dataType = "Long")
	})
	@ApiOperation(value = "审核车辆预约信息(通过or拒绝)", httpMethod = "POST", notes = "审核车辆预约信息(通过or拒绝)")
	@PostMapping(value = "/auditCarUserRelationInfoById")
	public Response<Object> auditCarUserRelationInfoById(HttpServletRequest request, String id, Long riAuditStatus) {
		

		Response<Object> result = new Response<>();
		if (StringUtils.isEmpty(id)) {
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "id不能为空"));
		}
		CarUserRelationInfo carUserRelationInfoNew =carUserRelationInfoService.getById(id);
		if(carUserRelationInfoNew !=null && Constants.YES.equals(carUserRelationInfoNew.getRiIsDelete())){
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "该预约已取消！"));
		}
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		carUserRelationInfo.setId(id);
		carUserRelationInfo.setRiAuditStatus(riAuditStatus);
		carUserRelationInfo.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());//审核人id
		carUserRelationInfo.setRiAuditName(UserServletContext.getUserInfo().getUserName());
		carUserRelationInfo.setUpdatedDt(new Date());
		carUserRelationInfo.setRiAuditTime(System.currentTimeMillis());
		carUserRelationInfoService.updateById(carUserRelationInfo);
		result.setData(carUserRelationInfo);
		return result;
	}

	/**
	 * 我的预约详情 和 我的审核详情
	 * 根据id查询车辆预约信息详情
	 *

	 * @param id
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "车辆预约信息详情(注：我的预约详情 和 我的审核详情 都掉此接口。web/app 端都调此接口)", httpMethod = "GET", notes = "车辆预约信息详情")
	@GetMapping(value = "/getCarUserRelationInfoById")
	public Response<Object> getCarUserRelationInfoById(String id) {
		

		Response<Object> result = new Response<>();
		if (StringUtils.isEmpty(id)) {
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "id不能为空"));
		}
		CarUserRelationInfoVo carUserRelationInfoVo = carUserRelationInfoService.detailCarUserRelationInfo(id);
		if(carUserRelationInfoVo.getRiUseTimes() == null){
			carUserRelationInfoVo.setRiUseTimes("");
		}
		carUserRelationInfoVo.setCreatedDtLong(carUserRelationInfoVo.getCreatedDt().getTime());
		CarAuditRelationInfoVo carAuditRelationInfoVo = new CarAuditRelationInfoVo();
		CarAuditRelationInfoVo carAuditRelationInfoVo1 = new CarAuditRelationInfoVo();
		if(Constants.AUDIT_STATUS_1.equals(carUserRelationInfoVo.getRiAuditStatus())){
			/*假数据*/
			//申请者 1 已提交(审核中) 2 已通过 3已拒绝)
			carAuditRelationInfoVo.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo.setDeptName(UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo.setStatus(Constants.myCarUserRelationInfo.AUDIT_STATUS_0);
			carAuditRelationInfoVo.setTime(carUserRelationInfoVo.getCreatedDt().getTime());
			carAuditRelationInfoVo.setEnd_journey(carUserRelationInfoVo.getEndJourney());
			//审核者
			carAuditRelationInfoVo1.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo1.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo1.setDeptName(UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo1.setStatus(Constants.myCarUserRelationInfo.AUDIT_STATUS_1);
			carAuditRelationInfoVo1.setTime(System.currentTimeMillis());
		}else if(Constants.AUDIT_STATUS_2.equals(carUserRelationInfoVo.getRiAuditStatus()) || Constants.AUDIT_STATUS_3.equals(carUserRelationInfoVo.getRiAuditStatus())){
			//申请者  提交预约0 审核中1  审核拒绝3 审核通过2
			carAuditRelationInfoVo.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo.setDeptName(UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo.setStatus(Constants.myCarUserRelationInfo.AUDIT_STATUS_0);
			carAuditRelationInfoVo.setTime(carUserRelationInfoVo.getCreatedDt().getTime());
			carAuditRelationInfoVo.setEnd_journey(carUserRelationInfoVo.getEndJourney());
			//审核者
			carAuditRelationInfoVo1.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo1.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo1.setDeptName(UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo1.setStatus(carUserRelationInfoVo.getRiAuditStatus());
			carAuditRelationInfoVo1.setTime(carUserRelationInfoVo.getRiAuditTime());
		}else if(Constants.AUDIT_STATUS_4.equals(carUserRelationInfoVo.getRiAuditStatus()) || Constants.AUDIT_STATUS_5.equals(carUserRelationInfoVo.getRiAuditStatus())){
			//申请者  提交预约0 审核中1  审核拒绝3 审核通过2
			carAuditRelationInfoVo.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo.setDeptName(UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getComName() + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo.setStatus(Constants.myCarUserRelationInfo.AUDIT_STATUS_0);
			carAuditRelationInfoVo.setTime(carUserRelationInfoVo.getCreatedDt().getTime());
			carAuditRelationInfoVo.setEnd_journey(carUserRelationInfoVo.getEndJourney());
			//审核者
			carAuditRelationInfoVo1.setRiAuditName(UserServletContext.getUserInfo().getUserName());
			carAuditRelationInfoVo1.setRiAuditName1(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
			carAuditRelationInfoVo1.setDeptName(UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getComName() + "/" + UserServletContext.getUserInfo().getPositionName());
			carAuditRelationInfoVo1.setStatus(Constants.myCarUserRelationInfo.AUDIT_STATUS_2);
			carAuditRelationInfoVo1.setTime(carUserRelationInfoVo.getRiAuditTime());
		}
		Map<String,Object> map=new HashMap<>(4);
		List<CarAuditRelationInfoVo> list = new ArrayList<>(4);
		list.add(carAuditRelationInfoVo);
		list.add(carAuditRelationInfoVo1);
		map.put("carUserRelationInfoList",carUserRelationInfoVo);
		map.put("carAuditRelationInfoVoList",list);
		result.setData(map);
		return result;
	}


	/**
	 * 增加车辆预约信息APP端
	 *

	 * @param carUserRelationInfoParam
	 * @return
	 */
	@ApiOperation(value = "新建车辆预约信息APP端", httpMethod = "POST", notes = "新建车辆预约信息APP端")
	@PostMapping(value = "/addCarUserRelationInfoForAPP")
	public Response<Object> addCarUserRelationInfoForAPP( @RequestBody CarUserRelationInfoParam2 carUserRelationInfoParam) {

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		Response<Object> result = new Response<>();
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		BeanUtils.copyProperties(carUserRelationInfoParam, carUserRelationInfo);

		carUserRelationInfo.setRiAuditStatus(Constants.PASSING);
		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setRiApplyName(UserServletContext.getUserInfo().getUserName());
		carUserRelationInfo.setRiUserName(UserServletContext.getUserInfo().getUserName());
		//carUserRelationInfo.setRiUserPhone("0");

		//同一车辆 同一时间 只能被预约一次
		CarUserRelationInfo carUserRelationInfoNew=new CarUserRelationInfo();
		carUserRelationInfoNew.setRiCarId(carUserRelationInfo.getRiCarId());
		carUserRelationInfoNew.setRiUseStart(carUserRelationInfo.getRiUseStart());
		if(StringUtils.isNotEmpty(carUserRelationInfo.getRiUseTimes())){
			carUserRelationInfoNew.setRiUseEnd(carUserRelationInfo.getRiUseStart()+tranferSeconds(carUserRelationInfo.getRiUseTimes()));
		}
		carUserRelationInfoNew.setModuCode(moduCode);
		carUserRelationInfoNew.setComCode(comCode);

		List<CarUserRelationInfo> isRelation = carUserRelationInfoService.findRelation(carUserRelationInfoNew);
		if (isRelation != null && isRelation.size() > 0) {
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "车辆在此时间段内已被预约！"));
		}
		carUserRelationInfo.setComCode(comCode);
		carUserRelationInfo.setModuCode(moduCode);
		carUserRelationInfoService.addCarUserRelationInfoForApp(carUserRelationInfo);//新建车辆预约信息
		result.setData(carUserRelationInfo);
		return result;
	}

	/**
	 * 分页查询登录管理员(申请人)下的审核车辆信息 app端
	 *

	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "当前页", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页展示条数", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "condition", value = "搜索框", required = false, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "ciNumber", value = "车牌号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "riAuditStatus", value = "审核状态(0:未审核 1：已审核)", required = false, paramType = "query", dataType = "Long")
	})
	@ApiOperation(value = "我的审核【分页查询登录管理员(申请人)下的审核车辆信息(APP端)】", httpMethod = "POST", notes = "我的审核【分页查询登录管理员(申请人)下的审核车辆信息(APP端)】")
	@PostMapping(value = "/findListPageByAuditStatusForApp")
	public Response<Page<CarUserRelationInfoVo>> findListPageByAuditStatusForApp( int pageNum, int pageSize, Long riAuditStatus,String condition) {
 		CarUserRelationInfoVo carUserRelationInfo = new CarUserRelationInfoVo();

		String moduCode = UserServletContext.getUserInfo().getModuCode();
		String comCode = UserServletContext.getUserInfo().getComCode();
		if (riAuditStatus != null) {
			carUserRelationInfo.setRiAuditStatus(riAuditStatus);
		}
		if(StringUtils.isNotEmpty(condition)){
			carUserRelationInfo.setCondition(StringUtil.fuzzySearchStr(condition));
		}
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setModuCode(moduCode);
		carUserRelationInfo.setComCode(comCode);
		Page<CarUserRelationInfoVo> page = carUserRelationInfoService.findListPageByAuditStatusForApp(carUserRelationInfo, pageNum, pageSize);
		Response<Page<CarUserRelationInfoVo>> result = new Response<>();
		result.setData(page);
		return result;
	}

	/**
	 * 我已提交状态的预约申请个数 app端
	 *

	 * @return
	 */

	@ApiImplicitParams({
			/*@ApiImplicitParam(name = "riApplyId", value = "申请人id", required = true, paramType = "query", dataType = "String")*/
	})
	@ApiOperation(value = "我已提交状态的预约申请个数(APP端)", httpMethod = "POST", notes = "我已提交状态的预约申请个数(APP端)")
	@PostMapping(value = "/getApplyCount")
	public Response<Object> getApplyCount() {

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();

		carUserRelationInfo.setRiApplyId(UserServletContext.getUserInfo().getUserNo()==null?null: UserServletContext.getUserInfo().getUserNo());//申请人的id(此登陆者的id)
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setComCode(comCode);
		carUserRelationInfo.setModuCode(moduCode);
		Integer count = carUserRelationInfoService.getApplyCount(carUserRelationInfo);
		Response<Object> result = new Response<>();
		result.setData(count);
		return result;
	}

	/**
	 * 需要我审核的预约申请个数 app端
	 *

	 * @return
	 */

	@ApiImplicitParams({
			/*@ApiImplicitParam(name = "riApplyId", value = "申请人id", required = true, paramType = "query", dataType = "String")*/
	})
	@ApiOperation(value = "需要我审核的预约申请个数(APP端)", httpMethod = "POST", notes = "需要我审核的预约申请个数(APP端)")
	@PostMapping(value = "/getAuditCount")
	public Response<Object> getAuditCount() {

		String comCode = UserServletContext.getUserInfo().getComCode();
		String moduCode = UserServletContext.getUserInfo().getModuCode();
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		carUserRelationInfo.setRiIsDelete(Constants.NO);
		carUserRelationInfo.setModuCode(moduCode);
		carUserRelationInfo.setComCode(comCode);
		Integer count = carUserRelationInfoService.getAuditCount(carUserRelationInfo);
		Response<Object> result = new Response<>();
		result.setData(count);
		return result;
	}

	/**
	 * 查询预约时间段下的可预约车辆信息
	 *

	 * @param riUseEnd
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "riUseEnd", value = "预约开始时间(时间戳类型)", required = true, paramType = "query", dataType = "Long"),
			@ApiImplicitParam(name = "ciDepartmentId", value = "部门id", required = true, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "分页查询预约时间段下的可预约车辆信息(app端调)", httpMethod = "POST", notes = "分页查询预约时间段下的可预约车辆信息(app端调)")
	@PostMapping(value = "/getReservableCarInfoListForApp")
	public Response<List<CarInfo>> getReservableCarInfoListForApp( Long riUseEnd, String ciDepartmentId) {

		Page<CarInfo> page = carInfoService.findListForCarApp(UserServletContext.getUserInfo().getCompanyId(),riUseEnd,ciDepartmentId,Constants.carGearBoxType.PAGENUM,Integer.MAX_VALUE);
		Response<List<CarInfo>> result = new Response<>();
		result.setData(page.getList());
		return result;
	}

	/**
	 * 修改预约车辆信息重新提交
	 *

	 * @param id
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "修改预约车辆信息(APP端)", httpMethod = "POST", notes = "修改预约车辆信息(APP端)")
	@PostMapping(value = "/updateCarUserRelationInfoById")
	public Response<Object> updateCarUserRelationInfoById(HttpServletRequest request, String id, CarUserRelationInfoParam2 carUserRelationInfoParam2) {
		Response<Object> result = new Response<>();
		if (StringUtils.isEmpty(id)) {
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "id不能为空"));
		}
		CarUserRelationInfo carUserRelationInfoNew = carUserRelationInfoService.getById(id);
		if(carUserRelationInfoNew != null && Constants.AUDIT_STATUS_2.equals(carUserRelationInfoNew.getRiAuditStatus())){
			return expHandler(new BusinessException(ReturnType.ParamIllegal, "审核通过后不能更改预约！"));
		}
		//校验修改的预约信息 可选车辆和预约时间的匹配
		if(StringUtils.isNotEmpty(carUserRelationInfoParam2.getRiCarId())|| StringUtils.isNotEmpty(carUserRelationInfoParam2.getRiUseTimes())){
			Boolean flag=carUserRelationInfoService.judge1(id,carUserRelationInfoParam2);
			if(flag){
				return expHandler(new BusinessException(ReturnType.ParamIllegal, "预约车辆占用中,请重新修改！"));
			}
		}
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		BeanUtils.copyProperties(carUserRelationInfoParam2, carUserRelationInfo);
		carUserRelationInfo.setId(id);
		carUserRelationInfoService.updateCarUserRelationInfo(carUserRelationInfoNew,carUserRelationInfo);
		result.setData(carUserRelationInfo);
		return result;
	}
	/**
	 * 归还车辆(APP端)
	 *

	 * @param id
	 * @return
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "预约id", required = true, paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "归还车辆(APP端)", httpMethod = "POST", notes = "updateById")
	@PostMapping(value = "/returnCarForApp")
	public Response<Object> returnCarForApp(HttpServletRequest request, String id) {
		CarUserRelationInfo CarUserRelationInfo = carUserRelationInfoService.getById(id);
		if(CarUserRelationInfo !=null){
			if(Constants.AUDIT_STATUS_4.equals(CarUserRelationInfo.getRiAuditStatus()) || Constants.AUDIT_STATUS_5.equals(CarUserRelationInfo.getRiAuditStatus())){
				return expHandler(new BusinessException(ReturnType.ParamIllegal, "车辆已被归还！"));
			}
		}
		Response<Object> result = new Response<>();
		CarUserRelationInfo carUserRelationInfo = new CarUserRelationInfo();
		carUserRelationInfo.setUpdatedDt(new Date());
		carUserRelationInfo.setRiUseEnd(System.currentTimeMillis());
		carUserRelationInfo.setRiAuditStatus(Constants.AUDIT_STATUS_4);
		carUserRelationInfo.setId(id);
		carUserRelationInfoService.updateById(carUserRelationInfo);
		result.setData(carUserRelationInfo);
		return result;
	}

	public Long tranferSeconds(String time){
		return (Long.parseLong(time)*60*60*1000);
	}

	@ApiOperation(value = "获取保养列表", httpMethod = "POST", notes = "获取保养列表")
	@PostMapping(value = "/getMaintainDetail")
	public Response<Page<CarMaintainInfoVo>> getMaintainDetail(
														 @RequestParam("carId") String carId,int pageNum, int pageSize) {

		Response<Page<CarMaintainInfoVo>> result = new Response<>();
		result.setData(carMaintainInfoService.getMaintainDetail(carId,pageNum,pageSize));
		return result;
	}

	@ApiOperation(value = "新增保养", httpMethod = "POST", notes = "新增保养")
	@PostMapping(value = "/addMaintain")
	public Response<Boolean> addMaintain(
															   @RequestBody CarMaintainInfoVo carMaintainInfoVo) {

		Response<Boolean> result = new Response<>();
		result.setData(carMaintainInfoService.addMaintain(carMaintainInfoVo));
		return result;
	}

	@ApiOperation(value = "获取违章记录列表", httpMethod = "POST", notes = "获取违章记录列表")
	@PostMapping(value = "/getPunishPageInfo")
	public Response<Page<CarPunishInfoVo>> getPunishPageInfo(
															   @RequestParam("carId") String carId,int pageNum, int pageSize) {

		Response<Page<CarPunishInfoVo>> result = new Response<>();
		result.setData(carPunishInfoService.getPunishPageInfo(carId,pageNum,pageSize));
		return result;
	}


	@ApiOperation(value = "新增违章记录", httpMethod = "POST", notes = "新增违章记录")
	@PostMapping(value = "/addPunish")
	public Response<Boolean> addPunish(
										 @RequestBody CarPunishInfoVo carPunishInfoVo) {

		Response<Boolean> result = new Response<>();
		result.setData(carPunishInfoService.addPunish(carPunishInfoVo));
		return result;
	}
}