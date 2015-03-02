package com.pp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pp.spring.service.UserService;

@Controller
public class AjaxController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/checkUserName", method = RequestMethod.POST)
	@ResponseBody
	public String isUserNameInUse(@RequestParam("username") String username) {
		if (userService.findByUserName(username) == null) {
			return "true";
		}

		return "false";
	}

	@RequestMapping(value= "/checkUserEmail", method = RequestMethod.POST)
	@ResponseBody
	public String isEmailInUse(@RequestParam("email") String email) {
		if (userService.findByEmail(email) == null) {
			return "true";
		}

		return "false";
	}

}
