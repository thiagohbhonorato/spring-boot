# Eureka Server
![shield](https://img.shields.io/badge/ThiagoHonorato-EurekaServer-green)

Consiste em uma aplicação que atua como Service Registry permitindo que outras aplicações registrem suas instâncias, com isso, ele controla os endereços registrados mantendo-os atualizados e sinalizando quando um serviço não está disponível.

## application.properties
```application.properties
server.port=8761
spring.application.name=eureka

eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```
1. Por padrão a porta do servidor eureka é 8761.
2. As configurações `eureka.client.fetch-registry` e `eureka.client.register-with-eureka` definidas como `false` indicam que este microserviço não busca informações de registro do servidor eureka e não se registra no servidor eureka, pois esse microserviço é o próprio servidor eureka. Por padrão essas configurações são definidas como `true`. [[leia mais](https://docs.spring.io/spring-cloud-netflix/docs/2.2.5.RELEASE/reference/html/#spring-cloud-eureka-server-standalone-mode "Spring Cloud Netflix - Docs")]

## Endpoint

Para ter acesso a todos os microserviços registrados no servidor eureka, acesse:

http://localhost:8761/eureka/apps

O resultado será algo assim:

```xml
<applications>
    <versions__delta>1</versions__delta>
    <apps__hashcode>UP_1_</apps__hashcode>
    <application>
        <name>QUEST</name>
        <instance>
            <instanceId>10.0.0.105:quest:8080</instanceId>
            <hostName>10.0.0.105</hostName>
            <app>QUEST</app>
            <ipAddr>10.0.0.105</ipAddr>
            <status>UP</status>
            <overriddenstatus>UNKNOWN</overriddenstatus>
            <port enabled="true">8080</port>
            <securePort enabled="false">443</securePort>
            <countryId>1</countryId>
            <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
                <name>MyOwn</name>
            </dataCenterInfo>
            <leaseInfo>
                <renewalIntervalInSecs>30</renewalIntervalInSecs>
                <durationInSecs>90</durationInSecs>
                <registrationTimestamp>1600299334236</registrationTimestamp>
                <lastRenewalTimestamp>1600299634030</lastRenewalTimestamp>
                <evictionTimestamp>0</evictionTimestamp>
                <serviceUpTimestamp>1600299333564</serviceUpTimestamp>
            </leaseInfo>
            <metadata>
                <management.port>8080</management.port>
                <jmx.port>57297</jmx.port>
            </metadata>
            <homePageUrl>http://10.0.0.105:8080/</homePageUrl>
            <statusPageUrl>http://10.0.0.105:8080/actuator/info</statusPageUrl>
            <healthCheckUrl>http://10.0.0.105:8080/actuator/health</healthCheckUrl>
            <vipAddress>quest</vipAddress>
            <secureVipAddress>quest</secureVipAddress>
            <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
            <lastUpdatedTimestamp>1600299334236</lastUpdatedTimestamp>
            <lastDirtyTimestamp>1600299333426</lastDirtyTimestamp>
            <actionType>ADDED</actionType>
        </instance>
    </application>
</applications>
```

## Informações adicionais
### Versão
* Spring 2.3.3.RELEASE
* Java 1.8
* Spring Cloud Hoxton.SR7

### Dependência *pom.xml*
* Eureka Server
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

### Habilitar o Eureka Server
Para habilitar e executar o servidor eureka adicione `@EnableEurekaServer` como no exemplo a seguir:
```java
@SpringBootApplication
@EnableEurekaServer // <- aqui
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```
