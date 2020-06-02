package com.zjut.service.title.impl;

import com.zjut.pojo.Title;
import com.zjut.service.title.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zjut.dao.title.TitleDao;

import java.util.List;


/**
 *题库管理  service实现类
 *类说明
 */
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleDao titleDao;


    /**
     * 锁定题目
     * @param itemId
     * @return
     */
    @Override
    public String lockTitle(int itemId) {
        titleDao.lockItem(itemId);
        return "锁定成功";
    }

    /**
     * 解锁题目
     * @param itemId
     * @return
     */
    @Override
    public String unlockTitle(int itemId) {
        titleDao.unlockItem(itemId);
        return "解锁成功";
    }

    @Override
    public String queryLabelById(int labelId) {
        return titleDao.queryLabelById(labelId);
    }

    @Override
    public String queryCourseNameById(int courseId) {
        return titleDao.queryCourseNameById(courseId);
    }

    @Override
    public String queryUserNameById(int userId) {
        return titleDao.queryUserNameById(userId);
    }

    @Override
    public List<Title> setListInfo(List<Title> titleList) {
        int length = titleList.size(), labelId, courseId, userId;
        while(length > 0){
            labelId = titleList.get(length - 1).getLabelId();
            courseId = titleList.get(length - 1).getCourseId();
            userId = titleList.get(length - 1).getUserId();
            titleList.get(length - 1).setLabelContent(titleDao.queryLabelById(labelId));
            titleList.get(length - 1).setCourseName(titleDao.queryCourseNameById(courseId));
            titleList.get(length - 1).setUserName(titleDao.queryUserNameById(userId));
            length --;
        }

        return titleList;
    }


    /**
     * 新增题目
     * @param title  题目
     * @return
     */
    @Override
    public String addTitle(Title title) {
        titleDao.addItem(title);
        return "添加成功";
    }

    /**
     * 更新题目内容
     * @param title  题目
     * @return
     */
    @Override
    public String updateTitle(Title title) {
        titleDao.updateItem(title);
        return "修改题目成功";
    }

    /**
     * 更新题目内容
     * @param labelId 标签名
     * @return
     */
    @Override
    public List<Title> queryItemByLabel(int labelId) {
        return titleDao.queryItemByLabel(labelId);
    }

    /**
     * 更新题目内容
     * @param difficulty  难度
     * @return
     */
    @Override
    public List<Title> queryItemByDifficulty(String difficulty) {
        return titleDao.queryItemByDifficulty(difficulty);
    }

    @Override
    public Title queryTitleById(int titleId) {
        return titleDao.queryTitleById(titleId);
    }

    @Override
    public List<Title> listTitles() {
        return titleDao.listTitles();
    }


}
