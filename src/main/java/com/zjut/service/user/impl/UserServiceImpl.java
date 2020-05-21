package com.zjut.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.user.UserDao;
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
	/**
	 *@Description:根据id查看用户
	 *@param
	 *@return User
	 *@throws
	 */
	public User findById(PageData pd) {
		return userDao.findById(pd);
	}
	/*
	 * 新增用户
	 * */
	public void insert(User user) {
		userDao.insert(user);
	}
	/**
	 *@Description:修改用户
	 *@param
	 *@return void
	 *@throws
	 */
	public void edit(User user) {
		userDao.edit(user);
	}
	/**
	 *@Description:删除用户（单个删除）
	 *@param
	 *@return void
	 *@throws
	 */
	public void delete(PageData pd) {
		userDao.delete(pd);
	}
	/**
	 *@Description:删除用户（批量删除）
	 *@param
	 *@return void
	 *@throws
	 */
	public void deleteAll(String[] str) {
		userDao.deleteAll(str);
	}
	/**
	 *@Description:根据用户名和密码查询用户
	 *@param pd
	 *@return User
	 *@throws
	 */
	public User getUserByNameAndPwd(PageData pd) {
		return userDao.getUserByNameAndPwd(pd);
	}
	/**
	 *@Description:修改用户的上次登录时间和sessionId
	 *@param pd
	 *@return void
	 *@throws
	 */
	public void updateLastLogin(PageData pd) {
		userDao.updateLastLogin(pd);
	}
	/**
	 *@Description:根据用户名获取sessionId
	 *@param
	 *@return String
	 *@throws
	 */
	public String getSessionIdByUserName(String username) {
		return userDao.getSessionIdByUserName(username);
	}
	/**
	 *@Description:查看用户信息
	 *@param
	 *@return List<User>
	 *@throws
	 */
	public List<User> getUserList(PageData pd){
		return userDao.getUserList(pd);
	}
	/**
	 *@Description:根据用户昵称查看用户
	 *@param
	 *@return User
	 *@throws
	 */
	public User findByName(PageData pd) {
		return userDao.findByName(pd);
	}
	/**
	 *@Description:锁定/解锁用户
	 *@param
	 *@return void
	 *@throws
	 */
	public void lockUser(PageData pd) {
		userDao.lockUser(pd);
	}
	/**
	 *@Description:修改用户角色
	 *@param
	 *@return void
	 *@throws
	 */
	public void identityUser(PageData pd) {
		userDao.identityUser(pd);
	}
}
