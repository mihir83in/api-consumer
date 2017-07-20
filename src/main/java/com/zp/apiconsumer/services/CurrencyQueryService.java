package com.zp.apiconsumer.services;

import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import com.zp.apiconsumer.persistence.repository.CurrencyConvertQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service for Currency Query entity.
 */
@Service
@Slf4j
public class CurrencyQueryService {

    private CurrencyConvertQueryRepository queryRepository;


    public CurrencyQueryService(CurrencyConvertQueryRepository currencyConvertQueryRepository) {

        this.queryRepository = currencyConvertQueryRepository;
    }


    /**
     * Saves a {@link CurrencyConvertQuery}
     *
     * @param query Query to save
     */
    public void saveQuery(CurrencyConvertQuery query) {

        log.debug("saving query:{}", query);
        queryRepository.save(query);
    }


    /**
     * Returns last 10 currency  queries from db.
     *
     * @return List of currency queries.
     */
    public List<CurrencyConvertQuery> getLast10Queries() {

        List<CurrencyConvertQuery> queries = queryRepository.findFirst10ByOrderByCreatedDesc();
        log.debug("retrieving last 10 queries:{}", queries);

        return queries;
    }
}
