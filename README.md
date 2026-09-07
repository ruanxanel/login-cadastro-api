# API de Login e Cadastro de Usuário

API REST desenvolvida em Spring Boot para cadastro, autenticação e gerenciamento de perfil de usuários, utilizando JWT (JSON Web Token) para controle de acesso.

Este projeto foi construído como parte de uma trilha de estudos em Spring Boot, com foco em fixar conceitos fundamentais de backend: persistência de dados, validação, segurança e organização em camadas.

## Funcionalidades

- Cadastro de usuário com senha criptografada (BCrypt)
- Login com geração de token JWT
- Proteção de rotas via filtro de autenticação JWT
- Consulta, atualização e exclusão de perfil do usuário autenticado
- Tratamento centralizado de exceções, com respostas HTTP padronizadas

## Tecnologias utilizadas

- Java 21
- Spring Boot 4
- Spring Security
- Spring Data JPA
- PostgreSQL
- JJWT (geração e validação de tokens JWT)
- Lombok
- Maven
- Springdoc OpenAPI (Swagger)

## Endpoints

| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| POST | `/auth/register` | Cadastra um novo usuário | Público |
| POST | `/auth/login` | Autentica e retorna um token JWT | Público |
| GET | `/users/me` | Retorna dados do usuário autenticado | Autenticado |
| PUT | `/users/me` | Atualiza dados do usuário autenticado | Autenticado |
| DELETE | `/users/me` | Exclui a conta do usuário autenticado | Autenticado |

### Exemplo de cadastro

```
POST /auth/register
Content-Type: application/json

{
  "nome": "Ruan",
  "email": "ruan@email.com",
  "senha": "12345678"
}
```

### Exemplo de login

```
POST /auth/login
Content-Type: application/json

{
  "email": "ruan@email.com",
  "senha": "12345678"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### Acessando rotas protegidas

Envie o token retornado no login no cabeçalho `Authorization`:

```
Authorization: Bearer <seu_token_aqui>
```

## Como rodar o projeto localmente

### Pré-requisitos

- Java 21
- Maven
- PostgreSQL

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/login-cadastro-api.git
cd login-cadastro-api
```

### 2. Crie o banco de dados

```sql
CREATE DATABASE login_cadastro_db;
```

### 3. Configure as variáveis de ambiente

O projeto não expõe credenciais no código. Antes de rodar, defina as seguintes variáveis de ambiente:

| Variável | Descrição |
|---|---|
| `DB_PASSWORD` | Senha do usuário do PostgreSQL |
| `JWT_SECRET` | Chave secreta usada para assinar os tokens JWT (string longa em Base64) |

No IntelliJ: **Run → Edit Configurations → Environment variables**

Ou, via terminal:

```bash
export DB_PASSWORD=sua_senha_aqui
export JWT_SECRET=sua_chave_secreta_aqui
```

### 4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação sobe por padrão em `http://localhost:8080`.

## Estrutura do projeto

```
src/main/java/com/ruan/login_cadastro_api/
├── config/          # Configuração de segurança (Spring Security)
├── controller/      # Endpoints REST
├── dto/             # Objetos de transferência de dados (requisições e respostas)
├── exception/       # Exceções customizadas e tratamento global de erros
├── model/           # Entidades JPA
├── repository/      # Interfaces de acesso ao banco de dados
├── security/        # Geração/validação de JWT e filtro de autenticação
└── service/         # Regras de negócio
```

## Próximos passos

- [ ] Testes automatizados (unitários e de integração)
- [ ] Documentação interativa via Swagger UI
- [ ] Refresh token
- [ ] Deploy em ambiente de produção

## Autor

Desenvolvido por Ruan como projeto de estudo em Spring Boot.