package com.zjut.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.zjut.controller.interceptor.LoginHandlerInterceptor;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月16日下午11:09:04
*类说明：管理拦截器，将之前的拦截器注入其中
*/
@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport{
	
	@Autowired
	private LoginHandlerInterceptor loginHandlerInterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		//多个拦截器组成一个拦截器链
		//addPathPatterns用于添加拦截规则，/**表示拦截所有请求
		//excludePathPatterns用户排除拦截
		registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
