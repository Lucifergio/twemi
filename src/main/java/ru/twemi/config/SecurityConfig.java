package ru.twemi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.twemi.security.jwt.JwtConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String USER_ENDPOINT = "/users"; //TODO Разрулить эндпоинты
    public static final String REGISTRATION_ENDPOINT = "/auth/register";
    public static final String MESSAGE_ENDPOINT = "/message";

    private final JwtConfigurer jwtConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests(
                (requests) -> {
                    requests.antMatchers(LOGIN_ENDPOINT).permitAll();
                    requests.antMatchers(REGISTRATION_ENDPOINT).permitAll();
                    requests.antMatchers(USER_ENDPOINT).permitAll();
                    requests.antMatchers(MESSAGE_ENDPOINT).permitAll();
                    requests.anyRequest().authenticated();
                }
        );

        http.apply(jwtConfigurer);
        http.httpBasic().disable();
        http.csrf().disable();
    }
}