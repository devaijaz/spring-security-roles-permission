package com.todo.configurations;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserDetailsService appUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/**")
        .hasAnyAuthority("ROLE_ADMIN", "delete:permission").antMatchers(HttpMethod.POST, "/api/v1/**")
        .hasAnyAuthority("ROLE_ADMIN", "create:permission").antMatchers(HttpMethod.PUT, "/api/v1/**")
        .hasAnyAuthority("ROLE_ADMIN", "update:permission").antMatchers(HttpMethod.GET, "/api/v1/**")
        .hasAnyAuthority("ROLE_ADMIN", "read:permission").anyRequest().authenticated().and().httpBasic();

    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());

  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(appUserDetailsService);
    return provider;
  }

  @Bean
  AccessDeniedHandler accessDeniedHandler() {
    return (request, response, ex) -> {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      ServletOutputStream out = response.getOutputStream();
      new ObjectMapper().writeValue(out, new com.todo.handlers.AccessDeniedHandler());
      out.flush();
    };
  }

}
