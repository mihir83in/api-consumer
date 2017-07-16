package com.zp.apiconsumer.commons.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Country {
    String message() default "Invalid Country";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
