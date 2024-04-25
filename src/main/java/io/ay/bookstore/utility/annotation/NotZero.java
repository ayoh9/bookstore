package io.ay.bookstore.utility.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
public @interface NotZero {

    String message() default "Value must be a positive integer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
