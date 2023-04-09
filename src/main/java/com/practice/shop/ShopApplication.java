package com.practice.shop;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.TimeZone;


@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) throws RuntimeException {
        SpringApplication.run(ShopApplication.class, args);
//        for(String bean:context.getBeanDefinitionNames()) {
//            System.out.println(bean);
//        }
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}