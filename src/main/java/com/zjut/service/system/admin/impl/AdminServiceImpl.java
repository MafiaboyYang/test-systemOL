package com.zjut.service.system.admin.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.system.admin.AdminDao;
import com.zjut.pojo.system.Admin;
import com.zjut.service.system.admin.AdminService;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月23日下午2:52:28
*类说明
*/
@Service
public class AdminServiceImpl implements AdminService{
	@Resource
	private AdminDao adminDao;
	
	/**
	 *@Description:根据昵称读取管理员信息
	 *@param
	 *@return Admin
	 *@throws
	 */
	public Admin getAdminByName(PageData pd) {
		return adminDao.getAdminByName(pd);
	}
	/**
	 *@Description:根据用户名和密码读取管理员信息
	 *@param pd
	 *@return Admin
	 *@throws
	 */
	public Admin getAdminByNameAndPwd(PageData pd) {
		return adminDao.getAdminByNameAndPwd(pd);
	}
	/**
	 *@Description:修改管理员的上次登录时间和ip
	 *@param pd
	 *@return void
	 *@throws
	 */
	public void updateLastLogin(PageData pd) {
		adminDao.updateLastLogin(pd);
	}
	/**
	 *@Description:管理员修改个人信息
	 *@param
	 *@return void
	 *@throws
	 */
	public void editAdmin(PageData pd) {
		adminDao.editAdmin(pd);
	}
}
