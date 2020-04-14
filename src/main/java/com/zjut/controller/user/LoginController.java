package com.zjut.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjut.controller.base.BaseController;
import com.zjut.pojo.User;
import com.zjut.service.user.UserService;
import com.zjut.utils.Const;
import com.zjut.utils.DateUtil;
import com.zjut.utils.Jurisdiction;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月14日下午4:36:09
*类说明
*/
@Controller
public class LoginController extends BaseController{
	@Resource
	private UserService userService;
	
	/**
	 *@Description:用于用户登录跳转
	 *@param
	 *@return String
	 *@throws
	 */
	@RequestMapping(value="/login_toLogin")
	public String toLogin() throws Exception{
		System.out.println("用户登录跳转");
		return "login";
	}
	
	/**
	 *@Description:请求登录，验证用户
	 *@param 
	 *username 用户名
	 *password 密码
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/login")
//			,produces="application/json;charset=UTF-8")
//	@ResponseBody
	public Object login()throws Exception{
		System.out.println("验证用户");
		Map<String,String> outputData = new HashMap<String, String>();
		Session session = Jurisdiction.getSession();
		PageData pd = this.getPageData();
		String errInfo="";
		String username = pd.getString("username");
		String password = pd.getString("password");
		pd.put("username", username);
		pd.put("password", DigestUtils.md5DigestAsHex(password.getBytes()));
		User user = new User();
		user = userService.getUserByNameAndPwd(pd);	//根据用户名和密码去读取用户信息
		if(user != null) {
			pd.put("lastLogin", DateUtil.getTime().toString());
			userService.updateLastLogin(pd);	//修改用户上次登录时间
			session.setAttribute(Const.SESSION_USER, user);
			session.removeAttribute(Const.SESSION_SECURITY_CODE);
			//shiro加入身份验证
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			try {
				subject.login(token);
			}catch(AuthenticationException e) {
				errInfo = "身份验证失败!";
			}
		}else {
			errInfo = "用户名或密码错误";
			logBefore(logger, username+"登录系统用户名或密码错误");
		}
		if(errInfo == "") {
			errInfo = "success";					//验证成功
            session.setAttribute("username",username);
            session.setTimeout(-1000l);
		}
		outputData.put("errInfo",errInfo);
        System.out.println(errInfo);
        return outputData;
	}
}
