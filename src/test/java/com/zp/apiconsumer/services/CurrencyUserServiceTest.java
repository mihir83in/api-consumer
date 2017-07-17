package com.zp.apiconsumer.services;

import com.zp.apiconsumer.persistence.model.CurrencyUser;
import com.zp.apiconsumer.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;


public class CurrencyUserServiceTest {

    @InjectMocks
    private CurrencyUserService target;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addUser() throws Exception {

        String password = "foobar";

        CurrencyUser currencyUser = new CurrencyUser();
        currencyUser.setPassword(password);

        target.addUser(currencyUser);

        verify(passwordEncoder).encode(password);
        verify(userRepository).save(currencyUser);
    }

}