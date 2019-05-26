package com.sgai.property.ruag.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.web.BaseController;
import com.sgai.property.ruag.service.RuagLinkageRuleSpaceService;

/**
 * 
    * @ClassName: RuagLinkageRuleSpaceController  
    * @com.sgai.property.commonService.vo;(联动规则与位置的关系Controller)
    * @author 王天尧  
    * @date 2018年4月4日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "ruag/ruag/ruagLinkageRuleSpace")
public class RuagLinkageRuleSpaceController extends BaseController {

	@Autowired
	private RuagLinkageRuleSpaceService ruagLinkageRuleSpaceService;
	

}