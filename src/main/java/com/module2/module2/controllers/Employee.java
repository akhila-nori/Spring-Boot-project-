package com.module2.module2.controllers;

import com.module2.module2.dto.EmployeeDTO;
import com.module2.module2.entities.EmployeeEntity;
import com.module2.module2.repository.EmployeeRepository;
import com.module2.module2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeId){
        //return new EmployeeDTO(employeId,"Akhila","akhila@gamil.com",24, LocalDate.of(2025,1,2),true);
//        return employeeRepository.findById(employeId).orElse(null);

        //you need to unwrap Optional returned by your Service
        // your controller expects a direct EmployeeDTO object, but your service is giving it an Optional container that contains the DTO.
        return employeeService.getEmployeeById(employeId)
                .map(d -> ResponseEntity.ok(d))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required=false) Integer age, @RequestParam(required=false) String sortBy){
//        return "Hi age is ..."+age + " " +sortBy;
        return employeeService.getAllEmployeesService(age,sortBy).map(r -> ResponseEntity.ok(r)).orElse(ResponseEntity.notFound().build());



    }

//@Valid goes recursively inside DTO objects and checks validation annotations applied on fields -
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){  //validating @NotNull applied on employeeDTO
//        inputEmployee.setId(10L);
       EmployeeDTO savedEmployee = employeeService.createNewEmployeeService(inputEmployee);
//       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updatePartialEmployeeById(employeeId,updates));
    }


}
