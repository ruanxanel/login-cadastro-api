package com.ruan.login_cadastro_api.controller;

import com.ruan.login_cadastro_api.dto.UpdateUserRequest;
import com.ruan.login_cadastro_api.dto.UserResponse;
import com.ruan.login_cadastro_api.service.AuthService;
import com.ruan.login_cadastro_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/me")
    public UserResponse me(Authentication authentication) {
        return userService.buscarPerfil(authentication.getName());
    }

    @PutMapping("/me")
    public UserResponse update(Authentication authentication, @Valid @RequestBody UpdateUserRequest request) {
        return userService.atualizarPerfil(authentication.getName(), request);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> delete(Authentication authentication) {
        userService.deletarPerfil(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}