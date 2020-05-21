package com.zjut.service.system.role.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.system.role.RoleDao;
import com.zjut.service.system.role.RoleService;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月23日下午2:52:53
*类说明
*/
@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;
}
