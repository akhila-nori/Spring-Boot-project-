package com.module2.module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.module2.module2.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//this is POJO Class
//they are used to define entities inside our class
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotEmpty(message = "Name of the employee cannot be empty")
    private String name;
    private String email;

    @Max(value=80)
    @Min(value=10)
    private Integer age;
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

    @NotBlank(message="Role of the employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$" , message="Role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

    //above one are POJO

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public EmployeeDTO(){
//
//    }
//
//    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
}
