package com.zp.apiconsumer.commons.model.web;

import com.zp.apiconsumer.commons.validators.BirthDate;
import com.zp.apiconsumer.commons.validators.Country;
import com.zp.apiconsumer.commons.validators.FieldMatch;
import com.zp.apiconsumer.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@ToString
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
public class CurrencyUserRegistrationForm {

    @NotBlank(message = "Username cant be empty")
    @Pattern(regexp = Constants.ALPHA_NUM_PATTERN, message = "username can contain only A-Za-z0-9")
    @Size(min = 4, max = 20, message = "username should be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Password cant be empty")
    @Pattern(regexp = Constants.ALPHA_NUM_PATTERN, message = "password can contain only A-Za-z0-9")
    @Size(min = 4, max = 20, message = "password should be between 4 and 20 characters")
    private String password;

    @NotBlank(message = "Confirm password cant be empty")
    @Pattern(regexp = Constants.ALPHA_NUM_PATTERN, message = "confirmPassword can contain only A-Za-z0-9")
    @Size(min = 4, max = 20, message = "confirmPassword should be between 4 and 20 characters")
    private String confirmPassword;

    @NotBlank(message = "First Name cant be empty")
    @Size(max = 20, message = "firstName should not be more than 20 characters long")
    private String firstName;

    @NotBlank(message = "Last Name cant be empty")
    @Size(max = 20, message = "lastName should not be more than 20 characters long")
    private String lastName;

    @NotNull(message = "Birth Date cant be empty")
    @BirthDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Email(message = "Email is in invalid format")
    private String email;

    private String address;
    private String zipCode;
    private String city;

    @Country
    private String country;
}