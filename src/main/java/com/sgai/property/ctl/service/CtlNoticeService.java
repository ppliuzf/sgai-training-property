package com.sgai.property.ctl.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlNoticeDao;
import com.sgai.property.ctl.entity.CtlNotice;

/**
 * 消息通知Service
 * @author admin
 * @version 2018-06-15
 */
@Service
@Transactional
public class CtlNoticeService extends CrudServiceExt<CtlNoticeDao, CtlNotice> {

	public CtlNotice get(String id) {
		return super.get(id);
	}

	public List<CtlNotice> findList(CtlNotice ctlNotice) {
		return super.findList(ctlNotice);
	}

	public Page<CtlNotice> findPage(Page<CtlNotice> page, CtlNotice ctlNotice) {
		return super.findPage(page, ctlNotice);
	}

	@Transactional(readOnly = false)
	public void save(CtlNotice ctlNotice) {
		super.save(ctlNotice);
	}

	@Transactional(readOnly = false)
	public void delete(CtlNotice ctlNotice) {
		super.delete(ctlNotice);
	}
	/**
	 *
	    * @Title: haveRead
	    * @Description: (已读操作)
	    * @param @param id    参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void haveRead(String id) {
		CtlNotice ctlNotice = get(id);
		ctlNotice.setReadFlag("Y");
		save(ctlNotice);
	}
	@Transactional(readOnly = false)
	public void saveNotice(String userCode,Date noticeTime,String noticeInfo) {
		CtlNotice ctlNotice = new CtlNotice();
		ctlNotice.setNoticeInfo(noticeInfo);
		ctlNotice.setNoticeTime(noticeTime);
		ctlNotice.setUserCode(userCode);
		ctlNotice.setEnabledFlag("Y");
		ctlNotice.setReadFlag("N");
		save(ctlNotice);
	}
}
