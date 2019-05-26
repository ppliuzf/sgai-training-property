package com.sgai.property.em.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmEndedDao;
import com.sgai.property.em.entity.EmEnded;


/**
 *
    * @ClassName: EmEndedService
    * @com.sgai.property.commonService.vo;(事件终止Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmEndedService extends CrudServiceExt<EmEndedDao, EmEnded> {

	public EmEnded get(String id) {
		return super.get(id);
	}

	public List<EmEnded> findList(EmEnded emEnded) {
		return super.findList(emEnded);
	}

	public Page<EmEnded> findPage(Page<EmEnded> page, EmEnded emEnded) {
		return super.findPage(page, emEnded);
	}

	@Transactional(readOnly = false)
	public void save(EmEnded emEnded) {
		super.save(emEnded);
	}

	@Transactional(readOnly = false)
	public void delete(EmEnded emEnded) {
		super.delete(emEnded);
	}
	/**
	 *
	 * getByCode:(根据编码获取事件).
	 * @param emEnded
	 * @return :EmEnded
	 * @since JDK 1.8
	 * @author ASUS
	 */
	@Transactional(readOnly = false)
	public EmEnded getByCode(EmEnded emEnded) {
		emEnded.preGet();
		return dao.getByCode(emEnded);
	}
}
