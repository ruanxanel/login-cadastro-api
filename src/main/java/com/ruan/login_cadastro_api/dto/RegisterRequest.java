package com.ruan.login_cadastro_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank // verifica que o campo nao é null
    private String nome;

    @NotBlank
    @Email // verifica que a str tem formato e email valido
    private String email;

    @NotBlank
    @Size(min = 8) // Define o tamanho de caracteres
    private String senha;
}
