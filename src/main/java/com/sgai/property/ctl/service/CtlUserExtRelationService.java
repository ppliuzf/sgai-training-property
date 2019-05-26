package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlUserExtRelationDao;
import com.sgai.property.ctl.entity.CtlUserExtRelation;

/**
 * 用户关系表Service
 *
 * @author 李伟
 * @version 2018-02-07
 */
@Service
@Transactional
public class CtlUserExtRelationService extends CrudServiceExt<CtlUserExtRelationDao, CtlUserExtRelation> {

	public CtlUserExtRelation get(String id) {
		return super.get(id);
	}

	public List<CtlUserExtRelation> findList(CtlUserExtRelation ctlUserExtRelation) {
		return super.findList(ctlUserExtRelation);
	}

	public Page<CtlUserExtRelation> findPage(Page<CtlUserExtRelation> page, CtlUserExtRelation ctlUserExtRelation) {
		return super.findPage(page, ctlUserExtRelation);
	}

	@Transactional(readOnly = false)
	public void save(CtlUserExtRelation ctlUserExtRelation) {
		super.save(ctlUserExtRelation);
	}

	@Transactional(readOnly = false)
	public void delete(CtlUserExtRelation ctlUserExtRelation) {
		super.delete(ctlUserExtRelation);
	}

}
