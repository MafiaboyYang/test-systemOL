package com.zjut.controller.user;

import com.alibaba.fastjson.JSON;

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
import com.zjut.pojo.system.Admin;
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
@RequestMapping(value="/user")
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
//	@RequestMapping(value="/login_toLogin")
//	public String toLogin() throws Exception{
//		System.out.println("用户登录跳转");
//		return "login";
//	}
	
	
	/**
	 *@Description:用于用户注册
	 *@param
	 *username		用户名
	 *password		密码
	 *name			姓名
	 *phone			手机号码
	 *email			电子邮箱
	 *sex			性别
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value = "/register",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object insertU()throws Exception{
		Map<String,String> outputData = new HashMap<String, String>();
		String errInfo = "";	//用于记录是否错误
		PageData pd = new PageData();
		pd = this.getPageData();
		User user = new User();
		pd.put("lastLogin", "");	//上次登录时间
		pd.put("sessionId", "0");	//会话编号
		pd.put("lockd", "0");			//锁定（默认0未锁定）
		pd.put("identity", "1");		//用户身份（默认1答题用户）
		pd.put("registerTime", DateUtil.getTime().toString());	//用户注册时间
		pd.put("password", DigestUtils.md5DigestAsHex(pd.getString("password").getBytes()));	//密码加密
		user.setUsername(pd.getString("username"));
		user.setPassword(pd.getString("password"));
		user.setName(pd.getString("name"));
		user.setPhone(pd.getString("phone"));
		user.setEmail(pd.getString("email"));
		user.setSex(pd.getString("sex"));
		user.setLockd(Integer.parseInt(pd.getString("lockd")));
		user.setIdentity(Integer.parseInt(pd.getString("identity")));
		user.setRegisterTime(pd.getString("registerTime"));
		user.setSessionId(pd.getString("sessionId"));
		user.setLastLogin(pd.getString("lastLogin"));
		if(null != userService.findByUserName(pd)) {
			errInfo = "该用户名已存在";
			System.out.println("该用户名已存在");
		}else if(null != userService.findByPhone(pd)) {
			errInfo = "该手机号已被注册";
			System.out.println("该手机号已被注册");
		}else if(null != userService.findByEmail(pd)) {
			errInfo = "该电子邮箱已被注册";
			System.out.println("该电子邮箱已被注册");
		}else if(null != userService.findByName(pd)) {
			errInfo = "昵称已存在";
			System.out.println("昵称已存在");
		}else {
			userService.insert(user);	//执行保存
			errInfo = "success";
			System.out.println("用户注册成功");
		}
		outputData.put("errInfo",errInfo);
		String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:请求登录，验证用户
	 *@param 
	 *username 用户名
	 *password 密码
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/login",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		System.out.println("com.zjut.controller.user.LoginController.java：验证用户");
		Map<String,String> outputData = new HashMap<String, String>();
		Session session = Jurisdiction.getSession();
		PageData pd = this.getPageData();
		String errInfo="";
		String username = pd.getString("username");
		String password = DigestUtils.md5DigestAsHex(pd.getString("password").getBytes());
		pd.put("username", username);
		pd.put("password", password);
		User user = new User();
		user = userService.getUserByNameAndPwd(pd);	//根据用户名和密码去读取用户信息
		if(user != null && user.getLockd() == 0) {
			pd.put("lastLogin", DateUtil.getTime().toString());
			String authorization = (String)session.getId();
			pd.put("sessionId", authorization);
			userService.updateLastLogin(pd);	//修改用户上次登录时间和sessionId
			System.out.println("com.zjut.controller.user.LoginController.java：用户登录修改sessionId");
			outputData.put("sessionId", authorization);	//将authorization传给前端，用于MySessionManager中请求的验证
			user.setSessionId(authorization);	//修改用户的sessionId
			session.setAttribute(Const.SESSION_USER, user);		//将用户存入session
			session.removeAttribute(Const.SESSION_SECURITY_CODE);
			//shiro加入身份验证
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			//如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
			try {
				//getAuthenticationInfo执行时机（身份验证）
				subject.login(token);
			}catch(AuthenticationException e) {
				errInfo = "身份验证失败!";
			}
		}else if(user != null && user.getLockd() == 1){
			errInfo = "该用户已被锁定";
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
		String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:查看用户个人信息
	 *@param
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/infoUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object infoUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：查看用户个人信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		User user = (User)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);	//获得当前session下的用户
		outputData.put("user", user);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:修改用户信息
	 *@param
	 *id		用户id
	 *name		用户昵称
	 *password	密码
	 *email		电子邮箱
	 *phone		手机号
	 *sex		性别
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/editUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object editUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：修改用户信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		String errInfo = "success";
		PageData pd = this.getPageData();
		Session session = Jurisdiction.getSession();
		//如果昵称存在而且昵称的主人不是原主人
		if(userService.findByName(pd) != null && userService.findByName(pd).getId() != Integer.parseInt(pd.getString("id"))){
			errInfo = "昵称已存在";
		}//如果电子邮箱存在而且电子邮箱的主人不是原主人
		else if(userService.findByEmail(pd) != null && userService.findByEmail(pd).getId() != Integer.parseInt(pd.getString("id"))){
			errInfo = "电子邮箱已存在";
		}//如果手机号存在而且手机号的主人不是原主人
		else if(userService.findByPhone(pd) != null && userService.findByPhone(pd).getId() != Integer.parseInt(pd.getString("id"))){
			errInfo = "手机号已存在";
		}else {
			User user = new User();
			pd.put("password", DigestUtils.md5DigestAsHex(pd.getString("password").getBytes()));	//密码加密
			user.setId(Integer.parseInt(pd.getString("id")));
			user.setName(pd.getString("name"));
			user.setPassword(pd.getString("password"));
			user.setEmail(pd.getString("email"));
			user.setPhone(pd.getString("phone"));
			user.setSex(pd.getString("sex"));
			userService.edit(user);		//修改用户信息
			user = userService.findById(pd);
			session.setAttribute(Const.SESSION_USER, user);		//将用户信息存入session
		}
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:未登录
	 *@param
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value = "/unauth",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object unauth()throws Exception{
		Map<String,String> outputData = new HashMap<String, String>();
		outputData.put("errInfo", "未登录");
		String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
}