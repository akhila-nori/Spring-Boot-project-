package com.module2.module2.controllers;

import com.module2.module2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Employee {
    @GetMapping("/employees/{employeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeId){
        return new EmployeeDTO(employeId,"Akhila","akhila@gamil.com",24, LocalDate.of(2025,1,2),true);

    }

}
