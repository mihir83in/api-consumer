package com.zp.apiconsumer.commons.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


public class BirthDateValidator implements ConstraintValidator<BirthDate, Date> {

    @Override
    public void initialize(BirthDate birthDate) {

    }


    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        if (!Optional.ofNullable(date).isPresent()) {
            return false;
        }

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int birthYear = instance.get(Calendar.YEAR);

        int age = year - birthYear;

        return age > 1 && age < 125;
    }
}
