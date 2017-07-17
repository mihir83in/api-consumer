package com.zp.apiconsumer.security;

import com.zp.apiconsumer.exception.NotFoundException;
import com.zp.apiconsumer.persistence.model.CurrencyUser;
import com.zp.apiconsumer.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserPrincipalDetailsServiceTest {

    @InjectMocks
    private UserPrincipalDetailsService target;

    @Mock
    private UserRepository userRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void loadUserByUsername() throws Exception {

        String username = "foobar";
        String password = "foobar";

        CurrencyUser currencyUser = new CurrencyUser();
        currencyUser.setUsername(username);
        currencyUser.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(currencyUser);
        UserDetails userDetails = target.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(userRepository).findByUsername(username);
    }


    @Test
    public void loadUserByUsernameNotFound() throws Exception {

        String username = "foobar";

        when(userRepository.findByUsername(username)).thenReturn(null);
        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("User not found");

        target.loadUserByUsername(username);
    }

}