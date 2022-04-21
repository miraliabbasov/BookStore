package com.ingressgroup.BookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("v1/publisher").hasAnyRole("ADMIN")
                .antMatchers("v1/user/login").hasAnyRole("USER")
                .and()
                .httpBasic();
    }


    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user = User
                .withUsername("user")
                .password("1234")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .roles("USER").build();

        UserDetails admin = User
                .withUsername("admin")
                .password("1234")
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
                .roles("ADMIN").build();
        InMemoryUserDetailsManager detailsManager =  new InMemoryUserDetailsManager();
        detailsManager.createUser(user);
        detailsManager.createUser(admin);

        return detailsManager;
    }
}
