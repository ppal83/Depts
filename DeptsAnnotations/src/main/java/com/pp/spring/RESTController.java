package com.pp.spring;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@RestController
public class RESTController {

    private static final Logger logger = Logger.getLogger(RESTController.class);

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmployeeService emplService;

    @RequestMapping("/rest/depts")
    public List<Dept> getDeptsList() {
        logger.debug("Returning depts list JSON object");

        return deptService.getAllDepts();
    }

    @RequestMapping("/rest/dept/{id}")
    public Dept getDept(@PathVariable("id") int id) {
        logger.debug("Returning depts list JSON object");

        return deptService.getDeptById(id);
    }

    @RequestMapping("/rest/dept/delete/{id}")
    public Dept deleteDept(@PathVariable("id") int id) {
        logger.debug("Deleting dept id = " + id);

        Dept dept = deptService.getDeptById(id);

        for (Employee e : dept.getEmps()) {
            emplService.deleteEmployee(e);
        }
        deptService.deleteDeptById(id);

        return dept;
    }

    @RequestMapping("/rest/dept/edit/{id}")
    public Dept editDept(@RequestBody Dept dept) {
        logger.debug("Updating dept " + dept);
        deptService.updateDept(dept);

        return dept;
    }

    @RequestMapping("/rest/emps/{id}")
    public Set<Employee> getEmpsList(@PathVariable("id") int id) {
        logger.debug("Returning emps list JSON object");

        return deptService.getDeptById(id).getEmps();
    }

    @RequestMapping("/rest/emp/delete/{id}")
    public Employee deleteEmp(@PathVariable("id") int id) {
        logger.debug("Deleting employee id = " + id);

        Employee emp = emplService.getEmployeeById(id);

        emplService.deleteEmployee(emp);

        return emp;
    }


}