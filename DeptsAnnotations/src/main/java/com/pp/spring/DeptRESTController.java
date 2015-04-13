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
public class DeptRESTController {

    private static final Logger logger = Logger.getLogger(DeptRESTController.class);

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

    //-------------------------------Validation checks----------------------------

    @RequestMapping("/rest/check/dept/name")
    public boolean checkDeptName(@RequestBody Dept dept) {
        logger.debug("Checking whether dept name exists: " + dept);

        Dept dbDept = deptService.findByName(dept.getName());

        return !( dbDept != null && dept.getId() != dbDept.getId() );
    }

    @RequestMapping("/rest/check/dept/name-exists")
    public boolean checkDeptNameExists(@RequestBody Dept dept) {
        logger.debug("Checking whether dept name exists: " + dept);

        return deptService.findByName( dept.getName() ) != null;
    }

}