package com.zjut.pojo;
/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月13日下午12:40:41
*类说明：用户（答题用户或出题用户）
*/
public class User {
	private int id;				//用户id
	private String username;	//用户名
	private String password;	//密码
	private String name;		//姓名
	private String phone;		//手机号
	private String email;		//电子邮箱
	private String sex;			//性别（男或女）
	private int lockd;			//锁定（0代表未锁，1代表锁定）
	private int identity;		//用户身份（0代表出题用户，1代表答题用户）
	private String registerTime;//注册时间
	private String sessionId;	//会话编号
	private String lastLogin;	//上次登录时间
	public User() {}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getLockd() {
		return lockd;
	}
	public void setLockd(int lockd) {
		this.lockd = lockd;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
