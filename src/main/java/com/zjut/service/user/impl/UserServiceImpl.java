package com.zjut.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.UserDao;
import com.zjut.pojo.User;
import com.zjut.service.user.UserService;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月13日下午1:41:52
*类说明
*/
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	/*
	 * 查看用户名是否重复
	 * */
	public User findByUserName(PageData pd) {
		return userDao.findByUserName(pd);
	}
	/*
	 * 查看用户手机号是否重复
	 * */
	public User findByPhone(PageData pd) {
		return userDao.findByPhone(pd);
	}
	/*
	 * 查看电子邮箱是否重复
	 * */
	public User findByEmail(PageData pd) {
		return userDao.findByEmail(pd);
	}
	/*
	 * 新增用户
	 * */
	public void insert(User user) {
		userDao.insert(user);
	}
}
