package ru.twemi.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import ru.twemi.entity.Authority;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    @Value("${jwt.token.expired}")
    private long expired;

    @Value("${jwt.token.secret}")
    private String secret;

    @PostMapping
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }


    public String createToken(String username, Set<Authority> authorities) {
        Claims claims = Jwts.claims().setSubject(username); //TODO Создаем токен jwt и кладем в него полезную нагрузку, имя мользовтеля и роль
        claims.put("roles", authorities.stream()
                .map(Authority::getRole)
                .collect(Collectors.toSet()));

        Date now = new Date(); //TODO Задаем текущее время (Время старта токена)
        Date expiredDate = new Date(now.getTime() + expired); //TODO Задаем срок действия токена

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
