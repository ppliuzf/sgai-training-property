package com.sgai.property.ctl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlModuDao;
import com.sgai.property.ctl.entity.CtlModu;


/**
 * 模块维护Service
 * @author guanze
 * @version 2017-11-07
 */

@Service
@Transactional
public class CtlModuService extends CrudServiceExt<CtlModuDao, CtlModu> {

	@Autowired
	private CtlModuDao ctlModuDao;

	public CtlModu get(String id) {
		return super.get(id);
	}

	public List<CtlModu> findList(CtlModu ctlModu) {
		return super.findList(ctlModu);
	}

	public Page<CtlModu> findPage(Page<CtlModu> page, CtlModu ctlModu) {
		// 设置分页参数
		ctlModu.setPage(page);
		// 执行分页查询
		page.setList(findList(ctlModu));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(CtlModu ctlModu) {
		super.save(ctlModu);
	}

	@Transactional(readOnly = false)
	public void delete(CtlModu ctlModu) {
		super.delete(ctlModu);
	}

	@Transactional(readOnly = false)
	public List<CtlModu> batchDelete(List<CtlModu> objs) {
		return super.batchDelete(objs);
	}

	/**
	 * checkModu:检查表中是否存在满足条件的数据
	 * @param ctlModu
	 * @return :List<CtlModu> ：1 or 0
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	public List<CtlModu> checkModu(CtlModu ctlModu) {
		return super.dao.checkModu(ctlModu);
	}

	/**
	 * findModuById:根据Id获得单条数据
	 * @param qId
	 * @return :CtlModu
	 * @since JDK 1.8
	 * @author guanze
	 */
	@Transactional(readOnly = false)
	public CtlModu findModuById(String qId) {
		return this.get(qId);
	}

}
