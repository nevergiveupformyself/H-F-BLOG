package com.hf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by fjm on 2017/12/22.
 */
@Controller
public class HtmlController {

    protected static final String DEFAULT_VIEW_HOME = "";

    @RequestMapping(value = {"/{folder1}/{name}.html"})
    public ModelAndView renderFolder1View(@PathVariable String folder1, @PathVariable String name, Model model) {
        return new ModelAndView(
                new StringBuilder(getViewPath()).append("/").append(folder1).append("/").append(name).toString());
    }

    @RequestMapping(value = {"/{folder1}/{folder2}/{name}.html"})
    public ModelAndView renderFolder2View(@PathVariable String folder1, @PathVariable String folder2,
                                          @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder(getViewPath()).append("/").append(folder1).append("/").append(folder2)
                .append("/").append(name).toString());
    }

    @RequestMapping(value = {"/{folder1}/{folder2}/{folder3}/{name}.html"})
    public ModelAndView renderFolder3View(@PathVariable String folder1, @PathVariable String folder2,
                                          @PathVariable String folder3, @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder(getViewPath()).append("/").append(folder1).append("/").append(folder2)
                .append("/").append(folder3).append("/").append(name).toString());
    }

    @RequestMapping(value = {"/{folder1}/{folder2}/{folder3}/{folder4}/{name}.html"})
    public ModelAndView renderFolder4View(@PathVariable String folder1, @PathVariable String folder2,
                                          @PathVariable String folder3, @PathVariable String folder4, @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder(getViewPath()).append("/").append(folder1).append("/").append(folder2)
                .append("/").append(folder3).append("/").append(folder4).append("/").append(name).toString());
    }

    @RequestMapping(value = { "/{name}.html" })
    public ModelAndView renderView(@PathVariable String name, Model model) {
        return new ModelAndView(name);
    }

    private String getViewPath() {
        return DEFAULT_VIEW_HOME;
    }
}
