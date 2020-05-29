package com.zjut.pojo;

public class Result {
    private int id;
    private int paperId;
    private int time; 
    private int score;
    private int userid; 
    private String report;
	public Result(int paperId,int time,int score, int userid,String report ) {
		this.paperId=paperId;
		this.time=time;
		this.score=score;
		this.userid=userid;
		this.report=report;
	}
	public int getResult_id() {
		return id;
	}
	public void setResult_id(int id) {
		this.id = id;
	}
	public int getResult_paperId() {
		return paperId;
	}
	public void setResult_paperId(int paperId) {
		this.paperId = paperId;
	}
	public int getResult_score() {
		return score;
	}
	public void setResult_score(int score) {
		this.score = score;
	}
	public int getResult_time() {
		return time;
	}
	public void setResult_time(int time) {
		this.time = time;
	}
	public int getResult_userid() {
		return userid;
	}
	public void setResult_userid(int userid) {
		this.userid = userid;
	}
	public String getResult_report() {
		return report;
	}
	public void setResult_report(String report) {
		this.report = report;
	}


}

