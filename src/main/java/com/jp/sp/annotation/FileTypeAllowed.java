package com.jp.sp.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileTypeAllowedValidator.class})
public @interface FileTypeAllowed {
    String message() default "Allowed files are : ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] values();
}
