package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.service.CtlAttachfileService;
import com.sgai.property.em.dao.EmComplaintsDao;
import com.sgai.property.em.dto.EmComplaintsVo;
import com.sgai.property.em.dto.EmEvnetVo;
import com.sgai.property.em.entity.EmComplaints;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 * 投诉事件维护Service
 * @author guanze
 * @version 2017-12-05
 */
@Service
@Transactional
public class EmComplaintsService extends CrudServiceExt<EmComplaintsDao, EmComplaints> {

	@Autowired
	private EmComplaintsDao emComplaintsDao;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private CtlAttachfileService ctlAttachfileService;
	/*@Autowired
	private Sender  sender;*/

	public EmComplaints get(String id) {
		return super.get(id);
	}

	public List<EmComplaints> findList(EmComplaints emComplaints) {
		return super.findList(emComplaints);
	}

	public Page<EmComplaints> findPage(Page<EmComplaints> page, EmComplaints emComplaints) {
		page.setOrderBy("UPDATED_DT");
		return super.findPage(page, emComplaints);
	}

	public Page<EmComplaints> findComplaintsPage(Page<EmComplaints> page, EmComplaints emComplaints) {
		page.setOrderBy("a.UPDATED_DT");
		emComplaints.setPage(page);
		emComplaints.preGet();
		page.setList(dao.findComplaintsList(emComplaints));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(EmComplaints emComplaints) {
		super.save(emComplaints);
	}

	@Transactional(readOnly = false)
	public void delete(EmComplaints emComplaints) {
		super.delete(emComplaints);
	}

	@Transactional(readOnly = false)
	public List<EmComplaints> batchDelete(List<EmComplaints> objs) {
		return super.batchDelete(objs);
	}

	@Transactional(readOnly = false)
	public EmComplaints findNextCodeEmComplaints() {
		return emComplaintsDao.findNextCodeEmComplaints();
	}

	/**
	 *
	 * saveComplaint:(新增保存投诉事件).
	 * @param emComplaints
	 * @throws Exception :void
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public void saveComplaint(EmComplaints emComplaints, LoginUser user) throws Exception {
		super.save(emComplaints);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emComplaints.getComplCode());
		params.put("stepCode", "TS-A-001");
		params.put("type", "start");
		params.put("flowCode", "TS");
		wfInstanceRecordService.changeStatus(params,user);
	}

	/**
	 *
	 * updateComplaint:(更新投诉事件状态).
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public void updateComplaint(String complCode, String status ,String userCode, String userName) {
		EmComplaints emComplaints = new EmComplaints();
		emComplaints.setComplCode(complCode);
		emComplaints.preGet();
		EmComplaints info = dao.getEmComplaint(emComplaints);
		info.setStates(status);
		info.setProcPerson(userCode);
		if ("2".equals(status)) {
			info.setUserCode("");
		}else {
			info.setUserCode(userCode);
		}
		info.setUserName(userName);
		super.save(info);
	}

	/**
	 *
	 * getEmComplaints:(查询投诉事件).
	 * @return :EmComplaints
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public EmComplaintsVo getEmComplaints(EmComplaintsVo emComplaintsVo) {
		emComplaintsVo.preGet();
		return dao.getEmComplaints(emComplaintsVo);
	}

	/**
	 *
	 * findSkanPage:(查询已完成和已终止事件).
	 * @param page
	 * @param emComplaints
	 * @return :Page<EmComplaints>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Page<EmComplaints> findSkanPage(Page<EmComplaints> page, EmComplaints emComplaints) {
		emComplaints.setPage(page);
		emComplaints.preGet();
		page.setList(dao.findSkanList(emComplaints));
		return page;
	}

	/**
	 *
	 * findAppPage:(查询所有事件).
	 * @param page
	 * @param emEventVo
	 * @return :Page<EmEvnetVo>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Page<EmEvnetVo> findAppPage(Page<EmEvnetVo> page, EmEvnetVo emEventVo) {
		//page.setOrderBy(emEventVo.getEmTime());
		emEventVo.setPage(page);
		emEventVo.preGet();
		page.setList(dao.findAppList(emEventVo));
		return page;
	}


    /**
    * @Title: findUnhandledEventByPage
    * @Description: 获取当前用户的权限下未处理的事件【分页】 home页面  homeMsg页面
    * @param @param page
    * @param @param emEventVo
    * @param @return    参数
    * @return Page<EmEvnetVo>    返回类型
    * @author admin
    * @throws
    */
	public Page<EmEvnetVo> findUnhandledEventByPage(Page<EmEvnetVo> page, EmEvnetVo emEventVo) {
		//page.setOrderBy(emEventVo.getEmTime());
		emEventVo.setPage(page);
		emEventVo.preGet();
		page.setList(dao.findUnhandledEvent(emEventVo));
		return page;
	}

	/**
	 *
	 * findComplaintList:(查询投诉事件大屏展示数据).
	 * @param pageNo
	 * @param pageSize
	 * @param comCode
	 * @throws Exception :void
	 * @since JDK 1.8
	 * @author yangyz
	 *//*
	public void findComplaintList(Integer pageNo,Integer pageSize,String comCode) throws Exception {
		JSONObject msgJson = new JSONObject();
		JSONObject resultJson = new JSONObject();
		JSONArray arrayJson = new JSONArray();
		boolean states;
		String msg;
		long total = 0;
		int totalPage = 0;
		try{
			EmComplaints emComplaints = new EmComplaints();
			emComplaints.setComCode(comCode);//机构代码
			Page<EmComplaints> page = findPage(new Page<EmComplaints>(pageNo, pageSize), emComplaints);
			if(page.getList().size()>0){
				states = true;
				msg = "操作成功！";
				total = page.getCount();
				totalPage = page.getTotalPage();
				for(EmComplaints info:page.getList()){
					resultJson = new JSONObject();
					resultJson.put("emId", info.getId()==null?"":info.getId());//主键
					resultJson.put("emCode", info.getComplCode()==null?"":info.getComplCode());//事件编号
					resultJson.put("emCategory", info.getCompCategory()==null?"":info.getCompCategory());//事件类别
					resultJson.put("emType", info.getComplType()==null?"":info.getComplType());//事件类型
					resultJson.put("emFrom", info.getComplFrom()==null?"":info.getComplFrom());//来源
					resultJson.put("emTime", info.getComplTime()==null?"":info.getComplTime());//时间
					resultJson.put("emPerson", info.getComplPerson()==null?"":info.getComplPerson());//提报人
					resultJson.put("emTelephone", info.getTelphone()==null?"":info.getTelphone());//投诉电话
					resultJson.put("emAddress", info.getAddress()==null?"":info.getAddress());//地址
					resultJson.put("emContent", info.getComplContent()==null?"":info.getComplContent());//内容
					resultJson.put("emDesc", info.getDesc()==null?"":info.getDesc());//投诉描述
					resultJson.put("emState", info.getStates()==null?"":info.getStates());//状态
					CtlAttachfile inAttachfile = new CtlAttachfile();
					inAttachfile.setMasterFileId(info.getId());
					List<CtlAttachfile> list = ctlAttachfileService.findList(inAttachfile);
					if (list.size() > 0) {
						resultJson.put("emOthers", "1");//附件 0：无附件 1：有附件
					}else {
						resultJson.put("emOthers", "0");//附件 0：无附件 1：有附件
					}
					arrayJson.add(resultJson);
				}
			}else{
				states = false;
				msg = "未查到数据！";
			}

		}catch(Exception e){
			e.printStackTrace();
			states = false;
			msg = "操作失败！";
		}
		msgJson.put("states", states);
		msgJson.put("msg", msg);
		msgJson.put("total", total);
		msgJson.put("totalPage", totalPage);
		msgJson.put(Constant.TS_SEND, arrayJson);
		sender.send(msgJson.toString());
	}*/

}
