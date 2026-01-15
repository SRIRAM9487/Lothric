package com.lothric.backend.security.web;

import com.lothric.backend.security.constant.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/** Security Configuration class. */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  /**
   * Security filter chain that request passes through.
   *
   * @param http HttpSecurity object.
   * @return Modified Security filter chain.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {

    http.cors(AbstractHttpConfigurer::disable);
    http.csrf(AbstractHttpConfigurer::disable);
    http.logout(AbstractHttpConfigurer::disable);
    http.formLogin(AbstractHttpConfigurer::disable);

    http.authorizeHttpRequests(
        req ->
            req.requestMatchers(HttpMethod.GET, Path.auth)
                .permitAll()
                .anyRequest()
                .authenticated());

    return http.build();
  }
}
