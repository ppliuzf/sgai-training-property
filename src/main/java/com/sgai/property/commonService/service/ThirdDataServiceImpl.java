package com.sgai.property.commonService.service;

import com.sgai.modules.login.consts.SysConstant;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.JwtUtil;
import com.sgai.modules.login.support.UserServletContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取合同编码
 */
//@Service
public class ThirdDataServiceImpl {
    private static final Logger logger = LogManager.getLogger(ThirdDataServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${mat.Prefix-url}")
    private String urlPrefix;
    @Value("${mat.SpaceList-url}")
    private String spaceListUrl;


    public String getNumber(String comCode, String sequCode) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        //将请求参数放入map中
        Map<String,String> map = new HashMap<>();
        param.add("utf8", "✓");
        param.add("comCode",comCode);
        param.add("sequCode",sequCode);
        ResponseEntity<String> responseEntity = sendRequest(spaceListUrl, UserServletContext.getUserInfo(), param);
        System.out.println(responseEntity.getBody());
        logger.info("获取的合同编码:"+responseEntity.toString());
        return responseEntity.getBody();
    }


    /**
     * 发送请求获取数据
     *
     * @param url       请求url
     * @param loginUser 当前登录对象
     * @param param     参数放入一个map中，restTemplate不能用hashMap
     * @return
     */
    public ResponseEntity<String> sendRequest(String url, LoginUser loginUser, MultiValueMap<String, String> param) {
        String requestUrl = urlPrefix + url;
        RestTemplate template = new RestTemplate();

        //获取header
        HttpHeaders headers = getHeader(loginUser);
        logger.info("===>参数内容为：" + param.toString() + "===>headers内容为：" + headers.toString());
        //将参数和header组成一个请求
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = template.postForEntity(requestUrl, request, String.class);

        return response;
    }

    /**
     * 向head中放数据
     *
     * @param loginUser
     * @return
     */
    private HttpHeaders getHeader(LoginUser loginUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", getToken(loginUser));

        //post表单 ，如果是个json则设置为MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }


    /**
     * 生成首自信方需要的token
     *
     * @param loginUser 需要加密的对象
     * @return
     */
    public String getToken(LoginUser loginUser) {

        String result = null;
        try {
            result = JwtUtil.userToJwt(loginUser, SysConstant.JWT_TTL);
        } catch (Exception e) {
            logger.info("生成token,error!");
            e.printStackTrace();
        }

        return result;
    }

}
