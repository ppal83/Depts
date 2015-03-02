package com.pp.spring;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pp.spring.model.User;
import com.pp.spring.service.UserService;

@Controller
public class AuthController {
	
	private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String login(){

		return "login";
	}

	@RequestMapping(value="/logout")
	public String logout(){

		return "/";
	}

	@RequestMapping(value="/denied")
	public String denied(){

		return "denied";
	}

	@RequestMapping(value="/permission")
	public String permission(){

		return "permission";
	}

	@RequestMapping(value="/registration")
	public String registartion(Model model){

		model.addAttribute("user", new User());

		return "registration";
	}

	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute ("user") @Valid User user, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			user.setPassword("");
			return "registration";
		}

		userService.addUser(user);
		model.addAttribute("message", "success");

		return "login";
	}

}
