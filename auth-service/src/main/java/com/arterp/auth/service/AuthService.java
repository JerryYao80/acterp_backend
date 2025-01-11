package com.arterp.auth.service;

import com.arterp.auth.dto.LoginRequest;
import com.arterp.auth.dto.RegisterRequest;
import com.arterp.auth.dto.TokenResponse;
import com.arterp.auth.entity.User;
import com.arterp.auth.repository.UserRepository;
import com.arterp.auth.security.UserDetailsImpl;
import com.arterp.common.exception.BusinessException;
import com.arterp.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new TokenResponse(jwt, userDetails.getUsername(), 
                userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority().replace("ROLE_", ""))
                        .collect(java.util.stream.Collectors.toSet()));
    }

    @Transactional
    public TokenResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BusinessException("Username is already taken", 400);
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BusinessException("Email is already in use", 400);
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setPhone(registerRequest.getPhone());
        user.setRoles(registerRequest.getRoles() != null ? registerRequest.getRoles() : new HashSet<>());

        userRepository.save(user);

        return login(new LoginRequest(registerRequest.getUsername(), registerRequest.getPassword()));
    }
} 