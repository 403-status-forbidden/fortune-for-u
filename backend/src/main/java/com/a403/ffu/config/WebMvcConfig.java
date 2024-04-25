package com.a403.ffu.config;

import io.openvidu.java.client.OpenVidu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public OpenVidu openVidu(@Value("${openvidu.url}") String openviduUrl,
                             @Value("${openvidu.secret}") String openviduSecret){
        return new OpenVidu(openviduUrl, openviduSecret);
    }

}
