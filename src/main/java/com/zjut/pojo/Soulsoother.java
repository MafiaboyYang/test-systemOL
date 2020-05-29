package com.zjut.pojo;

public class Soulsoother {
	private Integer id;		//心灵鸡汤编号
	private String content;	//心灵鸡汤内容
	public Soulsoother() {}
	public Soulsoother(String soulsoother_content) {
		this.content = soulsoother_content;
	}
	public Integer getSoulsoother_id() {
		return id;
	}
	public void setSoulsoother_id(Integer id) {
		this.id = id;
	}
	public String getSoulsoother_content() {
		return content;
	}
	public void setSoulsoother_content(String content) {
		this.content = content;
	}	
}