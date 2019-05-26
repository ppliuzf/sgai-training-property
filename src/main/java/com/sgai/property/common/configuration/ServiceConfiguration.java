package com.sgai.property.common.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.sgai.property.*.service",
        "com.sgai.property.*.manager.impl",
        "com.sgai.property.mq",
        "com.sgai.property.task.spring",
        "com.sgai.property.socket",
        "com.sgai.property.video",
        "com.sgai.property.commonService.client",
})
public class ServiceConfiguration {
}
