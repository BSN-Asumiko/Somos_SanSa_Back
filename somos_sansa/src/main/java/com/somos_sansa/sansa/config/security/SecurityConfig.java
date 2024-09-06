package com.somos_sansa.sansa.config.security;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
@Configuration
@EnableMethodSecurity
class SecurityConfig {
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter){
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .cors(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                    .requestMatchers(HttpMethod.POST, SIGNIN_URL).permitAll()
                    .requestMatchers(HttpMethod.GET, GET_ALL_BRANCHES_URL).permitAll()
                    .requestMatchers(HttpMethod.GET, GET_TOPICS_BY_BRANCH_URL).permitAll()
                    .requestMatchers(HttpMethod.POST, ADD_NEW_TOPIC_URL).authenticated()
                    .requestMatchers(HttpMethod.GET, GET_COMMENTS_BY_TOPIC_URL).permitAll()
                    .requestMatchers(HttpMethod.GET, GET_COMMENT_BY_ID_URL).authenticated()
                    .requestMatchers(HttpMethod.POST, ADD_NEW_COMMENT_URL).authenticated()
                    .requestMatchers(HttpMethod.PUT, UPDATE_COMMENT_URL).authenticated()
                    .requestMatchers(HttpMethod.DELETE, DELETE_COMMENT_URL).authenticated()
            ).addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}