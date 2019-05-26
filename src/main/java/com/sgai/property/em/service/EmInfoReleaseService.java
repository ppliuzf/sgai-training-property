package com.sgai.property.em.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmInfoReleaseDao;
import com.sgai.property.em.entity.EmInfoRelease;


/**
 *
    * @ClassName: EmInfoReleaseService
    * @com.sgai.property.commonService.vo;(信息发布Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmInfoReleaseService extends CrudServiceExt<EmInfoReleaseDao, EmInfoRelease> {

	public EmInfoRelease get(String id) {
		return super.get(id);
	}

	public List<EmInfoRelease> findList(EmInfoRelease emInfoRelease) {
		return super.findList(emInfoRelease);
	}

	public Page<EmInfoRelease> findPage(Page<EmInfoRelease> page, EmInfoRelease emInfoRelease) {
		return super.findPage(page, emInfoRelease);
	}

	@Transactional(readOnly = false)
	public void save(EmInfoRelease emInfoRelease) {
		super.save(emInfoRelease);
	}

	@Transactional(readOnly = false)
	public void delete(EmInfoRelease emInfoRelease) {
		super.delete(emInfoRelease);
	}

}
