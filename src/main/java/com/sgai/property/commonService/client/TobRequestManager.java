package com.sgai.property.commonService.client;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

//@Component
public class TobRequestManager implements Serializable {

	@Value("${qitoon.app.spring.scheme}")
	private String scheme;
	@Value("${qitoon.app.spring.basePath}")
	private String basePath;
	@Value("${qitoon.app.spring.baseUrl}")
	private String baseUrl;
	@Value("${qitoon.app.spring.authPath}")
	private String authPath;
	@Value("${qitoon.app.spring.authUrl}")
	private String authUrl;
	@Value("${qitoon.app.spring.cardPath}")
	private String cardPath;
	@Value("${qitoon.app.spring.cardUrl}")
	private String cardUrl;
	
	
	private static final long serialVersionUID = 2327162299350348931L;
	private Logger logger = LoggerFactory.getLogger(TobRequestManager.class);

	HttpClient httpClient = null;

	public TobRequestManager() {
	}

	/**
	 * 获取指定接口方法请求路径（路由地址）
	 * @return 返回调用方法的url
	 */
	public String getUrl() {
		String url = "";
		try {
			URI uri = new URIBuilder().setScheme(this.scheme)
					.setHost(this.basePath)
					.setPath(this.baseUrl).build();
			url = uri.toString();
		} catch (URISyntaxException e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}
		return url;
	}

	/**
	 * 获取指定接口方法请求路径（路由地址）
	 * @return 返回调用方法的url
	 */
	public String getAuthUrl() {
		String url = "";
		try {
			URI uri = new URIBuilder().setScheme(this.scheme)
					.setHost(this.authPath).build();
			url = uri.toString();
		} catch (Exception e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}

		return url;
	}
	
	public String getCardStrategyUrl() {
		String url = "";
		try {
			URI uri = new URIBuilder().setScheme(this.scheme)
					.setHost(this.cardPath)
					.setPath(this.cardUrl).build();
			url = uri.toString();
		} catch (URISyntaxException e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}

		return url;
	}

	/**
	 * 调用uum接口方法并获取返回值方法
	 * 
	 * @param method
	 *            uum接口方法地址
	 * @param clazz
	 *            接口返回值要转换的对象
	 * @return 返回指定对象的结果集
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	protected String requestData(String method, Map paramsMap) throws Exception {
		httpClient = new HttpClient("https".equals(this.scheme) ? true : false);
		if (!StringUtils.startsWithIgnoreCase(method, "/")) {
			method = "/" + method;
		}
		String url = getUrl() + method;
		try {
			HttpEntity entity = this.httpClient.requestData(url, paramsMap);
			if (entity != null) {
				return HttpUtil.getStringFromEntity(entity);
			} else {
				return "";
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new Exception("向统一用户管理请求连接被拒，请重试！");
		}
	}

	/**
	 * 调用cardstrategy接口方法并获取返回值方法
	 * 
	 * @param method
	 *            cardstrategy接口方法地址
	 * @param clazz
	 *            接口返回值要转换的对象
	 * @return 返回指定对象的结果集
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	protected String requestCardStrategyData(String method, Map paramsMap) throws Exception {
		httpClient = new HttpClient("https".equals(this.scheme) ? true : false);
		if (!StringUtils.startsWithIgnoreCase(method, "/")) {
			method = "/" + method;
		}
		String url = getCardStrategyUrl() + method;
		try {
			HttpEntity entity = this.httpClient.requestData(url, paramsMap);
			if (entity != null) {
				return HttpUtil.getStringFromEntity(entity);
			} else {
				return "";
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new Exception("向统一用户管理请求连接被拒，请重试！");
		}
	}
	
	/**
	 * 调用uum认证接口
	 * 
	 * @param method
	 *            uum接口方法地址
	 * @param clazz
	 *            接口返回值要转换的对象
	 * @return 返回指定对象的结果集
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	protected String requestAuthtData(String method, Map paramsMap)
			throws Exception {
		httpClient = new HttpClient("https".equals(this.scheme) ? true : false);
		if (!StringUtils.startsWithIgnoreCase(method, "/")) {
			method = "/" + method;
		}
		String url = getAuthUrl() + method;
		try {
			HttpEntity entity = this.httpClient.requestData(url, paramsMap);
			if (entity != null) {
				return HttpUtil.getStringFromEntity(entity);
			} else {
				return "";
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new Exception("向统一用户管理请求连接被拒，请重试！");
		}
	}
}
