package com.hf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fjm on 2017/12/22.
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String helloView(HttpServletRequest request){
        return "hello";
    }
}
