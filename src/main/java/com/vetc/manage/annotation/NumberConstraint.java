package com.vetc.manage.annotation;

import com.vetc.manage.annotation.validator.NumberValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberConstraint {
    String message() default "Invalid date -format: dd-MM-yyyy" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
