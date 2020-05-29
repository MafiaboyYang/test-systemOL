package com.zjut.controller.wrongque;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.controller.base.BaseController;
import com.zjut.pojo.Wrongque;
import com.zjut.pojo.Result;
import com.zjut.pojo.Title;
import com.zjut.service.title.TitleService;
import com.zjut.service.wrongque.WrongqueService;
import com.zjut.utils.PageData;
@RequestMapping(value = "/userWrongque")
@Controller
public class WrongqueController extends BaseController{
    @Resource(name = "WrongqueService")
    private WrongqueService wrongqueService;
//    @Resource(name = "wrongque")
//    private Wrongque wrongque;
    @Resource
    private TitleService titleService; 
    /**
     * 分页根据用户ID查看考试结果
     * @return
     */
    @RequestMapping(value = "/findWrongquebyuserID", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findWrongquebyuserID(){ 	
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int userId = (int) pageData.get("userId");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Wrongque> wrongque = wrongqueService.findWrongquebyuserID(userId);
        PageInfo pageInfo = new PageInfo(wrongque);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;	
    }
    
    /**
     * 根据ID查题号看错题
     * @return
     */
    @RequestMapping(value = "/findtitlebywrongque_id", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findtitlebywrongque_id(){
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int itemId = (int) pageData.get("wrongque_id");
        
        Title title = titleService.queryTitleById(itemId);
        outputData.put("title", title);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);
        return data;
    }
}
