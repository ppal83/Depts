package com.pp.spring;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;

@Controller
public class DeptsController {

	private static final Logger logger = Logger.getLogger(DeptsController.class);

	@Autowired
	private EmployeeService emplService;

	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) {
		logger.info("Welcome to home page!");
        /*
        model.addAttribute("dept", new Dept());
		model.addAttribute("deptsList", deptService.getAllDepts());
		*/
        System.out.println("before returning from deptscontroller");
        return "home";
	}

	@RequestMapping(value = "/depts", method = RequestMethod.GET)
	public String listDepts(Model model) {
		/*
        model.addAttribute("dept", new Dept());
		model.addAttribute("deptsList", deptService.getAllDepts());
        */
		return "dept";
	}

	@RequestMapping(value= "/dept/add", method = RequestMethod.GET)
	public String addDept(@ModelAttribute ("dept") @Valid Dept dept, 
			BindingResult bindingResult, Model model) {

		model.addAttribute("deptsList", deptService.getAllDepts());
		model.addAttribute("dept", dept);

		if (dept.getId() != 0) {
			bindingResult = editBindingResult(bindingResult, dept);
			model.addAllAttributes(bindingResult.getModel());
		}
		
		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			return "dept";
		}
		
		if(dept.getId() == 0){
			//new dept
			deptService.addDept(dept);
		} else {
			//existing dept
			deptService.updateDept(dept);
		}

		return "redirect:/depts";
	}

	@RequestMapping("/dept/edit/{id}")
	public String editDept(@PathVariable("id") int id, Model model) {
		model.addAttribute("dept", deptService.getDeptById(id));
		model.addAttribute("deptsList", deptService.getAllDepts());

		return "dept";
	}

	@RequestMapping("/dept/remove/{id}")
	public String removeDept(@PathVariable("id") int id, Model model){
		for (Employee e : deptService.getDeptById(id).getEmps()) {
			emplService.deleteEmployee(e);
		}
		deptService.deleteDeptById(id);
		model.addAttribute("deptsList", deptService.getAllDepts());
		model.addAttribute("dept", new Dept());

		return "redirect:/depts";
	}

	//Helper method to allow editing existing dept without checking it's name uniqueness
	private BindingResult editBindingResult(BindingResult original, Dept dept) {
		
		Dept dbDept = deptService.getDeptById(dept.getId());
		BindingResult edited = new BeanPropertyBindingResult(dept, "dept");

		for (ObjectError objectError : original.getAllErrors()) {
			String code = objectError.getCode();
			if ( code.equals("UniqueDeptName") && 
					!original.getFieldValue("name").equals(dbDept.getName()) ) {
				edited.addError(objectError);
			} else if ( !code.equals("UniqueDeptName") ) {
				edited.addError(objectError);
			}
		}

		return edited;
	}
}
