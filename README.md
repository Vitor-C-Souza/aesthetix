# Aesthetix

Projeto backend Spring Boot (API REST) — autenticação JWT, modelos de usuário e perfis, tratamento padronizado de erros
e documentação OpenAPI/Swagger.

## Visão geral

- Spring Boot 4.x (Spring Framework 7)
- Autenticação por JWT (HS256) com roles: ADMIN, PROFESSIONAL, RECEPTIONIST
- Endpoints de autenticação: `/api/v1/auth/login` e `/api/v1/auth/register`
- Seed de usuário padrão em ambiente `dev` (ver DevDataLoader)
- Tratamento consistente de erros (StandardError / ValidationError)
- Integração com Springdoc OpenAPI (Swagger UI)

## Requisitos

- Java 17+ / JDK 21 (testado)
- Maven 3.8+
- MySQL (ou ajuste datasource em `application.properties`)

## Configuração

Edite `src/main/resources/application.properties` ou defina variáveis de ambiente:

- server.port (ex.: 8081)
- spring.datasource.* (DB)
- jwt.secret — segredo para assinar tokens (mínimo 32 bytes recomendado)
- jwt.expiration-ms — validade do token em ms

Exemplo mínimo:

```
jwt.secret=change-me-please-32-bytes-minimum-length-xxx
jwt.expiration-ms=86400000
server.port=8081
```

## Perfis

- `dev`: cria um usuário padrão (veja `DevDataLoader`). Use `--spring.profiles.active=dev` para ativar.

## Build & executar

1. Empacotar:
   mvn -DskipTests package
2. Executar:
   java -jar target\aesthetix-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

OU usando Maven (dev):
mvn -DskipTests -Dspring-boot.run.profiles=dev spring-boot:run

## Endpoints principais (autenticação)

- POST /api/v1/auth/register — registrar novo usuário body: { "username":"...", "password":"...", ... }
- POST /api/v1/auth/login — gerar token JWT body: { "username":"...", "password":"..." }
- Resposta de sucesso de login contém o token (Bearer) para usar em Authorization header

Exemplo curl (login):

```
curl -X POST http://localhost:8081/api/v1/auth/login -H "Content-Type: application/json" -d '{"username":"admin","password":"admin"}'
```

## Roles / Permissões

- Roles estão em enum `Role` e atribuídas ao usuário.
- Endpoints protegidos via `SecurityConfig` — ver a classe para regras detalhadas.

## Swagger / OpenAPI

- Dependência: springdoc-openapi-starter-webmvc-ui
- Configuração em `OpenApiConfig.java`
- UI normalmente disponível em `/swagger-ui.html` ou `/swagger-ui/index.html` quando habilitada

Observação importante: há compatibilidade entre Spring Boot 4 / Spring 7 e versões recentes do springdoc/swagger-core.
Em alguns ambientes `/v3/api-docs` pode retornar 500 (NoSuchMethodError) devido a incompatibilidades entre `spring-web`
e `swagger-core-jakarta`.

Workarounds:

- Desabilitar OpenAPI/Swagger configuracionalmente adicionando em `application.properties`:
  ```properties
  springdoc.api-docs.enabled=false
  springdoc.swagger-ui.enabled=false
  ```
- Alternativamente remova a dependência `org.springdoc:springdoc-openapi-starter-webmvc-ui` até haver versão compatível.

## Observações de implementação

- `JwtAuthEntryPoint` foi adaptado para usar o ObjectMapper do Spring e o módulo jsr310 para serializar
  `java.time.Instant`.
- A classe `JacksonConfig` registra `jackson-datatype-jsr310` e desativa timestamps para datas.
- JWT secret deve ser guardado com segurança em ambiente de produção (não em `application.properties` no repo).

## Troubleshooting rápido

- Porta em uso: pare processo Java ou altere `server.port`.
- /v3/api-docs 500: ver logs da aplicação para stacktrace; experimente desabilitar Springdoc (ver Workarounds).
- Erro de serialização de Instant: garanta `jackson-datatype-jsr310` no classpath (já incluído neste projeto).

## Contribuição

Pull requests são bem-vindos. Para mudanças sensíveis (segurança, dependências), abrir issue antes.

---
Gerado automaticamente. Para ajustes no README ou inclusão de exemplos adicionais diga o que deseja que eu adicione.