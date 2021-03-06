package com.zp.apiconsumer.security;

import com.google.common.collect.Lists;
import com.zp.apiconsumer.persistence.model.CurrencyUser;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;


@Getter
public class UserPrincipal extends User {

    private CurrencyUser currencyUser;


    public UserPrincipal(CurrencyUser currencyUser) {

        super(currencyUser.getUsername(), currencyUser.getPassword(), Lists.newArrayList());
        this.currencyUser = currencyUser;
    }
}
