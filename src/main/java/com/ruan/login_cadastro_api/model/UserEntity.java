package com.ruan.login_cadastro_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserEntity {

    @Id // Esse atributo é a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // o banco vai gerar o ID
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;
    private LocalDateTime data;
}
