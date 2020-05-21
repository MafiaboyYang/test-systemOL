package com.zjut.controller.system.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.controller.base.BaseController;
import com.zjut.pojo.User;
import com.zjut.pojo.system.Admin;
import com.zjut.service.system.admin.AdminService;
import com.zjut.service.user.UserService;
import com.zjut.utils.Const;
import com.zjut.utils.DateUtil;
import com.zjut.utils.Jurisdiction;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月23日下午2:48:38
*类说明：管理员管理
*/
@RequestMapping(value="/admin")
@Controller
public class AdminController extends BaseController{
	
	@Resource
	private AdminService adminService;
	@Resource 
	private UserService userService;
	
	/**
	 *@Description:请求登录，验证用户
	 *@param 
	 *username	用户名
	 *password	密码
	 *ip		登录ip
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/login",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：验证管理员");
		Map<String,String> outputData = new HashMap<String, String>();
		Session session = Jurisdiction.getSession();
		PageData pd = this.getPageData();
		String errInfo="";
		String username = pd.getString("username");
		String password = DigestUtils.md5DigestAsHex(pd.getString("password").getBytes());
		pd.put("username", username);
		pd.put("password", password);
		Admin admin = new Admin();
		admin = adminService.getAdminByNameAndPwd(pd);	//根据用户名和密码去读取管理员用户信息
		if(admin != null) {
			pd.put("lastLogin", DateUtil.getTime().toString());
			String authorization = (String)session.getId();
			pd.put("sessionId", authorization);
			adminService.updateLastLogin(pd);	//修改用户上次登录时间和ip
			System.out.println("com.zjut.controller.user.LoginController.java：管理员登录修改sessionId");
			outputData.put("sessionId", authorization);	//将authorization传给前端，用于MySessionManager中请求的验证
			session.setAttribute(Const.SESSION_USER, admin);		//将用户存入session
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
	 *@Description:查看用户信息（分页批量查看）
	 *@param
	 *id		用户id
	 *name		用户昵称
	 *phone		手机号
	 *page		当前页码
	 *pageSize	每页查询条数
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/list",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object listUsers()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：批量查看用户信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		String id = pd.getString("id");				//用户id
		String name = pd.getString("name");			//用户昵称
		String phone = pd.getString("phone");		//手机号
		String page = pd.getString("page");			//当前页码
		String pageSize = pd.getString("pageSize");	//每页查询条数
		if(null != id && !"".equals(id)){
            pd.put("id",id.trim());
        }
        if(null != name && !"".equals(name)){
            pd.put("name",name.trim());
        }
        if(null != phone && !"".equals(phone)){
            pd.put("phone",phone.trim());
        }
        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(pageSize));
        List<User> userList = userService.getUserList(pd);
        PageInfo pageInfo = new PageInfo(userList);
        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:查看单个用户信息
	 *@param
	 *name		用户昵称
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/name",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object getUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：查看单个用户信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		String name = pd.getString("name");			//用户昵称
        if(null != name && !"".equals(name)){
            pd.put("name",name.trim());
        }
        User user = userService.findByName(pd);
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
		}
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:删除用户（单个删除）
	 *@param
	 *id		用户id
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/deleteUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：删除单个用户");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		String errInfo = "success";
		userService.delete(pd);
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:删除用户（批量删除）
	 *@param
	 *ids		用户id组（每个id中间用“,”隔开）
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/deleteAllUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object deleteAllUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：批量删除用户");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String ids = pd.getString("ids");
		if(null != ids && !"".equals(ids)){
            String ArrayuserIDs[] = ids.split(",");
            userService.deleteAll(ArrayuserIDs);    //批量删除用户
            pd.put("msg", "ok");
        }else{
            pd.put("msg", "no");
        }
		pdList.add(pd);
		outputData.put("list", pdList);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:增加用户
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
	@RequestMapping(value="/insertUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object insertUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：增加用户");
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
	 *@Description:锁定/解锁用户
	 *@param
	 *id		用户id
	 *lockd		0或1（0代表未锁，1代表锁定）
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/lockUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object lockUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：锁定/解锁用户");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		String errInfo = "success";
		User user = userService.findById(pd);
		if(user == null) {
			errInfo = "要修改的用户不存在";
		}else if(Integer.parseInt(pd.getString("lockd")) == user.getLockd()) {
			errInfo = "修改用户与原来相同，修改失败";
		}else {
			userService.lockUser(pd);
		}
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:修改用户角色
	 *@param
	 *id		用户id
	 *identity	0或1（0代表出题用户，1代表答题用户）
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/identityUser",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object identityUser()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：修改用户角色");
		Map<String,Object> outputData = new HashMap<String, Object>();
		PageData pd = this.getPageData();
		String errInfo = "success";
		User user = userService.findById(pd);
		if(user == null) {
			errInfo = "要修改的用户不存在";
		}else if(Integer.parseInt(pd.getString("identity")) == user.getIdentity()) {
			errInfo = "修改用户角色与原来相同，修改失败";
		}else {
			userService.identityUser(pd);
		}
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:查看管理员个人信息
	 *@param
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/infoAdmin",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object infoAdmin()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：查看管理员个人信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		Admin admin = (Admin)Jurisdiction.getSession().getAttribute(Const.SESSION_USER);	//获得当前session下的用户
		outputData.put("admin", admin);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
	
	/**
	 *@Description:修改管理员个人信息
	 *@param
	 *id		用户id
	 *name		用户昵称
	 *password	密码
	 *@return Object
	 *@throws
	 */
	@RequestMapping(value="/editAdmin",produces="text/html;application/json;charset=UTF-8")
	@ResponseBody
	public Object editAdmin()throws Exception{
		System.out.println("com.zjut.controller.system.admin.AdminController.java：修改管理员个人信息");
		Map<String,Object> outputData = new HashMap<String, Object>();
		String errInfo = "success";
		Session session = Jurisdiction.getSession();
		PageData pd = this.getPageData();
		//如果昵称存在而且昵称的主人不是原主人
		if(adminService.getAdminByName(pd) != null && adminService.getAdminByName(pd).getId() != Integer.parseInt(pd.getString("id"))){
			errInfo = "昵称已存在";
		}else {
			pd.put("password", DigestUtils.md5DigestAsHex(pd.getString("password").getBytes()));	//密码加密
			adminService.editAdmin(pd);		//修改管理员信息
			Admin admin = adminService.getAdminByName(pd);
			session.setAttribute(Const.SESSION_USER, admin);		//将管理员信息存入session
		}
		outputData.put("errInfo", errInfo);
        String data = JSON.toJSONString(outputData);	//转化为json形式
        System.out.println("data" + data);
        return data;
	}
}
