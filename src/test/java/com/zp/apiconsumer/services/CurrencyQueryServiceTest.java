package com.zp.apiconsumer.services;

import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.persistence.repository.CurrencyConvertQueryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CurrencyQueryServiceTest {

    @InjectMocks
    private CurrencyQueryService target;

    @Mock
    private CurrencyConvertQueryRepository queryRepository;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void saveQuery() throws Exception {

        CurrencyConvertQuery query = new CurrencyConvertQuery();
        query.setAmount(BigDecimal.valueOf(10L));

        ArgumentCaptor<CurrencyConvertQuery> argumentCaptor = ArgumentCaptor.forClass(CurrencyConvertQuery.class);
        when(queryRepository.save(argumentCaptor.capture())).thenReturn(query);

        target.saveQuery(query);

        CurrencyConvertQuery convertQuery = argumentCaptor.getValue();
        assertEquals(10L, convertQuery.getAmount().longValue());
    }


    @Test
    public void getLast10Queries() throws Exception {

        target.getLast10Queries();

        verify(queryRepository).findFirst10ByOrderByCreatedDesc();
    }

}