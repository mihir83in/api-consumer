package com.zp.apiconsumer.commons.model.web;

import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
public class CurrencyConversionForm {

    @NotNull(message = "From cant be null")
    private Currency from;

    @NotNull(message = "To cant be null")
    private Currency to;

    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    private Date date = new Date();

    @NotNull(message = "Amount cant be null")
    @Min(value = 1, message = "Amount must be greater than 1")
    @NumberFormat(pattern = "#0.00")
    private BigDecimal amount;
}
