package com.zjut.controller.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjut.pojo.system.Admin;
import com.zjut.service.system.admin.AdminService;
import com.zjut.utils.Const;
import com.zjut.utils.Jurisdiction;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月30日下午8:23:45
*类说明：管理员登录过滤、权限验证
*/
@Component
public class AdminLoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private AdminService adminService;
	
	/**
	 *预处理回调方法，实现处理器的预处理
	 *返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		String path = request.getServletPath();
		System.out.println("com.zjut.controller.interceptor.AdminLoginHandlerInterceptor.java：开始拦截");
		//获取到项目名，以便下面进行重定向
		String homeUrl = request.getContextPath();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
		if(path.matches(Const.NO_INTERCEPTOR_PATH)) {	//没有被拦截的路径（主要是静态资源）
			return true;
		}else {
			Admin admin = (Admin)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);	//获得当前session下的用户
			if(admin != null) {	//用户已登录
				return true;
			}else {	//用户未登录
				//如果是ajax请求，则设置session状态、CONTEXTPATH的路径值
				//如果是ajax请求响应头会有，x-requested-with
				if(request.getHeader("x-requested-with") !=null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					response.setHeader("SESSIONSTATUS", "TIMEOUT");		//session超时而失效
					response.setHeader("CONTEXTPATH", homeUrl + Const.ADMIN_LOGIN);
					//FORBIDDEN 也就是禁止、403
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}else {
					//如果不是ajax请求，则直接跳转即可
					response.sendRedirect(homeUrl + Const.ADMIN_LOGIN);
				}
				return false;
			}
		}
	}
}	
