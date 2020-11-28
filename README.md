# APLICACAO DE REFERENCIA REST

Aplicacao baseada no spring boot usando spring data jpa, hibernate.

## Contract-First em REST com Swagger


Contract-First em REST com Swagger configurado no pom.xml para geração de codigo dos modelos de do recursos REST. Algumas validações da api são tambem gerados via contrato. Temos dois contratos no projeto para demostração:
1. src\main\resources\api\api.yaml - usado para gerar os modelos do exercicio pedido
2. src\main\resources\api\integracoes.yaml - usando para criar uma parte do modelo da api https://servicodados.ibge.gov.br/api/v1/


## Model Mapper para mapear entidades

Foi usado o Model Mapper (http://modelmapper.org/user-manual/) para mapper entidades de dominio para view model

## H2 para persistencia em dev e testes integrados

Por facilidade de instalação e manuseio foi utilizado o H2 para testes e desenvolvimento. O banco foi populado inicialmente usando o arquivo src\main\resources\data.sql

## Webclient para integraçao
Para integrar com serviço externo do IBGE usei https://www.baeldung.com/spring-5-webclient. Para permitir reuso foi criada uma classe abstrata (src\main\java\com\benoni\spring\interview\demo\integracoes\estados\RestClienteEstados.java) que pode ser enriquecida com outros metodos HTTP 
como POST, PUT, DELETE. **A ideia era usar para demostrar teste integrados com wiremock usar para enriquercer os serviços da api Cidades** 


## Estrutura basica do projeto
O projeto é organizado da seguinte forma:
1. Api Rest com.benoni.spring.interview.demo.api - Todas as implementações das apis.
2. INtegraçoes com.benoni.spring.interview.demo.integracoes - Implementações para acessar serviços externos.
3. Mapper com.benoni.spring.interview.demo.mapper - Implementação de conversores e mappers.
4. Repositorios com.benoni.spring.interview.demo.repositories - Implementações da camada de persistencia.
5. Services com.benoni.spring.interview.demo.services - Implementação dos serciços

#### Link para Postman com operações basicas da api desenvolvida
    https://www.postman.com/collections/4745e60f27ee37224b44