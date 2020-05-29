package com.zjut.dao.soulsoother;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.zjut.pojo.Soulsoother;

@Repository(value = "SoulsootherDao")
public interface SoulsootherDao {
    /**
     * 新增标签
     * @param soulsoother
     * @return
     */
	public void addSoulsoother(Soulsoother soulsoother);;
	//删
	/**
     * 删除标签
     * @param soulsoother_id
     * @return
     */
	public void deleteSoulsoother(@Param("soulsoother_id")int soulsoother_id);
    /**
     * 查询心灵鸡汤
     * @param
     * @return
     */
    public List<Soulsoother> findAllSoulsoother();
}
