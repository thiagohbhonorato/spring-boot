# Authentication Server
![shield](https://img.shields.io/badge/ThiagoHonorato-Authentication-red)

É responsável por expor recursos protegidos OAuth 2.0. A configuração envolve o estabelecimento de clientes OAuth 2.0 que podem acessar seus recursos protegidos de forma independente ou em nome de um usuário. O provedor faz isso gerenciando e verificando os tokens OAuth 2.0 usados para acessar os recursos protegidos.

## application.properties
```application.properties
server.port=8088

# JPA
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
1. A porta foi definida como 8088.
2. As propriedades `spring.jpa.show` e `spring.jpa.properties.hibernate.format_sql` definidas como `true` habilitam a exibição do SQL no log já com formatação.
3. O Authentication Server foi configurado para ler as configurações expostas pelo **Config Server**, que nesse caso são configurações de conexão com o banco de dados. Para que essas configurações sejam aplicadas na inicialização do Authentication Server é necessário utilizar um contexto anterior ao `application.properties`, o `bootstrap.properties`. A utilização de um contexto anterior é necessária pois o microserviço utiliza o **Spring Data JPA** para persistência em banco de dados, que precisa dessas configurações para iniciar.

    ### bootstrap.properties
    ```bootstrap.properties
    # Config Server
    spring.application.name=auth
    spring.profiles.active=default
    spring.cloud.config.uri=http://localhost:8888
    ```

## Endpoint
### Token de acesso

Para obter um token de acesso utilize: http://localhost:8088/oauth/token
    
Esse solicitação necessita das seguintes informações:
    
1. Authorization
    - Type: Basic Auth
    - username: *(identificação do client)*
    - password: *(senha do client)*
    
2. Body
    - grant_type: password
    - scope: web
    - username: *(identificação do usuário)*
    - password: *(senha do usuário)*

O resultado será algo assim:
```json
{
    "access_token": "a45e5e21-9063-4403-9be9-d9128ab195ae",
    "token_type": "bearer",
    "refresh_token": "642c153e-7317-48cb-a632-d7cbc76fbe22",
    "expires_in": 35999,
    "scope": "web"
}
```

Exemplo:
```javascript
const a;
```

## Informações adicionais

### Versão
* Spring 2.3.3.RELEASE
* Java 1.8
* Spring Cloud Hoxton.SR7

### Dependências pom.xml
* Spring Security
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-test</artifactId>
  <scope>test</scope>
</dependency>
```
* Cloud OAuth2
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-oauth2</artifactId>
</dependency>
```
* Spring Data JPA
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
* PostgreSQL Driver
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
* Config Client
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
* Spring Web
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
* Lombok
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
</dependency>
```

### Habilitar o Authorization Server
Para habilitar e executar o Authorization Server adicione `@EnableAuthorizationServer`, também foi configurado o `@EnableResourceServer` como no exemplo a seguir:
```java
@SpringBootApplication
@EnableAuthorizationServer // <- aqui
@EnableResourceServer // <- aqui
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
```
