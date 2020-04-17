package com.zjut.utils;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.zjut.pojo.User;
import com.zjut.service.user.UserService;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月16日下午3:18:19
*类说明：身份验证(getAuthenticationIfo方法)：验证账号和密码，并返回相关信息
*、权限获取(getAuthorizationIfo方法)：获取指定身份的权限，并返回相关信息
*、令牌支持(supports方法)：判断该令牌（Token）是否被支持
*/
public class ShiroRealm extends AuthorizingRealm{
	
	@SuppressWarnings("SpringJavaAutowiringInspection")	//忽略警告
	@Resource
	private UserService userService;
	/**
	 *登录信息和用户验证信息验证(non-Javadoc)
	 *login时调用
	 *@see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();  				//得到用户名
		String password = new String((char[])token.getCredentials()); 	//得到密码
		PageData pd = new PageData();
		User user = new User();
		pd.put("username", username);
		user = userService.findByUserName(pd);
		if(user == null) {
			//没有该用户
			throw new UnknownAccountException();
		}else if(!password.equals(user.getPassword())) {
			//密码错误
			throw new IncorrectCredentialsException();
		}
		//如果身份验证成功，返回一个AuthenticationInfo实现;
		return new SimpleAuthenticationInfo(username, password, getName());
	}
	
	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("========2");
		return null;
	}
}
