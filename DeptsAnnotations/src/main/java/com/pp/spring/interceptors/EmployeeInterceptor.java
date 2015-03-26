package com.pp.spring.interceptors;

import com.pp.spring.model.Employee;
import com.pp.spring.service.DeptService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by ppal on 26.03.15.
 */
public class EmployeeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(EmployeeInterceptor.class);

    @Autowired
    private DeptService deptService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView model) throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        Collection<Employee> empList = deptService.getDeptById(id).getEmps();
        model.addObject("empList", empList);
        model.addObject("deptsList", deptService.getAllDepts());
    }
}