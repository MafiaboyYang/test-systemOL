package com.zjut.pojo;

import org.springframework.stereotype.Component;

@Component(value = "result")
public class Result {
    private int result_id;
    private int result_paperId;
    private int result_time; 
    private int result_score;
    private int userid; 
    private String result_report;
	public Result(int result_paperId,int result_time,int result_score, int userid,String result_report ) {
		this.result_paperId=result_paperId;
		this.result_time=result_time;
		this.result_score=result_score;
		this.userid=userid;
		this.result_report=result_report;
	}
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	public int getResult_paperId() {
		return result_paperId;
	}
	public void setResult_paperId(int result_paperId) {
		this.result_paperId = result_paperId;
	}
	public int getResult_score() {
		return result_score;
	}
	public void setResult_score(int result_score) {
		this.result_score = result_score;
	}
	public int getResult_time() {
		return result_time;
	}
	public void setResult_time(int result_time) {
		this.result_time = result_time;
	}
	public int getResult_userid() {
		return userid;
	}
	public void setResult_userid(int userid) {
		this.userid = userid;
	}
	public String getResult_report() {
		return result_report;
	}
	public void setResult_report(String result_report) {
		this.result_report = result_report;
	}


}

