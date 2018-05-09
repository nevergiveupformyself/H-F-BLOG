package com.hf.interceptors;

import com.hf.dto.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by fjm on 2018/1/5.
 */
public class UserDetailInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(false);
        //按照海林的要求,此处往session放入user
        if (session == null){
            session = httpServletRequest.getSession();
            session.setAttribute("user",new User());
        }else {
            Object user = session.getAttribute("user");
            if (user == null){
                session.setAttribute("user",new User());
            }
        }
        return true;
    }

}
