package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagLinkageRuleSpaceDao;
import com.sgai.property.ruag.entity.RuagLinkageRuleSpace;
/**
 *
    * @ClassName: RuagLinkageRuleSpaceService
    * @com.sgai.property.commonService.vo;(联动规则与位置的关系Service)
    * @author 王天尧
    * @date 2018年4月4日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class RuagLinkageRuleSpaceService extends CrudServiceExt<RuagLinkageRuleSpaceDao, RuagLinkageRuleSpace> {

	public RuagLinkageRuleSpace get(String id) {
		return super.get(id);
	}

	public List<RuagLinkageRuleSpace> findList(RuagLinkageRuleSpace ruagLinkageRuleSpace) {
		return super.findList(ruagLinkageRuleSpace);
	}

	public Page<RuagLinkageRuleSpace> findPage(Page<RuagLinkageRuleSpace> page, RuagLinkageRuleSpace ruagLinkageRuleSpace) {
		return super.findPage(page, ruagLinkageRuleSpace);
	}

	@Transactional(readOnly = false)
	public void save(RuagLinkageRuleSpace ruagLinkageRuleSpace) {
		super.save(ruagLinkageRuleSpace);
	}

	@Transactional(readOnly = false)
	public void delete(RuagLinkageRuleSpace ruagLinkageRuleSpace) {
		super.delete(ruagLinkageRuleSpace);
	}
	/**
	 *
	    * @Title: getSpace
	    * @com.sgai.property.commonService.vo;(获得联动规则所在区域的所有位置)
	    * @param @param linkageCode    参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public String getSpace(String linkageCode) {
		String result = "";
		RuagLinkageRuleSpace ruagLinkageRuleSpace = new RuagLinkageRuleSpace();
		ruagLinkageRuleSpace.setLinkageCode(linkageCode);
		List<RuagLinkageRuleSpace> findList = findList(ruagLinkageRuleSpace);
		for (RuagLinkageRuleSpace ruagLinkageRuleSpace2 : findList) {
			result+=ruagLinkageRuleSpace2.getSpaceCode()+",";
		}
		return result;
	}
}
