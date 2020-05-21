package com.zjut.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjut.pojo.User;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月13日下午1:42:19
*类说明
*/
@Repository
public interface UserDao {
	/*
	 * 查看用户名是否重复
	 * */
	User findByUserName(PageData pd);
	/*
	 * 查看用户手机号是否重复
	 * */
	User findByPhone(PageData pd);
	/*
	 * 查看电子邮箱是否重复
	 * */
	User findByEmail(PageData pd);
	/**
	 *@Description:根据id查看用户
	 *@param
	 *@return User
	 *@throws
	 */
	User findById(PageData pd);
	/*
	 * 新增用户
	 * */
	void insert(User user);
	/**
	 *@Description:修改用户
	 *@param
	 *@return void
	 *@throws
	 */
	void edit(User user);
	/**
	 *@Description:删除用户（单个删除）
	 *@param
	 *@return void
	 *@throws
	 */
	void delete(PageData pd);
	/**
	 *@Description:删除用户（批量删除）
	 *@param
	 *@return void
	 *@throws
	 */
	void deleteAll(String[] str);
	/**
	 *@Description:根据用户昵称查看用户
	 *@param
	 *@return User
	 *@throws
	 */
	User findByName(PageData pd);
	/**
	 *@Description:根据用户名和密码读取用户信息
	 *@param pd
	 *@return User
	 *@throws
	 */
	User getUserByNameAndPwd(PageData pd);
	/**
	 *@Description:修改用户的上次登录时间和sessionId
	 *@param pd
	 *@return void
	 *@throws
	 */
	void updateLastLogin(PageData pd);
	/**
	 *@Description:根据用户名获取sessionId
	 *@param
	 *@return String
	 *@throws
	 */
	String getSessionIdByUserName(String username);
	/**
	 *@Description:查看用户信息
	 *@param
	 *@return List<User>
	 *@throws
	 */
	List<User> getUserList(PageData pd);
	/**
	 *@Description:锁定/解锁用户
	 *@param
	 *@return void
	 *@throws
	 */
	void lockUser(PageData pd);
	/**
	 *@Description:修改用户角色
	 *@param
	 *@return void
	 *@throws
	 */
	void identityUser(PageData pd);
}
