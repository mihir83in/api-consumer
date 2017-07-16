package com.zp.apiconsumer.persistence.model;

import com.zp.apiconsumer.commons.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Enumerated
    private Currency fromCurrency;

    @Enumerated
    @Column(nullable = false)
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
