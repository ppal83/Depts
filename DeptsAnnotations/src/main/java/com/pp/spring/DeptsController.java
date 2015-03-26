package com.pp.spring;

import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DeptsController {

	private static final Logger logger = Logger.getLogger(DeptsController.class);

	@Autowired
	private EmployeeService emplService;

	@Autowired
	private DeptService deptService;

	@Autowired
	@Qualifier("deptValidator")
	private Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/")
	public String homePage() {
		logger.info("Welcome to home page!");

		return "home";
	}

	@RequestMapping(value = "/depts")
	public String listDepts() {

		return "dept";
	}

	@RequestMapping(value= "/dept/add", method = RequestMethod.GET)
	public String addDept(@ModelAttribute ("dept") @Validated Dept dept,
			BindingResult bindingResult, Model model) {


		if (dept.getId() != 0) {
			bindingResult = editBindingResult(bindingResult, dept);
			model.addAllAttributes(bindingResult.getModel());
		}

		if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			model.addAttribute("deptsList", deptService.getAllDepts());

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
	public String removeDept(@PathVariable("id") int id){
		for (Employee e : deptService.getDeptById(id).getEmps()) {
			emplService.deleteEmployee(e);
		}
		deptService.deleteDeptById(id);

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
