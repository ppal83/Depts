package com.pp.spring.interceptors;

import com.pp.spring.model.Dept;
import com.pp.spring.service.DeptService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pyjama on 25.03.15.
 */

public class DeptInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(DeptInterceptor.class);

    @Autowired
    private DeptService deptService;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView model) throws Exception {
        model.addObject("dept", new Dept());
        model.addObject("deptsList", deptService.getAllDepts());
        logger.info("Interceptor objects were added");
    }
}
