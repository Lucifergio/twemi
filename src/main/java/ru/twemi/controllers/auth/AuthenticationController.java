package ru.twemi.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.twemi.dto.security.AuthenticationUserDto;
import ru.twemi.entity.User;
import ru.twemi.security.jwt.JwtTokenProvider;
import ru.twemi.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationUserDto authenticationUserDto) {
        try {
            final String username = authenticationUserDto.getUsername();
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username, user.getAuthorities());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
