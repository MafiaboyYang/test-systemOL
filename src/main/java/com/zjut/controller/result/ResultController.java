package com.zjut.controller.result;

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
import com.zjut.pojo.Result;
import com.zjut.service.result.ResultService;
import com.zjut.utils.PageData;

@RequestMapping(value = "/userResult")
@Controller
public class ResultController extends BaseController{
    @Resource(name = "ResultService")
    private ResultService resultService;
//    @Resource(name = "result")
//    private Result result;
    
    /**
     * 分页查询试卷列表
     * @return
     */
    @RequestMapping(value = "/findAllpaperid", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findAllpaperid(){ 	
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Result> result = resultService.findAllpaperid();
        PageInfo pageInfo = new PageInfo(result);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;	
    }
    
    /**
     * 分页根据试卷号查看考试结果
     * @return
     */
    @RequestMapping(value = "/findbypaperid", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findbypaperid(){ 	
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int paper_id = (int) pageData.get("paper_id");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Result> result = resultService.findbypaperid(paper_id);
        PageInfo pageInfo = new PageInfo(result);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;	
    }
    
    /**
     * 根据考试成绩分页排序
     * @return
     */
    @RequestMapping(value = "/findbypaperidAndresult_score", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object findbypaperidAndresult_score(){ 	
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int paperId = (int) pageData.get("paperId");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Result> result = resultService.findbypaperidAndresult_score(paperId);
        PageInfo pageInfo = new PageInfo(result);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;	
    }
    
    /**
     * 根据考试时间分页排序
     * @return
     */
    @RequestMapping(value = "/findbypaperidAndresult_time", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  Object findbypaperidAndresult_time(){ 	
    	Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int paperId = (int) pageData.get("paperId");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Result> result = resultService.findbypaperidAndresult_time(paperId);
        PageInfo pageInfo = new PageInfo(result);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;		
    }

}
