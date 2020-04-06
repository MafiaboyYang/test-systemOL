package com.zjut.service.test;

import java.util.List;
import com.zjut.pojo.Test;

public interface TestService {
	//根据用户id查询用户
	Test getUserById(Integer id);
	//根据用户名查询用户
	Test getUserByName(String name);
}
