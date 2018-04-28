package com.sundy.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value ="/testMethod")
	@ResponseBody
	public Map<String,Object> testMethod(){
		logger.info("*********≤‚ ‘µÿ÷∑****************");
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("code","1");
		resultMap.put("msg","Sucess!");
		return resultMap;
	}

}
