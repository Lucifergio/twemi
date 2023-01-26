package ru.twemi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class TwemiConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //Перенесен сюда из SecurityConfig во избежание циклической зависимости
        return new BCryptPasswordEncoder();
    }
}
