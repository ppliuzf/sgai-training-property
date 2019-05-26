package com.sgai.property.common.configuration;


import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan({"com.sgai.property.*.dao",
        "com.sgai.property.*.mapper",
        "com.sgmart.upload.mapper",
        "com.sgai.video.mapper",
})
public class MapperConfiguration {

}
