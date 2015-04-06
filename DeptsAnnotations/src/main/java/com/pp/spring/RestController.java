package com.pp.spring;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class RestController {

    private static final Logger logger = Logger.getLogger(RestController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping("/rest/emp/dummy")
    public @ResponseBody Employee getDummyEmployee() {
        logger.debug("Returning dummy employee JSON object");

        Employee emp = new Employee("Rulon Oboev",
                new GregorianCalendar(1950, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar().getTime(), "Pushkinskaya 40", new Dept("Dummy Dept"), 7777);

        return emp;
    }

    @RequestMapping("/rest/depts")
    public @ResponseBody List<Dept> getDeptsList() {
        logger.debug("Returning depts list JSON object");

        List<Dept> deptsList = deptService.getAllDepts();

        return deptsList;
    }

    @RequestMapping("/rest/dept/delete/{id}")
    public @ResponseBody boolean deleteDept(@PathVariable("id") int id) {
        logger.debug("Deleting dept id = " + id);

        deptService.deleteDeptById(id);

        return true;
    }

}
