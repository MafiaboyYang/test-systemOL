package com.zjut.config;

import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.zjut.utils.MySessionManager;
import com.zjut.utils.ShiroRealm;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月16日下午4:42:19
*类说明：过滤配置
*/
@Configuration
public class ShiroConfiguration {
	
//	@Bean
//	public FilterRegistrationBean<DelegatingFilterProxy> filterRegistration() {
//		FilterRegistrationBean<DelegatingFilterProxy> filterRegistration = new FilterRegistrationBean<DelegatingFilterProxy>();
//		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//		filterRegistration.setEnabled(true);
//		filterRegistration.addUrlPatterns("/*");
//		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//		return filterRegistration;
//	}
	
	/**
	 *@Description:Filter工厂，设置对应的过滤条件和跳转条件
	 *@param
	 *@return ShiroFilterFactoryBean
	 *@throws
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> map = new HashMap<String, String>();
		//anon，配置不会被拦截的请求 顺序判断
		map.put("/logout", "logout");
		map.put("/admin/login","anon");
		map.put("/user/login", "anon");
		map.put("/user/register", "anon");
		//authc，配置拦截的请求
		map.put("/**", "authc");
		//配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		shiroFilterFactoryBean.setLoginUrl("/unauth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}
	
	/**
	 *@Description:权限管理，配置主要是Realm的管理认证，这个类组合了登录，登出，权限，session的处理
	 *@param
	 *@return SecurityManager
	 *@throws
	 */
	@Bean
	public SecurityManager securityManager() {
		System.out.println("com.zjut.config.ShiroConfiguration.java：开始注入");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setSessionManager(sessionManager());
		//注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
	
	/**
	 *@Description:将自己的验证方式加入容器
	 *@param
	 *@return ShiroRealm
	 *@throws
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}
	
	/**
	 *@Description:自定义sessionManager，用户的唯一标识，即Token或Authorization的认证
	 *@param
	 *@return SessionManager
	 *@throws
	 */
	@Bean
	public SessionManager sessionManager() {
		MySessionManager mySessionManager = new MySessionManager();
		//mySessionManager.setSessionDAO(redisSessionDAO());
		mySessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return mySessionManager;
	}
	
	/**
	 *@Description:配置shiro redisManager 使用的是shiro-redis开源插件
	 *@param
	 *@return RedisManager
	 *@throws
	 */
//	public RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost(configCode.getRedisHost());
//		redisManager.setPort(configCode.getRedisPort());
//		redisManager.setExpire(configCode.getRedisExpire());
//		redisManager.setTimeout(configCode.getRedisTimeout());
//		return redisManager;
//	}
//	
//	@Bean
//	public RedisSessionDAO redisSessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//		// 自定义session管理 使用redis
//		redisSessionDAO.setRedisManager(redisManager());
//		return redisSessionDAO;
//	}
	
	/**
	 *@Description:此处对应前端“记住我”的功能，获取用户关联信息而无需登录
	 *@param
	 *@return SimpleCookie
	 *@throws
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
	      //这个参数是cookie的名称，对应前端的checkbox的name = remember
	      SimpleCookie simpleCookie = new SimpleCookie("remember");
	      simpleCookie.setMaxAge(259200);
	      return simpleCookie;
	}
	
	@Bean
	public CookieRememberMeManager rememberMeManager(){
	      CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
	      cookieRememberMeManager.setCookie(rememberMeCookie());
	      cookieRememberMeManager.setCipherKey(Base64.decode("one"));
	      return cookieRememberMeManager;
	}
	
	/**
	 *@Description:加入注解的使用，不加这个注解不生效
	 *@param
	 *@return AuthorizationAttributeSourceAdvisor
	 *@throws
	 */
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
}
