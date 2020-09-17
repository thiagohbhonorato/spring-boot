# Config Server
É um microserviço onde outros microserviços consultam as propriedades da própria aplicação. Quando a aplicação “cliente” sobe, usa a configuração de servidor de configuração e pergunta para o serviço de Config Server quais configurações precisa aplicar, se identificando pelo nome da aplicação.


Esse recurso pode ser muito útil para configurações de conexão com banco de dados, dessa forma essas configurações não ficam dentro dos diversos microserviços, facilitando a manutenção, pois estão em um ponto único.

## application.properties
```application.properties
server.port=8888
spring.application.name=config

spring.profiles.active=native
spring.cloud.config.server.native.search-locations=C:/config
```
1. Por padrão a porta do servidor de configuração é [8888](https://docs.spring.io/spring-cloud-config/docs/2.2.5.RELEASE/reference/html/#_spring_cloud_config_server "Spring Cloud Config - Docs").
2. A configuração `spring.profiles.active` definida como `native` indica o sistema de arquivo utilizada para leitura das propriedades de cada aplicação, identificadas pelo nome. Já a configuração `spring.cloud.config.server.native.search-locations` indica a URL estática onde o arquivo está localizado. [[leia mais](https://docs.spring.io/spring-cloud-config/docs/2.2.5.RELEASE/reference/html/#_file_system_backend "Spring Cloud Config - File System Backend")]

    Exemplo:


    ![file](https://github.com/thiagohbhonorato/spring-boot/blob/master/docs/config_file.png "Arquivo de Propriedades")
    
    
    No cliente terá uma configuração assim:
    ```application.properties
    spring.application.name=quest
    spring.profiles.active=default
    ```


## Endpoint
Se você deseja obter as configurações que uma determinada aplicação utiliza por meio do servidor de configuração, acesse:

http://localhost:8888/quest/default

O resultado será algo assim:
```json
{
    "name": "quest",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "file:C:/config/quest-default.properties",
            "source": {
                "spring.datasource.url": "jdbc:postgresql://localhost:5432/quest",
                "spring.datasource.username": "username",
                "spring.datasource.password": "password",
                "spring.jpa.hibernate.ddl-auto": "update",
                "spring.datasource.hikari.connectionTimeout": "20000",
                "spring.datasource.hikari.maximumPoolSize": "5"
            }
        }
    ]
}
```

## Informações adicionais

### Versão
* Spring 2.3.3.RELEASE
* Java 1.8
* Spring Cloud Hoxton.SR7

### Dependência pom.xml
* Config Server
```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

### Habilitar o Config Server
Para habilitar e executar o servidor de configuração adicione `@EnableConfigServer` como no exemplo a seguir:
```java
@SpringBootApplication
@EnableConfigServer // <- aqui
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
```
