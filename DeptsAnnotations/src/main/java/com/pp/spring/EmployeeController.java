package com.pp.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@SessionAttributes( {"empList", "id", "deptsList"} )
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService emplService;

	@Autowired
	private DeptService deptService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
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
		
		if (emp.getId() != 0) {
			bindingResult = editBindingResult(bindingResult, emp);
			model.addAllAttributes(bindingResult.getModel());
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

	@RequestMapping("/employee/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("employee", emplService.getEmployeeById(id));

		return "employee";
	}

	@RequestMapping("/employee/remove/{id}")
	public String removePerson(@PathVariable("id") int id, Model model){
		emplService.deleteEmployeeById(id);

		return "redirect:/employees";
	}

	/*Helper method to allow editing existing employee 
	  without checking it's name and email uniqueness*/
	private BindingResult editBindingResult(BindingResult original, Employee emp) {

		Employee dbEmp = emplService.getEmployeeById(emp.getId());
		emp.setBirthDate(dbEmp.getBirthDate()); //workaround to avoid getting incorrect
		emp.setHireDate(dbEmp.getHireDate());   //date format
		BindingResult edited = new BeanPropertyBindingResult(emp, "employee");

		for (ObjectError objectError : original.getAllErrors()) {
			String code = objectError.getCode();
			if ( code.equals("UniqueEmpName") && 
					!original.getFieldValue("name").equals(dbEmp.getName()) ) {
				edited.addError(objectError);
			} else if ( (code.equals("UniqueEmpEmail") && 
					!original.getFieldValue("email").equals(dbEmp.getEmail())) ) {
				edited.addError(objectError);
			} else if ( !(code.equals("UniqueEmpName") || code.equals("UniqueEmpEmail")) ) {
				edited.addError(objectError);
			}
		}

		return edited;
	}
}
