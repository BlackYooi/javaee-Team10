//配置了默认参数 比如登录页地址映射
package com.kindblack.team10.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        //静态资源；  *.css , *.js
        //SpringBoot已经做好了静态资源映射
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/asserts/**", "webjars/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("login").setViewName("login");
        registry.addViewController("sysmain").setViewName("sysmain");
        registry.addViewController("welcome").setViewName("welcome");
        //员工管理的三个管理
        registry.addViewController("emp/list").setViewName("emp/list");
        registry.addViewController("depart/departList").setViewName("depart/departList");
        registry.addViewController("posit/positList").setViewName("posit/positList");
        //简历管理的发布管理和投出管理
        /*registry.addViewController("recruit/list").setViewName("recruit/recruitList");*/
        registry.addViewController("resume/list").setViewName("resume/resumeList");
        //我的模板页面
        registry.addViewController("formwork/list").setViewName("formwork/formworkList");
        //管理员查询招聘投递
        registry.addViewController("oneRecuitsResume").setViewName("recruit/resumeListForSys");
    }

  /*  //国际化有时间再做
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }*/

    /*设置静态资源访问位置*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

}
