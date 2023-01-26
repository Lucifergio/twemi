package ru.twemi.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationUserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}