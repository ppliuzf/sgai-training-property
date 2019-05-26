package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.property.ctl.entity.CtlUserChart;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlUserChartDao;

/**
 * 用户图表关系表Service
 * @author admin
 * @version 2018-01-04
 */
@Service
@Transactional
public class CtlUserChartService extends CrudServiceExt<CtlUserChartDao, CtlUserChart> {

	public CtlUserChart get(String id) {
		return super.get(id);
	}

	public List<CtlUserChart> findList(CtlUserChart ctlUserChart) {
		return super.findList(ctlUserChart);
	}

	public Page<CtlUserChart> findPage(Page<CtlUserChart> page, CtlUserChart ctlUserChart) {
		return super.findPage(page, ctlUserChart);
	}

	@Transactional(readOnly = false)
	public void save(CtlUserChart ctlUserChart) {
		super.save(ctlUserChart);
	}

	@Transactional(readOnly = false)
	public void delete(CtlUserChart ctlUserChart) {
		super.delete(ctlUserChart);
	}

	@Transactional(readOnly = false)
	public void deleteByUserCode(String userCode) {
		dao.deleteByUserCode(userCode);
	}
}
