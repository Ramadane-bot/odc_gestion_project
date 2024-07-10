package com.odcspring.web_app.config;

import com.odcspring.web_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserService userService;
    private ODCAuthentication authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                        re -> re.requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(form -> {
                    form.loginPage("/login");
                    form.successForwardUrl("/tasks");
                    form.loginProcessingUrl("/login");
                })
                .authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Lazy
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Lazy
    @Autowired
    public void setAuthenticationManager(ODCAuthentication authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
