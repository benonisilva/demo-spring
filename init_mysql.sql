create database `CLIENTEDB`;
use `CLIENTEDB`;

CREATE TABLE `estado_entity` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cidade_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado_id` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `estado_fk` (`estado_id`),
  CONSTRAINT `estado_fk` FOREIGN KEY (`estado_id`) REFERENCES `estado_entity` (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cliente_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cidade_id` int(11) DEFAULT NULL,
  `data_nascimento` datetime(6) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cidade_fk` (`cidade_id`),
  CONSTRAINT `cidade_fk` FOREIGN KEY (`cidade_id`) REFERENCES `cidade_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;  


insert into `estado_entity` (ID, NOME, SIGLA) VALUES (1, 'ACRE', 'AC');
insert into `estado_entity` (ID, NOME, SIGLA) VALUES (2, 'ALAGOAS', 'AL');
insert into `estado_entity` (ID, NOME, SIGLA) VALUES (17, 'PERNAMBUCO', 'PE');