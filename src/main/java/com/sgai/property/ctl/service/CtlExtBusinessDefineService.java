package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlExtBusinessDefineDao;
import com.sgai.property.ctl.entity.CtlExtBusinessDefine;

/**
 * 外部对接系统定义Service
 *
 * @author 李伟
 * @version 2018-02-07
 */
@Service
@Transactional
public class CtlExtBusinessDefineService extends CrudServiceExt<CtlExtBusinessDefineDao, CtlExtBusinessDefine> {

	public CtlExtBusinessDefine get(String id) {
		return super.get(id);
	}

	public List<CtlExtBusinessDefine> findList(CtlExtBusinessDefine ctlExtBusinessDefine) {
		return super.findList(ctlExtBusinessDefine);
	}

	public Page<CtlExtBusinessDefine> findPage(Page<CtlExtBusinessDefine> page,
			CtlExtBusinessDefine ctlExtBusinessDefine) {
		return super.findPage(page, ctlExtBusinessDefine);
	}

	@Transactional(readOnly = false)
	public void save(CtlExtBusinessDefine ctlExtBusinessDefine) {
		super.save(ctlExtBusinessDefine);
	}

	@Transactional(readOnly = false)
	public void delete(CtlExtBusinessDefine ctlExtBusinessDefine) {
		super.delete(ctlExtBusinessDefine);
	}

}
