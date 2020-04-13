package com.zjut.pojo;
/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月13日下午12:54:18
*类说明：管理员
*/
public class Admin {
	private int id;				//管理员id
	private String username;	//用户名
	private String password;	//密码
	private String name;		//姓名
	private String lastLogin;	//最后登录时间
	private String ip;			//用户登录ip
	public Admin() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
