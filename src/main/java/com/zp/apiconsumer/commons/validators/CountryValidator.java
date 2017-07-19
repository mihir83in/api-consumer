package com.zp.apiconsumer.commons.validators;

import com.zp.apiconsumer.commons.model.web.Countries;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validates Country name.
 */
public class CountryValidator implements ConstraintValidator<Country, String> {

    @Override
    public void initialize(Country country) {

    }


    /**
     * Uses Locale countries to validate given country.
     *
     * @param country                    name of country
     * @param constraintValidatorContext constraint validator context
     * @return indication of country being valid.
     */
    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {

        return Countries.getInstance().isValid(country);
    }
}
