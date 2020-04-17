package com.zjut.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjut.pojo.User;
import com.zjut.utils.Const;
import com.zjut.utils.Jurisdiction;
/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月16日下午10:58:43
*类说明：登录过滤，权限验证
*/
@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 *预处理回调方法，实现处理器的预处理
	 *返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		String path = request.getServletPath();
		//获取到项目名，以便下面进行重定向
		String homeUrl = request.getContextPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)) {
			return true;
		}else {
			User user = (User)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if(user != null) {
				return true;
			}else {
				//如果是ajax请求，则设置session状态、CONTEXTPATH的路径值
				//如果是ajax请求响应头会有，x-requested-with
				if(request.getHeader("x-requested-with") !=null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					response.setHeader("SESSIONSTATUS", "TIMEOUT");
					response.setHeader("CONTEXTPATH", homeUrl + Const.LOGIN);
					//FORBIDDEN 也就是禁止、403
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}else {
					//如果不是ajax请求，则直接跳转即可
					response.sendRedirect(homeUrl + Const.LOGIN);
				}
				return false;
			}
		}
	}
}
