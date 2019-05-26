package com.sgai.property.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class RootConfiguration {

    @Bean
    public RestOperations nonLoadbalancedRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(1000*30);
        httpRequestFactory.setConnectTimeout(1000*30);
        httpRequestFactory.setReadTimeout(1000*60);
        return new RestTemplate(httpRequestFactory);
    }
   
}
