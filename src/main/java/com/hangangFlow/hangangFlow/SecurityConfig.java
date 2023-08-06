package com.hangangFlow.hangangFlow;
import com.hangangFlow.hangangFlow.domain.user.UserRepository;
import com.hangangFlow.hangangFlow.service.UserService;
import com.hangangFlow.hangangFlow.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


import com.hangangFlow.hangangFlow.service.UserService;
//import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;


//@Lazy
@EnableWebSecurity
//@EnableJpaRepositories(basePackages = {"com.hangangFlow.hangangFlow.domain.user", "com.hangangFlow.hangangFlow.domain", "com.hangangFlow.hangangFlow.domain.park"}) // "com.hangangFlow.hangangFlow.domain.park.Parks" 패키지를 추가
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {

            // 인가(접근권한) 설정
//            http.authorizeRequests()
////                    .antMatchers(HttpMethod.POST.valueOf("/api/register")).permitAll()
//                    .antMatchers(HttpMethod.valueOf("/api/**")).authenticated();

            // 사이트 위변조 요청 방지 비활성화
            http.csrf().disable();

            // 세션 관리 설정
            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // 로그인 설정
            http.formLogin()
                    .loginProcessingUrl("/api/login")
                    .defaultSuccessUrl("/api/home")
                    .failureUrl("/api/login?error")
                    .usernameParameter("loginId")
                    .passwordParameter("password");

            // 로그아웃 설정
            http.logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                    .logoutSuccessUrl("/api/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

            return http.build();


    }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
