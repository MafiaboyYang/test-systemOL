package com.zjut.controller.label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.controller.base.BaseController;
import com.zjut.pojo.Label;
import com.zjut.service.label.LabelService;
import com.zjut.utils.PageData;

@RequestMapping(value = "/userLabel")
@Controller
public class LabelController extends BaseController{
    @Resource(name = "labelService")
    private LabelService labelService;
    @Resource(name = "label")
    private Label label;
    /**
     * 新增标签
     * @param params  前端json参数
     * @return 操作结果
     */
    @RequestMapping(value = "/addLabel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addLabel(@RequestBody Map<String , Object> params){
    	String label_content = (String) params.get("label_content");
    	Label label=new Label(label_content);
    	String msg=labelService.addLabel(label);
    	return msg;	
    }
    
    /**
     * 删除标签
     * @param params  前端json参数
     * @return 操作结果
     */
    @RequestMapping(value = "/deleteLabel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteLabel(@RequestBody Map<String, Object> params){ 
    	int label_id = (int) params.get("label_id");
    	String msg=labelService.deleteLabel(label_id);
    	return msg;	
    }
    
    /**
     * 查询标签
     * @param params  前端json参数
     * @return 操作结果
     */
    @RequestMapping(value = "/findAllLabel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findAllLabel(){ 	
        Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数
    	
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Label> label =labelService.findAllLabel();
        PageInfo pageInfo = new PageInfo(label);

        outputData.put("pageInfo", pageInfo);
    	String data=JSON.toJSONString(outputData);	
    	System.out.println("data" + data);

        return data;
    }
}
