package com.dataart.apanch.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login**", "/", "/category-*", "/app-*", "/download/**").access("hasRole('USER') or hasRole('DEVELOPER')")
                .and().authorizeRequests().antMatchers("/new").access("hasRole('DEVELOPER')")
                .and().formLogin().loginProcessingUrl("/loginAction").permitAll()
                .and().logout().logoutSuccessUrl("/login").permitAll()
                .and().csrf().disable().exceptionHandling().accessDeniedPage("/access_denied");
        http.headers().contentTypeOptions().disable();
        http.headers().frameOptions().disable();
    }
}
