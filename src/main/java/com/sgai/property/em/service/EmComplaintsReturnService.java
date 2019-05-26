package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmComplaintsReturnDao;
import com.sgai.property.em.entity.EmComplaintsReturn;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 *
    * ClassName: EmComplaintsReturnService
    * com.sgai.property.commonService.vo;(这里用一句话描述这个类的作用)
    * @author yangyz
    * Date 2017年12月13日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmComplaintsReturnService extends CrudServiceExt<EmComplaintsReturnDao, EmComplaintsReturn> {

	@Autowired
	private EmComplaintsService emComplaintsService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;

	public EmComplaintsReturn get(String id) {
		return super.get(id);
	}

	public List<EmComplaintsReturn> findList(EmComplaintsReturn emComplaintsReturn) {
		return super.findList(emComplaintsReturn);
	}

	public Page<EmComplaintsReturn> findPage(Page<EmComplaintsReturn> page, EmComplaintsReturn emComplaintsReturn) {
		return super.findPage(page, emComplaintsReturn);
	}

	@Transactional(readOnly = false)
	public void save(EmComplaintsReturn emComplaintsReturn) {
		super.save(emComplaintsReturn);
	}

	@Transactional(readOnly = false)
	public void delete(EmComplaintsReturn emComplaintsReturn) {
		super.delete(emComplaintsReturn);
	}

	/**
	 *
	 * getComplaintsReturn:(查询回访内容).
	 * @param emComplaintsReturn
	 * @return :EmComplaintsReturn
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public EmComplaintsReturn getComplaintsReturn(EmComplaintsReturn emComplaintsReturn) {
		return dao.getComplaintsReturn(emComplaintsReturn);
	}

	/**
	 *
	 * saveReturn:(保存回访信息).
	 * @param emComplaintsReturn
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 * @throws Exception
	 */
	public Map<String, Object> saveReturn(EmComplaintsReturn emComplaintsReturn,LoginUser user) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		emComplaintsReturn.setProcPerson(user.getUserName());
		super.save(emComplaintsReturn);
		Map<String, Object> params = new HashMap<String, Object>();
		//如果是投诉事件回访
		if ("TS".equals(emComplaintsReturn.getEmType())) {
			emComplaintsService.updateComplaint(emComplaintsReturn.getEmCode(), "3",user.getUserId(),user.getUserName());
			params.put("emCode", emComplaintsReturn.getEmCode());
			params.put("stepCode", "TS-D-001");
			params.put("type", "next");
			params.put("flowCode", emComplaintsReturn.getEmType());
			wfInstanceRecordService.changeStatus(params,user);
		}
		map.put("result", "success");
		return map;
	}

}
