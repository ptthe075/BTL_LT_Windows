package com.devpro.shop16.ppthe.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConf implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
		registry.addResourceHandler("/areas/**").addResourceLocations("classpath:/areas/");

		// đăng kí thêm folder upload
		registry.addResourceHandler("/Upload/**").addResourceLocations("file:" + "D:/KHOAHOC/JAVAWEB/JavaSpring/Upload/");
	}

	@Bean
	public ViewResolver viewResolver() {
//		InternalResourceViewResolver bean = new InternalResourceViewResolver();
//		bean.setPrefix("/WEB-INF/views/");
//		bean.setSuffix(".jsp");

		InternalResourceViewResolver bean = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");

		return bean;
	}
}
