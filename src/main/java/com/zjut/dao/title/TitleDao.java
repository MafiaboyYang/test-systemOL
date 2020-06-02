package com.zjut.dao.title;

import com.zjut.pojo.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *题库操作dao
 *类说明
 */
public interface TitleDao {

    /**
     * 新增题目
     * @param item
     */
    void addItem(Title item);

    /**
     * 修改题目
     * @param item
     */
    void updateItem(Title item);

    /**
     * 锁定题目
     * @param itemId
     */
    void lockItem(@Param("itemId") int itemId);

    /**
     * 解锁题目
     * @param itemId
     */
    void unlockItem(@Param("itemId") int itemId);

    /**
     * 根据标签查询题目
     * @param labelId
     * @return titleList
     */
    List<Title> queryItemByLabel(@Param("labelId") int labelId);

    /**
     * 根据难度查询题目
     * @param difficulty
     * @return titleList
     */
    List<Title> queryItemByDifficulty(@Param("difficulty") String difficulty);

    /**
     * 查看所有题目
     * @return titleList
     */
    List<Title> listTitles();

    /**
     * 按id查询一道题目
     * @return title
     */
    Title queryTitleById(@Param("titleId") int titleId);

    /**
     * 按id查询标签
     * @return labelContent
     */
    String queryLabelById(@Param("labelId") int labelId);

    /**
     * 按id查询课程
     * @return CourseName
     */
    String queryCourseNameById(@Param("courseId") int courseId);


    /**
     * 按id查询用户名
     * @return UserName
     */
    String queryUserNameById(@Param("userId") int userId);
}
