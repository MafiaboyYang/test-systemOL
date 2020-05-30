package com.zjut.service.result;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zjut.pojo.Result;
public interface ResultService {
	//试卷列表
	public List<Integer> findAllpaperid();
	//根据试卷号查看考试结果
	public List<Result> findbypaperid(int paperid);
	//将试卷以考试结果进行排序
	public List<Result> findbypaperidAndresult_score(int paperid);
	//将试卷以考试时长进行排序
	public List<Result> findbypaperidAndresult_time(int paperid);
}
