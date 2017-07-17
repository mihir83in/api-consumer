package com.zp.apiconsumer.services;

import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.persistence.repository.CurrencyConvertQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyQueryService {

    private CurrencyConvertQueryRepository queryRepository;


    public CurrencyQueryService(CurrencyConvertQueryRepository currencyConvertQueryRepository) {

        this.queryRepository = currencyConvertQueryRepository;
    }


    public void saveQuery(CurrencyConvertQuery query) {

        queryRepository.save(query);
    }


    public List<CurrencyConvertQuery> getLast10Queries() {

        return queryRepository.findFirst10ByOrderByCreatedDesc();
    }
}
