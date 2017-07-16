package com.zp.apiconsumer.persistence.repository;

import com.zp.apiconsumer.persistence.model.CurrencyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CurrencyUser, Long> {
    CurrencyUser findByUsername(String username);
}
