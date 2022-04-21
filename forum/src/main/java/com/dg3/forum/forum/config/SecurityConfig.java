//package com.dg3.forum.forum.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.dg3.forum.forum.rest.CustomAccessDeniedHandler;
//import com.dg3.forum.forum.rest.JwtAuthenticationTokenFilter;
//import com.dg3.forum.forum.rest.RestAuthenticationEntryPoint;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
//        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
//        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
//        return jwtAuthenticationTokenFilter;
//    }
//
//    @Bean
//    public RestAuthenticationEntryPoint restServicesEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }
//
//    @Bean
//    public CustomAccessDeniedHandler customAccessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        // Disable crsf cho đường dẫn /rest/**
//        http.csrf().ignoringAntMatchers("/api/v1/admin/**").disable();
//
//        http.authorizeRequests().antMatchers("/api/v1/login**").permitAll();
////
////        http.antMatcher("/api/v1/admin/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
////                .antMatchers(HttpMethod.GET, "/api/v1/admin/**").access("hasRole('ROLE_ADMIN') ")
////                .antMatchers(HttpMethod.POST, "/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
////                .antMatchers(HttpMethod.DELETE, "/api/v1/admin/**").access("hasRole('ROLE_ADMIN')").and()
////                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
////                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
//    }
//}
