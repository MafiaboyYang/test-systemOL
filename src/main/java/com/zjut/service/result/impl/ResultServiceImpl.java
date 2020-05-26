package com.zjut.service.result.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zjut.dao.result.ResultDao;
import com.zjut.pojo.Result;
import com.zjut.service.result.ResultService;

@Service(value = "resultService")
public class ResultServiceImpl implements ResultService{
    @Resource(name = "result")
    private ResultDao resultDao;
    /**
     * 试卷列表
     * @param paperid
     * @return
     */
	public List<Result>  findAllpaperid(){
    	return  resultDao.findAllpaperid();
    } 
    /**
     * 根据试卷号查看考试结果
     * @param paperid
     * @return
     */
	public List<Result> findbypaperid(int paper_id){
		return  resultDao.findbypaperid(paper_id);
	};
    /**
     * 按成绩查询考试结果
     * @param paper_id 试卷标签id
     * @return
     */
    public List<Result> findbypaperidAndresult_score(int paper_id) {
    	return  resultDao.findbypaperidAndresult_score(paper_id);
    } 
    
    /**
     * 按时间查询考试结果
     * @param paper_id  试卷id
     * @return
     */
    public List<Result> findbypaperidAndresult_time(int paper_id) {
    	return resultDao.findbypaperidAndresult_time(paper_id);
    }
}
