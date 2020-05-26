 package com.zjut.pojo;

public class Label {
	private Integer label_id;		//标签编号
	private String label_content;	//标签内容
	public Label(String label_content ) {
		this.label_content=label_content;
	}
	public Integer getLabel_id() {
		return label_id;
	}
	public void setLabel_id(Integer id) {
		this.label_id = id;
	}
	public String getLabel_content() {
		return label_content;
	}
	public void setLabel_content(String content) {
		this.label_content = content;
	}	
}
