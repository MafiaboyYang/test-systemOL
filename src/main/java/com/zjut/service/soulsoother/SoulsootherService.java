package com.zjut.service.soulsoother;

import java.util.List;
import com.zjut.pojo.Soulsoother;
import com.zjut.utils.Page;

import com.zjut.pojo.Soulsoother;

public interface SoulsootherService {
    //添加心灵鸡汤
    public String addSoulsoother(Soulsoother soulsoother);
	//删
	public String deleteSoulsoother(int id);
    //显示所有的心灵鸡汤
    public List<Soulsoother> findAllSoulsoother();
}
