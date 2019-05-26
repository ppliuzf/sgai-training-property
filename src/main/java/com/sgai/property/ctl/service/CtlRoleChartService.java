package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.property.ctl.entity.CtlRoleChart;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlRoleChartDao;

/**
 * 角色和图表关系Service
 * @author admin
 * @version 2018-01-04
 */
@Service
@Transactional
public class CtlRoleChartService extends CrudServiceExt<CtlRoleChartDao, CtlRoleChart> {

	public CtlRoleChart get(String id) {
		return super.get(id);
	}

	public List<CtlRoleChart> findList(CtlRoleChart ctlRoleChart) {
		return super.findList(ctlRoleChart);
	}

	public Page<CtlRoleChart> findPage(Page<CtlRoleChart> page, CtlRoleChart ctlRoleChart) {
		return super.findPage(page, ctlRoleChart);
	}

	@Transactional(readOnly = false)
	public void save(CtlRoleChart ctlRoleChart) {
		super.save(ctlRoleChart);
	}

	@Transactional(readOnly = false)
	public void delete(CtlRoleChart ctlRoleChart) {
		super.delete(ctlRoleChart);
	}

	@Transactional(readOnly = false)
	public void deleteByRoleCode(String roleCode) {
		dao.deleteByRoleCode(roleCode);
	}
}
