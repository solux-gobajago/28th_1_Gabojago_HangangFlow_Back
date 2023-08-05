package com.hangangFlow.hangangFlow.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

// spring security에 대한 설정
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.anyRequest().permitAll()
                );
        return http.build();
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()            // 인증, 인가 필요한 url 지정
//                .antMatchers().authenticated()
//                .antMatchers().hasAuthority(UserRole.ADMIN.name())
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .usermameParameter("loginId")
//                .passwordParameter("password")
//                .loginPage("/security-login/login")
//                .defaultSuccessUrl("/security-login")
//                .failureUrl("/security-login/login")
//                .and()
//                .logout()
//                .logoutUrl("/security-login/logout")
//                .invalidHttpSession(true)
//                .deleteCookies("JSESSIONID");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
