package com.pp.spring;

import com.pp.spring.model.Dept;
import com.pp.spring.model.Employee;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class RestController {

    private static final Logger logger = Logger.getLogger(RestController.class);

    @RequestMapping(value= "/rest/emp/dummy", method = RequestMethod.GET)
    public @ResponseBody Employee getDummyEmployee() {
        logger.debug("Returning dummy employee JSON object");

        Employee emp = new Employee("Rulon Oboev",
                new GregorianCalendar(1950, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar().getTime(), "Pushkinskaya 40", new Dept("Dummy Dept"), 7777);

        return emp;
    }
}
