package com.zjut.utils;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import com.alibaba.druid.util.StringUtils;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月17日下午8:57:03
*类说明：用于请求时前端传来的AUTHORIZATION和shiro中的AUTHORIZATION进行验证
*/
public class MySessionManager extends DefaultWebSessionManager{
	
	private static final String AUTHORIZATION = "Authorization";
	
	public MySessionManager() {
		super();
	}
	
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		//前端ajax的headers中必须传入Authorization的值
		String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		//如果请求头中有Authorization 则其值为sessionId
		if(!StringUtils.isEmpty(sessionId)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sessionId;
		}else {
			//否则按默认规则从cookie取sessionId
			return super.getSessionId(request, response);
		}
	}
}
