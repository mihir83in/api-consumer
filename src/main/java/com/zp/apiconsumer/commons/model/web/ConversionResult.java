package com.zp.apiconsumer.commons.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConversionResult {

    @NumberFormat(pattern = "#0.00")
    private BigDecimal value;
    @NumberFormat(pattern = "#0.00")
    private BigDecimal rate;
}
