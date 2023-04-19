package com.practice.shop;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.TimeZone;


@SpringBootApplication
@ConfigurationPropertiesScan
public class ShopApplication {

    public static void main(String[] args) throws RuntimeException {
        SpringApplication.run(ShopApplication.class, args);
//        for(String bean:context.getBeanDefinitionNames()) {
//            System.out.println(bean);
//        }
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}