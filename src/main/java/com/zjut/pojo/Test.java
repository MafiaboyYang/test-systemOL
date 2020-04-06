package com.zjut.pojo;

public class Test {
	private Integer id;		//用户编号
	private String name;	//用户名
	private String password;//密码
	public Test() {}
	public Test(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
