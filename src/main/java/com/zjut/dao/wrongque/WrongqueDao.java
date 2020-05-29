package com.zjut.dao.wrongque;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.zjut.pojo.Label;
import com.zjut.pojo.Wrongque;

import java.util.List;

@Repository(value = "WrongqueDao")
public interface WrongqueDao {
    /**
     * 查询用户错题集
     * @param
     * @return
     */
	public List<Wrongque> findWrongquebyuserID(@Param("userId")int userId);
}
