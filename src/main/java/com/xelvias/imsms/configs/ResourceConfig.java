package com.xelvias.imsms.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class ResourceConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/assets/**")
                    .addResourceLocations("classpath:/static/angular/dist/angular/assets/");
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("classpath:/static/angular/dist/angular/");
            registry.addResourceHandler("/*.js")
                    .addResourceLocations("classpath:/static/angular/dist/angular/");
            registry.addResourceHandler("/*.js.map")
                    .addResourceLocations("classpath:/static/angular/dist/angular/");
            registry.addResourceHandler("/*.json")
                    .addResourceLocations("classpath:/static/angular/dist/angular/");
            registry.addResourceHandler("/*.ico")
                    .addResourceLocations("classpath:/static/angular/dist/angular/");
            registry.addResourceHandler("/index.html")
                    .addResourceLocations("classpath:/static/angular/dist/angular/index.html");



        }

        @Override
        public void addViewControllers(final ViewControllerRegistry registry){
//            registry.addViewController("/processing").setViewName("");
//            registry.addViewController("/adminpanel").setViewName("forward:/static/angular/dist/angular/index.html");
        }

}
