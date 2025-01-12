package com.arterp.auth.controller;

import com.arterp.auth.dto.LoginRequest;
import com.arterp.auth.dto.RegisterRequest;
import com.arterp.auth.dto.TokenResponse;
import com.arterp.auth.service.AuthService;
import com.arterp.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return BaseResponse.success(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public BaseResponse<TokenResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return BaseResponse.success(authService.register(registerRequest));
    }
} 