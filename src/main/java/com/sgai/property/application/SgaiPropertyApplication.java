package com.sgai.property.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@EnableScheduling
@EnableCaching
@SpringBootApplication(scanBasePackages= {"com.sgai.property.common.configuration","com.sgai.property.wy.support"})
@ServletComponentScan("com.sgai.property")
public class SgaiPropertyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgaiPropertyApplication.class, args);
    }

}

