package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.property.ctl.entity.CtlRuleInfo;
import com.sgai.property.ctl.dao.CtlRuleInfoDao;
import com.sgai.property.ctl.entity.CtlRuleItem;
import com.sgai.property.ctl.dao.CtlRuleItemDao;

/**
 * 编码规则Service
 * @author chenxing
 * @version 2017-11-20
 */
@Service
@Transactional
public class CtlRuleInfoService extends CrudServiceExt<CtlRuleInfoDao, CtlRuleInfo> {

	@Autowired
	private CtlRuleItemDao ctlRuleItemDao;

	public CtlRuleInfo get(String id) {
		CtlRuleInfo ctlRuleInfo = super.get(id);
		if(ctlRuleInfo!=null){
		    ctlRuleInfo.setCtlRuleItemList(ctlRuleItemDao.findList(new CtlRuleItem(ctlRuleInfo)));
		}

		return ctlRuleInfo;
	}

	public CtlRuleInfo getRuleInfo(CtlRuleInfo param) {
		CtlRuleInfo ctlRuleInfo = dao.getRuleInfo(param);
		if(ctlRuleInfo!=null){
			ctlRuleInfo.setCtlRuleItemList(ctlRuleItemDao.findList(new CtlRuleItem(ctlRuleInfo)));
		}
		return ctlRuleInfo;
	}

	public List<CtlRuleInfo> findList(CtlRuleInfo ctlRuleInfo) {
		return super.findList(ctlRuleInfo);
	}

	public Page<CtlRuleInfo> findPage(Page<CtlRuleInfo> page, CtlRuleInfo ctlRuleInfo) {
		return super.findPage(page, ctlRuleInfo);
	}

	@Transactional(readOnly = false)
	public void save(CtlRuleInfo ctlRuleInfo) {
		super.save(ctlRuleInfo);
		for (CtlRuleItem ctlRuleItem : ctlRuleInfo.getCtlRuleItemList()){
			if (ctlRuleItem.getId() == null){
				continue;
			}
			if (CtlRuleItem.DEL_FLAG_NORMAL.equals(ctlRuleItem.getDelFlag())){
				ctlRuleItem.setSequCode(ctlRuleInfo.getSequCode());
				if (StringUtils.isBlank(ctlRuleItem.getId())){
					ctlRuleItem.preInsert(ctlRuleItem.getComCode(),ctlRuleItem.getModuCode());
					ctlRuleItemDao.insert(ctlRuleItem);
				}else{
					ctlRuleItem.preUpdate(ctlRuleItem.getComCode(),ctlRuleItem.getModuCode());
					ctlRuleItemDao.update(ctlRuleItem);
				}
			}else{
				ctlRuleItemDao.delete(ctlRuleItem);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(CtlRuleInfo ctlRuleInfo) {
		super.delete(ctlRuleInfo);
		ctlRuleItemDao.delete(new CtlRuleItem(ctlRuleInfo));
	}

	public List<CtlRuleItem> getRuleItemList(CtlRuleItem ctlRuleItem){
		return ctlRuleItemDao.getRuleItemList(ctlRuleItem);
	}

	@Transactional(readOnly = false)
	public void batchDeleteRuleInfo(List<String> idList) {
		ctlRuleItemDao.batchDelete(idList);
		dao.batchDelete(idList);
	}

	/**
	 * 统计除自己之外的地方，总共出现了几个同样的sequCode
	 */
	public Integer countSequCodeExceptSelf(CtlRuleInfo entity) {
		return dao.countSequCodeExceptSelf(entity);
	}
}
