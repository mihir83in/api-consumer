package com.zp.apiconsumer.commons.model.web;

import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
public class CurrencyQuery {

    private Currency from;
    private Currency to;

    @DateTimeFormat(pattern = Constants.DATE_PATTERN)
    private Date date;

    @NumberFormat(pattern = "#0.00")
    private BigDecimal amount;

    @NumberFormat(pattern = "#0.00")
    private BigDecimal result;

    @NumberFormat(pattern = "#0.00")
    private BigDecimal rate;
}
