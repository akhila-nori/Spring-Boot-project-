package com.module2.module2.controllers;

import com.module2.module2.dto.EmployeeDTO;
import com.module2.module2.entities.EmployeeEntity;
import com.module2.module2.repository.EmployeeRepository;
import com.module2.module2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
public class Employee {

//    //injecting the Bean - object of EmployeeRepository inside our controller - inside this class I can use employeeRepository where ever I want
    //not a good practice to inject directly repository bean in controller
//    private final EmployeeRepository employeeRepository;

    // Bean is injected inside the argument of the constructor of this class Employee
    //bean is injected by the argument of the constructor
    //this is where the dependency injection is taking place
//    public Employee(EmployeeRepository employeeRepository){
//        this.employeeRepository = employeeRepository;
//    }

    //This is not a Spring bean, declaration of a dependency that requires injection.
    //The EmployeeService class itself must be annotated with one of these (@Service) for Spring to create an instance of it (the bean).
   // So, you are not making the field a bean; you are making the field hold a reference to an already existing bean.
    private final EmployeeService employeeService;

    //Bean injection
    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{employeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeId){
        //return new EmployeeDTO(employeId,"Akhila","akhila@gamil.com",24, LocalDate.of(2025,1,2),true);
//        return employeeRepository.findById(employeId).orElse(null);
        return employeeService.getEmployeeById(employeId);
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required=false) Integer age, @RequestParam(required=false) String sortBy){
//        return "Hi age is ..."+age + " " +sortBy;
        return employeeService.getAllEmployeesService(age,sortBy);

    }

    @PostMapping("/employees")
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(10L);
       return employeeService.createNewEmployeeService(inputEmployee);
    }

    @PatchMapping("/employees/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        return employeeService.updatePartialEmployeeById(employeeId,updates);
    }


}
