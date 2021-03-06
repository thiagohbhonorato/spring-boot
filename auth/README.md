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
### Token de acesso / Access Token

Para obter um token de acesso utilize: http://localhost:8088/oauth/token
    
Essa solicitação necessita das seguintes informações:
 
1. `Method: POST`
2. Headers: Authorization
    - Type: Basic Auth
    - username: *(identificação do client)*
    - password: *(senha do client)*
    
    Exemplo: `(..).set('Authorization', 'Basic Base64Encode==') // o username e password são criptografados em Base64`
    
3. Body
    - grant_type: password
    - scope: web
    - username: *(identificação do usuário)*
    - password: *(senha do usuário)*

Exemplo utilizando a ferramenta [Postman](https://www.postman.com/):

![oauth_access_token](https://github.com/thiagohbhonorato/spring-boot/blob/master/docs/oauth_access_token_postman.png "Exemplo da requisição Oauth - Acces Token")

### Token de atualização / Refresh Token

Para obter um novo token de acesso por meio de um token de atualização, utilize: http://localhost:8088/oauth/token

>*Veja que a URI do serviço é a mesma para obter um token de acesso, a diferença está a seguir:*

Essa solicitação necessita das seguintes informações:
 
1. `Method: POST`
2. Headers: Authorization *(mesma configuração do token de acesso)*
    - Type: Basic Auth
    - username: *(identificação do client)*
    - password: *(senha do client)*
    
    Exemplo: `(..).set('Authorization', 'Basic Base64Encode==') // o username e password são criptografados em Base64`
    
3. Body
    - grant_type: refresh_token
    - scope: web
    - refresh_token: *(token de atualização obtido junto com o token de acesso)*

Exemplo utilizando a ferramenta [Postman](https://www.postman.com/):

![oauth_refresh_token](https://github.com/thiagohbhonorato/spring-boot/blob/master/docs/oauth_refresh_token_postman.png "Exemplo da requisição Oauth - Refresh Token")

### Invalidar Token de acesso / Revoke Token

Para invalidar um token de acesso utilize:
1. http://localhost:8088/tokens/revoke/{token}
    
    O token é passado como parêmtro na URL.
    
2. http://localhost:8088/tokens/revoke/
    
    Nesse opção o token é enviado no cabeçalho da requisição (Header -> Authorization).
    
Utilize `Method DELETE`



Exemplo utilizando a ferramenta [Postman](https://www.postman.com/):

![oauth_revoke_token](https://github.com/thiagohbhonorato/spring-boot/blob/master/docs/oauth_revoke_token_postman.png "Exemplo da requisição Oauth - Revoke Token")

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
