package com.zjut.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月14日下午9:31:38
*类说明：权限处理
*/
public class Jurisdiction {
	/**
	 *@Description:获取当前登录的用户名
	 *@param
	 *@return String
	 *@throws
	 */
	public static String getUsername() {
		return getSession().getAttribute("username").toString();
	}
	
	/**
	 *@Description:shiro管理的session
	 *@param
	 *@return Session
	 *@throws
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
}
