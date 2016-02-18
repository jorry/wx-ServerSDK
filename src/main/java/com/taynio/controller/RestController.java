package com.taynio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taynio.dto.ListBean;
import com.taynio.dto.UserInfo;
import com.taynio.service.UserService;

@Controller
@RequestMapping(value = "/rest", method = RequestMethod.POST)
public class RestController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ListBean getAllTest(String uid ,String utype,String pageIndex) throws Exception {
		return userService.getAllUsers();
	}
	
	
}
