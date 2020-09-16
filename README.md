# Arquitetura back-end com Spring Boot
Esse projeto consiste em uma arquitetura back-end feita em Java utilizando o framework Spring Boot. Seu objetivo é ser a arquitetura base para a criação de diversos microserviços. Inicialmente essa arquitetura foi criada para o desenvolvimento de uma aplicação de questionário.

## Sobre a Arquitetura
Nessa arquitetura foram utilizadas tecnologias...

* **Eureka Server**
Consiste em uma aplicação que atua como Service Registry permitindo que outras aplicações registrem suas instâncias, com isso, ele controla os endereços registrados mantendo-os atualizados e sinalizando quando um serviço não está disponível.

* **Config Server**
É um microserviço onde outros microserviços consultam as propriedades da própria aplicação. Quando a aplicação “cliente” sobe, usa a configuração de servidor de configuração e pergunta para o serviço de Config Server quais configurações precisa aplicar, se identificando pelo nome da aplicação.

* **API Gateway Zuul**
Funciona como uma porta de entrada da aplicação, todo o tráfego passa por ele antes de ser encaminhado para o microserviço específico respeitando as rotas que são configuradas no mesmo. O Gateway geralmente recebe a requisição desejada pelo cliente e consulta no Service Registry (Eureka) qual instância de microserviço responde por aquela rota.

* **Authentication Server**
É responsável por expor recursos protegidos OAuth 2.0. A configuração envolve o estabelecimento de clientes OAuth 2.0 que podem acessar seus recursos protegidos de forma independente ou em nome de um usuário. O provedor faz isso gerenciando e verificando os tokens OAuth 2.0 usados para acessar os recursos protegidos.

* **API Quest**
Refere-se ao microserviço do sistema de questionário que possui as regras de negócios da aplicação.
