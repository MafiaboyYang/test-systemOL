package com.zjut.utils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
*@author Mafiaboy E-mail:3040998845@qq.com
*@version 创建时间:2020年4月4日下午3:36:06
*类说明：参数封装Map
*/
public class PageData extends HashMap implements Map{
private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	/*
	* 将前端（如jsp页面）所提交的数据放置到map容器中
	* key为String类型
	* value为String类型
	* */
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();	//记录前端（如jsp页面）所提交请求中的请求参数和请求值的映射关系
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 	//获取map的迭代器，用作遍历map，其中map。entrySet()是把HashMap类型转换为集合类型
		Map.Entry entry; 	//表示一个映射项（里面有key和value），里面有getKey和getValue方法
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); //去掉最后的逗号
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}

	/*
	 *获取map容器中的对象（按照key值）
	 * 返回类型：Object
	 * */
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	/*
	*返回map容器中的value值，按照key值查找
	* 返回类型：String
	* */
	public String getString(Object key) {
		return (String)get(key);
	}

	/*
	*将key和value插入map中（如果传递现有密钥，则返回先前的值。如果传递了一对新对，则返回NULL）
	* 返回类型：Object
	* */
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
}
