package com.example.oauth2.config;

import com.example.oauth2.domain.Role;
import com.example.oauth2.exception.CustomAccessDeniedHandler;
import com.example.oauth2.service.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/denied").permitAll()
                .antMatchers("/user").hasRole(Role.USER.name())
                .antMatchers("/guest","/redirect").authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/redirect")
                .userInfoEndpoint()
                .userService(customOauth2UserService);
//                .and()
//                .successHandler()
//                .failureHandler();
    }
}
