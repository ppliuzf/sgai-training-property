package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.exception.PostException;
import com.sgai.property.reformer.service.RestTemplateService;
import com.sgai.property.reformer.xmlBean.DataXmlBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T objGet(String url, Class<T> tClass) {
        ResponseEntity<T> responseEntity;
        try {
            HttpHeaders headers = new HttpHeaders();
            //都是英文不存在乱码问题.
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Basic YWRtaW5pc3RyYXRvcjpBZG1pbmlzdHJhdG9yMTIz");
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PostException("post请求异常");
        }
        return responseEntity.getBody();
    }

    @Override
    public BigDecimal getWithUrl(String url) {
        BigDecimal data = BigDecimal.ZERO;
        try {
            data = this.objGet(url, DataXmlBean.class).getVal();
        } catch (Exception ignored) {
        }
        return data;
    }

    @Override
    public String strPost(String psdParam, String url) {
        String response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            //都是英文不存在乱码问题.
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> formEntity = new HttpEntity<>(psdParam, headers);
            response = restTemplate.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            throw new PostException("post请求异常");
        }
        return response;
    }

}



