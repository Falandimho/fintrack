# 💰 FinTrack API

Swagger: https://fintrack--AnthonyFalador.replit.app/swagger-ui/index.html

### API REST para Controle Financeiro Pessoal

------------------------------------------------------------------------

## 📌 Sobre o Projeto

O **FinTrack** é uma API REST desenvolvida com **Java e Spring Boot**
para gerenciamento de finanças pessoais.

O sistema permite que usuários cadastrem:

-   Receitas
-   Despesas
-   Categorias
-   Relatórios por período
-   Consulta de saldo consolidado

Este projeto foi desenvolvido com foco em aplicar corretamente os
fundamentos de desenvolvimento backend, organização de código e boas
práticas de APIs REST.

------------------------------------------------------------------------

## 🎯 Objetivo

O principal objetivo do projeto é demonstrar:

-   Conhecimento em **Java e Spring Boot**
-   Estruturação de projeto em **arquitetura em camadas**
-   Uso de **JPA/Hibernate**
-   Implementação de **API REST**
-   Aplicação de **DTOs**
-   Configuração básica de **Spring Security**
-   Organização de código pensando em escalabilidade

------------------------------------------------------------------------

## 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**, separando
responsabilidades:

Controller → Service → Repository → Model

### 📂 Estrutura do Projeto

src/main/java/com/fintrack/fintrack

-   controller/ → Endpoints da API\
-   service/ → Regras de negócio\
-   repository/ → Comunicação com o banco (Spring Data JPA)\
-   model/ → Entidades JPA\
-   dto/ → Objetos de entrada e saída\
-   config/ → Configurações (ex: segurança)

Essa organização facilita manutenção, testes e evolução do sistema.

------------------------------------------------------------------------

## 🧠 Conceitos Aplicados

### ✔️ API REST

-   Uso correto de métodos HTTP (GET, POST, PUT, DELETE)
-   Organização de rotas por recurso
-   Retorno de status HTTP apropriado

### ✔️ DTO (Data Transfer Object)

Separação entre: - Objetos de entrada (Input) - Objetos de saída
(Output) - Objetos de atualização (Update)

Evita expor diretamente as entidades do banco e melhora organização.

### ✔️ JPA / Hibernate

-   Mapeamento de entidades com @Entity
-   Relacionamentos entre tabelas
-   Persistência com Spring Data JPA
-   Uso de repositórios para abstrair acesso ao banco

### ✔️ Spring Security (Configuração Básica)

-   Proteção de rotas
-   Controle de acesso por usuário
-   Estrutura preparada para autenticação

------------------------------------------------------------------------

## 📊 Funcionalidades

### 👤 Usuários

-   Cadastro de usuário
-   Consulta de perfil
-   Consulta de saldo total

### 💵 Receitas

-   Criar
-   Atualizar
-   Remover
-   Listar

### 💸 Despesas

-   Criar
-   Atualizar
-   Remover
-   Listar

### 🗂️ Categorias

-   Criar
-   Atualizar
-   Listar
-   Classificação por tipo (RECEITA ou DESPESA)

### 📈 Relatórios

-   Consulta por período
-   Consolidação de saldo
-   Agrupamento por categoria

------------------------------------------------------------------------

## ⚙️ Tecnologias Utilizadas

-   Java 17+
-   Spring Boot
-   Spring Data JPA
-   Hibernate
-   Spring Security
-   Maven
-   RESTful API

------------------------------------------------------------------------

## 🚀 Como Executar o Projeto

### 🔹 Pré-requisitos

-   Java 17+
-   Maven
-   Banco de dados configurado

### 🔹 Clonar o projeto

git clone https://github.com/seu-usuario/fintrack.git\
cd fintrack

### 🔹 Configurar o banco

Editar:

src/main/resources/application.properties

Configurar:

spring.datasource.url=\
spring.datasource.username=\
spring.datasource.password=

### 🔹 Rodar a aplicação

mvn spring-boot:run

A aplicação iniciará em:

http://localhost:8080

------------------------------------------------------------------------

## 🔎 Exemplos de Endpoints

  Método   Endpoint              Descrição
  -------- --------------------- -----------------------
  POST     /receitas             Criar receita
  POST     /despesas             Criar despesa
  GET      /categorias           Listar categorias
  GET      /relatorios/periodo   Relatório por período

------------------------------------------------------------------------

## 📈 Aprendizados com o Projeto

Durante o desenvolvimento deste projeto, pratiquei:

-   Organização de backend em camadas
-   Modelagem de entidades
-   Estruturação de API REST
-   Separação de responsabilidades
-   Boas práticas de código limpo
-   Uso de DTOs para desacoplamento
-   Conceitos básicos de segurança em APIs

------------------------------------------------------------------------

## 🔮 Melhorias Futuras

-   Implementação de testes unitários
-   Documentação com Swagger/OpenAPI
-   Dockerização
-   Deploy em ambiente cloud
-   Paginação e filtros
-   Autenticação com JWT

------------------------------------------------------------------------

## 👨‍💻 Sobre Mim

Projeto desenvolvido como parte do meu portfólio para consolidar
conhecimentos em desenvolvimento backend com Java e Spring Boot.

Estou buscando oportunidades como **Desenvolvedor Backend Java Júnior**,
onde posso contribuir com dedicação, aprender com o time e evoluir
tecnicamente.

------------------------------------------------------------------------

## 📄 Licença

Projeto para fins educacionais e de portfólio.
