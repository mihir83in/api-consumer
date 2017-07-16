package com.zp.apiconsumer.commons.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDate {
    String message() default "Invalid Birth Date";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
