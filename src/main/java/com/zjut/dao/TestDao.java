package com.zjut.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.zjut.pojo.Test;

@Repository
public interface TestDao {
	//根据用户id查看用户
	Test getUserById(Integer id);
	//新增用户
	void insert(Test test);
	//根据用户名查询用户
	Test getUserByName(String name);
}
