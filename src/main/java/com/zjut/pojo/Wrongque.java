 package com.zjut.pojo;

public class Wrongque {
	private Integer wrongque_id;
	private Integer userid;//标签编号
	private Integer wrongque_questionId;	//标签内容
	public Wrongque(Integer userid,Integer wrongque_questionId ) {
		this.userid=userid;
		this.wrongque_questionId=wrongque_questionId;
	}
	public Integer getWrongque_id() {
		return wrongque_id;
	}
	public void setWrongque_id(Integer wrongque_id) {
		this.wrongque_id = wrongque_id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getWrongque_questionId() {
		return wrongque_questionId;
	}
	public void setWrongque_questionId(Integer wrongque_questionId) {
		this.wrongque_questionId = wrongque_questionId;
	}
}
