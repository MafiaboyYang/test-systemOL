package com.zjut.dao.result;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zjut.pojo.Result;
import java.util.List;

@Repository(value = "resultDao")
public interface ResultDao {
    /**
     * 试卷列表
     * @param paperid
     * @return
     */
	public List<Result> findAllpaperid();
    /**
     * 根据试卷号查看考试结果
     * @param paperid
     * @return
     */
	public List<Result> findbypaperid(@Param("paperid")int paperid);
    /**
     * 根据考试结果查询排序
     * @param paperid
     * @return
     */
	public List<Result> findbypaperidAndresult_score(@Param("paperid")int paperid);
    /**
     * 根据考试结果查询排序
     * @param paperid
     * @return
     */
	public List<Result> findbypaperidAndresult_time(@Param("paperid")int paperid);
}
