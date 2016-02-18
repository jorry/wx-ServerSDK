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
@RequestMapping(value = "/rest-get", method = RequestMethod.GET)
public class RestControllerGet {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public UserInfo show(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return userService.getUserInfo(id);

	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ListBean getAll() throws Exception {
		return userService.getAllUsers();
	}
 
	
}
