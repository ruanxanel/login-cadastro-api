package com.ruan.login_cadastro_api.controller;

import com.ruan.login_cadastro_api.dto.AuthResponse;
import com.ruan.login_cadastro_api.dto.LoginRequest;
import com.ruan.login_cadastro_api.dto.RegisterRequest;
import com.ruan.login_cadastro_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return "Usuario cadastrado com sucesso";
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
