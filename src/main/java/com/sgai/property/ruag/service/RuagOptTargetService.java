package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagOptTargetDao;
import com.sgai.property.ruag.entity.RuagOptTarget;

/**
 * 优化目标Service
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagOptTargetService extends CrudServiceExt<RuagOptTargetDao, RuagOptTarget> {

	public RuagOptTarget get(String id) {
		return super.get(id);
	}

	public List<RuagOptTarget> findList(RuagOptTarget ruagOptTarget) {
		return super.findList(ruagOptTarget);
	}

	public Page<RuagOptTarget> findPage(Page<RuagOptTarget> page, RuagOptTarget ruagOptTarget) {
		return super.findPage(page, ruagOptTarget);
	}

	@Transactional(readOnly = false)
	public void save(RuagOptTarget ruagOptTarget) {
		super.save(ruagOptTarget);
	}

	@Transactional(readOnly = false)
	public void delete(RuagOptTarget ruagOptTarget) {
		super.delete(ruagOptTarget);
	}

}
