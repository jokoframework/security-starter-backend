package io.github.jokoframework.securitystarterbackend.config;

import io.github.jokoframework.securitystarterbackend.constants.ApiPaths;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Order(50)
public class BackendSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(ApiPaths.STATUS, ApiPaths.SIGNUP, io.github.jokoframework.security.ApiPaths.LOGIN).permitAll()
                .antMatchers(ApiPaths.USER + ApiPaths.CREATE_USER).authenticated()
                .antMatchers(ApiPaths.USER + ApiPaths.UPDATE_USER).authenticated()
                .antMatchers(ApiPaths.USER + ApiPaths.DELETE_USER).authenticated()
                .antMatchers(ApiPaths.USER + ApiPaths.LIST_USERS).authenticated()
                .antMatchers(ApiPaths.USER + ApiPaths.GET_USER).authenticated()
                .and()
                .csrf().disable();
    }
}