package com.sgai.property.commonService.client;

import com.sgai.property.common.util.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
public class TobApi extends TobRequestManager {

	private static final long serialVersionUID = 1L;

	private String access_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * 获取所有用户
	 * @return
	 * @throws Exception
	 */
	public String getAllUsers() throws Exception {
		initHttpRequestType("get");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String accessToken = this.getAccess_token();
		paramsMap.put("access_token", accessToken);
		String json = this.requestData("/get_all_users", paramsMap);
		return json;
	}
	
	/**
	 * 获取所有角色
	 * @return
	 * @throws Exception
	 */
	public String getAllRoles() throws Exception {
		initHttpRequestType("get");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String accessToken = this.getAccess_token();
		paramsMap.put("access_token", accessToken);
		String json = this.requestData("/get_role_simplelist?id=-1", paramsMap);
		return json;
	}
	
	/**
	 * 获取所有组织单元
	 * @return
	 * @throws Exception
	 */
	public String getAllOrganUnit() throws Exception {
		initHttpRequestType("get");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String accessToken = this.getAccess_token();
		paramsMap.put("access_token", accessToken);
		String json = this.requestData("/get_organ_unit?id=-1", paramsMap);
		return json;
	}
	
	/**
	 * 通过名片id获取toon_user_id
	 * @return
	 * @throws Exception
	 */
	public String getToonUserId(List<String> ids) throws Exception {
		initHttpRequestType("numPost");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String accessToken = this.getAccess_token();
		paramsMap.put("access_token", accessToken);
		paramsMap.put("ids", ids);
		String json = this.requestCardStrategyData("/getStaffCardList", paramsMap);
		return json;
	}

	/**
	 * 设置Get\Post请求
	 */
	private void initHttpRequestType(String requestType) {
		Configuration.setDefaultProperty("http.requestType", requestType);
	}
	
//	public String getAccessToken(Map<String, Object> paramsMap)
//			throws Exception {
//		initHttpRequestType("get");
//		initHttpRequestType("get");
//		String json = this.requestAuthtData(
//				"/get_token?access_id="
//						+ paramsMap.get("access_id") + "&access_secret="
//						+ paramsMap.get("access_secret"), paramsMap);
//		return json;
//	}

}