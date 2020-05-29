package com.zjut.controller.title;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.controller.base.BaseController;
import com.zjut.pojo.Title;
import com.zjut.service.title.TitleService;
import com.zjut.utils.PageData;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年5月26日下午1:15:57
*类说明
*/
@RequestMapping(value="/userTitle")
@Controller
public class UserTitleController extends BaseController {

    @Autowired
    private TitleService titleService;    //题库管理服务类


    /**
     * 新增题目
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/addTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addItem() {

        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pd = this.getPageData();

        String content = pd.getString("content");
        String answer = pd.getString("answer");
        int courseId = Integer.parseInt(pd.getString("courseId"));
        String difficulty = pd.getString("difficulty");
        int userId = Integer.parseInt(pd.getString("userId"));
        String time = pd.getString("time");
        String picture = pd.getString("picture");
        int lockd = Integer.parseInt(pd.getString("lockd"));
        int labelId = Integer.parseInt(pd.getString("labelId"));
        String type = pd.getString("type");

        Title title = new Title(content, answer, courseId, difficulty, userId, time, picture, lockd, labelId, type);
        String msg = titleService.addTitle(title);

        outputData.put("result_message", msg);
        String data = JSON.toJSONString(outputData);
        return data;
    }

    /**
     * 修改题目
     * @return 修改结果
     */
    @RequestMapping(value = "/updateTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateItem() {
        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pd = this.getPageData();

        String content = pd.getString("content");
        String answer = pd.getString("answer");
        int courseId = Integer.parseInt(pd.getString("courseId"));
        String difficulty = pd.getString("difficulty");
        int userId = Integer.parseInt(pd.getString("userId"));
        String time = pd.getString("time");
        String picture = pd.getString("picture");
        int lockd = Integer.parseInt(pd.getString("lockd"));
        int labelId = Integer.parseInt(pd.getString("labelId"));
        String type = pd.getString("type");

        Title title = new Title(content, answer, courseId, difficulty, userId, time, picture, lockd, labelId, type);
        title.setItemId(Integer.parseInt(pd.getString("itemId")));

        String msg = titleService.updateTitle(title);
        outputData.put("result_message", msg);
        String data = JSON.toJSONString(outputData);
        return data;
    }

    /**
     * 根据标签查询题目
     *
     * @return
     */
    @RequestMapping(value = "/queryTitleByLabel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryItemByLabel() {
        System.out.println("com.zjut.controller.title.TitleController.java;按标签分页查询题目");

        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int labelId = (int) pageData.get("labelId");
        String page = pageData.getString("page");            //当前页码
        String pageSize = pageData.getString("pageSize");    //每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Title> titleList = titleService.queryItemByLabel(labelId);
        PageInfo pageInfo = new PageInfo(titleList);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;
    }

    /**
     * 根据难度查询题目
     *
     * @return object
     */
    @RequestMapping(value = "/queryTitleByDifficulty", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryItemByDifficulty() {
        System.out.println("com.zjut.controller.title.TitleController.java;按难度分页查询题目");

        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        String difficulty = pageData.getString("difficulty");
        String page = pageData.getString("page");            //当前页码
        String pageSize = pageData.getString("pageSize");    //每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Title> titleList = titleService.queryItemByDifficulty(difficulty);
        PageInfo pageInfo = new PageInfo(titleList);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;
    }

    /**
     * 查询显示所有题目
     *
     * @return object
     */
    @RequestMapping(value = "/listTitles", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object listTitles() {
        System.out.println("com.zjut.controller.title.TitleController.java; 查看所有题目信息");

        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pd = this.getPageData();
        String page = pd.getString("page");            //当前页码
        String pageSize = pd.getString("pageSize");    //每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Title> titleList = titleService.listTitles();
        PageInfo pageInfo = new PageInfo(titleList);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);
        return data;
    }

    /**
     * 查看单道题目
     * @return data
     */
    @RequestMapping(value = "/queryTitleById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryTitleById(){
        System.out.println("com.zjut.controller.title.TitleController.java; 查看一道题目信息");

        Map<String, Object> outputData = new HashMap<String, Object>();
        PageData pd = this.getPageData();
        int titleId = Integer.parseInt(pd.getString("itemId"));

        Title title = titleService.queryTitleById(titleId);
        outputData.put("title", title);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);
        return data;
    }

}

