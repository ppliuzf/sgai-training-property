package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlLogDao;
import com.sgai.property.ctl.entity.CtlLog;

/**
 * &quot;在线用户管理&quot;Service
 * @author guanze
 * @version 2017-11-09
 */
@Service
@Transactional
public class CtlLogService extends CrudServiceExt<CtlLogDao, CtlLog>{

	@Autowired
	private CtlLogDao ctlLogDao;

	public CtlLog get(String id) {
		return super.get(id);
	}

	public List<CtlLog> findList(CtlLog ctlLog) {
		return super.findList(ctlLog);
	}

	public Page<CtlLog> findPage(Page<CtlLog> page, CtlLog ctlLog) {
		// 设置分页参数
		ctlLog.setPage(page);
		// 执行分页查询
		page.setList(ctlLogDao.findList(ctlLog));
		return page;
	}

	/**
	 * findLogDetail:根据sessionId获取日志记录明细
	 * @param sessionId
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author guanze
	 */
	public Page<Map<String,String>> findLogs(Page<Map<String,String>> page, CtlLog ctlLog) {
		// 执行分页查询
		Page<CtlLog> page1 = new Page<CtlLog>(ctlLog.getPageNo(), ctlLog.getPageSize());
		ctlLog.setPage(page1);
		List<Map<String,String>> list = super.dao.findLogDetail(ctlLog);
		page.setCount(page1.getCount());
		page.setList(list);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(CtlLog ctlLog) {
		super.save(ctlLog);
	}

	@Transactional(readOnly = false)
	public void delete(CtlLog ctlLog) {
		super.delete(ctlLog);
	}

	@Transactional(readOnly = false)
	public List<CtlLog> batchDelete(List<CtlLog> objs) {
		return super.batchDelete(objs);
	}



	/**
	 * getComList:获取机构下拉列表
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author guanze
	 */
	public List<Map<String, String>> getComList() {
		return super.dao.getComList();
	}

	/**
	 * getComFromUserList:获取用户下拉列表
	 * @param comCode
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author guanze
	 */
	public List<Map<String, String>> getComFromUserList(String comCode) {
		return super.dao.getComFromUserList(comCode);
	}
}
