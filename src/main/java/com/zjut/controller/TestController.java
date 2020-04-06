package com.zjut.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zjut.controller.base.BaseController;
import com.zjut.pojo.Test;
import com.zjut.service.test.TestService;

import com.zjut.utils.*;

@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController{
	@Resource
	private TestService testService;
	
	/**账号密码登录（账号为用户手机号）
     * @param
     * @return
     * @throws Exception
     * 需要参数：
     * phone        用户手机号(登录名)
     * password     密码
     */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login() throws Exception {
		Map<String,Object> map =  new HashMap<String,Object>();
		PageData pd = this.getPageData();
		Test returnTest;
		String result;
		String message;
		if(Tools.isEmpty(pd.getString("phone"))||Tools.isEmpty(pd.getString("password"))) {;
			result = "0";
			message = "参数不完整";
		}else {
			Test test = new Test();
			String phone = pd.getString("phone");		//获取手机号（登录名）
			String password = pd.getString("password");	//获取密码
			String password1 = DigestUtils.md5DigestAsHex(password.getBytes());	//密码加密
			System.out.println(password1);
//			String password1 = password;
			test.setName(phone);	//设置用户名
			test.setPassword(password1);	//设置密码
			returnTest = testService.getUserByName(phone);
			if(returnTest == null) {
				result = "0";
				message = "无此用户";
			}else {
				if(password1.equals(returnTest.getPassword())) {
					result = "1";
					message = "登录成功";
				}else {
					result = "0";
					message = "密码错误";
				}
			}
		}
		map.put("result", result);
		map.put("message", message);
		return AppUtil.returnObject(new PageData(), map);
	}
	//根据用户id查询用户
	@RequestMapping("/showUser")
	@ResponseBody
	public Test toIndex(HttpServletRequest request, Model model) {
		String message;	//用于记录是否有此用户
		String result;
		Integer id = Integer.parseInt(request.getParameter("id"));
		Test test = this.testService.getUserById(id);
		if(test == null) {
			result = "0";
			message = "无此用户";
		}else {
			result = "1";
			message = "成功";
		}
		return test;
	}
}
