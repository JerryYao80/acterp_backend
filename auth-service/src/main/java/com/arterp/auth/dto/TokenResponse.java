package com.arterp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Set<String> roles;

    public TokenResponse(String token, String username, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
} 