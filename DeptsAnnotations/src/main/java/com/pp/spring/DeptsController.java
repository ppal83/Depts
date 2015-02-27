package com.pp.spring;

import java.util.Collection;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.model.User;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;
import com.pp.spring.service.UserService;

@Controller
@SessionAttributes({"empList", "id", "deptsList"})
public class DeptsController {

	private static final Logger logger = Logger.getLogger(DeptsController.class);
	
	private EmployeeService emplService;
	private DeptService deptService;
	private UserService userService;
	
	@Autowired(required=true)
	@Qualifier(value="emplService")
	public void setEmplService(EmployeeService emplService){
		this.emplService = emplService;
	}

	@Autowired(required=true)
	@Qualifier(value="deptService")
	public void setDeptService(DeptService deptService){
		this.deptService = deptService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService){
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listDepts(Model model) {
		logger.info("Welcome to home page!");
		model.addAttribute("dept", new Dept());
		model.addAttribute("deptsList", deptService.getAllDepts());

		return "home";
	}
	
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
	public String editDept(@RequestParam("id") int id, Model model) {
		Dept dept = deptService.getDeptById(id);
		if (dept == null) {
			model.addAttribute("dept", new Dept());
			model.addAttribute("error", "Please choose department");
			
			return "home";
		}
		model.addAttribute("dept", dept);
		
		return "dept";
	}
	
	@RequestMapping(value = "/newdept", method = RequestMethod.GET)
	public String createDept(@ModelAttribute("dept") @Valid Dept dept, 
								BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			return "dept";
		}
		
		if (deptService.findByName(dept.getName()) != null) {
			model.addAttribute("dept", dept);
			model.addAttribute("error", "Dept already exists");
			
			return "dept";
		}
		deptService.addDept(dept);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/updatedept", method = RequestMethod.GET)
	public String updateDept(@ModelAttribute("dept") @Valid Dept dept,
								BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			return "dept";
		}
		
		deptService.updateDept(dept);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/deletedept", method = RequestMethod.GET)
	public String deleteDept(@ModelAttribute("dept") Dept dept) {
		for (Employee e : deptService.getDeptById(dept.getId()).getEmps()) {
			emplService.deleteEmployee(e);
		}
		deptService.deleteDept(dept);
		return "redirect:/";
	}
	

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listPersons(@RequestParam("id") int id, Model model) {
		Collection<Employee> empList = null;
		if (id == 0) {
			empList = emplService.getAllEmployees();
		} else {
			empList = deptService.getDeptById(id).getEmps();
		}
		model.addAttribute("employee", new Employee());
		model.addAttribute("empList", empList);
		model.addAttribute("id", id);

		model.addAttribute("dept", new Dept());

		return "employee";
	}

	//For add and update employee both
	@RequestMapping(value= "/employee/add", method = RequestMethod.GET)
	public String addPerson(@ModelAttribute ("employee") @Valid Employee emp, 
			BindingResult bindingResult, @RequestParam("deptId") int deptId, Model model) {
		
		Employee empByName = emplService.findByName(emp.getName());
		Employee empByEmail = emplService.findByEmail(emp.getEmail());
		String message = null;		
		
		if ((empByName != null) || (empByEmail != null)) {
			if (empByName != null) {
				message = "Employee <em>" + emp.getName() + "</em> already exists!";
			} else {
				message = "Email <em>" + emp.getEmail() + "</em> already exists!";
			}
			model.addAttribute("error", message);
			
			return "employee";
		}

		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			return "employee";
		}

		emp.setDept(deptService.getDeptById(deptId));
		if(emp.getId() == 0){
			//new employee
			emplService.addEmloyee(emp);
		} else {
			//existing employee
			emplService.updateEmployee(emp);
		}

		return "redirect:/employees";
	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id, Model model){
		emplService.deleteEmployeeById(id);
		
		return "redirect:/employees";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("employee", emplService.getEmployeeById(id));
		
		return "employee";
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
	public String registerUser(@ModelAttribute ("user") @Valid User user, 
			BindingResult bindingResult, Model model) {

		User usrByName = userService.findByUserName(user.getUsername());
		User usrByEmail = userService.findByEmail(user.getEmail());
		String message = null;		
		
		if ((usrByName != null) || (usrByEmail != null)) {
			if (usrByName != null) {
				message = "User <em>" + user.getUsername() + "</em> already exists!";
			} else {
				message = "Email <em>" + user.getEmail() + "</em> already exists!";
			}
			user.setPassword("");
			model.addAttribute("error", message);
			
			return "registration";
		} 
		
		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			
			return "registration";
		}
		
		userService.addUser(user);
		model.addAttribute("message", "success");
		
		return "login";
	}
	
	//AJAX check methods
	
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
