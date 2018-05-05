package com.sundy.controller.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/employeeList.page")
	public String employeeDataList(Model model) {
		
		return "employee/employeeData";
	}
	
	@RequestMapping("/loadEmployeeData")
	@ResponseBody
	public Object loadEmployeeData(HttpServletRequest params) {
		
		   // 获取前端过来的参数,下面三个参数是 dataTable默认的，不要随便更改 
		 Integer sEcho = Integer.valueOf(params.getParameter("sEcho"));// 记录操作的次数 每次加1 
		 Integer iDisplayStart = Integer.valueOf(params.getParameter("iDisplayStart"));// 起始 
		 Integer iDisplayLength = Integer.valueOf(params.getParameter("iDisplayLength"));// 每页显示的size 
		 // 为操作次数加1，必须这样做 
		   int initEcho = sEcho + 1;
		   
		   Map<String,Object> returnMap=new HashMap<String,Object>();
		   returnMap.put("sEcho", initEcho); 
		   returnMap.put("iTotalRecords",100);//数据总条数 
		   returnMap.put("iTotalDisplayRecords",10);//显示的条数 
		   returnMap.put("aData","");//数据集合 
		
		return returnMap;
	}
}
