package com.zjut.dao.system.admin;

import org.springframework.stereotype.Repository;

import com.zjut.pojo.system.Admin;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月23日下午2:49:22
*类说明
*/
@Repository
public interface AdminDao {
	/**
	 *@Description:根据昵称读取管理员信息
	 *@param
	 *@return Admin
	 *@throws
	 */
	Admin getAdminByName(PageData pd);
	/**
	 *@Description:根据用户名和密码读取管理员信息
	 *@param pd
	 *@return Admin
	 *@throws
	 */
	Admin getAdminByNameAndPwd(PageData pd);
	/**
	 *@Description:修改管理员的上次登录时间和ip
	 *@param pd
	 *@return void
	 *@throws
	 */
	void updateLastLogin(PageData pd);
	/**
	 *@Description:管理员修改个人信息
	 *@param
	 *@return void
	 *@throws
	 */
	void editAdmin(PageData pd);
}
