package com.zjut.service.system.menu.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.system.menu.MenuDao;
import com.zjut.service.system.menu.MenuService;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月23日下午2:52:42
*类说明
*/
@Service
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDao menuDao;
}
