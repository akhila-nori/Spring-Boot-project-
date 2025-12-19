package com.module2.module2.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})   //it will define element type on field only
@Constraint(validatedBy = {EmployeeRoleValidator.class}) // <--- THIS IS THE CONNECTION
//he @Constraint annotation has a requirement: any class listed in validatedBy must implement the ConstraintValidator interface. If it doesn't, your code won't even compile.


public @interface EmployeeRoleValidation {

    String message() default "Role of employee can be USER or ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


