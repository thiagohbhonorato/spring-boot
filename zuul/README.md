# API Gateway Zuul
Funciona como uma porta de entrada da aplicação, todo o tráfego passa por ele antes de ser encaminhado para o microserviço específico respeitando as rotas que são configuradas no mesmo. O Gateway geralmente recebe a requisição desejada pelo cliente e consulta no Service Registry (Eureka) qual instância de microserviço responde por aquela rota.
