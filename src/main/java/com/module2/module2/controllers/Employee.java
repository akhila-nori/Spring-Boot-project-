package com.module2.module2.controllers;

import com.module2.module2.dto.EmployeeDTO;
import com.module2.module2.entities.EmployeeEntity;
import com.module2.module2.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;




@RestController
public class Employee {

    //injecting the Bean - object of EmployeeRepository inside our controller - inside this class I can use employeeRepository where ever I want
    private final EmployeeRepository employeeRepository;

    // Bean is injected inside the argument of the constructor of this class Employee
    //bean is injected by the argument of the constructor
    //this is where the dependency injection is taking place
    public Employee(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/{employeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeId){
        //return new EmployeeDTO(employeId,"Akhila","akhila@gamil.com",24, LocalDate.of(2025,1,2),true);
        return employeeRepository.findById(employeId).orElse(null);
    }

    @GetMapping("/employees")
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required=false) Integer age, @RequestParam(required=false) String sortBy){
//        return "Hi age is ..."+age + " " +sortBy;
        return employeeRepository.findAll();

    }

    @PostMapping("/employees")
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        inputEmployee.setId(10L);
        return employeeRepository.save(inputEmployee);
    }

}
