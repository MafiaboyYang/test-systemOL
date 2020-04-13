package com.zjut.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zjut.controller.base.BaseController;
import com.zjut.pojo.User;
import com.zjut.service.user.UserService;
import com.zjut.utils.AppUtil;
import com.zjut.utils.DateUtil;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月13日下午1:40:09
*类说明
*/
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	
	/*
	 *新增用户判断用户名、手机号和邮箱是否重复
	 * */
	@RequestMapping(value = "/hasU")
	public Object hasU() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			//用户名已存在
			if(userService.findByUserName(pd) !=null) {
				errInfo = "error1";
			}
			//手机号已存在
			if(userService.findByPhone(pd) !=null) {
				errInfo = "error2";
			}
			//电子邮箱已存在
			if(userService.findByEmail(pd) !=null) {
				errInfo = "error3";
			}
		}catch(Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);	//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/*
	 * 保存用户
	 * */
	@RequestMapping(value = "/insertU")
	public ModelAndView insertU()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		User user = new User();
		pd.put("lastLogin", "");	//上次登录时间
		pd.put("sessionId", "0");	//会话编号
		pd.put("lockd", "0");			//锁定（默认0未锁定）
		pd.put("identity", "1");		//用户身份（默认1答题用户）
		pd.put("registerTime", DateUtil.getTime());	//用户注册时间
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
		if(null == userService.findByUserName(pd) && null == userService.findByPhone(pd) &&null == userService.findByEmail(pd)) {
			userService.insert(user);	//执行保存
			mv.addObject("msg", "success");
			System.out.println("用户保存成功");
		}else {
			mv.addObject("msg", "failed");
			System.out.println("用户保存失败");
		}
		mv.setViewName("save_result");	//保存成功界面
		return mv;
	}
}
