package com.zp.apiconsumer.commons.validators;

import lombok.Getter;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * Validates pair of fields for equality
 */
@Getter
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;


    @Override
    public void initialize(final FieldMatch constraintAnnotation) {

        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }


    /**
     * Validates two fields for equality
     *
     * @param object                     object having two fields
     * @param constraintValidatorContext constraint validator context
     * @return true if both fields match
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        try {
            final Object firstObj = BeanUtils.getProperty(object, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(object, secondFieldName);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore) {
            return false;
        }
    }
}
