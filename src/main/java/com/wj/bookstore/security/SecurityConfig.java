package com.wj.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailService(){
        UserDetails user1 = User.builder()
                .username("Joe Snow")
                .password(encoder().encode("joejoe123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("Jan Kowalski")
                .password(encoder().encode("jank123"))
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(user1, user2);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http
                .csrf().disable()
                .cors().and()
                .authorizeExchange((exchange) -> exchange
                        .pathMatchers("/order/**").hasRole("USER")
                        .pathMatchers("/cart/**").hasRole("USER")
                        .anyExchange().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
