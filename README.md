# Projeto de Aprendizado de Microserviços em Spring Boot

Este repositório é uma referência para um curso de microserviços em Spring Boot e destina-se principalmente a fins didáticos. Nosso objetivo aqui não é seguir todas as boas práticas de desenvolvimento de APIs, como tratamento de erros, uso de DTOs ou clean code, mas sim focar nos conceitos fundamentais de microserviços.

## Visão Geral do Projeto

O projeto é uma simulação de um sistema de Recursos Humanos, composto por vários microserviços interconectados, cada um desempenhando um papel específico:

1. **hr-worker** - Este microserviço é responsável por consultar os trabalhadores no banco de dados. Os dados são gerados manualmente com a finalidade de aprendizado.

2. **hr-user** - Aqui, você pode criar usuários no banco de dados. É uma parte essencial do sistema para autenticação.

3. **hr-oauth** - Este microserviço lida com a autenticação dos usuários e a atribuição de funções (roles) como admin e operator para acessar as rotas apropriadas.

4. **hr-payroll** - Ele consulta o microserviço hr-worker para calcular a diária de um trabalhador com base no ID do trabalhador e no número de dias trabalhados.

5. **config-server** - Este microserviço contém configurações de perfis como dev, prod e test em um repositório privado. Ele também inclui informações para geração de token.

6. **eureka-server** - O servidor Eureka é responsável pelo registro e descoberta de serviços em execução, facilitando a comunicação entre eles.

7. **api-gateway** - A API Gateway é a porta de entrada para os microserviços. Ela encaminha as solicitações dos clientes para os microserviços apropriados.

## Escalabilidade

Um destaque deste projeto é a capacidade de escalabilidade do microserviço **hr-worker**. Você pode facilmente aumentar o número de instâncias desse serviço para atender a demandas crescentes.

## Considerações Finais

Este repositório foi criado com o intuito de ajudar na compreensão dos conceitos fundamentais de microserviços em Spring Boot. É importante notar que não seguimos estritamente as melhores práticas de desenvolvimento neste projeto, pois nosso foco principal era a aprendizagem.

Fique à vontade para explorar, estudar e, se necessário, adaptar os conceitos deste projeto para suas próprias necessidades. Lembre-se de que este é um recurso didático e pode não ser adequado para ambientes de produção.

Esperamos que este repositório seja útil para o seu aprendizado e desenvolvimento de habilidades em microserviços.

![image](https://github.com/Lucas-B-Teixeira/Microservices-Learning/assets/122762806/7779b408-ccb2-4a98-8df7-62e3ca907d83)

