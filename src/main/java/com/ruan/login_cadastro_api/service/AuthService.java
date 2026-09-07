package com.ruan.login_cadastro_api.service;

import com.ruan.login_cadastro_api.dto.AuthResponse;
import com.ruan.login_cadastro_api.dto.LoginRequest;
import com.ruan.login_cadastro_api.dto.RegisterRequest;
import com.ruan.login_cadastro_api.exception.CredenciaisInvalidasException;
import com.ruan.login_cadastro_api.exception.EmailCadastradoException;
import com.ruan.login_cadastro_api.model.UserEntity;
import com.ruan.login_cadastro_api.repository.UserRepository;
import com.ruan.login_cadastro_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailCadastradoException("E-mail já cadastrado");
        }

        UserEntity user = new UserEntity();
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenha(passwordEncoder.encode(request.getSenha()));
        user.setData(LocalDateTime.now());

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CredenciaisInvalidasException("E-mail ou senha invalidos"));

        if (!passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            throw new CredenciaisInvalidasException("E-mail ou senha invalidos");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
