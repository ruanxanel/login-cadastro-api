package com.ruan.login_cadastro_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String nome;
    private String email;
    private LocalDateTime data;
}
