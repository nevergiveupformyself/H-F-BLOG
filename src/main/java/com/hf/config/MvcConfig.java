package com.hf.config;

import com.hf.interceptors.UserDetailInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * mvc配置类
 * Created by fjm on 2017/12/22.
 */
@Configuration//表名是一个配置文件
@EnableWebMvc//开启mvc注解驱动
@ComponentScan("com.hf.controllers")//扫描控制器
public class MvcConfig extends WebMvcConfigurerAdapter{

//    使用配置文件配置
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver viewResolver = new SpringResourceTemplateResolver();
//        viewResolver.setPrefix("/WEB-INF/classes/views/");
//        viewResolver.setSuffix(".html");
//        viewResolver.setTemplateMode("HTML");
//        return viewResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(){
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver(){
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        return viewResolver;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指定访问静态资源文件的路径为/js/**和/css/**
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/src/**").addResourceLocations("classpath:/static/src/");
        registry.addResourceHandler("/bootstrap-3.3.7/**").addResourceLocations("classpath:/static/bootstrap-3.3.7/");
        registry.addResourceHandler("/font-awesome-4.6.3/**").addResourceLocations("classpath:/static/font-awesome-4.6.3/");

        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userDetailInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public UserDetailInterceptor userDetailInterceptor(){
        return new UserDetailInterceptor();
    }

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        //dispatcherServlet无法加载到请求url的资源时,抛出NoHandlerFound异常
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return registration;
    }


}
