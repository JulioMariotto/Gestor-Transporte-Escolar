
CREATE SCHEMA `escolar`;

DROP TABLE IF EXISTS `escolar`.`usuario`;
DROP TABLE IF EXISTS `escolar`.`aluno`;
DROP TABLE IF EXISTS `escolar`.`responsavel`;
DROP TABLE IF EXISTS `escolar`.`pagamento`;
DROP TABLE IF EXISTS `escolar`.`motorista`;
DROP TABLE IF EXISTS `escolar`.`veiculo`;
DROP TABLE IF EXISTS `escolar`.`dispesa`;
DROP TABLE IF EXISTS `escolar`.`abastecimento`;
DROP TABLE IF EXISTS `escolar`.`manutencao`;

CREATE TABLE `usuario` (
  `id_usuario` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login_usuario` varchar(50) DEFAULT NULL,
  `senha_usuario` varchar(50) DEFAULT NULL,
  `nome_usuario` varchar(100) DEFAULT NULL
) ;

INSERT INTO `usuario` VALUES (7,'juliomariotto@hotmail.com','Julio!050595','Julio Mariotto');


CREATE TABLE `responsavel` (
  `id_responsavel` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome_responsavel` varchar(60) DEFAULT NULL,
  `cpf_responsavel` varchar(15) DEFAULT NULL,
  `rg_responsavel` varchar(15) DEFAULT NULL,
  `telefone_responsavel` varchar(16) DEFAULT NULL,
  `relacao_responsavel` varchar(11) DEFAULT NULL
) ;

CREATE TABLE `aluno` (
  `id_aluno` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome_aluno` varchar(60) DEFAULT NULL,
  `telefone_aluno` varchar(20) DEFAULT NULL,
  `endereco_aluno` varchar(100) DEFAULT NULL,
  `nome_mae_aluno` varchar(60) DEFAULT NULL,
  `telefone_mae_aluno` varchar(16) DEFAULT NULL,
  `nome_pai_aluno` varchar(60) DEFAULT NULL,
  `telefone_pai_aluno` varchar(16) DEFAULT NULL,
  `id_responsavel_aluno` int(11) DEFAULT NULL,
  `escola_aluno` varchar(60) DEFAULT NULL,
  `periodo_aluno` varchar(30) DEFAULT NULL,
  `turma_aluno` varchar(30) DEFAULT NULL,
  `horario_entrada_aluno` varchar(30) DEFAULT NULL,
  `horario_saida_aluno` varchar(30) DEFAULT NULL,
  `valor_mensalidade_aluno` float DEFAULT NULL,
  `vencimento_aluno` int(11) DEFAULT '10',
  `data_inicio_aluno` date DEFAULT NULL,
  `data_fim_aluno` date DEFAULT '2000-01-01',
  `status_aluno` int(2) DEFAULT '1',
  `data_nasc_aluno` date DEFAULT NULL,
  `contrato_aluno` int(1) DEFAULT NULL,
  FOREIGN KEY (`id_responsavel_aluno`) 
	REFERENCES `responsavel` (`id_responsavel`) 
		ON DELETE NO ACTION 
		ON UPDATE NO ACTION
) ;

CREATE TABLE `pagamento` (
  `id_pagamento` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_aluno` int(11) NOT NULL,
  `valor_pago` float DEFAULT NULL,
  `mes_referencia` varchar(25) DEFAULT NULL,
  `data_pagamento` date DEFAULT NULL
) ;

CREATE TABLE `escolar`.`motorista` (
  `id_motorista` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nome_motorista` VARCHAR(30) NULL,
  `sobrenome_motorista` VARCHAR(45) NULL,
  `cnh_motorista` VARCHAR(15) NULL,
  `telefone_motorista` VARCHAR(15) NULL,
  `endereco_motorista` VARCHAR(60) NULL
);

CREATE TABLE `escolar`.`veiculo` (
  `id_veiculo` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `modelo_veiculo` VARCHAR(45) NULL,
  `cor_veiculo` VARCHAR(15) NULL,
  `numero_veiculo` VARCHAR(5) NULL,
  `placa_veiculo` VARCHAR(10) NULL,
  `licenca_veiculo` VARCHAR(45) NULL,
  `capacidade_veiculo` INT NULL,
  `status_veiculo` INT(2) NOT NULL DEFAULT 1,
  `id_motorista_veiculo` INT(11) NOT NULL,
  FOREIGN KEY (`id_motorista_veiculo`) 
	REFERENCES `motorista` (`id_motorista`) 
		ON DELETE CASCADE 
        ON UPDATE NO ACTION
) ;

CREATE TABLE `escolar`.`dispesa` (
  `id_dispesa` INT(11) PRIMARY KEY PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `valor_dispesa` FLOAT NULL,
  `data_dispesa` DATE NULL,
  `descricao_dispesa` VARCHAR(120) NULL
) ;

CREATE TABLE `escolar`.`abastecimento` (
  `id_dispesa_abastecimento` INT(11) PRIMARY KEY NOT NULL,
  `id_veiculo_abastecimento` INT(11) NOT NULL,
  `km_abastecimento` INT NULL,
  `litros_abastecimento` FLOAT NULL,
  `posto_abastecimento` VARCHAR(30) NULL,  
  FOREIGN KEY (`id_veiculo_abastecimento`) 
	REFERENCES `veiculo` (`id_veiculo`) 
		ON DELETE NO ACTION 
        ON UPDATE NO ACTION,
  FOREIGN KEY (`id_dispesa_abastecimento`) 
	REFERENCES `dispesa` (`id_dispesa`) 
		ON DELETE CASCADE 
        ON UPDATE NO ACTION
) ;

CREATE TABLE `escolar`.`manutencao` (
  `id_dispesa_manutencao` INT(11) PRIMARY KEY NOT NULL,
  `id_veiculo_manutencao` INT(11) NOT NULL,
  `problema_manutencao` VARCHAR(120) NULL,
  `km_manutencao` VARCHAR(11) NULL,
  FOREIGN KEY (`id_dispesa_manutencao`) 
	REFERENCES `dispesa` (`id_dispesa`) 
		ON DELETE CASCADE 
        ON UPDATE NO ACTION,
  FOREIGN KEY (`id_veiculo_manutencao`) 
	REFERENCES `veiculo` (`id_veiculo`) 
		ON DELETE CASCADE 
        ON UPDATE NO ACTION
);

ALTER TABLE escolar.abastecimento ENGINE = InnoDB ;
ALTER TABLE escolar.aluno ENGINE = InnoDB ;
ALTER TABLE escolar.dispesa ENGINE = InnoDB ;
ALTER TABLE escolar.manutencao ENGINE = InnoDB ;
ALTER TABLE escolar.motorista ENGINE = InnoDB ;
ALTER TABLE escolar.pagamento ENGINE = InnoDB ;
ALTER TABLE escolar.responsavel ENGINE = InnoDB ;
ALTER TABLE escolar.usuario ENGINE = InnoDB ;
ALTER TABLE escolar.veiculo ENGINE = InnoDB ;