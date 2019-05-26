package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.IdGen;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.ctl.dao.CtlLogProgDao;
import com.sgai.property.ctl.entity.CtlLogProg;
import com.sgai.property.ctl.entity.CtlProg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


   /**
    * ClassName: CtlLogProgService
    * Description: (这里用一句话描述这个类的作用)
    * @author admin
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
    */
@Service
@Transactional
public class CtlLogProgService extends CrudServiceExt<CtlLogProgDao, CtlLogProg> {

	@Autowired
	private CtlProgService   ctlProgService;

	public CtlLogProg get(String id) {
		return super.get(id);
	}

	public List<CtlLogProg> findList(CtlLogProg ctlLogProg) {
		return super.findList(ctlLogProg);
	}

	public Page<CtlLogProg> findPage(Page<CtlLogProg> page, CtlLogProg ctlLogProg) {
		return super.findPage(page, ctlLogProg);
	}



	@Transactional(readOnly = false)
	public void save(CtlLogProg ctlLogProg) {
		super.save(ctlLogProg);
	}

	@Transactional(readOnly = false)
	public void delete(CtlLogProg ctlLogProg) {
		super.delete(ctlLogProg);
	}

	@Transactional(readOnly = false)
	public void doLogProg(HttpServletRequest request) throws Exception {
		String path = request.getServletPath();
		String token = request.getHeader("token");
		LoginUser loginUser = UserServletContext.getUserInfo();
		List<CtlProg> listCtlProg = null;
		if(token !=null){
		   listCtlProg =ctlProgService.findCtlProg(path,loginUser.getUserType());
		}
		if(listCtlProg.size() != 0) {
		CtlLogProg logProg = new CtlLogProg();
		logProg.setSessionId(IdGen.uuid());
		logProg.setComId(loginUser.getComCode());
		logProg.setUserCode(loginUser.getUserId());
		logProg.setUserName(loginUser.getUserName());
		logProg.setUserType(loginUser.getUserType());
		logProg.setComName(loginUser.getComName());
		this.save(logProg);
		}

	}
}
