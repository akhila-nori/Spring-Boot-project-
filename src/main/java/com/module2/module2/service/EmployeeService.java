package com.module2.module2.service;

import com.module2.module2.dto.EmployeeDTO;
import com.module2.module2.entities.EmployeeEntity;
import com.module2.module2.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//when you annotate it with @Service - so that bean of this class is created --> BEAN IS CREATED
@Service
@RequiredArgsConstructor
public class EmployeeService {

    //inject bean Repository inside my Service with the help of coonstructor
    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;


    public Optional<EmployeeDTO> getEmployeeById(Long employeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeId);


        //how to convery entity returned by repository from database to Employee DTO in postman
        //one way to do that is to return new Employee DTO and inside the new Employee DTO -->> take all things from EmployeeEntity and map it to Employee DTO
       //METHOD - 2
        //there are multiple libraries that handle this mapping - and we are going to use one such library which is called Model Mapper
       //ModelMapper - we can use model mapper to convert any kind of class ie., Entity class to Java Object (POJO)
        //How this works ?
        //--> it will actually look for all the fields that are common in both of them and it will create an Object of EmployeeDTO using the fields from Entity

//        ModelMapper mapper = new ModelMapper();
//        return modelMapper.map(employeeEntity, EmployeeDTO.class); // what this does is , it returns an EmployeeDTO Object
       //src, destination type --> it will be of type class


        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class ));
    }

    public List<EmployeeDTO> getAllEmployeesService(Integer age, String sortBy) {

        //Spring way of doing things, not to create 'new' object ... rather to create a Bean
        //How can we create a Bean of ModelMapper here ? - check MapperConfig there we have created Bean of ModelMpper
//        ModelMapper mapper = new ModelMapper();


        List<EmployeeEntity> employeeEntities  = employeeRepository.findAll();

        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployeeService(EmployeeDTO inputEmployee) {

        //it will convert DTO to Entity
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);

        //save method accepts only Entity type and not DTO type
        EmployeeEntity employeeEntity =  employeeRepository.save(toSaveEntity);
        //it will return an entity

        //use modelMpaper to convert returned entity from database to DTO
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
//        EmployeeEntity employeeEntity = modelMapper.map(updates,EmployeeEntity.class);
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) return null;
       EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

     //Method 1 : Preferred
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
       return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

//       Method - 2 - for-each old style Map
//
//        for(Map.Entry<String,Object> e : updates.entrySet()){
//            String fieldName = e.getKey();
//            Object value = e.getValue();
//
//            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,fieldName);
//            fieldToBeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
//        }
//        return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
