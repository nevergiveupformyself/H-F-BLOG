package com.hf.controllers;

import com.hf.dto.Demo;
import com.hf.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjm on 2017/12/22.
 */
@Controller
public class HelloController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/")
    public String helloView(HttpServletRequest request) {
        demoService.selectAll();
        return "hello";
    }

    @RequestMapping("/insert")
    public String insert(){
        Demo demo = new Demo();
        demo.setName("demo");
        demoService.insert(demo);
        return "hello";
    }

    @RequestMapping("/delAll")
    public String delAll(){
        Demo demo = new Demo();
        demo.setId(1L);
        demo.setName("demo");
        List<Demo> list = new ArrayList<>();
        list.add(demo);
        demoService.batchDelete(list);
        return "hello";
    }
}
