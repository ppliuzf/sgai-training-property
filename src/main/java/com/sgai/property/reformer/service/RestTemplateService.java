package com.sgai.property.reformer.service;


import java.math.BigDecimal;

public interface RestTemplateService {

    <T> T objGet(String url, Class<T> tClass);

    BigDecimal getWithUrl(String url);

    String strPost(String psdParam, String url);

}
