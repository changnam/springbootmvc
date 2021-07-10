package com.honsoft.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.honsoft.web.listener.MyBeanPostProcessor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

 
 @Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("prefix", "/WEB-INF/jsp");
		registry.jsp("suffix", ".jsp");
	}
 
 @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/home").setViewName("thymeleaf/home");
	}
  
}
