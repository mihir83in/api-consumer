package com.zp.apiconsumer.persistence.repository;

import com.zp.apiconsumer.persistence.model.CurrencyConvertQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CurrencyConvertQueryRepository extends JpaRepository<CurrencyConvertQuery, Long> {

    List<CurrencyConvertQuery> findFirst10ByOrderByCreatedDesc();
}
