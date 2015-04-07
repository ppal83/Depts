package com.pp.spring;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import com.pp.spring.service.EmployeeService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger logger = Logger.getLogger(RestController.class);

    @Autowired
    private DeptService deptService;

    @Autowired
    private EmployeeService emplService;

    @RequestMapping("/rest/emp/dummy")
    public Employee getDummyEmployee() {
        logger.debug("Returning dummy employee JSON object");

        Employee emp = new Employee("Rulon Oboev",
                new GregorianCalendar(1950, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar().getTime(), "Pushkinskaya 40", new Dept("Dummy Dept"), 7777);

        return emp;
    }

    @RequestMapping("/rest/depts")
    public List<Dept> getDeptsList() {
        logger.debug("Returning depts list JSON object");

        return deptService.getAllDepts();
    }

    @RequestMapping("/rest/emps/{id}")
    public Set<Employee> getEmpsList(@PathVariable("id") int id) {
        logger.debug("Returning emps list JSON object");

        return deptService.getDeptById(id).getEmps();
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

}
