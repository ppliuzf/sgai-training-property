package com.sgai.property.common.util;

import com.alibaba.fastjson.JSONObject;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * Http/Https 调用工具类
 * <p>
 * SPDY 调用需要添加jvm参数
 * -Xbootclasspath/p:npn-boot-1.1.7.v20140316.jar
 * -Xbootclasspath/p:alpn-boot-7.1.2.v20141202.jar
 */
public class HttpsUtils {
    private static HttpsUtils instance=new HttpsUtils();
    private HttpsUtils(){}
    public static HttpsUtils getInstance(){
        return instance;
    }

    public  HttpEntity doPostJsonHttp(final String url, Map<String, Object> params) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            if (params != null && params.containsKey("accesstoken")) {
                httpPost.addHeader("accesstoken", params.get("accesstoken").toString());
                params.remove("accesstoken");
            }
            Object value = null;
            for (String key : params.keySet()) {
                value = params.get(key);
            }
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(value), Charset.forName("UTF-8")));

            // 创建响应控制器
            DefaultHttpClient httpClient = new DefaultHttpClient();
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[] {};
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { x509TrustManager }, null);
            SSLSocketFactory sFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme scheme = new Scheme("https", 443, sFactory);
            httpClient.getConnectionManager().getSchemeRegistry().register(scheme);
            HttpEntity httpEntity = httpClient.execute(httpPost).getEntity();
            return httpEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ReturnType.Error,"获取名片详情失败");
        }
    }
    
	/**
	 * 以get方式发送http请求
	 * 
	 * @param url 请求url
	 * @param params 传递的参数集
	 * @return 返回请求返回实体
	 */
	public HttpEntity doGetHttp(final String url, Map<String, Object> params/*, String charset*/) {
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
			
			List<NameValuePair> pairs = MapUtils.mapToList(params);
			if (pairs != null && params.size() > 0) {
				String label = "?";
				if (url.contains("?")) {
                    label = "&";
                }
				fullUrl += label + EntityUtils.toString(new UrlEncodedFormEntity(pairs, "UTF-8"));
			}

			HttpGet httpGet = new HttpGet(fullUrl);
			if (!access_token.isEmpty()) {
				httpGet.addHeader("accesstoken", access_token);
			}

			// 创建响应控制器
			DefaultHttpClient httpClient = new DefaultHttpClient();
            X509TrustManager x509TrustManager = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[] {};
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { x509TrustManager }, null);
            SSLSocketFactory sFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Scheme scheme = new Scheme("https", 443, sFactory);
            httpClient.getConnectionManager().getSchemeRegistry().register(scheme);
            HttpEntity httpEntity = httpClient.execute(httpGet).getEntity();
			return httpEntity;
		} catch (Exception e) {
			 e.printStackTrace();
	         throw new BusinessException(ReturnType.Error,"获取公司名片详情失败");
		}
	}
	
	
}