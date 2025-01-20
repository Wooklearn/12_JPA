package com.ohgiraffers.jpaTest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.management.modelmbean.ModelMBean;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.jpaTest")
public class config {

    @Bean
    public ModelMapper modelmapper(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

        return modelMapper;
    }


}
