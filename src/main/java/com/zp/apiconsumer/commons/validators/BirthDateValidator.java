package com.zp.apiconsumer.commons.validators;

import com.zp.apiconsumer.constants.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


/**
 * Validates BirthDate to logical valid bounds
 */
public class BirthDateValidator implements ConstraintValidator<BirthDate, Date> {

    @Override
    public void initialize(BirthDate birthDate) {

    }


    /**
     * Validates birthdate to logical valid value bounds
     *
     * @param date                       date
     * @param constraintValidatorContext constraints validation context
     * @return wheather birthDate is valid or not.
     */
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

        return age > Constants.MIN_HUMAN_AGE && age < Constants.MAX_HUMAN_AGE;
    }
}
