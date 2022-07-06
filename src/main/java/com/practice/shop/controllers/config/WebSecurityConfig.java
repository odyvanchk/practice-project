package com.practice.shop.controllers.config;


import com.practice.shop.controllers.security.ExceptionHandlerFilter;
import com.practice.shop.controllers.security.jwt.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                httpBasic().disable().
                sessionManagement().sessionCreationPolicy(STATELESS).
                and().
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
                addFilterBefore(exceptionHandlerFilter, JwtFilter.class);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "/**"));
        source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
        return source;
    }

}

