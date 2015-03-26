package com.pp.spring;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pp.spring.model.User;
import com.pp.spring.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AuthController {
	
	private static final Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
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
	public String registerUser(@ModelAttribute ("user") @Validated User user,
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
