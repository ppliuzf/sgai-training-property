package com.sgai.property.common.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootConfiguration
@PropertySource("classpath:url.properties")
@Component
public class RemoteProperties {
	@Value("${url.picRealPath}")
	private String picRealPath;
	@Value("${url.picUrlPath}")
	private String picUrlPath;
    public String getPicRealPath() {
		return picRealPath;
	}
	public void setPicRealPath(String picRealPath) {
		this.picRealPath = picRealPath;
	}
	public String getPicUrlPath() {
		return picUrlPath;
	}
	public void setPicUrlPath(String picUrlPath) {
		this.picUrlPath = picUrlPath;
	}
}
