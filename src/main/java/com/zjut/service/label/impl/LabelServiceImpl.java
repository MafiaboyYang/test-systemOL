package com.zjut.service.label.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.label.LabelDao;
import com.zjut.pojo.Label;
import com.zjut.service.label.LabelService;

@Service(value = "labelService")
public class LabelServiceImpl implements LabelService{
    @Resource(name = "label")
    private LabelDao labelDao;
    /**
     * 增加标签
     * @param label 
     * @return
     */
    @Override
    public String addLabel(Label label) {
    	labelDao.addLabel(label);
        return "增加成功";
    }
    
	/**
     * 删除标签
     * @param label_id
     * @return
     */
	@Override
	public String deleteLabel(int label_id) {
		labelDao.deleteLabel(label_id);	
		return "删除成功";
	}
	
    /**
     * 查询题目
     * @param
     * @return
     */
	@Override
    public List<Label> findAllLabel() {
		return  labelDao.findAllLabel();
    }
}
