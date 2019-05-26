package com.sgai.property.common.util;
import com.sgai.property.common.exception.SyswinException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * http帮助类 提供http相关的一些属性值和一些常用方法
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

//	public static String getScheme() {
//		return isUseSSL() ? "https" : "http";
//	}

	/**
	 * 返回http请求字符集
	 * 
	 * @return 默认字符集:utf-8
	 */
	public static String getCharset() {
		String charset = Configuration.getDefaultProperty("http.charset");
		if (StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		return charset;
	}

	/**
	 * 是否使用ssl
	 * 
	 * @return 默认不使用
	 */
//	public static boolean isUseSSL() {
//		return Boolean.valueOf(Configuration.getDefaultProperty("http.useSSL"));
//	}

	/**
	 * 是否自动处理重定向
	 * 
	 * @return 默认为true
	 */
	public static boolean isAutoRedirect() {
		return Boolean.valueOf(Configuration
				.getDefaultProperty("http.autoRedirect"));
	}

	/**
	 * 是否是采用post方式提交请求
	 * 
	 * @return 默认为false
	 */
	public static boolean isPost() {
		String requestType = Configuration
				.getDefaultProperty("http.requestType");
        return "numPost".equals(requestType);
    }

	public static int getConnectionTimeout() {
		try {
			return Integer.valueOf(Configuration
					.getDefaultProperty("http.connectTimeout"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getSocketTimeout() {
		try {
			return Integer.valueOf(Configuration
					.getDefaultProperty("http.socketTimeout"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getRetryCount() {
		try {
			return Integer.valueOf(Configuration
					.getDefaultProperty("http.retryCount"));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 连接池最大连接数
	 * 
	 * @return
	 */
	public static int getMaxConnection() {
		try {
			return Integer.valueOf(Configuration
					.getDefaultProperty("http.maxConnection"));
		} catch (Exception e) {
			return 1;
		}
	}

	/**
	 * 单路由最大连接数
	 * 
	 * @return
	 */
	public static int getMaxConPerRoute() {
		try {
			return Integer.valueOf(Configuration
					.getDefaultProperty("http.maxConPerRoute"));
		} catch (Exception e) {
			return 1;
		}
	}

	/**
	 * 根据给定entity获取字符串
	 * 
	 * @param entity
	 * @return
	 *             转换出错或关闭httpEntity
	 */
	public static String getStringFromEntity(HttpEntity entity) {
		if (entity == null) {
			return null;
		}
		String result = null;
		try {
			result = EntityUtils.toString(entity, getCharset());
			// 关闭httpEntity流
			EntityUtils.consume(entity);
		} catch (IOException e) {
			logger.debug("", e); // 关闭流的错误只记录下来
		} catch (Exception e) {
			throw new SyswinException(e); // 其它错误需要抛出
		}
		return result;
	}
}
