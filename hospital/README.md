## Padrões de Projeto e Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando várias tecnologias e ferramentas para garantir uma arquitetura robusta e eficiente. Abaixo está uma visão geral das principais tecnologias e padrões aplicados:

  - **http://localhost:8080/swagger-ui/index.html**

## Funcionalidades

- **Adicionar Ficha:** A funcionalidade permite adicionar várias fichas para um paciente, desde que não exista outra ficha cadastrada com o mesmo plano e especialidade da ficha que está sendo adicionada.
- **Editar Ficha:** Modifique uma ficha existente, desde que o plano e a especialidade não sejam alterados para se tornarem iguais a uma outra ficha já cadastrada para o mesmo paciente.
- **Excluir Fichas:** Remova fichas que não são mais necessárias.
- **Listagem:** Veja suas fichas em uma lista organizada.

## Pré-Requisitos
   
- [Java JDK 17 ou superior](https://www.oracle.com/java/technologies/downloads/#java17)
- [Spring Boot 3.3.3](https://spring.io/projects/spring-boot)

### Tecnologias e Ferramentas

- **[Spring Boot](https://spring.io/projects/spring-boot):** 
  Framework utilizado para criar aplicações Java com configuração mínima. As seguintes dependências do Spring Boot foram utilizadas:
  - **spring-boot-starter-data-jpa:** Facilita a integração com o banco de dados relacional utilizando JPA (Java Persistence API).
  - **spring-boot-starter-web:** Fornece suporte para criar aplicações web, incluindo APIs RESTful.
  - **spring-boot-starter-test:** Inclui ferramentas para testes de unidade e integração.
  
- **[PostgreSQL](https://www.postgresql.org/):** 
  Sistema de gerenciamento de banco de dados relacional utilizado para armazenar e gerenciar os dados da aplicação. A dependência `postgresql` permite a conexão com o banco de dados PostgreSQL.

- **[JUnit 5](https://junit.org/junit5/):** 
  Framework de testes para criar e executar testes automatizados. As seguintes dependências foram usadas:
  - **junit-jupiter-api:** Fornece as APIs para escrever testes.
  - **junit-jupiter-engine:** Motor para executar os testes JUnit 5.

- **[Mockito](https://site.mockito.org/):** 
  Framework de simulação para criar mocks e stubs em testes de unidade, ajudando a isolar e testar a lógica de aplicação.

- **[ModelMapper](http://modelmapper.org/):** 
  Biblioteca para simplificar a conversão entre diferentes representações de objetos, como DTOs e entidades. Facilita o mapeamento de propriedades entre classes.

- **[Springdoc OpenAPI](https://springdoc.org/):** 
  Biblioteca para gerar a documentação da API no formato OpenAPI (Swagger). A dependência `springdoc-openapi-starter-webmvc-ui` permite a geração de uma interface gráfica para explorar e testar a API.


### Arquitetura da Aplicação

A arquitetura da aplicação é projetada para seguir boas práticas de desenvolvimento e garantir uma estrutura clara e modular:

- **Camadas da Aplicação:**


  - **Controller:** Gerencia as requisições HTTP e interage com a camada de serviço. Responsável por expor endpoints da API.
  - **Service:** Contém a lógica de negócio e orquestra a interação entre as camadas de dados e apresentação.
  - **Repository:** Gerencia a persistência dos dados, realizando operações de CRUD com o banco de dados.
  - **DTO (Data Transfer Object):** Usado para transportar dados entre camadas de forma segura e desacoplada.
  
  
- **Documentação da API:**
  - **Swagger:** A documentação interativa da API é gerada e acessível através da interface do Swagger, facilitando a compreensão e testes dos endpoints da API.

  
  
- **Testes:** 
- **JUnit 5:** Framework de testes para criar e executar testes automatizados.
- **A cobertura de testes para este projeto está em: ** [███████████████████████████████-------] 84%