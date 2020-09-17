# API Gateway Zuul
Funciona como uma porta de entrada da aplicação, todo o tráfego passa por ele antes de ser encaminhado para o microserviço específico respeitando as rotas que são configuradas no mesmo. O Gateway geralmente recebe a requisição desejada pelo cliente e consulta no Service Registry (Eureka) qual instância de microserviço responde por aquela rota.

## application.properties
```application.properties
server.port=5555
spring.application.name=zuul

# Eureka Server
eureka.client.fetch-registry=true
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone: http://localhost:8761/eureka

# Zuul
management.endpoints.web.exposure.include=routes
```
1. A porta foi definida como 5555.
2. As configurações `eureka.client.fetch-registry` e `eureka.client.register-with-eureka` definidas como `true` indicam que este microserviço busca informações de registro do servidor eureka e também se registra no servidor eureka. Essa configuração se faz necessária pois esse microserviço é a porta de entrada para as aplicações e é no Eureka Server onde todos os microserviços estão registrados.
3. A configuração `management.endpoints.web.exposure.include=routes` habilita o endpoint http://localhost:5555/actuator/routes/.

## Endpoint
Para ter acesso a todas as rotas do Zuul, acesse:


http://localhost:5555/actuator/routes/


O resultado será algo assim:
```json
{
    "/quest/**": "quest"
}
```

## Informações adicionais

### Versão
* Spring 2.3.3.RELEASE
* Java 1.8
* Spring Cloud Hoxton.SR7

### Dependências pom.xml
* Zuul
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```
* Eureka Discovery Client

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
* Spring Web
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### Habilitar o Zuul
Para habilitar e executar o Zuul adicione `@EnableZuulProxy` como no exemplo a seguir:
```java
@SpringBootApplication
@EnableZuulProxy // <- aqui
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
```
