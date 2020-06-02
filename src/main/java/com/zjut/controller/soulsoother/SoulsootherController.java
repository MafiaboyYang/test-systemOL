package com.zjut.controller.soulsoother;

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
import com.zjut.pojo.Soulsoother;
import com.zjut.service.soulsoother.SoulsootherService;
import com.zjut.utils.PageData;

@RequestMapping(value = "/userSoulsoother")
@Controller
public class SoulsootherController extends BaseController {
	    @Resource(name = "SoulsootherService")
	    private SoulsootherService soulsootherService;
//	    @Resource(name = "soulsoother")
//	    private Soulsoother soulsoother;
	    /**
	     * 新增心灵鸡汤
	     * @param params  前端json参数
	     * @return 操作结果
	     */
	    @RequestMapping(value = "/addSoulsoother", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public String addSoulsoother(@RequestBody Map<String , Object> params){
	    	String soulsoother_content = (String) params.get("soulsoother_content");
	    	Soulsoother soulsoother=new Soulsoother(soulsoother_content);
	    	String msg=soulsootherService.addSoulsoother(soulsoother);
	    	return msg;	
	    }
	    
	    /**
	     * 删除心灵鸡汤
	     * @param params  前端json参数
	     * @return 操作结果
	     */
	    @RequestMapping(value = "/ddeleteSoulsoother", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public String deleteSoulsoother(@RequestBody Map<String, Object> params){ 
	    	int soulsoother_id = (int) params.get("soulsoother_id");
	    	String msg=soulsootherService.deleteSoulsoother(soulsoother_id);
	    	return msg;	
	    }
	    
	    /**
	     * 删除心灵鸡汤
	     * @param params  前端json参数
	     * @return 操作结果
	     */
	    @RequestMapping(value = "/findAllSoulsoother", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public Object findAllSoulsoother(){ 	
	        Map<String,Object> outputData = new HashMap<String, Object>();
	        PageData pageData = this.getPageData();
	        String page = pageData.getString("page");			//当前页码
	        String pageSize = pageData.getString("pageSize");	//每页查询条数
	    	
	        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(pageSize));
	        List<Soulsoother> soulsoother =soulsootherService.findAllSoulsoother();
	        PageInfo pageInfo = new PageInfo(soulsoother);

	        outputData.put("pageInfo", pageInfo);
	    	String data=JSON.toJSONString(outputData);	
	    	System.out.println("data" + data);

	        return data;
	    }
}


