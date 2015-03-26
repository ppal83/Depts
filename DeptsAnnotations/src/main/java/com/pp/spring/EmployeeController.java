package com.pp.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;

@Controller
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService emplService;

	@Autowired
	private DeptService deptService;

	@Autowired
	@Qualifier("employeeValidator")
	private Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/employees")
	public String listPersons(@RequestParam("id") int id, Model model) {
		Employee emp = new Employee();
		emp.setDept(deptService.getDeptById(id));
		model.addAttribute("employee", emp);

		return "employee";
	}

	//For add and update employee both
	@RequestMapping(value= "/employee/add", method = RequestMethod.GET)
	public String addPerson(@ModelAttribute ("employee") @Validated Employee emp,
							BindingResult bindingResult, @RequestParam("deptId") int deptId, Model model) {

		model.addAttribute("id", deptId);
		emp.setDept(deptService.getDeptById(deptId));

        if (bindingResult.hasErrors()) {
			logger.info("Errors found");
			Collection<Employee> empList = deptService.getDeptById(deptId).getEmps();
			model.addAttribute("empList", empList);
			model.addAttribute("deptsList", deptService.getAllDepts());

			return "employee";
		}

		if(emp.getId() == 0){
			//new employee
			emplService.addEmloyee(emp);
		} else {
			//existing employee
			emplService.updateEmployee(emp);
		}

		return "redirect:/employees";
	}

	@RequestMapping("/employee/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		Employee emp = emplService.getEmployeeById(id);
		model.addAttribute("employee", emp);
		Collection<Employee> empList = emp.getDept().getEmps();
		model.addAttribute("empList", empList);
		model.addAttribute("deptsList", deptService.getAllDepts());

		return "employee";
	}

	@RequestMapping("/employee/remove/{id}")
	public String removePerson(@PathVariable("id") int id, Model model){
		Employee emp = emplService.getEmployeeById(id);
		emplService.deleteEmployee(emp);
		model.addAttribute("id", emp.getDept().getId());

		return "redirect:/employees";
	}

}