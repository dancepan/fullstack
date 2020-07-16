package com.tips.front;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/member/new").permitAll()  // member 요청은 권한없이 접근 가능
                .antMatchers("/admin").hasRole("ADMIN")  // admin 요청은 ADMIN 권한만 가능
                .anyRequest().authenticated()                        // 그외 다른요총은 인증된 사용자만 접근 가능
                .and()
            .formLogin()
                .permitAll()                                         // 권한없이 접근 가능
                .defaultSuccessUrl("/main")                          // 성공하면 이쪽으로
                .and()
            .logout();
    }

    // 콘솔창의 Using generated security password 다음에 나오는 패스워드로 로그인 가능
    // 유저는 User
}
