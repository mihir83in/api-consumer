package com.zp.apiconsumer.services;

import com.zp.apiconsumer.persistence.model.CurrencyUser;
import com.zp.apiconsumer.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CurrencyUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(CurrencyUser currencyUser) {
        currencyUser.setPassword(passwordEncoder.encode(currencyUser.getPassword()));
        userRepository.save(currencyUser);
    }

    public void deleteUser(CurrencyUser currencyUser) {
        userRepository.delete(currencyUser);
    }

    public void update(CurrencyUser currencyUser) {
        userRepository.save(currencyUser);
    }

    public CurrencyUser getUser(long userId) {
        return userRepository.findOne(userId);
    }

    public CurrencyUser getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
