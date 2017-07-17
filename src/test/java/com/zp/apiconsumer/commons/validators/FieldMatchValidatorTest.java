package com.zp.apiconsumer.commons.validators;

import com.zp.apiconsumer.commons.model.web.CurrencyUserRegistrationForm;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class FieldMatchValidatorTest {

    @InjectMocks
    private FieldMatchValidator target;

    @Mock
    private FieldMatch fieldMatch;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void initialize() throws Exception {

        String first = "foo";
        String second = "bar";

        when(fieldMatch.first()).thenReturn(first);
        when(fieldMatch.second()).thenReturn(second);

        target.initialize(fieldMatch);

        assertEquals(first, target.getFirstFieldName());
        assertEquals(second, target.getSecondFieldName());
    }


    @Test
    public void isValid() throws Exception {

        String first = "password";
        String second = "confirmPassword";

        when(fieldMatch.first()).thenReturn(first);
        when(fieldMatch.second()).thenReturn(second);

        CurrencyUserRegistrationForm form = new CurrencyUserRegistrationForm();
        form.setPassword(first);
        form.setUsername(first);

        target.isValid(form, null);
    }


    @Test
    public void isValidNot() throws Exception {

        String first = "password";
        String second = "confirmPassword";

        when(fieldMatch.first()).thenReturn(first);
        when(fieldMatch.second()).thenReturn(second);

        CurrencyUserRegistrationForm form = new CurrencyUserRegistrationForm();
        form.setPassword(first);
        form.setUsername(second);

        target.isValid(form, null);
    }

}