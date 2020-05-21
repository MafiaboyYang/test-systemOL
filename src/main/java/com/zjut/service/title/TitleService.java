package com.zjut.service.title;

import com.zjut.pojo.Title;

import java.util.List;

/**
 *题库管理  service接口
 *类说明
 */
public interface TitleService {
    public String addTitle(Title item);
    public String updateTitle(Title item);

    public List<Title> queryItemByLabel(int labelId);
    public List<Title> queryItemByDifficulty(String difficulty);

    public String lockTitle(int itemId);
    public String unlockTitle(int itemId);
}