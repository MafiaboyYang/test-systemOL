package com.zjut.controller.title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zjut.pojo.Title;
import com.zjut.service.title.TitleService;
import com.zjut.dao.title.TitleDao;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.controller.base.BaseController;
import com.zjut.pojo.User;
import com.zjut.pojo.system.Admin;
import com.zjut.service.system.admin.AdminService;
import com.zjut.service.user.UserService;
import com.zjut.utils.Const;
import com.zjut.utils.DateUtil;
import com.zjut.utils.Jurisdiction;
import com.zjut.utils.PageData;
import java.util.Map;


/**
 *题库管理  controller
 *类说明
 */
@Controller
public class titleController extends BaseController {

    @Autowired
    private TitleService titleService;    //题库管理服务类


    /**
     * 新增题目
     * @param params  前端json参数
     * @return 操作结果
     */
    @RequestMapping(value = "/addTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addItem(@RequestBody Map<String , Object> params){
        String content = (String) params.get("content");
        String answer = (String) params.get("answer");
        int courseId = (int) params.get("courseId");
        String difficulty = (String) params.get("difficulty");
        int userId = (int) params.get("userId");
        String time = (String) params.get("time");
        String picture = (String) params.get("picture");
        int lockd = (int) params.get("lockd");
        int labelId = (int) params.get("labelId");
        String type = (String) params.get("type");

        Title title = new Title(content, answer, courseId, difficulty, userId, time, picture, lockd, labelId, type);
        String msg = titleService.addTitle(title);

        return msg;
    }

    /**
     * 修改题目
     * @param params 前端json参数
     * @return 修改结果
     */
    @RequestMapping(value = "/updateTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateItem(@RequestBody Map<String , Object> params){
        String content = (String) params.get("content");
        String answer = (String) params.get("answer");
        int courseId = (int) params.get("courseId");
        String difficulty = (String) params.get("difficulty");
        int userId = (int) params.get("userId");
        String time = (String) params.get("time");
        String picture = (String) params.get("picture");
        int lockd = (int) params.get("lockd");
        int labelId = (int) params.get("labelId");
        String type = (String) params.get("type");

        Title title = new Title(content, answer, courseId, difficulty, userId, time, picture, lockd, labelId, type);
        title.setItemId((int) params.get("itemId"));

        String msg = titleService.updateTitle(title);

        return msg;
    }

    /**
     * 锁定题目
     * @param params 前端json参数
     * @return 锁定信息
     */
    @RequestMapping(value = "/lockTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String lockItem(@RequestBody Map<String, Object> params){
        int itemId = (int) params.get("itemId");
        String msg = titleService.lockTitle(itemId);
        return msg;
    }

    /**
     * 解锁题目
     * @param params 前端json参数
     * @return 解锁信息
     */
    @RequestMapping(value = "/unlockTitle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String unlockItem(@RequestBody Map<String, Object> params){
        int itemId = (int) params.get("itemId");
        String msg = titleService.unlockTitle(itemId);
        return msg;
    }

    /**
     * 根据标签查询题目
     * @return
     */
    @RequestMapping(value = "/queryTitleByLabel", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryItemByLabel(){
        System.out.println("com.zjut.controller.title.TitleController    按标签分页查询题目");

        Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        int labelId = (int) pageData.get("labelId");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

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
     * @return
     */
    @RequestMapping(value = "/queryTitleByDifficulty", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object queryItemByDifficulty(){
        System.out.println("com.zjut.controller.title.TitleController    按难度分页查询题目");

        Map<String,Object> outputData = new HashMap<String, Object>();
        PageData pageData = this.getPageData();
        String difficulty = pageData.getString("difficulty");
        String page = pageData.getString("page");			//当前页码
        String pageSize = pageData.getString("pageSize");	//每页查询条数

        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        List<Title> titleList = titleService.queryItemByDifficulty(difficulty);
        PageInfo pageInfo = new PageInfo(titleList);

        outputData.put("pageInfo", pageInfo);
        String data = JSON.toJSONString(outputData);
        System.out.println("data" + data);

        return data;
    }

}
