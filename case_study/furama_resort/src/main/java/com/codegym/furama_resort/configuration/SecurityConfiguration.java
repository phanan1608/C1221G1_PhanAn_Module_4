//package com.codegym.furama_resort.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.activation.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//
//        http.authorizeRequests().antMatchers("/", "/index").permitAll().and()
//                .exceptionHandling().accessDeniedPage("/libraryAppError");
//        http.authorizeRequests().antMatchers("/resources/**","/", "/home", "/js/**", "/css/**").permitAll().anyRequest().permitAll();
//    }
//}
