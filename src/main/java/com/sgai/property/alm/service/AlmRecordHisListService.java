package com.sgai.property.alm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.alm.dao.AlmRecordHisListDao;
import com.sgai.property.alm.entity.AlmRecordHisList;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlUserService;
import net.sf.json.JSONObject;

/**
 *
 * ClassName: AlmRecordHisListService com.sgai.property.commonService.vo;(报警记录历史表Service)
 *
 * @author 王天尧 Date 2017年11月26日 Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class AlmRecordHisListService extends CrudServiceExt<AlmRecordHisListDao, AlmRecordHisList> {

	@Autowired
	private CtlUserService ctlUserService;

	public AlmRecordHisList get(String id) {
		return super.get(id);
	}

	public List<AlmRecordHisList> findList(AlmRecordHisList almRecordHisList) {
		return super.findList(almRecordHisList);
	}

	public Page<AlmRecordHisList> findPage(Page<AlmRecordHisList> page, AlmRecordHisList almRecordHisList) {
		return super.findPage(page, almRecordHisList);
	}

	@Transactional(readOnly = false)
	public void save(AlmRecordHisList almRecordHisList) {
		super.save(almRecordHisList);
	}

	@Transactional(readOnly = false)
	public void delete(AlmRecordHisList almRecordHisList) {
		super.delete(almRecordHisList);
	}

	/**
	 *
	 * @Title: getMsgById @com.sgai.property.commonService.vo;(获取处理信息详情) @param @param
	 * id @param @return 参数 @return Map<String,Object> 返回类型 @throws
	 */
	public Map<String, Object> getMsgById(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		AlmRecordHisList almRecordList = get(id);
		result.put("date", almRecordList.getProcessTime());
		CtlUser user = new CtlUser();
		user.setUserCode(almRecordList.getUpdatedBy());
		List<CtlUser> findList = ctlUserService.findAllList(user);
		result.put("person", findList.get(0).getUserName());
		result.put("remarks", almRecordList.getRemarks());
		return result;

	}


}
