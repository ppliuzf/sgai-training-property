package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagOptIndicatorDao;
import com.sgai.property.ruag.entity.RuagOptIndicator;

/**
 * 优化指标Service
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagOptIndicatorService extends CrudServiceExt<RuagOptIndicatorDao, RuagOptIndicator> {

	public RuagOptIndicator get(String id) {
		return super.get(id);
	}

	public List<RuagOptIndicator> findList(RuagOptIndicator ruagOptIndicator) {
		return super.findList(ruagOptIndicator);
	}

	public Page<RuagOptIndicator> findPage(Page<RuagOptIndicator> page, RuagOptIndicator ruagOptIndicator) {
		return super.findPage(page, ruagOptIndicator);
	}

	@Transactional(readOnly = false)
	public void save(RuagOptIndicator ruagOptIndicator) {
		super.save(ruagOptIndicator);
	}

	@Transactional(readOnly = false)
	public void delete(RuagOptIndicator ruagOptIndicator) {
		super.delete(ruagOptIndicator);
	}

}
