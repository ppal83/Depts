package com.pp.spring;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EmpRESTController {

    private static final Logger logger = Logger.getLogger(EmpRESTController.class);

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmployeeService emplService;

    @RequestMapping("/rest/emps/{id}")
    public Set<Employee> getEmpsList(@PathVariable("id") int id) {
        logger.debug("Returning emps list JSON object");

        return deptService.getDeptById(id).getEmps();
    }

    @RequestMapping("/rest/emp/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        logger.debug("Returning employee JSON object, id = " + id);

        return emplService.getEmployeeById(id);
    }

    @RequestMapping("/rest/emp/add")
    public Employee addEmp(@RequestBody Employee emp) {
        Dept dept = deptService.findByName( emp.getDept().getName() );
        emp.setDept(dept);

        logger.debug("Adding employee " + emp);

        emplService.addEmloyee(emp);

        return emp;
    }

    @RequestMapping("/rest/emp/delete/{id}")
    public Employee deleteEmp(@PathVariable("id") int id) {
        logger.debug("Deleting employee id = " + id);

        Employee emp = emplService.getEmployeeById(id);

        emplService.deleteEmployee(emp);

        return emp;
    }

    @RequestMapping("/rest/emp/edit/{id}")
    public Employee editEmp(@RequestBody Employee emp, @PathVariable("id") int id) {
        Dept dept = deptService.findByName( emp.getDept().getName() );
        emp.setDept(dept);

        logger.debug("Updating employee " + emp + " id = " + id);
        emplService.updateEmployee(emp);

        return emp;
    }

    //-------------------------------Validation checks----------------------------

    @RequestMapping("/rest/check/emp/name")
    public boolean checkEmpName(@RequestBody Employee emp) {
        logger.debug("Checking whether employee name exists: " + emp);

        Employee dbEmp = emplService.findByName(emp.getName());

        return !( dbEmp != null && emp.getId() != dbEmp.getId() );
    }

    @RequestMapping("/rest/check/emp/email")
    public boolean checkEmpEmail(@RequestBody Employee emp) {
        logger.debug("Checking whether employee email exists: " + emp);

        Employee dbEmp = emplService.findByEmail(emp.getEmail());

        return !( dbEmp != null && emp.getId() != dbEmp.getId() );
    }

}