package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.service.DeleteRulesUtils;

/**
 * @author guanze
 * @version 2018-1-26
 */
@RestController
@RequestMapping(value = "${adminPath}/deleteRulesUtils")
public class DeleteRulesUtilsController extends BaseController {
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;

	@RequestMapping(value = "checkBeforeDelete")
	public Map<String,String> checkBeforeDelete(Class clazz,List<String> idList) {
		return deleteRulesUtils.checkBeforeDelete(clazz, idList);
	}
}