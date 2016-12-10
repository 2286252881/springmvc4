package com.zh.springmvc4;

import java.util.List;

import javax.validation.constraints.Pattern.Flag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zh.springmvc4.interceptor.DemoInterceptor;
import com.zh.springmvc4.messageconverter.MyMessageConverter;


@Configuration
@EnableWebMvc
@ComponentScan("com.zh.springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2

	/**
	 * 通过java方式配置mvc的视图解析器
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	//3.addResourceLocations:文件放置的目录。
	//addResourceHandler：对象暴露的访问路径
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");//3
	}
	
	
	@Bean
	public DemoInterceptor demoInterceptor(){
		return new DemoInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}
	
	/**
	 * 无任何业务处理的controller可以在配置中简写
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/abc").setViewName("/index");
		/**
		 * 文件上环
		 */
		registry.addViewController("/toUpload").setViewName("/upload");
		/**
		 * messageconverter
		 */
		registry.addViewController("/converter").setViewName("/converter");
	}
	
	/**
	 * 访问路径不忽略带"."
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}
	
	
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	
	
	
	
	
	@Bean
	public MyMessageConverter converter(){
		return new MyMessageConverter();
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		converters.add(converter());
	}
	
	
	
}
