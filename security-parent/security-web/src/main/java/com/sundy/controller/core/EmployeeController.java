package com.sundy.controller.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/employeeList")
	public String employeeDataList(Model model) {
		
		return "employee/employeeList";
	}
}
