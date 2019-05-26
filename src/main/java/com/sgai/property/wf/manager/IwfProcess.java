package com.sgai.property.wf.manager;

import java.util.List;
import java.util.Map;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.entity.WfInstanceRecord;

/**
 * 
    * ClassName: IwfProcess  
    * com.sgai.property.commonService.vo;(事件实例化驱动接口)
    * @author yangyz  
    * Date 2017年12月6日  
    * Company 首自信--智慧城市创新中心
 */
public interface IwfProcess {
	/**
	 * 
	 * startFlow:(流程启动).
	 * @param params
	 * @throws Exception :void 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    void startFlow(Map<String, Object> params, LoginUser user) throws Exception;
	/**
	 * 
	 * endFlow:(流程结束).
	 * @param instanceId :void 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    void endFlow(Map<String, Object> params, LoginUser user);
	/**
	 * 
	 * nextStep:(下一步处理).
	 * @param params :void 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    void nextStep(Map<String, Object> params, LoginUser user);
	/**
	 * 
	 * preStep:(上一步处理).
	 * @param params :void 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    void preStep(Map<String, Object> params, LoginUser user);
	
	/**
	 * 
	 * findProcessList:(查找某事件流程实例列表).
	 * @param instanceId
	 * @return :List<WfInstanceRecord> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<WfInstanceRecord> findProcessList(String emCode);
}

