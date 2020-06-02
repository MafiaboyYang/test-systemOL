 package com.zjut.pojo;

public class Wrongque {
	private Integer id;
	private Integer userid;//标签编号
	private Integer questionId;	//标签内容
	public Wrongque(Integer userid,Integer wrongque_questionId ) {
		this.userid=userid;
		this.questionId=wrongque_questionId;
	}
	public Integer getWrongque_id() {
		return id;
	}
	public void setWrongque_id(Integer wrongque_id) {
		this.id = wrongque_id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getWrongque_questionId() {
		return questionId;
	}
	public void setWrongque_questionId(Integer wrongque_questionId) {
		this.questionId = wrongque_questionId;
	}
}
