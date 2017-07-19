package com.zp.apiconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Configuration of spring security
 */
@Configuration
@EnableWebSecurity
public class WebMvcConfiguration {

    /**
     * Web app configuration
     */
    @Configuration
    public static class CurrencyConverter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/login", "/register", "/error").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }
    }


    /**
     * Management context path configuration
     */
    @Configuration
    @Order(1)
    public static class ManagementSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable()
                    .antMatcher("/management/**").authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();
        }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.inMemoryAuthentication().passwordEncoder(new PlaintextPasswordEncoder())
                    .withUser("admin").password("currencyGuy").roles("ACTUATOR");
        }
    }


    /**
     * Plain password storing in db is no-no, we use a Bcrypt password encoder
     *
     * @return Password Encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
