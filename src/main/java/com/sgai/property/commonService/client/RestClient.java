package com.sgai.property.commonService.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestClient {
	private static RestClient instance=new RestClient();
	private RestClient(){}
	public static RestClient getInstance(){
		return instance;
	}
    private static final Logger logger = LogManager.getLogger(RestClient.class);
    public String postJsonRequest(String url, Object param,RestTemplate restTemplate) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    	HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(param), headers);
    	ResponseEntity<String> response =restTemplate.postForEntity(url, requestEntity, String.class);
		parseResponse(response);
		return response.getBody();
    }


	public String postFormRequest(String url, Map<String, String> param,RestTemplate restTemplate) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
    	for(Map.Entry<String,String> entry : param.entrySet()) {
    		map.add(entry.getKey(), entry.getValue());    
    	} 
    	HttpEntity<LinkedMultiValueMap<String,String>> requestEntity = new HttpEntity<>(map, headers);
    	ResponseEntity<String> response =restTemplate.postForEntity(url, requestEntity, String.class);
		parseResponse(response);
		return response.getBody();
    }

    /**
     *  只适合组织平台使用
     */
	public String postMapRequest(String url, Map<String,Object> param,RestTemplate restTemplate) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		LinkedMultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
		for(Map.Entry<String,Object> entry : param.entrySet()) {
			map.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<LinkedMultiValueMap<String,Object>> requestEntity = new HttpEntity<>(map, headers);
		ResponseEntity<String> response =restTemplate.postForEntity(url, requestEntity, String.class);
		parseResponse(response);
		return response.getBody();
	}

    /**
     *  只适合组织平台使用
     */
	public String postOrgMapRequest(String url, Map<String,Object> param,String accessToken,RestTemplate restTemplate) {
        try {
            HttpHeaders	headers = new HttpHeaders();
            headers.set("accesstoken",accessToken);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            LinkedMultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
            if(param!=null) {
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    map.add(entry.getKey(), entry.getValue());
                }
            }
            HttpEntity<LinkedMultiValueMap<String,Object>> requestEntity = new HttpEntity<>(map, headers);
            ResponseEntity<String> response =restTemplate.postForEntity(url, requestEntity, String.class);
			parseOrganResponse(response);
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("调用组织平台接口出错,错误信息:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
	public String getOrganMapRequest(String url, Map<String, String> param,String accessToken,RestTemplate restTemplate) {
		StringBuilder sb=new StringBuilder();
		if(param!=null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				sb.append("&" + entry.getKey() + "=" + entry.getValue());
			}
		}
		if(sb.length()>0) {
			if (url.contains("?")) {
				url = url + sb.toString().substring(1, sb.length());
			} else {
				url = url + "?" + sb.toString().substring(1, sb.length());
			}
		}
		String response ="";
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("accesstoken",accessToken);
			HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
			ResponseEntity<String> rss = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, param);
			response=rss.getBody();
			parseOrganResponse(rss);
		}catch (Exception e){
            logger.error("调用组织平台接口出错,错误信息:"+e.getMessage());
			throw new BusinessException(ReturnType.ServiceError.getCode(),ReturnType.ServiceError.getType(),e.getMessage());
		}
		return response;
	}

	private void parseResponse(ResponseEntity<String> response) {
		  JSONObject jsonObject= JSON.parseObject(response.getBody());
		  if(jsonObject.getString("code")==null || !(jsonObject.getString("code").equals("0")) ){
				throw new BusinessException(jsonObject.getString("code"),"",jsonObject.getString("message"));
		  }
	}

	private void parseOrganResponse(ResponseEntity<String> response) {
		JSONObject jsonObject= JSON.parseObject(response.getBody());
		JSONObject mateObject= jsonObject.getJSONObject("meta");
		if(mateObject.getString("code")==null || !(mateObject.getString("code").equals("0")) ){
			throw new BusinessException(mateObject.getString("code"),"",mateObject.getString("message"));
		}
	}

}
