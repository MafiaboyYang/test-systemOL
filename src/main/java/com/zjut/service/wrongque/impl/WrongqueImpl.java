package com.zjut.service.wrongque.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjut.dao.wrongque.WrongqueDao;
import com.zjut.pojo.Wrongque;
import com.zjut.service.wrongque.WrongqueService;

@Service(value = "wrongqueService")
public class WrongqueImpl implements WrongqueService{
    @Resource(name = "wrongque")
    private WrongqueDao wrongqueDao;	
    /**
     * 查询用户错题号
     * @param
     * @return
     */
	@Override
    public List<Wrongque> findWrongquebyuserID(int userId) {
		return  wrongqueDao.findWrongquebyuserID(userId);
    }
}
