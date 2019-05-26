package com.sgai.property.ruag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ruag.dao.RuagOptItemDao;
import com.sgai.property.ruag.entity.RuagOptItem;

/**
 * 优化项Service
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagOptItemService extends CrudServiceExt<RuagOptItemDao, RuagOptItem> {

	public RuagOptItem get(String id) {
		return super.get(id);
	}

	public List<RuagOptItem> findList(RuagOptItem ruagOptItem) {
		return super.findList(ruagOptItem);
	}

	public Page<RuagOptItem> findPage(Page<RuagOptItem> page, RuagOptItem ruagOptItem) {
		return super.findPage(page, ruagOptItem);
	}

	@Transactional(readOnly = false)
	public void save(RuagOptItem ruagOptItem) {
		super.save(ruagOptItem);
	}

	@Transactional(readOnly = false)
	public void delete(RuagOptItem ruagOptItem) {
		super.delete(ruagOptItem);
	}

}
