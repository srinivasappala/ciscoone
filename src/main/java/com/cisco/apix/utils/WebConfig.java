package com.cisco.apix.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ronshah on 7/14/17.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowCredentials(true)
          .allowedOrigins("http://localhost:4200")
          .allowedMethods("PUT", "POST", "GET", "OPTIONS", "DELETE")
          .allowedHeaders("content-type", "authorization", "x-requested-with", "accept", "origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
          .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
          .exposedHeaders("x-requested-with, accept, authorization, content-type");
    }
}
