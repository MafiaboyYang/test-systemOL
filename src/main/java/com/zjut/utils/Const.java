package com.zjut.utils;
/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月4日下午8:34:27
*类说明：用于存放常量
*/
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";//验证码
	public static final String SESSION_USER = "sessionUser";			//session中的用户
	public static final String SESSION_USERNAME = "username";			//用户名
	public static final String LOGIN = "/login.html";				//登录地址
	public static final String NO_INTERCEPTOR_PATH = ".*/((register)|(login)|(logout)).*";	//不对匹配该值的访问路径拦截（正则）
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
}
