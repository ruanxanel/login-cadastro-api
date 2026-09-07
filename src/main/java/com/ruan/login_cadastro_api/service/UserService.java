package com.ruan.login_cadastro_api.service;

import com.ruan.login_cadastro_api.dto.UpdateUserRequest;
import com.ruan.login_cadastro_api.dto.UserResponse;
import com.ruan.login_cadastro_api.exception.UsuarioNaoEncontradoException;
import com.ruan.login_cadastro_api.model.UserEntity;
import com.ruan.login_cadastro_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse buscarPerfil(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
        return new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getData());
    }

    public UserResponse atualizarPerfil(String email, UpdateUserRequest request) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        user.setNome(request.getNome());

        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            user.setSenha(passwordEncoder.encode(request.getSenha()));
        }

        userRepository.save(user);

        return new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getData());
    }

    public void deletarPerfil(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        userRepository.delete(user);
    }
}
