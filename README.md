# Aplicação Spring Boot de Pré-Cadastro de Clientes (Apenas o desafío 01)

> Este README fornece instruções básicas sobre como configurar e executar a aplicação Spring Boot de Pré-Cadastro de Clientes

## Pré-requisitos
* Java Development Kit (JDK)
* Maven

## Configuração do Banco de Dados H2
> A aplicação utiliza o banco de dados H2 para armazenar os dados dos clientes. Os parâmetros de configuração do H2 estão definidos no arquivo src/main/resources/application.properties

## Configuração do Swagger
> A documentação da API é gerada automaticamente com o Swagger. Você pode acessar a documentação Swagger em swagger-ui/index.html.

## Executando a Aplicação
1. Abra um terminal e navegue até o diretório raiz do projeto.
2. Compile o projeto usando o Maven:
```
mvn clean install

```
3. inicie a aplicação com o Mavem
```
mvn spring-boot:run

```

## Executando a Aplicação
### A aplicação oferece as seguintes funcionalidades:
* Consulta de um cliente pelo ID.
* Consulta de todos os clientes cadastrados.
* Exclusão de um cliente pelo ID.
* Cadastro e alteração de clientes pessoa física.
* Cadastro e alteração de clientes pessoa jurídica.