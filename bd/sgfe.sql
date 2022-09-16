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
-- Extraindo dados da tabela `abastecimento`
--

INSERT INTO `abastecimento` (`id_despesa_abastecimento`, `id_veiculo_abastecimento`, `km_abastecimento`, `litros_abastecimento`, `posto_abastecimento`) VALUES
(1, 1, 90798, 0, 'Registro de KM'),
(4, 1, 101101, 41.88, 'Posto Barão'),
(5, 1, 90799, 32.13, 'Barão Rui Barbosa'),
(6, 1, 91278, 49.97, 'Barão Rui Barbosa'),
(7, 1, 92112, 41.73, 'Barão Rui Barbosa'),
(8, 1, 92981, 30, 'Barão Rui Barbosa'),
(9, 1, 93420, 31.87, 'Barão Rui Barbosa'),
(10, 1, 93893, 47.18, 'Barão Rui Barbosa'),
(11, 1, 94787, 30.57, 'Barão Rui Barbosa'),
(12, 1, 95113, 52.66, 'Barão Rui Barbosa'),
(13, 1, 96080, 50.78, 'Barão Rui Barbosa'),
(14, 1, 96951, 33.42, 'Barão Rui Barbosa'),
(15, 1, 97512, 41.92, 'Barão Rui Barbosa'),
(16, 1, 98245, 22.22, 'Barão Rui Barbosa'),
(17, 1, 98560, 46.81, 'Barão Rui Barbosa'),
(18, 1, 99301, 44.15, 'Barão Rui Barbosa'),
(19, 1, 100012, 43.93, 'Barão Rui Barbosa'),
(20, 1, 100826, 36.65, 'Barão Rui Barbosa'),
(21, 1, 90978, 50.78, 'Barão Rui Barbosa'),
(22, 1, 91856, 39.79, 'Barão Rui Barbosa'),
(23, 1, 92064, 26.47, 'Barão Rui Barbosa'),
(24, 1, 92550, 41.88, 'Barão Rui Barbosa'),
(25, 1, 93221, 36.5, 'Barão Rui Barbosa'),
(26, 1, 93689, 29.47, 'Barão Rui Barbosa'),
(27, 1, 94025, 22.22, 'Barão Rui Barbosa'),
(28, 1, 95842, 30.3, 'Barão Rui Barbosa'),
(29, 1, 96452, 30.26, 'Barão Rui Barbosa'),
(30, 1, 97963, 21.74, 'Barão Rui Barbosa'),
(31, 1, 99054, 20.31, 'Barão Rui Barbosa'),
(32, 1, 99698, 443.5, 'Barão Rui Barbosa'),
(50, 1, 101549, 39.79, 'Barão Rui Barbosa'),
(52, 1, 101954, 57.86, 'Barão Rui Barbosa'),
(54, 1, 102215, 41.06, 'Barão Rui Barbosa'),
(57, 1, 102563, 13.5, 'Barão Rui Barbosa'),
(59, 1, 102680, 21.5, 'Barão Rui Barbosa'),
(60, 1, 102921, 32.25, 'Barão Rui Barbosa'),
(61, 1, 103231, 23.39, 'Barão Rui Barbosa'),
(62, 1, 103415, 15.08, 'Barão Rui Barbosa'),
(63, 1, 103530, 61.7, 'Barão Rui Barbosa'),
(64, 1, 104021, 9.65, 'Barão Rui Barbosa'),
(65, 1, 104100, 30.14, 'Barão Rui Barbosa'),
(66, 1, 104348, 34.89, 'Barão Rui Barbosa'),
(75, 1, 104662, 30.42, 'Barão Rui Barbosa'),
(76, 1, 104876, 14.63, 'Barão Rui Barbosa'),
(78, 1, 105002, 32, 'Barão Rui Barbosa'),
(79, 1, 105278, 31.98, 'Barão Rui Barbosa'),
(80, 1, 105425, 31.98, 'Barão Rui Barbosa');

-- --------------------------------------------------------

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
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id_aluno`, `nome_aluno`, `telefone_aluno`, `endereco_aluno`, `nome_responsavel`, `cpf_responsavel`, `id_escola_aluno`, `periodo_aluno`, `turma_aluno`, `horario_casa_ida`, `horario_escola_ida`, `horario_casa_volta`, `horario_escola_volta`, `valor_mensalidade_aluno`, `vencimento_aluno`, `data_inicio_aluno`, `data_fim_aluno`, `status_aluno`, `data_nasc_aluno`, `id_veiculo_aluno`) VALUES
(1, 'Bruno Matheus Torbez', '(41) 99842-6145', 'Av Rui Barbosa, 9848', 'Cecilia Popqueviz', '876.798.149-20', 1, 'Tarde', '6', '12:37:00', '12:40:00', '17:30:00', '17:20:00', 200, 10, '2022-01-31', '2000-01-01', 1, '2010-10-28', 1),
(2, 'Lucas Pedro Costa', '(41) 99564-7836', 'Rua Joiville, 672', 'Fabiana Marques', '069.548.793-25', 1, 'Manhã', '5', '06:35:00', '07:05:00', '12:55:00', '11:35:00', 200, 10, '2022-02-07', '2022-02-28', 0, '2013-02-13', 1),
(3, 'Raissa Rocha', '(41) 99786-1181', 'Rua Adolfo Savinski, 500 Casa 2', 'Renato Rocha', '007.364.289-41', 1, 'Manhã', '', '06:45:00', '07:05:00', '12:55:00', '11:35:00', 200, 15, '2022-01-08', '2000-01-01', 1, '2007-02-17', 1),
(4, 'Eduardo Marques da Silva', '(41) 99243-9207', 'Rua Severino Fasolin, 426, Ap203B', 'Raquel Felicia Pinheiro Marques', '011.458.729-99', 2, 'Manhã', '', '06:47:00', '07:00:00', '13:05:00', '11:45:00', 200, 20, '2022-01-30', '2000-01-01', 1, '2006-09-15', 1),
(5, 'Thiago Nogueira dos Santos', '(41) 99907-3746', 'Rua Diomira Moro Zen, 406', 'Nilda Aparecida dos Santos', '022.405.409-07', 1, 'Manhã', '', '06:48:00', '07:05:00', '13:08:00', '11:35:00', 200, 10, '2022-03-10', '2000-01-01', 1, '2007-01-15', 1),
(6, 'Samantha Eloisa de Almeida Padilha', '(41) 99778-0808', 'Rua Antonio Foggiatto, 91', 'Wilian Stefan Padilha', '023.706.959-86', 2, 'Tarde', '', '12:11:00', '12:45:00', '18:20:00', '17:25:00', 220, 20, '2022-02-02', '2000-01-01', 1, '2010-11-10', 1),
(7, 'Rafaella Pereira Joara', '(41) 99934-4100', 'Rua Joaqui Ferreira da Dores, 85', 'Bruna Suelen M. Pereira', '053.375.529-70', 2, 'Tarde', '7°E', '12:23:00', '12:45:00', '18:07:00', '17:25:00', 200, 10, '2022-01-29', '2000-01-01', 1, '2010-01-03', 1),
(8, 'Nickolas Renato de Lima Oliveira', '(41) 99716-3218', 'Rua Adelaide Maria Pissaia, 18', 'Meire Lee Silva Cime', '058.593.179-80', 1, 'Manhã', '9ºB', '', '07:05:00', '', '11:35:00', 240, 20, '2022-03-21', '2022-07-07', 0, '2007-01-19', 1),
(9, 'Nicolas A. C. Igerski', '(41) 99596-9845', 'Rua Silvano Moreski, 31', 'Ariane Cristina Alves Da Cruz', '024.299.819-47', 1, 'Tarde', '', '12:38:00', '12:40:00', '17:32:00', '17:20:00', 180, 10, '2022-01-04', '2000-01-01', 1, '2008-12-22', 1),
(10, 'Nathaly F. Erdmann', '(41) 99598-0830', 'Rua Francisco Dal''negro, 4177', 'Lidiane Cordeiro', '049.322.689-39', 1, 'Manhã', '2', '06:40:00', '07:05:00', '12:03:00', '11:35:00', 240, 10, '2022-04-12', '2000-01-01', 1, '2005-05-13', 1),
(11, 'Mateus Alberto Feiber', '(41) 98473-1808', 'Rua Pedro Plantes dos Anjos, 200', 'Silvane Feiben', '054.594.319-13', 1, 'Tarde', '7', '12:27:00', '12:40:00', '17:57:00', '17:20:00', 200, 20, '2022-03-14', '2000-01-01', 1, '2009-01-21', 1),
(12, 'Maria Luiza da Silva', '(41) 99811-6159', 'Rua Padre José Cruz, 74', 'Ivete Teresinha Pedroso', '036.527.029-64', 1, 'Manhã', '1ºA', '06:52:00', '07:05:00', '13:00:00', '11:35:00', 240, 10, '2022-05-12', '2000-01-01', 1, '2006-10-09', 1),
(13, 'Maria Eduarda Dantas', '(41) 99517-9923', 'Rua Antonio Kuss, 260 BL E AP101', 'Rosangela Luzia Bafa', '041.593.099-50', 1, 'Manhã', '3ºB', '06:50:00', '07:05:00', '11:55:00', '11:35:00', 200, 10, '2022-03-13', '2000-01-01', 1, '2005-08-02', 1),
(14, 'Luan Adil de Lima M. Silva', '(41) 98474-4885', 'Rua Guaira, 1', 'Patricia de Lima Silva', '031.178.389-96', 4, 'Noite', '1ºD', '', '', '', '', 180, 10, '2022-02-10', '2022-07-06', 0, '2005-10-25', 1),
(15, 'Kamily Fernandes Rezende', '(41) 99818-0152', 'Rua Hugo Zen, 300', 'Rosilene Fernandes Candido', '009.483.349-95', 2, 'Manhã', '2ºA', '06:25:00', '07:00:00', '12:08:00', '11:45:00', 200, 20, '2022-02-14', '2022-08-29', 0, '2005-12-06', 1),
(16, 'Julia Sohn Tozo', '(41) 99809-3407', 'Rua Antonio Zaramella, 1941', 'Daniele Sohn', '028.029.197-51', 1, 'Manhã', '9ºC', '06:37:00', '07:05:00', '12:07:00', '11:35:00', 240, 20, '2022-06-24', '2000-01-01', 1, '2008-05-19', 1),
(17, 'Isabella Flores de Freitas', '(41) 99964-2084', 'Rua Adolfo Savinsk, 566 AP12 BL 7', 'Daiane Flores da Rosa', '056.078.719-75', 1, 'Tarde', '6ºD', '12:25:00', '12:40:00', '18:05:00', '17:20:00', 240, 10, '2022-06-14', '2000-01-01', 1, '2011-03-28', 1),
(18, 'Geovanna Pereira dos Santos', '(41) 99662-6615', 'Rua Hugo Zen, 78', 'Renata Pereira dos Santos', '010.953.659-24', 3, 'Tarde', '9', '', '', '', '', 200, 10, '2022-01-29', '2022-07-06', 0, '2006-12-28', 1),
(19, 'Gabriela Pereira Dias', '(41) 99662-6615', 'Rua Hugo Zen, 78', 'Renata Pereira dos Santos', '010.953.659-24', 3, 'Tarde', '7', '', '', '', '', 200, 10, '2022-01-29', '2022-07-06', 0, '2010-07-04', 1),
(20, 'Gabriel da Silva Rosário', '(41) 99948-9232', 'Rua Silvano Mareschi, 67', 'Maria Rosilene da Silva Rosário', '427.600.622-87', 3, 'Trade', '8ºC', '', '', '', '', 200, 10, '2022-03-10', '2022-07-06', 0, '2009-07-23', 1),
(21, 'Felipe Gabriel Alesio Heinkel', '(41) 99526-8813', 'Rua José Gomes de Almeida, 80', 'Andréia Alesio Ramalho', '066.429.435-10', 5, 'Tarde', '', '12:10:00', '12:33:00', '18:18:00', '17:10:00', 240, 20, '2022-05-18', '2000-01-01', 1, '2010-10-08', 1),
(22, 'Ernani Gabryel Linzmeyer', '(41) 98864-3475', 'Rua Antonio Kuss, 260', 'Everli Gabriel de Fatima Cristof Linzermeryer', '923.017.899-34', 1, 'Tarde', '8ºC', '', '', '18:00:00', '17:20:00', 180, 15, '2022-02-05', '2000-01-01', 1, '2009-01-15', 1),
(23, 'Erik Bregeschi', '(41) 99112-6730', 'Rua Maria Max, 155', 'Vivian Regina Bregeschi', '047.663.519-59', 1, 'Tarde', '7ºB', '', '', '18:03:00', '17:20:00', 180, 10, '2022-03-16', '2000-01-01', 1, '2010-12-28', 1),
(24, 'Emily Ávilla de Lima', '(41) 98496-1081', 'Rua Monteiro Lobato, 233', 'Michele Ávilla dos Santos', '006.365.599-39', 1, 'Manhã', '2ºA', '06:34:00', '07:05:00', '12:05:00', '11:35:00', 200, 10, '2022-04-11', '2000-01-01', 1, '2006-02-13', 1),
(25, 'Eduardo Mariguela Gembaroski', '(41) 99784-1319', 'Rua Inezita Resende Ribeiro, 456', 'Camila Garcia Mariguela', '062.613.899-00', 3, 'Tarde', '6', '12:20:00', '12:45:00', '18:10:00', '17:25:00', 200, 10, '2022-01-30', '2000-01-01', 1, '2011-11-13', 1),
(26, 'Daniel Sammy Santos Ono', '(41) 99596-0932', 'Rua Desembargador James Maceio, 1118', 'Raquel dos Santos', '215.281.278-62', 2, 'Manhã', '2ºA', '06:42:00', '07:00:00', '11:58:00', '11:45:00', 220, 10, '2022-02-07', '2000-01-01', 1, '2005-02-01', 1),
(27, 'Davi Correa Alexandre', '(41) 99774-8002', 'Rua Deputado João Leopoldo Jacomel, 690', 'Jéssica Daiane Jacob Corrêa', '388.628.288-02', 1, 'Tarde', '6ºB', '12:30:00', '12:40:00', '17:55:00', '17:20:00', 220, 10, '2022-02-06', '2000-01-01', 1, '2010-10-16', 1),
(28, 'Carlos Miguel Souza Wetmann', '(41) 99741-4438', 'Rua Moacir Tomelin, 40 SB2', 'Claudilene', '022.666.309-46', 1, 'Tarde', '8ºC', '12:16:00', '12:40:00', '18:14:00', '17:20:00', 200, 20, '2022-03-22', '2000-01-01', 1, '2009-09-05', 1),
(29, 'Allan Ávilla de Lima', '(41) 98496-1081', 'Rua Monteiro Lobato, 233', 'Michele Ávilla dos Santos', '006.365.599-39', 1, 'Tarde', '8ºB', '12:14:00', '12:40:00', '18:12:00', '17:20:00', 200, 10, '2022-04-11', '2000-01-01', 1, '2009-02-09', 1),
(30, 'Ágatha Martins Barilla', '(41) 99915-7566', 'Rua Desembargador James Portugal, 965', 'Jessi Aparecida Martins', '834.387.269-04', 1, 'Manhã', '2ºC', '06:43:00', '07:05:00', '11:57:00', '11:35:00', 200, 10, '2022-07-15', '2000-01-01', 1, '2004-10-25', 1),
(31, 'Milena Monteiro', '(41) 97871-2337', 'Alameda Marcílio Dias, 79', 'Catarina Nascimento', '643.725.449-83', 1, 'Manhã', '', '06:30:00', '07:05:00', '12:00:00', '11:35:00', 240, 10, '2022-06-07', '2022-09-13', 0, '2006-07-05', 1),
(32, 'Renato Freitas', '(41) 99761-3439', 'Jardim Aquidaban, 18', 'Ana Moura Freitas', '126.333.181-50', 1, 'Manhã', '', '06:55:00', '07:05:00', '11:50:00', '11:35:00', 240, 10, '2022-04-11', '2000-01-01', 1, '2007-09-10', 1),
(33, 'Helena ', '(47) 99562-0009', 'Av Rui Barbosa, 233', 'Luiza', '456.373.679-11', 2, 'Manhã', '', '', '', '12:39:00', '11:45:00', 150, 10, '2022-07-04', '2000-01-01', 1, '2010-10-15', 1),
(34, 'Helena T.', '(41) 97871-2337', 'Rua Hugo Zen, 300', 'Luiza', '643.725.449-83', 3, 'Tarde', '', '12:08:00', '12:45:00', '18:17:00', '17:25:00', 220, 10, '2022-02-01', '2000-01-01', 1, '2009-03-10', 1),
(35, 'Samuel R. Soares', '(41) 97871-2337', 'Alameda Marcílio Dias, 79', 'Paula', '643.725.449-83', 1, 'Tarde', '', '12:09:00', '12:40:00', '18:18:00', '17:20:00', 200, 10, '2022-03-10', '2000-01-01', 1, '2008-08-08', 1),
(36, 'Yago Eduarto', '(41) 99761-3439', 'Rua Alferes João Bortolotti, 128', 'Felipe Rezende', '019.273.126-89', 1, 'Tarde', '', '12:12:00', '12:40:00', '18:16:00', '17:20:00', 200, 10, '2022-01-05', '2000-01-01', 1, '2007-02-13', 1),
(37, 'Emanuela', '(41) 99856-8452', 'Rua James Portugal, 7452', 'Susan', '', 5, 'Tarde', '', '11:59:00', '12:31:00', '17:58:00', '17:05:00', 240, 10, '2022-08-01', '2000-01-01', 1, '2012-04-15', 1),
(38, 'Daniel Antonio', '(41) 98874-5263', 'Rua Hugo Zen, 300', 'José', '', 1, 'Tarde', '', '12:08:00', '12:40:00', '18:17:00', '17:20:00', 240, 10, '2022-08-01', '2000-01-01', 1, '2010-06-09', 1),
(39, 'Mariana Lopes', '(41) 99856-365', 'Rua Antonio Kuss, 200', 'Joana', '', 2, 'Tarde', '', '11:52:00', '12:35:00', '17:59:00', '17:25:00', 240, 10, '2022-08-01', '2000-01-01', 1, '2008-05-12', 1);

-- --------------------------------------------------------

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
-- Extraindo dados da tabela `contato`
--

INSERT INTO `contato` (`id_contato`, `id_aluno_contato`, `nome_contato`, `telefone_contato`, `parentesco`) VALUES
(70, 1, 'Cecilia', '(41) 99842-6145', 'Mãe'),
(69, 1, 'Darci', '(41) 99810-4620', 'Pai'),
(5, 2, 'Fabiana', '(41) 99564-7836', 'Mãe'),
(103, 3, 'Renato Rocha', '(41) 99786-1181', 'Pai'),
(79, 4, 'Junior', '(41) 99173-9950', 'Pai'),
(78, 4, 'Raquel', '(41) 99243-9207', 'Mãe'),
(110, 5, 'Adriano', '(41) 99515-2620', 'Pai'),
(109, 5, 'Nilda', '(41) 99907-3746', 'Mãe'),
(107, 6, 'Gisele', '(41) 99778-0808', 'Mãe'),
(106, 6, 'William', '(41) 98833-8371', 'Pai'),
(102, 7, 'Bruna', '(41) 99934-4100', 'Mãe'),
(101, 7, 'João', '(41) 99676-0115', 'Pai'),
(18, 8, 'Meire', '(41) 99716-3218', 'Mãe'),
(100, 9, 'Ariane', '(41) 99596-9845', 'Mãe'),
(99, 10, 'Gilberto', '(41) 99550-1740', 'Pai'),
(98, 10, 'Lidiane', '(41) 99598-0830', 'Mãe'),
(96, 11, 'Silvana', '(41) 98473-1808', 'Mãe'),
(95, 12, 'Ivete', '(41) 99811-6159', 'Mãe'),
(94, 13, 'Rosangela', '(41) 99517-9923', 'Mãe'),
(26, 14, 'Patricia', '(41) 98474-4885', 'Mãe'),
(92, 15, 'Rosilene', '(41) 99818-0152', 'Mãe'),
(91, 16, 'Antonio', '(41) 99634-8065', 'Pai'),
(90, 16, 'Daniele', '(41) 99809-3407', 'Mãe'),
(114, 17, 'Daiane', '(41) 99964-2087', 'Mãe'),
(113, 17, 'Rafael', '(41) 99803-9611', 'Pai'),
(33, 18, 'Renata', '(41) 99662-6615', 'Mãe'),
(34, 18, 'Andre', '(41) 98888-8751', 'Pai'),
(35, 19, 'Renata Pereira dos Santos', '(41) 99662-6615', 'Mãe'),
(36, 20, 'Rose', '(41) 99948-9232', 'Mãe'),
(85, 21, 'Andréia', '(41) 99526-8813', 'Mãe'),
(84, 22, 'Everli', '(41) 98864-3475', 'Mãe'),
(83, 22, 'Sidnei', '(41) 98886-5125', 'Pai'),
(82, 23, 'Augusto', '(41) 99226-6997', 'Pai'),
(81, 23, 'Vivian', '(41) 99112-6730', 'Mãe'),
(80, 24, 'Michele', '(41) 98496-1081', 'Mãe'),
(77, 25, 'Camila', '(41) 99184-1319', 'Mãe'),
(76, 25, 'Conceição', '(41) 99208-6412', 'Avó'),
(73, 26, 'Raquel', '(41) 99596-0932', 'Mãe'),
(75, 27, 'Douglas', '(41) 99146-8827', 'Pai'),
(74, 27, 'Jéssica', '(41) 99774-8002', 'Mãe'),
(72, 28, 'Claudilene', '(41) 99741-4438', 'Mãe'),
(71, 28, 'Emerson', '(41) 99932-8703', 'Pai'),
(68, 29, 'Michele', '(41) 98496-1081', 'Mãe'),
(67, 30, 'Jessi', '(41) 99915-7566', 'Mãe'),
(66, 30, 'Marcelo', '(41) 99918-1626', 'Pai'),
(97, 31, 'Catarina Nascimento', '(41) 97118-1308', 'Mãe'),
(105, 32, 'Ana', '(41) 99562-0009', 'Mãe'),
(86, 33, 'Luiza', '(41) 99765-1298', 'Mãe'),
(87, 34, 'Luiza', '(41) 97118-1308', 'Mãe'),
(108, 35, 'Paula', '(41) 97118-1308', 'Mãe'),
(111, 36, 'Felipe', '(41) 99765-1298', 'Pai'),
(93, 15, 'Erik', '(41) 99174-0026', 'Pai'),
(104, 3, 'Gisele', '(41) 99268-4484', 'Mãe'),
(112, 37, 'Susan', '(41) 99856-8452', 'Mãe'),
(115, 38, 'José ', '(41) 99685-4137', 'Pai'),
(116, 39, 'Joana', '(41) 99125-4144', 'Mãe');

-- --------------------------------------------------------

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

--
-- Extraindo dados da tabela `despesa`
--

INSERT INTO `despesa` (`id_despesa`, `valor_despesa`, `data_despesa`, `descricao_despesa`) VALUES
(1, 0, '2022-01-01', 'Registro da Kilometragem de veículo, NÃO EXCLUIR!'),
(4, 313.7, '2022-06-29', 'Abastecimento'),
(5, 169.15, '2022-01-03', 'Abastecimento'),
(6, 334.72, '2022-01-20', 'Abastecimento'),
(7, 297.27, '2022-02-16', 'Abastecimento'),
(8, 190.22, '2022-03-02', 'Abastecimento'),
(9, 199.48, '2022-03-15', 'Abastecimento'),
(10, 347.05, '2022-03-30', 'Abastecimento'),
(11, 201.14, '2022-04-08', 'Abastecimento'),
(12, 363.15, '2022-04-14', 'Abastecimento'),
(13, 218.94, '2022-04-28', 'Abastecimento'),
(14, 218.94, '2022-05-11', 'Abastecimento'),
(15, 298.22, '2022-05-16', 'Abastecimento'),
(16, 151.15, '2022-05-26', 'Abastecimento'),
(17, 306.04, '2022-05-31', 'Abastecimento'),
(18, 305.57, '2022-06-09', 'Abastecimento'),
(19, 299.65, '2022-06-17', 'Abastecimento'),
(20, 272.2, '2022-06-25', 'Abastecimento'),
(21, 356.38, '2022-01-12', 'Abastecimento'),
(22, 278.03, '2022-02-01', 'Abastecimento'),
(23, 150, '2022-02-08', 'Abastecimento'),
(24, 303.71, '2022-02-28', 'Abastecimento'),
(25, 264.5, '2022-03-10', 'Abastecimento'),
(26, 193.05, '2022-03-22', 'Abastecimento'),
(27, 150, '2022-04-04', 'Abastecimento'),
(28, 196.36, '2022-04-21', 'Abastecimento'),
(29, 200.97, '2022-05-05', 'Abastecimento'),
(30, 150, '2022-05-20', 'Abastecimento'),
(31, 139.97, '2022-06-04', 'Abastecimento'),
(32, 305, '2022-06-13', 'Abastecimento'),
(33, 107.16, '2022-01-03', 'Seguro Van 1/12'),
(34, 107.16, '2022-02-02', 'Seguro Van 2/12'),
(35, 107.16, '2022-03-02', 'Seguro Van 3/12'),
(36, 107.16, '2022-04-01', 'Seguro Van 4/12'),
(37, 107.16, '2022-05-02', 'Seguro Van 5/12'),
(38, 107.16, '2022-06-01', 'Seguro Van 6/12'),
(39, 107.16, '2022-07-01', 'Seguro Van 7/12'),
(40, 133.32, '2022-01-14', 'INSS Jan/2022'),
(41, 133.32, '2022-02-15', 'INSS Fev/2022'),
(42, 133.32, '2022-03-15', 'INSS Mar/2022'),
(43, 133.32, '2022-04-15', 'INSS Abr/2022'),
(44, 133.32, '2022-05-13', 'INSS Mai/2022'),
(45, 133.32, '2022-06-15', 'INSS Jun/2022'),
(46, 650, '2022-03-07', 'Troca de óleo, filtro de óleo, filtro de ar, filtro de combustível.'),
(47, 3000, '2022-01-05', 'Troca da polia do motor, correia da polia e mossinética.'),
(48, 1223, '2022-03-23', 'Troca de 2 pneus, montagem e balanceamento.'),
(49, 600, '2022-02-03', 'Documentação para regularização na prefeitura.'),
(50, 298.03, '2022-07-03', 'Abastecimento'),
(52, 380.38, '2022-07-06', 'Abastecimento'),
(53, 440, '2022-07-01', 'Monitora escolar'),
(54, 300, '2022-08-01', 'Abastecimento'),
(55, 107, '2022-08-02', 'Seguro'),
(56, 250, '2022-08-02', 'Impostos e taxas.'),
(57, 96, '2022-08-04', 'Abastecimento'),
(58, 102, '2022-08-03', 'Detran - Taxa da Licença do transporte escolar'),
(59, 150, '2022-08-05', 'Abastecimento'),
(60, 220, '2022-08-07', 'Abastecimento'),
(61, 155, '2022-08-11', 'Abastecimento'),
(62, 100, '2022-08-13', 'Abastecimento'),
(63, 400, '2022-08-15', 'Abastecimento'),
(64, 60, '2022-08-20', 'Abastecimento'),
(65, 200, '2022-08-22', 'Abastecimento'),
(66, 230, '2022-08-25', 'Abastecimento'),
(67, 600, '2022-08-01', 'Monitora'),
(68, 600, '2022-02-01', 'Monitora'),
(69, 600, '2022-03-01', 'Monitora'),
(70, 600, '2022-04-01', 'Monitora'),
(71, 600, '2022-05-01', 'Monitora'),
(72, 600, '2022-06-01', 'Monitora'),
(73, 600, '2022-07-01', 'Monitora'),
(74, 600, '2022-09-01', 'Monitora'),
(75, 195, '2022-08-28', 'Abastecimento.'),
(76, 100, '2022-08-31', 'Abastecimento.'),
(78, 207, '2022-09-01', 'Abastecimento.'),
(79, 200, '2022-09-06', 'Abastecimento.'),
(80, 200, '2022-09-12', 'Abastecimento.'),
(81, 90, '2022-09-13', 'Paletas do limpador de parabrisa'),
(82, 450, '2022-09-10', 'Troca de óleo');

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

--
-- Extraindo dados da tabela `escola`
--

INSERT INTO `escola` (`id_escola`, `nome_escola`, `endereco_escola`, `telefone_escola`) VALUES
(1, 'Arnaldo Jansen', 'R. Scharfenberg de Quadros, 6', '(41) 33827-754'),
(2, 'Costa Viana', 'R. Paulino de Siqueira Cortês, 2685', '(41) 32835-583'),
(3, 'Silveira da Motta', 'Largo Ver. Segismundo Salata, 1123', '(41) 3382-1241'),
(4, 'Unidade Polo', 'R. Joinville, 2770', '(41) 3382-1442'),
(5, 'Pedro Moro', 'R. Joinville, 2678', '(41) 3383-1105');

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

--
-- Extraindo dados da tabela `motorista`
--

INSERT INTO `motorista` (`id_motorista`, `nome_motorista`, `cnh_motorista`, `telefone_motorista`, `endereco_motorista`) VALUES
(1, 'Julio Cesar Mariotto', '123456789', '(41) 99226-8697', 'Rua Alferes João Bortolotti, 128 - Colonia Rio Grande');

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
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`id_pagamento`, `id_aluno`, `valor_pago`, `mes_referencia`, `data_pagamento`) VALUES
(1, 1, 180, '2022-01-01', '2022-01-31'),
(2, 1, 200, '2022-02-01', '2022-02-10'),
(3, 2, 200, '2022-02-01', '2022-02-07'),
(4, 3, 200, '2022-01-01', '2022-01-08'),
(5, 3, 200, '2022-02-01', '2022-02-15'),
(6, 3, 200, '2022-03-01', '2022-03-15'),
(7, 3, 200, '2022-04-01', '2022-04-15'),
(8, 3, 200, '2022-05-01', '2022-05-13'),
(9, 3, 200, '2022-06-01', '2022-06-15'),
(10, 4, 200, '2022-01-01', '2022-01-30'),
(14, 4, 200, '2022-02-01', '2022-02-20'),
(12, 4, 200, '2022-03-01', '2022-03-18'),
(13, 4, 200, '2022-04-01', '2022-04-20'),
(15, 4, 200, '2022-05-01', '2022-05-20'),
(16, 4, 200, '2022-06-01', '2022-06-20'),
(17, 5, 200, '2022-03-01', '2022-03-10'),
(18, 5, 200, '2022-04-01', '2022-04-12'),
(19, 5, 200, '2022-05-01', '2022-05-10'),
(21, 5, 200, '2022-06-01', '2022-06-10'),
(22, 6, 200, '2022-02-01', '2022-02-20'),
(23, 6, 200, '2022-03-01', '2022-03-20'),
(24, 6, 220, '2022-04-01', '2022-04-20'),
(25, 6, 220, '2022-05-01', '2022-05-20'),
(26, 6, 220, '2022-06-01', '2022-06-20'),
(27, 7, 200, '2022-02-01', '2022-01-29'),
(28, 7, 200, '2022-03-01', '2022-03-10'),
(29, 7, 200, '2022-04-01', '2022-04-10'),
(30, 7, 200, '2022-05-01', '2022-05-10'),
(31, 7, 200, '2022-06-01', '2022-06-10'),
(32, 8, 240, '2022-03-01', '2022-03-21'),
(33, 8, 240, '2022-04-01', '2022-04-20'),
(34, 8, 240, '2022-05-01', '2022-05-20'),
(35, 8, 240, '2022-06-01', '2022-06-20'),
(43, 9, 180, '2022-04-01', '2022-04-10'),
(42, 9, 180, '2022-03-01', '2022-03-10'),
(40, 9, 180, '2022-01-01', '2022-01-04'),
(41, 9, 180, '2022-02-01', '2022-02-10'),
(45, 9, 180, '2022-05-01', '2022-05-10'),
(46, 9, 180, '2022-06-01', '2022-06-10'),
(47, 10, 240, '2022-04-01', '2022-04-12'),
(48, 10, 240, '2022-05-01', '2022-05-10'),
(49, 10, 240, '2022-06-01', '2022-06-10'),
(50, 11, 200, '2022-03-01', '2022-03-14'),
(51, 11, 200, '2022-04-01', '2022-04-20'),
(52, 11, 200, '2022-05-01', '2022-05-20'),
(88, 11, 200, '2022-06-01', '2022-06-20'),
(54, 12, 240, '2022-05-01', '2022-05-12'),
(55, 12, 240, '2022-06-01', '2022-06-12'),
(56, 13, 200, '2022-03-01', '2022-03-13'),
(57, 13, 200, '2022-04-01', '2022-04-10'),
(58, 13, 200, '2022-05-01', '2022-05-10'),
(59, 13, 200, '2022-06-01', '2022-06-10'),
(60, 14, 180, '2022-02-01', '2022-02-10'),
(61, 14, 180, '2022-03-01', '2022-03-10'),
(62, 14, 180, '2022-04-01', '2022-04-10'),
(63, 14, 180, '2022-05-01', '2022-05-10'),
(64, 14, 180, '2022-06-01', '2022-06-10'),
(65, 15, 200, '2022-02-01', '2022-02-14'),
(66, 15, 200, '2022-03-01', '2022-03-20'),
(67, 15, 200, '2022-04-01', '2022-04-20'),
(68, 15, 200, '2022-05-01', '2022-05-20'),
(69, 15, 200, '2022-06-01', '2022-06-20'),
(70, 16, 240, '2022-06-01', '2022-06-24'),
(71, 17, 240, '2022-06-01', '2022-06-14'),
(72, 18, 200, '2022-02-01', '2022-01-29'),
(73, 18, 200, '2022-03-01', '2022-03-10'),
(74, 18, 200, '2022-04-01', '2022-04-10'),
(75, 18, 200, '2022-05-01', '2022-05-10'),
(76, 18, 200, '2022-06-01', '2022-06-10'),
(77, 19, 200, '2022-02-01', '2022-01-29'),
(78, 19, 200, '2022-03-01', '2022-03-10'),
(79, 19, 200, '2022-04-01', '2022-04-10'),
(80, 19, 200, '2022-05-01', '2022-05-10'),
(81, 19, 200, '2022-06-01', '2022-06-10'),
(82, 20, 200, '2022-03-01', '2022-03-10'),
(83, 20, 200, '2022-04-01', '2022-04-10'),
(84, 20, 200, '2022-05-01', '2022-05-10'),
(85, 20, 200, '2022-06-01', '2022-06-10'),
(86, 21, 240, '2022-05-01', '2022-05-18'),
(87, 21, 240, '2022-06-01', '2022-06-20'),
(89, 22, 180, '2022-02-01', '2022-02-05'),
(90, 22, 180, '2022-03-01', '2022-03-15'),
(91, 22, 180, '2022-04-01', '2022-04-15'),
(92, 22, 180, '2022-05-01', '2022-05-15'),
(93, 22, 180, '2022-06-01', '2022-06-15'),
(94, 23, 180, '2022-03-01', '2022-03-16'),
(95, 23, 180, '2022-04-01', '2022-04-10'),
(96, 23, 180, '2022-05-01', '2022-05-10'),
(97, 23, 180, '2022-06-01', '2022-06-10'),
(98, 24, 200, '2022-04-01', '2022-04-11'),
(99, 24, 200, '2022-05-01', '2022-05-10'),
(100, 24, 200, '2022-06-01', '2022-06-10'),
(101, 25, 200, '2022-02-01', '2022-01-30'),
(102, 25, 200, '2022-03-01', '2022-03-10'),
(103, 25, 200, '2022-04-01', '2022-04-10'),
(104, 25, 200, '2022-05-01', '2022-05-10'),
(105, 25, 200, '2022-06-01', '2022-06-10'),
(106, 26, 220, '2022-02-01', '2022-02-07'),
(107, 26, 220, '2022-03-01', '2022-03-10'),
(108, 26, 220, '2022-04-01', '2022-04-10'),
(109, 26, 220, '2022-05-01', '2022-05-10'),
(110, 26, 220, '2022-06-01', '2022-06-10'),
(111, 27, 220, '2022-02-01', '0002-02-06'),
(112, 27, 220, '2022-03-01', '2022-03-10'),
(113, 27, 220, '2022-04-01', '2022-04-10'),
(114, 27, 220, '2022-05-01', '2022-05-10'),
(115, 27, 220, '2022-06-01', '2022-06-11'),
(116, 28, 200, '2022-03-01', '2022-03-20'),
(117, 28, 200, '2022-04-01', '2022-04-20'),
(118, 28, 200, '2022-05-01', '2022-05-20'),
(136, 28, 200, '2022-06-01', '2022-06-20'),
(120, 29, 200, '2022-04-01', '2022-04-11'),
(121, 29, 200, '2022-05-01', '2022-05-10'),
(122, 29, 200, '2022-06-01', '2022-06-10'),
(123, 30, 200, '2022-05-01', '2022-05-01'),
(124, 30, 200, '2022-06-01', '2022-06-10'),
(125, 31, 240, '2022-06-01', '2022-06-07'),
(127, 31, 240, '2022-07-01', '2022-07-04'),
(128, 32, 240, '2022-04-01', '2022-04-11'),
(129, 32, 240, '2022-05-01', '2022-05-10'),
(130, 32, 240, '2022-06-01', '2022-06-10'),
(131, 34, 220, '2022-02-01', '2022-02-01'),
(132, 34, 220, '2022-03-01', '2022-03-10'),
(133, 34, 220, '2022-04-01', '2022-04-10'),
(134, 34, 220, '2022-05-01', '2022-05-10'),
(135, 34, 220, '2022-06-01', '2022-06-10'),
(137, 35, 100, '2022-03-01', '2022-03-10'),
(138, 35, 200, '2022-04-01', '2022-04-10'),
(139, 35, 200, '2022-05-01', '2022-05-10'),
(140, 35, 200, '2022-06-01', '2022-06-10'),
(141, 36, 200, '2022-01-01', '2022-01-05'),
(142, 36, 200, '2022-02-01', '2022-02-10'),
(143, 36, 200, '2022-03-01', '2022-03-10'),
(144, 36, 200, '2022-04-01', '2022-04-10'),
(145, 36, 200, '2022-05-01', '2022-05-10'),
(146, 36, 200, '2022-06-01', '2022-06-10'),
(147, 7, 200, '2022-07-01', '2022-07-07'),
(148, 27, 220, '2022-07-01', '2022-07-07'),
(149, 9, 200, '2022-07-01', '2022-07-07'),
(150, 23, 180, '2022-07-01', '2022-07-07'),
(151, 16, 240, '2022-08-01', '2022-08-10'),
(152, 24, 200, '2022-08-01', '2022-08-10'),
(153, 10, 240, '2022-08-01', '2022-08-10'),
(154, 26, 220, '2022-08-01', '2022-08-10'),
(155, 30, 220, '2022-08-01', '2022-08-10'),
(156, 3, 200, '2022-08-01', '2022-08-15'),
(157, 4, 200, '2022-08-01', '2022-08-20'),
(158, 5, 200, '2022-08-01', '2022-08-10'),
(159, 13, 200, '2022-08-01', '2022-08-10'),
(160, 12, 240, '2022-08-01', '2022-08-10'),
(161, 32, 240, '2022-08-01', '2022-08-10'),
(162, 33, 150, '2022-08-01', '2022-08-20'),
(163, 37, 240, '2022-08-01', '2022-08-10'),
(164, 35, 200, '2022-08-01', '2022-08-10'),
(165, 28, 200, '2022-08-01', '2022-08-10'),
(166, 25, 200, '2022-08-01', '2022-08-10'),
(167, 7, 200, '2022-08-01', '2022-08-10'),
(168, 9, 200, '2022-08-01', '2022-08-10'),
(169, 27, 220, '2022-08-01', '2022-08-10'),
(170, 22, 180, '2022-08-01', '2022-08-15'),
(171, 23, 180, '2022-08-01', '2022-08-10'),
(172, 17, 240, '2022-08-01', '2022-08-10'),
(173, 38, 240, '2022-08-01', '2022-08-10'),
(174, 11, 200, '2022-08-01', '2022-08-20'),
(175, 1, 200, '2022-08-01', '2022-08-30'),
(176, 34, 220, '2022-08-01', '2022-08-20'),
(177, 6, 220, '2022-08-01', '2022-08-20'),
(178, 29, 200, '2022-08-01', '2022-08-10'),
(179, 21, 220, '2022-08-01', '2022-08-10'),
(180, 39, 240, '2022-08-01', '2022-08-10'),
(181, 39, 240, '2022-09-01', '2022-09-10'),
(182, 21, 220, '2022-09-01', '2022-09-10'),
(183, 29, 200, '2022-09-01', '2022-09-10'),
(184, 6, 220, '2022-09-01', '2022-09-20'),
(185, 34, 220, '2022-09-01', '2022-09-20'),
(187, 11, 200, '2022-09-01', '2022-09-20'),
(188, 38, 240, '2022-09-01', '2022-09-10'),
(189, 17, 240, '2022-09-01', '2022-09-10'),
(190, 23, 180, '2022-09-01', '2022-09-10'),
(191, 22, 180, '2022-09-01', '2022-09-15'),
(192, 27, 220, '2022-09-01', '2022-09-10'),
(193, 9, 200, '2022-09-01', '2022-09-10'),
(194, 7, 200, '2022-09-01', '2022-09-10'),
(195, 25, 200, '2022-09-01', '2022-09-10'),
(196, 28, 200, '2022-09-01', '2022-09-10'),
(197, 35, 200, '2022-09-01', '2022-09-10'),
(198, 37, 240, '2022-09-01', '2022-09-10'),
(199, 33, 150, '2022-09-01', '2022-09-20'),
(200, 32, 240, '2022-09-01', '2022-09-10'),
(201, 12, 240, '2022-09-01', '2022-09-10'),
(202, 13, 200, '2022-09-01', '2022-09-10'),
(203, 5, 200, '2022-09-01', '2022-09-10'),
(204, 4, 200, '2022-09-01', '2022-09-20'),
(205, 3, 200, '2022-09-01', '2022-09-15'),
(233, 36, 200, '2022-07-01', '2022-07-10'),
(207, 26, 220, '2022-09-01', '2022-09-10'),
(208, 10, 240, '2022-09-01', '2022-09-10'),
(209, 24, 200, '2022-09-01', '2022-09-10'),
(210, 16, 240, '2022-09-01', '2022-09-10'),
(211, 39, 240, '2022-07-01', '2022-07-10'),
(212, 21, 220, '2022-07-01', '2022-07-10'),
(213, 29, 200, '2022-07-01', '2022-07-10'),
(214, 6, 220, '2022-07-01', '2022-07-20'),
(215, 34, 220, '2022-07-01', '2022-07-20'),
(216, 1, 200, '2022-07-01', '2022-07-30'),
(217, 11, 200, '2022-07-01', '2022-07-20'),
(218, 38, 240, '2022-07-01', '2022-07-10'),
(219, 17, 240, '2022-07-01', '2022-07-10'),
(220, 33, 150, '2022-07-01', '2022-07-20'),
(221, 32, 240, '2022-07-01', '2022-07-10'),
(222, 12, 240, '2022-07-01', '2022-07-10'),
(223, 13, 200, '2022-07-01', '2022-07-10'),
(224, 5, 200, '2022-07-01', '2022-07-10'),
(225, 4, 200, '2022-07-01', '2022-07-20'),
(226, 3, 200, '2022-07-01', '2022-07-15'),
(227, 30, 220, '2022-07-01', '2022-07-10'),
(228, 26, 220, '2022-07-01', '2022-07-10'),
(229, 10, 240, '2022-07-01', '2022-07-10'),
(230, 24, 200, '2022-07-01', '2022-07-10'),
(231, 16, 240, '2022-07-01', '2022-07-10'),
(232, 30, 200, '2022-09-01', '2022-09-10'),
(234, 36, 200, '2022-08-01', '2022-08-10'),
(235, 36, 200, '2022-09-01', '2022-09-10');

-- --------------------------------------------------------

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

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id_veiculo`, `modelo_veiculo`, `cor_veiculo`, `numero_veiculo`, `placa_veiculo`, `num_licenca_veiculo`, `capacidade_veiculo`, `status_veiculo`, `id_motorista_veiculo`) VALUES
(1, 'Renault Master', 'Cinza', '367', 'QEZ-9I30', '', 16, 1, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
