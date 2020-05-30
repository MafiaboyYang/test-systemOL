package com.zjut.service.result.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zjut.dao.result.ResultDao;
import com.zjut.pojo.Result;
import com.zjut.service.result.ResultService;

@Service(value = "ResultService")
public class ResultServiceImpl implements ResultService{
    @Resource(name = "ResultDao")
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
	public List<Result> findbypaperid(int paperId){
		return  resultDao.findbypaperid(paperId);
	};
    /**
     * 按成绩查询考试结果
     * @param paperid 试卷标签id
     * @return
     */
    public List<Result> findbypaperidAndresult_score(int paperId) {
    	return  resultDao.findbypaperidAndresult_score(paperId);
    } 
    
    /**
     * 按时间查询考试结果
     * @param paperid  试卷id
     * @return
     */
    public List<Result> findbypaperidAndresult_time(int paperId) {
    	return resultDao.findbypaperidAndresult_time(paperId);
    }
}
