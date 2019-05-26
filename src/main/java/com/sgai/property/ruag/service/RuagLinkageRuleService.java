package com.sgai.property.ruag.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.sgai.property.ctl.service.CtlComRuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ruag.dao.RuagLinkageRuleDao;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import com.sgai.property.ruag.entity.RuagLinkageRuleSpace;

/**
 * 联动规则定义Service
 *
 * @author yangyz
 * @version 2018-01-02
 */
@Service
@Transactional
public class RuagLinkageRuleService extends CrudServiceExt<RuagLinkageRuleDao, RuagLinkageRule> {
	@Autowired
	private RuagLinkaageFrontDeviceService ruagLinkaageFrontDeviceService;
	@Autowired
	private RuagLinkageRuleSpaceService ruagLinkageRuleSpaceService;
	@Autowired
	private RuagLinkaageNextDeviceService RuagLinkaageNextDeviceService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	public RuagLinkageRule get(String id) {
		return super.get(id);
	}

	public List<RuagLinkageRule> findList(RuagLinkageRule ruagLinkageRule) {
		return super.findList(ruagLinkageRule);
	}

	public Page<RuagLinkageRule> findPage(Page<RuagLinkageRule> page, RuagLinkageRule ruagLinkageRule) {
		return super.findPage(page, ruagLinkageRule);
	}

	@Transactional(readOnly = false)
	public void save(RuagLinkageRule ruagLinkageRule) {
		super.save(ruagLinkageRule);
	}

	@Transactional(readOnly = false)
	public void delete(RuagLinkageRule ruagLinkageRule) {
		super.delete(ruagLinkageRule);
	}

	public Map<String, Object> saveLinkageRule(RuagLinkageRule ruagLinkageRule, String spaceCodes,
			String oldSpaceCode) {
		LoginUser user = UserServletContext.getUserInfo();
		String[] split = spaceCodes.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(ruagLinkageRule.getId())) {
			if (ruagLinkageRule.getSpaceCode().equals(oldSpaceCode)) {
				super.save(ruagLinkageRule);
			} else {
				RuagLinkageRuleSpace ruagLinkageRuleSpace = new RuagLinkageRuleSpace();
				ruagLinkageRuleSpace.setLinkageCode(ruagLinkageRule.getLinkageCode());
				List<RuagLinkageRuleSpace> findList = ruagLinkageRuleSpaceService.findList(ruagLinkageRuleSpace);
				for (RuagLinkageRuleSpace ruagLinkageRuleSpace2 : findList) {
					ruagLinkageRuleSpaceService.delete(ruagLinkageRuleSpace2);
				}
				for (String space : split) {
					RuagLinkageRuleSpace ruagLinkageRuleSpaceN = new RuagLinkageRuleSpace();
					ruagLinkageRuleSpaceN.setLinkageCode(ruagLinkageRule.getLinkageCode());
					ruagLinkageRuleSpaceN.setSpaceCode(space);
					ruagLinkageRuleSpaceService.save(ruagLinkageRuleSpaceN);
					super.save(ruagLinkageRule);

				}
			}
			map.put("msg", "success");
		} else {
			RuagLinkageRule info = new RuagLinkageRule();
			info.setLinkageName(ruagLinkageRule.getLinkageName());
			info.setEnabledFlag("Y");
			info.setComCode(user.getComCode());
			info.setModuCode(user.getModuCode());
			List<RuagLinkageRule> list = dao.findByName(info);
			if (list.size() > 0) {
				map.put("msg", "haveData");
			} else {
				String code = ctlComRuleService.getNext(user.getComCode(), "LINK_RULE");
				ruagLinkageRule.setLinkageCode(code);
				ruagLinkageRule.setEnabledFlag("Y");
				super.save(ruagLinkageRule);
				for (String space : split) {
					RuagLinkageRuleSpace ruagLinkageRuleSpaceN = new RuagLinkageRuleSpace();
					ruagLinkageRuleSpaceN.setLinkageCode(ruagLinkageRule.getLinkageCode());
					ruagLinkageRuleSpaceN.setSpaceCode(space);
					ruagLinkageRuleSpaceService.save(ruagLinkageRuleSpaceN);
				}
				map.put("msg", "success");
			}
		}

		return map;
	}

	public RuagLinkageRule getLinkageCode(String linkageCode, String comCode, String moduCode) {
		RuagLinkageRule ruagLinkageRule = new RuagLinkageRule();
		ruagLinkageRule.setComCode(comCode);
		ruagLinkageRule.setModuCode(moduCode);
		ruagLinkageRule.setLinkageCode(linkageCode);
		return dao.getLinkageCode(ruagLinkageRule);
	}

	/**
	 *
	    * @Title: scanRule
	    * @com.sgai.property.commonService.vo;(遍历联动规则查看是否符合触发条件)
	    * @param @param rules
	    * @param @return
	    * @param @throws IOException
	    * @param @throws ServletException
	    * @param @throws ParseException    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public boolean scanRule(List<RuagLinkageRule> rules) throws IOException, ServletException, ParseException {
		// 遍历联动规则看是否附和触发条件
		for (RuagLinkageRule rule : rules) {
			// 解析联动规则的前置设备是否符合触发条件
			boolean analysisRule = ruagLinkaageFrontDeviceService.analysisRule(rule);
		}
		return true;
	}

	/**
	 *
	 * @throws ParseException
	 * @throws ServletException
	 * @throws IOException
	 * @Title: checkConflict @com.sgai.property.commonService.vo;(查看是否与联动规则指令冲突并做处理) @param 参数 @return
	 * void 返回类型 @throws
	 */
	public void checkConflict() throws IOException, ServletException, ParseException {
		// 获取所有已启动的联动规则
		RuagLinkageRule ruagLinkageRule = new RuagLinkageRule();
		ruagLinkageRule.setStatus("Y");
		List<RuagLinkageRule> rules = findList(ruagLinkageRule);
		if (rules.size() > 0) {
			// 遍历联动规则才看看是否有冲突的设备并处理
			for (RuagLinkageRule rule : rules) {
				RuagLinkaageNextDeviceService.SelectDevicesNextIns(rule.getLinkageCode());
			}
		}
	}
}
