package com.zp.apiconsumer.persistence.model;

import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.converter.CurrencyConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString
@Entity
public class CurrencyConvertQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = CurrencyConverter.class)
    private Currency fromCurrency;

    @Column(nullable = false)
    @Convert(converter = CurrencyConverter.class)
    private Currency toCurrency;

    @Column(nullable = false)
    private Date dateEntered;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal result;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(nullable = false)
    private Date created;
}
