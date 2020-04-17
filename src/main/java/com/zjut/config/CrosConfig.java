package com.zjut.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月17日下午3:49:02
*类说明：跨域
*/
@Configuration
public class CrosConfig {
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//允许跨域访问的域名
		corsConfiguration.addAllowedOrigin("*"); // 1
		//请求头
		corsConfiguration.addAllowedHeader("*"); // 2
		//请求方法
		corsConfiguration.addAllowedMethod("*"); // 3
		return corsConfiguration;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig()); // 4
		CorsFilter filter = new CorsFilter(source);
		registrationBean.setFilter(filter);
		registrationBean.setOrder(HIGHEST_PRECEDENCE);
		return registrationBean;
	}

}
