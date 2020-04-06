package com.zjut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages="com.zjut.dao")
//@ComponentScan(basePackages = {"com.zjut.dao"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class TestSystemOlApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(TestSystemOlApplication.class, args);
	}

}
