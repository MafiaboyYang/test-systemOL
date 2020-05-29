package com.zjut.service.soulsoother.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.pojo.Soulsoother;
import com.zjut.dao.soulsoother.SoulsootherDao;
import com.zjut.service.soulsoother.SoulsootherService;

@Service(value = "SoulsootherService")
public class SoulsootherServiceImpl implements SoulsootherService{
    @Resource(name = "SoulsootherDao")
    private SoulsootherDao soulsootherDao;
    /**
     * 增加心灵鸡汤
     * @param soulsoother
     * @return
     */
    @Override
    public String addSoulsoother(Soulsoother soulsoother) {
    	soulsootherDao.addSoulsoother(soulsoother);
        return "增加成功";
    }
	/**
     * 
     * 删除心灵鸡汤
     * @param soulsoother_id
     * @return
     */
	@Override
	public String deleteSoulsoother(int soulsoother_id) {
		soulsootherDao.deleteSoulsoother(soulsoother_id);
		return "删除成功";
	}
    /**
     * 查询心灵鸡汤
     * @param
     * @return
     */
	@Override
    public List<Soulsoother> findAllSoulsoother() {
		return  soulsootherDao.findAllSoulsoother();
    }
}
