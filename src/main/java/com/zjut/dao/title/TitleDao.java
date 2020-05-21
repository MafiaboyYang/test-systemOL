package com.zjut.dao.title;

import com.zjut.pojo.Title;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Null;
import java.util.List;

/**
 *题库操作dao
 *类说明
 */
public interface TitleDao {

    /**
     * 新增题目
     * @param item
     * @return
     */
    void addItem(Title item);

    /**
     * 修改题目
     * @param item
     * @return
     */
    void update(Title item);

    /**
     * 锁定题目
     * @param itemId
     * @return
     */
    void lockItem(@Param("itemId") int itemId);

    /**
     * 解锁题目
     * @param itemId
     * @return
     */
    void unlockItem(@Param("itemId") int itemId);

    /**
     * 根据标签查询题目
     * @param labelId
     * @return
     */
    List<Title> queryItemByLabel(@Param("labelId") int labelId);

    /**
     * 根据难度查询题目
     * @param difficulty
     * @return
     */
    List<Title> queryItemByDifficulty(@Param("difficulty") String difficulty);
}
