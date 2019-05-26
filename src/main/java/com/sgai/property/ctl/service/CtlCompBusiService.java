package com.sgai.property.ctl.service;

import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlCompBusiDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlCompBusi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 机构子系统关系Service
 * @author admin
 * @version 2018-03-28
 */
@Service
@Transactional
public class CtlCompBusiService extends CrudServiceExt<CtlCompBusiDao, CtlCompBusi> {

	@Autowired
	private CtlBusinessDefineService ctlBusinessDefineService;

	public CtlCompBusi get(String id) {
		return super.get(id);
	}

	public List<CtlCompBusi> findList(CtlCompBusi ctlCompBusi) {
		return super.findList(ctlCompBusi);
	}

	public Page<CtlCompBusi> findPage(Page<CtlCompBusi> page, CtlCompBusi ctlCompBusi) {
		return super.findPage(page, ctlCompBusi);
	}


	/**
	 * 机构指定子系统时 选择没有被指定过的子系统
	 * findLackList:(这里用一句话描述这个方法的作用).
	 * @param page
	 * @param ctlBusinessDefine
	 * @return :Page<CtlBusinessDefine>
	 * @since JDK 1.8
	 * @author admin
	 */
	public Page<CtlBusinessDefine> findBusiPageList(Page<CtlBusinessDefine> page,CtlBusinessDefine  ctlBusinessDefine){
		ctlBusinessDefine.setPage(page);
		page.setList(ctlBusinessDefineService.findBusiList(ctlBusinessDefine));
		return page;
	}


	/**
	 * 在机构关联子系统页面 保存选择后的关联结果
	 * saveCompBusi:(这里用一句话描述这个方法的作用).
	 * @param compCode  机构编码
	 * @param busiCode  子系统编码
	 * @param previousBusiCode    原子系统编码
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author admin
	 */
	public Map<String ,String> saveCompBusi(String compCode,String busiCode,String previousBusiCode) {
		Map<String ,String>  mapResult =  Maps.newHashMap();
		if(previousBusiCode != null && !"".equals(previousBusiCode)) {
			if(compCode.equals(previousBusiCode)) {
				mapResult.put("msg", "success");
				return mapResult;
			}
			deleteByCompCode(compCode);
			CtlCompBusi   ctlCompBusi  = new CtlCompBusi();
			ctlCompBusi.setComCode(compCode);
			ctlCompBusi.setBusiCode(busiCode);
			this.save(ctlCompBusi);
			mapResult.put("msg", "success");
			return mapResult;
		}
		CtlCompBusi   ctlCompBusi  = new CtlCompBusi();
		ctlCompBusi.setComCode(compCode);
		ctlCompBusi.setBusiCode(busiCode);
		this.save(ctlCompBusi);
		mapResult.put("msg", "success");
		return  mapResult;
	}


	/**
	 * 删除机构和子系统的关联关系
	 * deleteByCompCode:(这里用一句话描述这个方法的作用).
	 * @since JDK 1.8
	 * @author admin
	 */
	public void deleteByCompCode(String compCode) {
		dao.deleteByCompCode(compCode);
	}


	@Transactional(readOnly = false)
	public void save(CtlCompBusi ctlCompBusi) {
		super.save(ctlCompBusi);
	}

	@Transactional(readOnly = false)
	public void delete(CtlCompBusi ctlCompBusi) {
		super.delete(ctlCompBusi);
	}

}
