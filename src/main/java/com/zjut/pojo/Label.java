 package com.zjut.pojo;

public class Label {
	private Integer id;		//标签编号
	private String content;	//标签内容
	public Label(String label_content ) {
		this.content=label_content;
	}
	public Integer getLabel_id() {
		return id;
	}
	public void setLabel_id(Integer id) {
		this.id = id;
	}
	public String getLabel_content() {
		return content;
	}
	public void setLabel_content(String content) {
		this.content = content;
	}	
}
