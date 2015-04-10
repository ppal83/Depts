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
        logger.debug("Returning dept JSON object, id = " + id);

        return deptService.getDeptById(id);
    }

    @RequestMapping("/rest/dept/add")
    public Dept addDept(@RequestBody Dept dept) {
        logger.debug("Adding dept " + dept);
        deptService.addDept(dept);

        return dept;
    }

    @RequestMapping("/rest/dept/delete/{id}")
    public Dept deleteDept(@PathVariable("id") int id) {
        logger.debug("Deleting dept, id = " + id);

        Dept dept = deptService.getDeptById(id);

        for (Employee e : dept.getEmps()) {
            emplService.deleteEmployee(e);
        }
        deptService.deleteDeptById(id);

        return dept;
    }

    @RequestMapping("/rest/dept/edit/{id}")
    public Dept editDept(@RequestBody Dept dept, @PathVariable("id") int id) {
        logger.debug("Updating dept " + dept + ", id = " + id);
        deptService.updateDept(dept);

        return dept;
    }

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
        logger.debug("Request body emp " + emp);
        logger.debug("emp.getDept().getname() " + emp.getDept().getName());
        logger.debug("deptService.findByName() " + deptService.findByName("Sales") );
        Dept dept = deptService.findByName( emp.getDept().getName() );

        logger.debug("dept by name " + dept);
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
        logger.debug("Updating employee " + emp + " id = " + id);
        emplService.updateEmployee(emp);

        return emp;
    }

    //-------------------------------Validation checks----------------------------

    @RequestMapping("/rest/check/dept/name")
    public boolean checkDeptName(@RequestBody Dept dept) {
        logger.debug("Checking whether dept name exists: " + dept);

        Dept dbDept = deptService.findByName(dept.getName());

        return !( dbDept != null && dept.getId() != dbDept.getId() );
    }

    @RequestMapping("/rest/check/dept/name-exists")
    public boolean checkDeptNameExists(@RequestParam String dept) {
        logger.debug("Checking whether dept name exists: " + dept);

        return deptService.findByName(dept) != null;
    }

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