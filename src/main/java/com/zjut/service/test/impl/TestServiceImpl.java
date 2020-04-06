package com.zjut.service.test.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjut.dao.TestDao;
import com.zjut.pojo.Test;
import com.zjut.service.test.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Resource
	private TestDao testDao;
	//根据用户id查询用户
	public Test getUserById(Integer id){
		return testDao.getUserById(id);
	}
	//根据用户名查询用户
	public Test getUserByName(String name) {
		return testDao.getUserByName(name);
	}
}
