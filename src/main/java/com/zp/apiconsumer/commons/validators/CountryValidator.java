package com.zp.apiconsumer.commons.validators;

import com.zp.apiconsumer.commons.model.web.Countries;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CountryValidator implements ConstraintValidator<Country, String> {

    @Override
    public void initialize(Country country) {

    }


    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {

        return Countries.getInstance().isValid(country);
    }
}
