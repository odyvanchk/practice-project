package com.practice.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShopApplication.class, args);
//        for(String bean:context.getBeanDefinitionNames()) {
//            System.out.println(bean);
//        }
    }
}
