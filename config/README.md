# Config Server
É um microserviço onde outros microserviços consultam as propriedades da própria aplicação. Quando a aplicação “cliente” sobe, usa a configuração de servidor de configuração e pergunta para o serviço de Config Server quais configurações precisa aplicar, se identificando pelo nome da aplicação.

## application.properties
```application.properties
server.port=8888
spring.application.name=config

spring.profiles.active=native
spring.cloud.config.server.native.search-locations=C:/config
```

## Endpoint

## Informações adicionais

### Versão

### Dependência pom.xml
