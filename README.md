# Desafio PontoCob

Este projeto é um desafio para a empresa PontoCob e consiste em uma aplicação web com um backend Java Spring e um frontend Vue.js, utilizando Quasar para a interface e Axios para comunicação com o backend. O banco de dados PostgreSQL é gerenciado com o auxílio do Flyway.

## Pré-requisitos

Para executar este projeto, você deve ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Docker (e Docker Compose)
- Maven
- Node.js
- NPM (ou Yarn)

## Configuração do Banco de Dados

Certifique-se de ter o Docker e o Docker Compose instalados e execute o seguinte comando para configurar o banco de dados PostgreSQL com o Flyway:

```bash 
docker-compose up -d
```
Isso iniciará um contêiner Docker com o PostgreSQL e aplicará as migrações do Flyway para criar o esquema do banco de dados.

## Backend
Acesse a pasta do projeto backend e siga os seguintes comandos para construir e iniciar o servidor Java Spring:

```bash
mvn clean install
mvn spring-boot:run
```

O servidor estará disponível em http://localhost:8080.

## Frontend
Acesse a pasta do projeto frontend e siga um dos seguintes conjuntos de comandos, dependendo se você está usando NPM ou Yarn:

Usando NPM:
```bash
npm install
npm run serve
```
Usando Yarn:
```bash
yarn install
yarn serve
```
O servidor de desenvolvimento Vue.js estará disponível em http://localhost:8081.

Agora você pode acessar a aplicação web através do navegador.