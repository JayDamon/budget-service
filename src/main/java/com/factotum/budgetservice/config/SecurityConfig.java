package com.factotum.budgetservice.config;

import com.factotum.budgetservice.component.Oauth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    @Profile({"!test & !local"})
    public static class StandardSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/actuator/**")
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .oauth2ResourceServer().jwt();
        }
    }

    @Configuration
    @Profile({"local"})
    public static class LocalSecurityConfig extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/actuator/**", "/h2-console/*")
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .headers().frameOptions().disable()
                    .and()
                    .oauth2ResourceServer().jwt();
        }

    }

    @Configuration
    @Profile({"test"})
    public static class TestSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private Oauth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(oauth2AuthenticationEntryPoint)
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
            authenticationManagerBuilder.inMemoryAuthentication();
        }

        @Bean
        @Primary
        public UserDetailsService userDetailsService() {

            return new InMemoryUserDetailsManager();
        }

    }

}
