package com.sgai.property.customer.web;


import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.car.dao.ICarUserRelationInfoDao;
import com.sgai.property.car.entity.CarInfo;
import com.sgai.property.car.entity.CarUserRelationInfo;
import com.sgai.property.car.service.CarInfoServiceImpl;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.dto.SgaiDeptDto;
import com.sgai.property.commonService.vo.OrgSgaiTreeNode;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.service.CtlDeptService;
import com.sgai.property.customer.constants.Constants;
import com.sgai.property.customer.entity.UploadRecordInfo;
import com.sgai.property.customer.service.CustomInfoServiceImpl;
import com.sgai.property.customer.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * x
 *
 * @author
 * @date 2017年12月22日11:18:08
 */
@RestController
@RequestMapping("/customer")
@Api(description = "客户档案")
public class CustomerInfoController extends BaseController {
    @Autowired
    private CustomInfoServiceImpl customInfoService;
    @Autowired
    private CarInfoServiceImpl carInfoService;
    @Autowired
    private ICarUserRelationInfoDao carUserRelationInfoDao;
    @Autowired
	private CtlDeptService ctlDeptService;

    @RequestMapping(value = "/uploadRecordInfoPageList", method = RequestMethod.POST)
    @ApiOperation(value = "导入详情列表", httpMethod = "POST", notes = "导入详情列表")
    public Response<Page<UploadRecordInfo>> uploadRecordInfoPageList( @RequestBody UploadRecordInfo uploadRecordInfo, int pageNum, int pageSize) {
        Page<UploadRecordInfo> page = customInfoService.findUploadRecordInfoPageList(uploadRecordInfo, pageNum, pageSize);
        Response<Page<UploadRecordInfo>> result = new Response<>();
        result.setData(page);
        return result;
    }


    @ApiOperation(value = "保存机构客户对象", httpMethod = "POST", notes = "保存机构客户对象")
    @RequestMapping(value = "/saveOrgRecordInfo", method = RequestMethod.POST)
    public Response<Boolean> saveOrgRecordInfo( @RequestBody OrgRecordInfoDto orgRecordInfoDto) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.saveOrgRecordInfo(orgRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }

    @ApiOperation(value = "更新机构客户对象", httpMethod = "POST", notes = "更新机构客户对象")
    @RequestMapping(value = "/updateOrgRecordInfoById", method = RequestMethod.POST)
    public Response<Boolean> updateOrgRecordInfoById( @RequestBody OrgRecordInfoDto orgRecordInfoDto) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.updateOrgRecordInfoById(orgRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "删除机构客户对象", httpMethod = "POST", notes = "删除机构客户对象")
    @RequestMapping(value = "/deleteOrgRecordInfoById", method = RequestMethod.POST)
    public Response<Boolean> deleteOrgRecordInfoById( @RequestParam("orId") String orId) {
        Response<Boolean> result = new Response<>();
        OrgRecordInfoDto orgRecordInfoDto = new OrgRecordInfoDto();
        orgRecordInfoDto.setOrId(orId);
        orgRecordInfoDto.setOrIsDelete(Constants.IsDelete.Yes);
        boolean flag = customInfoService.updateOrgRecordInfoById(orgRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @RequestMapping(value = "/getOrgRecordInfoById", method = RequestMethod.POST)
    @ApiOperation(value = "机构客户详细信息", httpMethod = "POST", notes = "机构客户详细信息")
    public Response<OrgRecordInfoDetailVo> getOrgRecordInfoById( @RequestParam("orId") String orId) {
        OrgRecordInfoDetailVo orgRecordInfoDetailVo = customInfoService.getOrgRecordInfoById(orId);
        Response<OrgRecordInfoDetailVo> result = new Response<>();
        result.setData(orgRecordInfoDetailVo);
        return result;
    }


    @RequestMapping(value = "/orgRecordInfoList", method = RequestMethod.POST)
    @ApiOperation(value = "机构客户信息列表", httpMethod = "POST", notes = "机构客户信息列表")
    public Response<Page<OrgRecordInfoVo>> orgRecordInfoList( @RequestBody(required = false)
            OrgRecordInfoRequest orgRecordInfoRequest, int pageNum, int pageSize) {
        Page<OrgRecordInfoVo> orgRecordInfoVos = customInfoService.findAllOrgRecordInfoPageList(orgRecordInfoRequest, pageNum, pageSize);
        Response<Page<OrgRecordInfoVo>> result = new Response<>();
        result.setData(orgRecordInfoVos);
        return result;
    }


    @ApiOperation(value = "保存个人客户对象", httpMethod = "POST", notes = "保存个人客户对象")
    @RequestMapping(value = "/savePersonalRecordInfo", method = RequestMethod.POST)
    public Response<Boolean> savePersonalRecordInfo( @RequestBody PersonalRecordInfoDto personalRecordInfoDto) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.savePersonalRecordInfo(personalRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "更新个人客户对象", httpMethod = "POST", notes = "更新个人客户对象")
    @RequestMapping(value = "/updatePersonalRecordInfoById", method = RequestMethod.POST)
    public Response<Boolean> updateCustomTypeInfoById( @RequestBody PersonalRecordInfoDto personalRecordInfoDto) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.updatePersonalRecordInfoById(personalRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "删除个人客户对象", httpMethod = "POST", notes = "删除个人客户对象")
    @RequestMapping(value = "/deletePersonalRecordInfoById", method = RequestMethod.POST)
    public Response<Boolean> deletePersonalRecordInfoById( @RequestParam("prId") String prId) {
        Response<Boolean> result = new Response<>();
        PersonalRecordInfoDto personalRecordInfoDto = new PersonalRecordInfoDto();
        personalRecordInfoDto.setPrId(prId);
        personalRecordInfoDto.setPrIsDelete(Constants.IsDelete.Yes);
        boolean flag = customInfoService.updatePersonalRecordInfoById(personalRecordInfoDto);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @RequestMapping(value = "/getPersonalRecordInfoById", method = RequestMethod.POST)
    @ApiOperation(value = "个人客户详细信息", httpMethod = "POST", notes = "个人客户详细信息")
    public Response<PersonalRecordDetailInfoVo> getPersonalRecordInfoById( @RequestParam("prId") String prId) {
        PersonalRecordDetailInfoVo personalRecordDetailInfoVo = customInfoService.getPersonalRecordInfoById(prId);
        Response<PersonalRecordDetailInfoVo> result = new Response<>();
        result.setData(personalRecordDetailInfoVo);
        return result;
    }


    @RequestMapping(value = "/customInfoList", method = RequestMethod.POST)
    @ApiOperation(value = "个人客户信息列表", httpMethod = "POST", notes = "个人客户信息列表")
    public Response<Page<PersonalRecordInfoVo>> customInfoList( @RequestBody(required = false) PersonalRecordInfoRequest personalRecordInfoRequest,
                                                               @RequestParam(required=false,defaultValue="1") int pageNum, @RequestParam(required=false,defaultValue="10") int pageSize) {
        if(pageNum == 0){
        	pageNum = 1;
        }
        
        if(pageSize == 0){
        	pageSize = 10;
        }
    	
    	Page<PersonalRecordInfoVo> pageInfo = customInfoService.findAllCustomerInfosPageList(personalRecordInfoRequest, pageNum, pageSize);
        Response<Page<PersonalRecordInfoVo>> result = new Response<>();
        result.setData(pageInfo);
        return result;
    }


    @RequestMapping(value = "/customTypeInfoList", method = RequestMethod.POST)
    @ApiOperation(value = "类型设置查询列表", httpMethod = "POST", notes = "类型设置查询列表")
    public Response<List<CustomTypeInfoVo>> listCustomTypeInfos( @RequestParam(name = "typeClass", required = true) Integer typeClass) {
        List<CustomTypeInfoVo> customTypeInfoList = customInfoService.findAllCustomTypeInfos(typeClass);
        Response<List<CustomTypeInfoVo>> result = new Response<>();
        result.setData(customTypeInfoList);
        return result;
    }


    @RequestMapping(value = "/getCustomTypeInfoById", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取类型信息", httpMethod = "POST", notes = "根据ID获取类型信息")
    public Response<CustomTypeInfoVo> getCustomTypeInfoById( @RequestParam("ctId") String ctId) {
        Response<CustomTypeInfoVo> result = new Response<>();
        result.setData(customInfoService.getCustomTypeInfoById(ctId));
        return result;
    }


    @ApiOperation(value = "保存类型设置对象", httpMethod = "POST", notes = "保存类型设置对象")
    @RequestMapping(value = "/saveCustomTypeInfo", method = RequestMethod.POST)
    public Response<Boolean> saveCustomTypeInfo( @RequestBody CustomTypeInfoVo customTypeInfoVo) {
        Response<Boolean> result = new Response<>();
        customTypeInfoVo.setTypeStatus(Constants.IsDelete.Yes); //设置类型,自定义
        boolean flag = customInfoService.saveCustomTypeInfo(customTypeInfoVo);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "更新类型设置对象", httpMethod = "POST", notes = "更新类型设置对象")
    @RequestMapping(value = "/updateCustomTypeInfoById", method = RequestMethod.POST)
    public Response<Boolean> updateCustomTypeInfoById( @RequestBody CustomTypeInfoVo customTypeInfoVo) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.updateCustomTypeInfoById(customTypeInfoVo,null);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "删除类型设置对象", httpMethod = "POST", notes = "删除类型设置对象")
    @RequestMapping(value = "/deleteCustomTypeInfoById", method = RequestMethod.POST)
    public Response<Boolean> deleteCustomTypeInfoById( @RequestParam("ctId") String ctId) {
        Response<Boolean> result = new Response<>();
        CustomTypeInfoVo customTypeInfoVo = new CustomTypeInfoVo();
        customTypeInfoVo.setCtId(ctId);
        boolean flag = customInfoService.updateCustomTypeInfoById(customTypeInfoVo,Constants.TRUE);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @RequestMapping(value = "/customLevelInfoList", method = RequestMethod.POST)
    @ApiOperation(value = "级别设置查询列表", httpMethod = "POST", notes = "级别设置查询列表")
    public Response<List<CustomLevelInfoVo>> listCustomLevelInfos() {
        List<CustomLevelInfoVo> customLevelInfos = customInfoService.findAllCustomLevelInfos();
        Response<List<CustomLevelInfoVo>> result = new Response<>();
        result.setData(customLevelInfos);
        return result;
    }


    @RequestMapping(value = "/getCustomLevelInfoById", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取级别设置信息", httpMethod = "POST", notes = "根据ID获取级别设置信息")
    public Response<CustomLevelInfoVo> getCustomLevelInfoById( @RequestParam("clId") String clId) {
        Response<CustomLevelInfoVo> result = new Response<>();
        result.setData(customInfoService.getCustomLevelInfoById(clId));
        return result;
    }


    @ApiOperation(value = "保存级别设置对象", httpMethod = "POST", notes = "保存级别设置对象")
    @RequestMapping(value = "/saveCustomLevelInfo", method = RequestMethod.POST)
    public Response<Boolean> saveCustomLevelInfo( @RequestBody CustomLevelInfoVo customLevelInfoVo) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.saveCustomLevelInfo(customLevelInfoVo);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }

    @ApiOperation(value = "更新级别设置对象", httpMethod = "POST", notes = "更新级别设置对象")
    @RequestMapping(value = "/updateCustomLevelInfoById", method = RequestMethod.POST)
    public Response<Boolean> updateCustomLevelInfoById( @RequestBody CustomLevelInfoVo customLevelInfoVo) {
        Response<Boolean> result = new Response<>();
        boolean flag = customInfoService.updateCustomLevelInfoById(customLevelInfoVo);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "删除级别设置对象", httpMethod = "POST", notes = "删除级别设置对象")
    @RequestMapping(value = "/deleteCustomLevelInfoById", method = RequestMethod.POST)
    public Response<Boolean> deleteCustomLevelInfoById( @RequestParam("clId") String clId) {
        Response<Boolean> result = new Response<>();
        CustomLevelInfoVo customLevelInfoVo = new CustomLevelInfoVo();
        customLevelInfoVo.setClId(clId);
        customLevelInfoVo.setClIsDelete(Constants.TRUE);
        boolean flag = customInfoService.updateCustomLevelInfoById(customLevelInfoVo);
        result.setData(flag);
        if (!flag) {
            result.setMessage("执行失败");
        }
        return result;
    }


    @ApiOperation(value = "证件类型", httpMethod = "POST", notes = "证件类型")
    @RequestMapping(value = "/certificateList", method = RequestMethod.POST)
    public Response<List<CustomCardInfoVo>> certificateList( @RequestParam(name="type",required=false) Integer type) {
        Response<List<CustomCardInfoVo>> result = new Response<>();
        result.setData(customInfoService.findCustomCardInfos(type));
        return result;

    }

    @ApiOperation(value = "修改证件类型", httpMethod = "POST", notes = "证件类型")
    @RequestMapping(value = "/updateCardInfo", method = RequestMethod.POST)
    public Response<Boolean> updateCardInfo( @RequestBody CustomCardInfoDto customCardInfoDto) {
        Response<Boolean> result = new Response<>();
        result.setData(customInfoService.updateCardInfo(customCardInfoDto));
        return result;

    }

    @ApiOperation(value = "保存证件类型", httpMethod = "POST", notes = "证件类型")
    @RequestMapping(value = "/saveCardInfo", method = RequestMethod.POST)
    public Response<Boolean> saveCardInfo( @RequestBody CustomCardInfoDto customCardInfoDto) {
        Response<Boolean> result = new Response<>();
        result.setData(customInfoService.saveCardInfo(customCardInfoDto));
        return result;

    }

    @ApiOperation(value = "获取车辆信息-客户详情页", httpMethod = "POST", notes = "获取车辆信息-客户详情页")
    @RequestMapping(value = "/getCarInfoList", method = RequestMethod.POST)
    public Response<Page<CarInfo>> getCarInfoList(
                                           @RequestBody CustomInfoParam customInfoParam,
                                           @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
    	CarInfo carInfo=new CarInfo();
    	Long ciOwnerPhone1 = customInfoParam.getCiOwnerPhone1();
    	Long ciOwnerPhone2 = customInfoParam.getCiOwnerPhone2();
    	String ciOwnerName = customInfoParam.getCiOwnerName();
    	
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
		Page<CarInfo> page=carInfoService.findByCustomer(carInfo,pageNum,pageSize);
		Response<Page<CarInfo>> result = new Response<>();
		result.setData(page);
		return result;
    }


    @ApiOperation(value = "根据id删除车辆信息-客户详情页", httpMethod = "POST", notes = "根据id删除车辆信息-客户详情页")
    @RequestMapping(value = "/deleteCarInfoById", method = RequestMethod.POST)
    public Response<Object> deleteCarInfoById(
                                       @RequestParam(value = "id")String id){
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

    @ApiOperation(value = "获取工单信息-客户详情页", httpMethod = "POST", notes = "工单-客户详情页")
    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    public Response<List<EventOrder>> getOrderInfo(
                                  @RequestBody CustomInfoParam customInfoParam){
        EventOrderSearch eventOrderSearch = new EventOrderSearch();
        eventOrderSearch.setContactPerson(customInfoParam.getCiOwnerName());
        List<String> phoneNub = new ArrayList<>(4);
        phoneNub.add(customInfoParam.getCiOwnerPhone1()+"");
        if(null != customInfoParam.getCiOwnerPhone2()){
            phoneNub.add(customInfoParam.getCiOwnerPhone2()+"");
        }
        eventOrderSearch.setTelephones(phoneNub);
        Response<List<EventOrder>> result = null;//orderRemoteService.getListByCondition(accessToken,eventOrderSearch);
        return result;
    }
    

    @ApiOperation(value = "获取部门信息(首自信)", httpMethod = "POST", notes = "获取部门信息(首自信)")
	@RequestMapping(value = "/getSgaiDept", method = RequestMethod.POST)
	public Response<List<SgaiDeptDto>> getSgaiDept() {
		Response<List<SgaiDeptDto>> response = new Response<>();
		List<SgaiDeptDto> list = ctlDeptService.getDeptList();
		response.setData(list);
		return response;
	}
    
    @ApiOperation(value = "全局搜索首自信员工数据（组织树弹出窗口上的搜索人员）", httpMethod = "POST", notes = "全局搜索首自信员工数据（组织树弹出窗口上的搜索人员）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "deptId", value = "0:根部门", required = true, paramType = "query", dataType = "String"),
	})
	@RequestMapping(value = "/searchSgaiEmp", method = RequestMethod.POST)
	public Response<List<OrgSgaiTreeNode>> searchSgaiEmp(
													  @RequestParam("lastname")String lastname) {
		return null;//commonsRomeotService.searchSgaiEmp(accessToken, lastname);
	}
    
    
}