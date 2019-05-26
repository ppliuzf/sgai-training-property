package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlBusiUserDao;
import com.sgai.property.ctl.entity.CtlBusiUser;

/**
 * 外部系统用户维护Service
 *
 * @author 李伟
 * @version 2018-02-07
 */
@Service
@Transactional
public class CtlBusiUserService extends CrudServiceExt<CtlBusiUserDao, CtlBusiUser> {

	public CtlBusiUser get(String id) {
		return super.get(id);
	}

	public List<CtlBusiUser> findList(CtlBusiUser ctlBusiUser) {
		return super.findList(ctlBusiUser);
	}

	public Page<CtlBusiUser> findPage(Page<CtlBusiUser> page, CtlBusiUser ctlBusiUser) {
		return super.findPage(page, ctlBusiUser);
	}

	@Transactional(readOnly = false)
	public void save(CtlBusiUser ctlBusiUser) {
		super.save(ctlBusiUser);
	}

	@Transactional(readOnly = false)
	public void delete(CtlBusiUser ctlBusiUser) {
		super.delete(ctlBusiUser);
	}

}
