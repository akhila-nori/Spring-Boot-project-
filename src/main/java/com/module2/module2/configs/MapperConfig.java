package com.module2.module2.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //it will return an object of ModelMapper
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
