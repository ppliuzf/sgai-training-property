package com.sgai.property.mdm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.web.BaseController;
import com.sgai.property.mdm.service.MdmDeviceKpiInfoService;

/**
 * 
    * @ClassName: MdmDeviceKpiInfoController  
    * @com.sgai.property.commonService.vo;(主数据设备kpi详情controller)
    * @author wangtianyao  
    * @date 2018年7月27日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/mdm/mdmDeviceKpiInfo")
public class MdmDeviceKpiInfoController extends BaseController {

	@Autowired
	private MdmDeviceKpiInfoService mdmDeviceKpiInfoService;
	

}