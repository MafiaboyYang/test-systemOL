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
        titleDao.update(title);
        return "修改题目成功";
    }

    /**
     * 更新题目内容
     * @param labelName 标签名
     * @return
     */
    @Override
    public List<Title> queryItemByLabel(String labelName) {
        return titleDao.queryItemByLabel(labelName);
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
