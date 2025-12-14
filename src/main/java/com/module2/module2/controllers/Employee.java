package com.module2.module2.controllers;

import com.module2.module2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class Employee {
    @GetMapping("/employees/{employeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeId){
        return new EmployeeDTO(employeId,"Akhila","akhila@gamil.com",24, LocalDate.of(2025,1,2),true);
    }

    @GetMapping("/employees")
    public String getAllEmployees(@RequestParam(required=false) Integer age, @RequestParam(required=false) String sortBy){
        return "Hi age is ..."+age + " " +sortBy;

    }

}
