package com.sundy.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisTemplate redisTemplate;
	@RequestMapping(value ="/testMethod")
	@ResponseBody
	public Map<String,Object> testMethod(){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("code","1");
		resultMap.put("msg","Sucess!");
		return resultMap;
	}
	@RequestMapping(value ="/test01")
	@ResponseBody
	public Object test01(){
		
		 String SESSIONIDS = "sessionIds";
		 String PRINCIPALS = "principals";
		 
		 //session
		BoundHashOperations<String, String,Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
		hashOperations.delete("com.sundy.core.User@901aa19e");
	    Set<String> keySet=hashOperations.keys();
		return keySet;
		
	}
	
	

}
