package com.zjut.pojo;

public class Soulsoother {
	private Integer soulsoother_id;		//心灵鸡汤编号
	private String soulsoother_content;	//心灵鸡汤内容
	public Soulsoother() {}
	public Soulsoother(String soulsoother_content) {
		this.soulsoother_content = soulsoother_content;
	}
	public Integer getSoulsoother_id() {
		return soulsoother_id;
	}
	public void setSoulsoother_id(Integer id) {
		this.soulsoother_id = id;
	}
	public String getSoulsoother_content() {
		return soulsoother_content;
	}
	public void setSoulsoother_content(String content) {
		this.soulsoother_content = content;
	}	
}