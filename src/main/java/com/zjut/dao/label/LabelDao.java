package com.zjut.dao.label;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.zjut.pojo.Label;
import java.util.List;

@Repository(value = "labelDao")
public interface LabelDao {
	//增
    /**
     * 新增标签
     * @param label
     * @return
     */
	public void addLabel(Label label);
	//删
	/**
     * 删除标签
     * @param label_id
     * @return
     */
	public void deleteLabel(@Param("label_id")int label_id);
	//根据指定的页数，来显示 标签
    public List<Label> findByLabel(int start,int rows);
    //显示所有的标签
    /**
     * 查询标签
     * 根据指定的页数，来显示 标签
     * @param
     * @return
     */
    public List<Label> findAllLabel();
}
