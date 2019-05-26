package com.sgai.property.common.configuration;

import com.sgai.property.common.configuration.properties.RemoteProperties;
import com.sgmart.upload.service.UploadFileService;
import com.sgmart.upload.service.impl.UploadFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传service注入.
 */
@Configuration
public class EaUploadConfiguration {

    @Autowired
    private RemoteProperties remoteProperties;

    @Bean
    public UploadFileService uploadFileService() {
        return new UploadFileServiceImpl(remoteProperties.getPicRealPath(), remoteProperties.getPicUrlPath());
    }
}
