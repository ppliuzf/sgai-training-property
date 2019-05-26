package com.sgai.property.commonService.client;

import com.alibaba.fastjson.JSONObject;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.Configuration;
import com.sgai.property.common.util.HttpUtil;
import com.sgai.property.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

//import com.qitoon.framework.organ.toon.util.http.HttpClient;

@SuppressWarnings({ "unused", "deprecation" })
public class HttpClient implements Serializable {
	
	private boolean isUseSSL;

	public boolean isUseSSL() {
		return isUseSSL;
	}

	public void setUseSSL(boolean isUseSSL) {
		this.isUseSSL = isUseSSL;
	}

	private static Logger logger = LoggerFactory.getLogger(HttpClient.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -6885337978793941734L;
	// private static HttpClient httpClient = new HttpClient();

	
	
	public HttpClient(Boolean isUseSSL) {
		this.setUseSSL(isUseSSL);
		initHttpClient();
	}

	// public static HttpClient getInstance() {
	// return HttpClient.httpClient;
	// }

	public HttpClient() {
		initHttpClient();
	}

	private static final int OK = 200;// OK: Success!
	private static final int NOT_MODIFIED = 304;// Not Modified: There was no
												// new data to return.
	private static final int BAD_REQUEST = 400;// Bad Request: The request was
												// invalid. An accompanying
												// error message will explain
												// why. This is the status code
												// will be returned during rate
												// limiting.
	private static final int NOT_AUTHORIZED = 401;// Not Authorized:
													// Authentication
													// credentials were missing
													// or incorrect.
	private static final int FORBIDDEN = 403;// Forbidden: The request is
												// understood, but it has been
												// refused. An accompanying
												// error message will explain
												// why.
	private static final int NOT_FOUND = 404;// Not Found: The URI requested is
												// invalid or the resource
												// requested, such as a user,
												// does not exists.
	private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by
													// the Search API when an
													// invalid format is
													// specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
															// Error: Something
															// is broken. Please
															// numPost to the group
															// so the Weibo team
															// can investigate.
	private static final int BAD_GATEWAY = 502;// Bad Gateway: Weibo is down or
												// being upgraded.
	private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:
														// The Weibo servers are
														// up, but overloaded
														// with requests. Try
														// again later. The
														// search and trend
														// methods use this to
														// indicate when you are
														// being rate limited.

	private static Log log = LogFactory.getLog(HttpClient.class);
	private final static boolean DEBUG = Configuration.getDebug();

	/**
	 * 记录日志
	 */
	private static void log(Exception e) {
		if (DEBUG) {
			log.debug(e);
		}
	}

	/**
	 * CloseableHttpClient
	 */
	private CloseableHttpClient closeableHttpClient = null;

	private static HttpRequestRetryHandler retryHandler = null;

	/**
	 * 信任证书
	 */
	private X509Certificate certificate = null;

	/**
	 * 请求配置
	 */
	private RequestConfig requestConfig = null;

	public RequestConfig getRequestConfig() {
		if (requestConfig == null) {
			setDefaultRequestConfig();
		}
		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	public HttpRequestRetryHandler getRetryHandler() {
		if (retryHandler == null)
			setDefaultRetryHandler();
		return retryHandler;
	}

	public void setRetryHandler(HttpRequestRetryHandler retryHandler) {
		HttpClient.retryHandler = retryHandler;
	}

	public CloseableHttpClient getCloseableHttpClient() {
		return closeableHttpClient;
	}

	public void setCloseableHttpClient(CloseableHttpClient closeableHttpClient) {
		this.closeableHttpClient = closeableHttpClient;
	}

	/**
	 * 默认retry回调方法
	 */
	public static void setDefaultRetryHandler() {
		retryHandler = new HttpRequestRetryHandler() {

			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				int retryCount = HttpUtil.getRetryCount();
				// 超过最大连接次数，返回false
				if (executionCount > retryCount) {
					return false;
				}
				if (exception instanceof InterruptedIOException) {// io操作中断
					return false;
				}
				if (exception instanceof UnknownHostException) {// 未找到主机
					// Unknown host
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接超时
					return true;
				}
				if (exception instanceof SSLException) {
					// SSL handshake exception
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);

				HttpRequest request = clientContext.getRequest();

				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);

                return idempotent;
            }
		};
	}

	/**
	 * 设置请求配置
	 */
	public void setDefaultRequestConfig() {
		requestConfig = RequestConfig.custom()
				.setConnectTimeout(HttpUtil.getConnectionTimeout())
				.setSocketTimeout(HttpUtil.getSocketTimeout())
				.build();
	}

	/**
	 * 初始化
	 */
	public void initHttpClient() {
		// 集成连接管路器
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
		Registry<ConnectionSocketFactory> registry = null;
		// http协议采用默认处理方式
		ConnectionSocketFactory plainsf = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainsf);
		// https协议只支持证书支持项
//		if (HttpUtil.isUseSSL()) {
		if(isUseSSL()) {
			// 暂不初始化证书
			// initCertificate();
			LayeredConnectionSocketFactory sslsf = getSSLSocketFactory();
			registryBuilder.register("https", sslsf);
		}

		registry = registryBuilder.build();
		// 创建httpclient连接池
		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);

		httpClientConnectionManager.setMaxTotal(HttpUtil.getMaxConnection()); // 设置连接池线程最大数量
		httpClientConnectionManager.setDefaultMaxPerRoute(HttpUtil.getMaxConPerRoute()); // 设置单个路由最大的连接线程数量

		// 设置重定向策略
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager)
				.setRetryHandler(getRetryHandler())
				.setDefaultRequestConfig(getRequestConfig())
				.setRedirectStrategy(redirectStrategy);

		// 初始化httpclient客户端
		setCloseableHttpClient(httpClientBuilder.build());
	}

	protected void initCertificate() {
		CertificateFactory certificateFactory;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
		} catch (Exception e1) {
			throw new BusinessException(ReturnType.ServiceError,e1.getMessage());
		}
		// 获取证书文件路径
		String certFileFile = Configuration.getDefaultProperty("https.certificateFile");
		InputStream is = null;
		if (StringUtils.isEmpty(certFileFile)) {
			// 若没有配置值则查询class路径下
			String name = Configuration.getDefaultProperty("https.certificateName");
			if (StringUtils.isEmpty(name)) {
				throw new BusinessException(ReturnType.ServiceError, "没有配置证书文件路径");
			}
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		} else {
			try {
				// 根据用户配置信息读取文件
				is = new FileInputStream(new File(certFileFile));
			} catch (FileNotFoundException e) {
				// 判断是否能在classes路径下找到
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(certFileFile);
				if (is == null){
					throw new BusinessException(ReturnType.ServiceError,"读取证书文件 [\" + certFileFile + \"] 失败");
				}
			}
		}

		if (is == null) {
			return;
		}
		try {
			certificate = (X509Certificate) certificateFactory.generateCertificate(is);
		} catch (Exception e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				log(e);
			}
		}
	}

	/**
	 * <pre>
	 * 定制ssl
	 * </pre>
	 * 
	 * 创建一个{@link LayeredConnectionSocketFactory}实例，该实例由
	 * {@link SSLConnectionSocketFactory}实现。 使用tls，信任策略由
	 * {@link #getNewTrustStrategy()}实现。且使用严格的主机名验证方式
	 * {@link SSLConnectionSocketFactory#STRICT_HOSTNAME_VERIFIER}
	 * 
	 * @return 返回一个SSLConnectionSocketFactory实例
	 */
	protected LayeredConnectionSocketFactory getSSLSocketFactory() {
		KeyStore myTrustStore = getKeyStore();
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(myTrustStore, getNewTrustStrategy()).build();
			if (sslContext == null)
				sslContext = SSLContexts.createDefault();
		} catch (Exception e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}

		// 严格的主机名验证
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
				SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);
		return sslsf;
	}

	/**
	 * 获取keystore
	 * 
	 * @return 返回一个默认的keystore
	 */
	protected KeyStore getKeyStore() {
		// 获取默认类型的keystore
		KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (Exception e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}
		return keyStore;
	}

	/**
	 * 获取信任策略,只有当证书链中包含指定证书，且该证书处于有效期内才作为信赖证书，否则不信任
	 * 
	 * @return 返回一个新创建的特定的信赖策略实例
	 */
	protected TrustStrategy getNewTrustStrategy() {
		return new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] chain, String authType) {
				/*
				 * if (certificate == null) return false;
				 * 
				 * // 判断传入的证书链是否包含指定的证书文件 log.debug("---开始证书验证---"); for
				 * (X509Certificate cer: chain){ cer.checkValidity(); //
				 * 验证证书有效期，无效证书直接抛出异常 try {
				 * cer.verify(certificate.getPublicKey()); // 验证签名
				 * log.debug("---结束证书验证：验证签名成功---"); return true; // 包含，此证书为信赖证书
				 * } catch (Exception e) { log.debug("验证证书链:" + e.getMessage());
				 * if (cer.equals(certificate)){ // 放宽限制, 对于非RSA签名证书，判断两者证书是否一致
				 * log.debug("---结束证书验证：验证证书相等成功---"); return true; } } }
				 * log.debug("---结束证书验证：验证失败---"); return false; //
				 * 不包含，传入证书不包含信赖证书
				 */
				return true;
			}
		};
	}

	/**
	 * 请求数据，根据配置使用相应的请求方式 numPost/get 默认为get
	 * 
	 * @param url
	 *            提供请求路径
	 * @param params
	 *            提供请求参数
	 * @return 返回响应实体
	 */
	public HttpEntity requestData(String url, Map<String, Object> params) {
		if (HttpUtil.isPost()) {
			return doPostHttp(url, params);
		}else {
			return doGetHttp(url, params);
		}
	}

	/**
	 * 以post方式发送请求,默认使用utf-8字符集
	 * 
	 * @param url 请求路径
	 * @param params 请求参数
	 * @return 返回响应实体
	 */
	public HttpEntity doPost(String url, Map<String, Object> params) {
		return doPostHttp(url, params, HttpUtil.getCharset());
	}

	public HttpEntity doGet(String url, Map<String, Object> params) {
		return doGetHttp(url, params, HttpUtil.getCharset());
	}

	public HttpEntity doPostHttp(String url, Map<String, Object> params) {
		return doPostHttp(url, params, HttpUtil.getCharset());
	}

	/**
	 * 以get方式发送http请求,默认使用utf-8字符集<br/>
	 * 
	 * @param url
	 *            可采用下述方式构建url<br/>
	 *            uri = new URIBuilder()<br/>
	 *            .setScheme("http")<br/>
	 *            .setHost("www.syswin.com")<br/>
	 *            .setPath("/api")<br/>
	 *            .setParameter("", "")//可以通过setParameter设置多个参数<br/>
	 *            .setParameter("", "")<br/>
	 *            .setParameter("", "")<br/>
	 *            .build();<br/>
	 * @param params
	 * @return 返回值可采用下述方式得到字符串<br/>
	 *         HttpEntity entity = response.getEntity();<br/>
	 *         String result = null;<br/>
	 *         if (entity != null){<br/>
	 *         result = EntityUtils.toString(entity, charset);<br/>
	 *         }<br/>
	 *         //关闭httpEntity流<br/>
	 *         EntityUtils.consume(entity);<br/>
	 */
	public HttpEntity doGetHttp(String url, Map<String, Object> params) {
		return doGetHttp(url, params, HttpUtil.getCharset());
	}

	/**
	 * 以post方式发送http请求
	 * 
	 * @param url 请求url
	 * @param params 请求参数
	 * @param charset 字符集
	 * @return 返回请求返回实体
	 */
	@SuppressWarnings("resource")
	public HttpEntity doPostHttp(final String url, Map<String, Object> params, String charset) {
		if (StringUtils.isEmpty(url))
			return null;

		HttpPost httpPost = new HttpPost(url);

		try {
			/*
			 * httpPost.addHeader("Content-type",
			 * "application/json; charset=utf-8"); httpPost.setHeader("Accept",
			 * "application/json");
			 */
			httpPost.addHeader("Content-type", "application/json;charset=UTF-8");

			if (params.containsKey("access_token") && null != params.get("access_token")) {
				httpPost.addHeader("accesstoken", params.get("access_token").toString());
				params.remove("access_token");
			}
			
//			List<NameValuePair> pairs = MapUtils.mapToList(params);
//			if (pairs != null) {				
//				httpPost.setEntity(new StringEntity(JSONObject.toJSONString(pairs), Charset.forName("UTF-8")));
//			}
			if(params.containsKey("jsonStr") && null != params.get("jsonStr")) {				
				String jsonStr = (String) params.get("jsonStr");
				httpPost.setEntity(new StringEntity(jsonStr, Charset.forName("UTF-8")));
			}
			if(params.containsKey("ids") && null != params.get("ids")) {				
				@SuppressWarnings("unchecked")
				List<String> list = (List<String>) params.get("ids");
				httpPost.setEntity(new StringEntity(JSONObject.toJSONString(list), Charset.forName("UTF-8")));
			}


			// 创建响应控制器
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = httpClient.execute(httpPost).getEntity();

			return httpEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		}
	}

	/**
	 * 以get方式发送http请求
	 * 
	 * @param url 请求url
	 * @param params 传递的参数集
	 * @param charset 参数使用的字符集
	 * @return 返回请求返回实体
	 */
	public HttpEntity doGetHttp(final String url, Map<String, Object> params, String charset) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}

		String fullUrl = new String(url);
		String access_token = "";
		try {
			if (params.containsKey("access_token")) {
				access_token = params.get("access_token").toString();
				params.remove("access_token");
			}
//			List<NameValuePair> pairs = MapUtils.mapToList(params);

//			if (pairs != null && params.size() > 0) {
//				String label = "?";
//				if (url.contains("?"))
//					label = "&";
//				fullUrl += label + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//			}

			HttpGet httpGet = new HttpGet(fullUrl);
			if (!access_token.isEmpty()) {
				httpGet.addHeader("accesstoken", access_token);
			}

			// 创建响应控制器
			ResponseHandler<HttpEntity> handler = createResponseHandler(url);

			HttpEntity httpEntity = closeableHttpClient.execute(httpGet, handler);

			return httpEntity;
		} catch (Exception e) {
			throw new BusinessException(ReturnType.ServiceError,e.getMessage());
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public void closeHttpClient() {
//		try {
//			if (closeableHttpClient != null) {
//				closeableHttpClient.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 创建一个响应控制器
	 * @return 返回一个请求响应控制实例
	 */
	private ResponseHandler<HttpEntity> createResponseHandler(final String url) {
		return new ResponseHandler<HttpEntity>() {
			public HttpEntity handleResponse(HttpResponse response) throws IOException {
				int status = response.getStatusLine().getStatusCode();
				if (status != OK) {
					throw new BusinessException(ReturnType.ServiceError,"request [" + url + "] failed, detail: " + getCause(status));
				}

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return new BufferedHttpEntity(entity);
				} else {
					return null;
				}
			}
		};
	}

	/**
	 * 获取状态描述
	 * 
	 * @param statusCode
	 * @return
	 */
	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested.";
			break;
		case NOT_ACCEPTABLE:
			cause = "Returned by the Search API when an invalid format is specified in the request.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken. Please numPost to the group so the Toon-Auth team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "Toon-Auth is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The Toon-Auth servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}
}
