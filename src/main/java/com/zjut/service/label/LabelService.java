package com.zjut.service.label;

import java.util.List;
import com.zjut.pojo.Label;
import com.zjut.utils.Page;

public interface LabelService {
    //添加试卷
    public String addLabel(Label label);
	//删
	public String deleteLabel(int id);
	//查
	//public Page<Label> findByLabel(String _currentPage,int rows);
	
    //显示所有的标签
    public List<Label> findAllLabel();
}
