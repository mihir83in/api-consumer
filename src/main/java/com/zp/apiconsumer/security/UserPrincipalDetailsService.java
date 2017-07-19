package com.zp.apiconsumer.security;

import com.zp.apiconsumer.exception.NotFoundException;
import com.zp.apiconsumer.persistence.model.CurrencyUser;
import com.zp.apiconsumer.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * User Details Service that provides user objects for authentication
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public UserPrincipalDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {

        CurrencyUser userDetails = userRepository.findByUsername(username);

        if (userDetails == null) {
            throw new NotFoundException("User not found");
        }

        return new UserPrincipal(userDetails);
    }
}
