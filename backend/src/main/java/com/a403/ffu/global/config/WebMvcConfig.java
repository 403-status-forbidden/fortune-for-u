package com.ssafy.a403.global.config;

import io.openvidu.java.client.OpenVidu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public OpenVidu openVidu(@Value("${OPENVIDU_URL}") String OPENVIDU_URL,
                             @Value("${OPENVIDU_SECRET}") String OPENVIDU_SECRET){
        return new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

}
