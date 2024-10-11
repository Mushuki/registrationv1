package com.api.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {
    //Because ModelMapper is an external librey so we can't just implement dependency injection
    // we can not use spring ioc , it won't be able to understand which object to create so we need to use
    //config class in configuration package by using @Configuration
    @Bean
    public  ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    //we need to delete later on
    // Define a method where the return type is the same the object you are creating ;
    // annotate the object with bean @Bean
    // object = new ModelMapper();
    // return type = ModelMapper
    // it is kind of ModelMapper modelMapper = new ModelMapper();
    //since we can not write it in service layer and spring ioc can not create object because external librery
    //so we use configuration
    // we can not use @Bean in any another class other than configuration class
}
