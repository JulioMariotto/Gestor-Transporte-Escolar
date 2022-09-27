-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tempo de Geração: 
-- Versão do Servidor: 5.1.49-community-log
-- Versão do PHP: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `sgfe`
--

CREATE DATABASE `sgfe` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sgfe`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `abastecimento`
--

DROP TABLE IF EXISTS `abastecimento`;
CREATE TABLE IF NOT EXISTS `abastecimento` (
  `id_despesa_abastecimento` int(11) NOT NULL,
  `id_veiculo_abastecimento` int(11) NOT NULL,
  `km_abastecimento` int(11) DEFAULT NULL,
  `litros_abastecimento` float DEFAULT NULL,
  `posto_abastecimento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_despesa_abastecimento`),
  KEY `id_veiculo_abastecimento` (`id_veiculo_abastecimento`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Estrutura da tabela `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE IF NOT EXISTS `aluno` (
  `id_aluno` int(11) NOT NULL AUTO_INCREMENT,
  `nome_aluno` varchar(60) DEFAULT NULL,
  `telefone_aluno` varchar(20) DEFAULT NULL,
  `endereco_aluno` varchar(100) DEFAULT NULL,
  `nome_responsavel` varchar(60) DEFAULT NULL,
  `cpf_responsavel` varchar(14) DEFAULT NULL,
  `id_escola_aluno` int(11) NOT NULL,
  `periodo_aluno` varchar(30) DEFAULT NULL,
  `turma_aluno` varchar(30) DEFAULT NULL,
  `horario_casa_ida` varchar(10) DEFAULT NULL,
  `horario_escola_ida` varchar(10) DEFAULT NULL,
  `horario_casa_volta` varchar(10) DEFAULT NULL,
  `horario_escola_volta` varchar(10) DEFAULT NULL,
  `valor_mensalidade_aluno` float DEFAULT NULL,
  `vencimento_aluno` int(11) DEFAULT '10',
  `data_inicio_aluno` date DEFAULT NULL,
  `data_fim_aluno` date DEFAULT '2000-01-01',
  `status_aluno` int(2) DEFAULT '1',
  `data_nasc_aluno` date DEFAULT NULL,
  `id_veiculo_aluno` int(11) NOT NULL,
  PRIMARY KEY (`id_aluno`),
  KEY `id_escola_aluno` (`id_escola_aluno`),
  KEY `id_veiculo_aluno` (`id_veiculo_aluno`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;


--
-- Estrutura da tabela `contato`
--

DROP TABLE IF EXISTS `contato`;
CREATE TABLE IF NOT EXISTS `contato` (
  `id_contato` int(11) NOT NULL AUTO_INCREMENT,
  `id_aluno_contato` int(45) NOT NULL,
  `nome_contato` varchar(65) DEFAULT NULL,
  `telefone_contato` varchar(16) DEFAULT NULL,
  `parentesco` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_contato`),
  KEY `id_aluno_contato` (`id_aluno_contato`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=117 ;


--
-- Estrutura da tabela `despesa`
--

DROP TABLE IF EXISTS `despesa`;
CREATE TABLE IF NOT EXISTS `despesa` (
  `id_despesa` int(11) NOT NULL AUTO_INCREMENT,
  `valor_despesa` float DEFAULT NULL,
  `data_despesa` date DEFAULT NULL,
  `descricao_despesa` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id_despesa`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=83 ;


-- --------------------------------------------------------

--
-- Estrutura da tabela `escola`
--

DROP TABLE IF EXISTS `escola`;
CREATE TABLE IF NOT EXISTS `escola` (
  `id_escola` int(11) NOT NULL AUTO_INCREMENT,
  `nome_escola` varchar(30) DEFAULT NULL,
  `endereco_escola` varchar(60) DEFAULT NULL,
  `telefone_escola` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id_escola`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `manutencao`
--

DROP TABLE IF EXISTS `manutencao`;
CREATE TABLE IF NOT EXISTS `manutencao` (
  `id_despesa_manutencao` int(11) NOT NULL,
  `id_veiculo_manutencao` int(11) NOT NULL,
  `problema_manutencao` varchar(120) DEFAULT NULL,
  `km_manutencao` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id_despesa_manutencao`),
  KEY `id_veiculo_manutencao` (`id_veiculo_manutencao`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `manutencao`
--

INSERT INTO `manutencao` (`id_despesa_manutencao`, `id_veiculo_manutencao`, `problema_manutencao`, `km_manutencao`) VALUES
(46, 1, 'Manutenção preventiva', '93000'),
(47, 1, 'Carro sem potência, acelera mas não anda.', '91000'),
(48, 1, 'Manutenção preventiva', '93700'),
(81, 1, 'Desgaste de uso natural', '105000'),
(82, 1, 'Manutenção preventiva', '104000');

-- --------------------------------------------------------

--
-- Estrutura da tabela `motorista`
--

DROP TABLE IF EXISTS `motorista`;
CREATE TABLE IF NOT EXISTS `motorista` (
  `id_motorista` int(11) NOT NULL AUTO_INCREMENT,
  `nome_motorista` varchar(50) DEFAULT NULL,
  `cnh_motorista` varchar(15) DEFAULT NULL,
  `telefone_motorista` varchar(15) DEFAULT NULL,
  `endereco_motorista` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id_motorista`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `id_pagamento` int(11) NOT NULL AUTO_INCREMENT,
  `id_aluno` int(11) NOT NULL,
  `valor_pago` float NOT NULL,
  `mes_referencia` date NOT NULL,
  `data_pagamento` date NOT NULL,
  PRIMARY KEY (`id_pagamento`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=237 ;

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `login_usuario` varchar(50) DEFAULT NULL,
  `senha_usuario` varchar(50) DEFAULT NULL,
  `nome_usuario` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `login_usuario`, `senha_usuario`, `nome_usuario`) VALUES
(1, 'adm@adm.com', 'adm!adm00', 'Administrador');

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id_veiculo` int(11) NOT NULL AUTO_INCREMENT,
  `modelo_veiculo` varchar(45) DEFAULT NULL,
  `cor_veiculo` varchar(15) DEFAULT NULL,
  `numero_veiculo` varchar(5) DEFAULT NULL,
  `placa_veiculo` varchar(10) DEFAULT NULL,
  `num_licenca_veiculo` varchar(15) DEFAULT NULL,
  `capacidade_veiculo` int(11) DEFAULT NULL,
  `status_veiculo` int(2) NOT NULL DEFAULT '1',
  `id_motorista_veiculo` int(11) NOT NULL,
  PRIMARY KEY (`id_veiculo`),
  KEY `id_motorista_veiculo` (`id_motorista_veiculo`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
