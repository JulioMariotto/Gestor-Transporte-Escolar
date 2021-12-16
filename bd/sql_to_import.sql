-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tempo de Geração: 
-- Versão do Servidor: 5.1.49-community
-- Versão do PHP: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `sgte`
--

DROP SCHEMA IF EXISTS `sgte`;
CREATE SCHEMA IF NOT EXISTS `sgte`;
USE `sgte`;

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
-- Estrutura da tabela `escola`
--

DROP TABLE IF EXISTS `escola`;
CREATE TABLE IF NOT EXISTS `escola` (
  `id_escola` int(11) NOT NULL AUTO_INCREMENT,
  `nome_escola` varchar(30) DEFAULT NULL,
  `endereco_escola` varchar(60) DEFAULT NULL,
  `telefone_escola` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id_escola`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Extraindo dados da tabela `escola`
--

INSERT INTO `escola` (`id_escola`, `nome_escola`, `endereco_escola`, `telefone_escola`) VALUES
(1, 'Padre Arnaldo Jansen', 'R. Scharfenberg de Quadros, 66 - Centro', '(41) 3382-7754'),
(2, 'Costa Viana', 'R. Paulino de Siqueira Cortês, 2685 - Vl Braga', '(41) 3283-5583'),
(3, 'Silveira da Motta', 'Largo Ver. Segismundo Salata, 1123 - Centro', '(41) 3382-1241'),
(4, 'Unidade Polo', 'R. Joinville, 2770 - Pedro Moro', '(41) 3382-1442'),
(5, 'Top Gun', 'R. Veríssimo Marques, 584 - Centro', '(41) 3096-3344'),
(6, 'Adventista', 'R. Duque de Caxias, 1665 - Bom Jesus', '(41) 3051-8700');


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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Extraindo dados da tabela `motorista`
--

INSERT INTO `motorista` (`id_motorista`, `nome_motorista`, `cnh_motorista`, `telefone_motorista`, `endereco_motorista`) VALUES
(13, 'Luiza M. A. Monteiro', '58716951000141', '(71) 99626-2742', 'Marginal David Canabarro, 3398'),
(14, 'Israel L. C. Barros', '67854187000161', '(68) 99830-5937', 'Alameda Araxá, 1415'),
(15, 'Jonas M. C. Pereira', '27532405344654', '(86) 98434-3285', 'Jardim Graça Aranha, 32');

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id_veiculo`, `modelo_veiculo`, `cor_veiculo`, `numero_veiculo`, `placa_veiculo`, `num_licenca_veiculo`, `capacidade_veiculo`, `status_veiculo`, `id_motorista_veiculo`) VALUES
(7, 'Ducato Economy', 'Branco', '215', 'BEZ-1455', '9318840778', 18, 1, 13),
(8, 'Volare Attack 9', 'Azul', '382', 'RHJ-9Y37', '82873858', 49, 1, 15),
(9, 'Renault Master', 'Prata', '367', 'QEZ-9I30', '20461238', 16, 1, 14);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Extraindo dados da tabela `aluno`
--

INSERT INTO `aluno` (`id_aluno`, `nome_aluno`, `telefone_aluno`, `endereco_aluno`, `nome_responsavel`, `cpf_responsavel`, `id_escola_aluno`, `periodo_aluno`, `turma_aluno`, `horario_casa_ida`, `horario_escola_ida`, `horario_casa_volta`, `horario_escola_volta`, `valor_mensalidade_aluno`, `vencimento_aluno`, `data_inicio_aluno`, `data_fim_aluno`, `status_aluno`, `data_nasc_aluno`, `id_veiculo_aluno`) VALUES
(11, 'Gabrielly P. F. Carvalho', '(41) 99698-4115', 'Rua São Luiz Gonzaga, 1313', 'Luiza Monteiro Fernandes Pereira', '687.312.248-30', 1, 'Manhã', '7ªD', '06:15:00', '07:30:00', '12:30:00', '11:50:00', 220, 10, '2021-02-16', '2021-09-01', 1, '2006-11-22', 7),
(12, 'Henrique Martins Costa Barbosa', '(41) 98744-2187', 'Rua Pe. Luciano Peixoto, 2521', 'Maria Aparecida dos Santos', '631.782.489-43', 1, 'Manhã', '6ªA', '06:22:00', '07:30:00', '', '16:37', 200, 15, '2021-03-10', '2000-01-01', 1, '2002-10-22', 7),
(13, 'Frank Frotté Martins Antonio', '(42) 98271-0672', 'Rua Benedito Muniz Pontes, 342', 'Bruna Figueiredo Chaves', '292.087.029-79', 1, 'Manhã', '7ªB', '06:25:00', '07:30:00', '12:20:00', '11:50:00', 200, 20, '2021-03-12', '2000-01-01', 1, '2001-01-09', 7),
(14, 'Dhiego Cordeiro Castro Norte', '(45) 98709-4557', 'Rua Emiliano Zapata, 562', 'Mikaela Cristo Nascimento', '424.881.189-82', 1, 'Tarde', '5ªA', '11:30:00', '13:00:00', '17:55:00', '17:30:00', 180, 10, '2021-03-14', '2000-01-01', 1, '2002-05-21', 7),
(15, 'Lilian Farinha Louzano Biango', '(45) 99518-7823', 'Rua Xambrê, 66', 'Dilza Correia Mattos', '053.382.299-86', 1, 'Tarde', '8ªC', '12:29:00', '13:00:00', '17:40:00', '17:30:00', 250, 10, '2021-03-20', '2000-01-01', 1, '2005-01-31', 7),
(16, 'Rogério Freitas Belmiro de Jesus', '(45) 98122-4658', 'Rua Doutor Kival Fernando Pereira, 82', 'Lidia Constantino Santomauro', '558.832.819-15', 2, 'Manhã', '1ºC', '06:10:00', '07:25:00', '12:35:00', '12:00:00', 220, 15, '2021-03-25', '2021-09-01', 1, '2008-03-12', 7),
(17, 'Rosana Prata Mendonça de Sá', '(46) 97454-7257', 'Rua Presidente John F. Kennedy,2992', 'Adelir Grilo Muniz', '754.576.869-81', 2, 'Manhã', '2ºA', '07:12:00', '07:25:00', '12:12:00', '12:00:00', 200, 10, '2021-04-03', '2000-01-01', 1, '2007-02-03', 7),
(18, 'Conceição Muchão Sampaio Brito', '(42) 97484-1211', 'Rua Guilherme Ihlenfeldt, 12', 'Ariana Cordeiro Folly', '038.377.129-31', 2, 'Tarde', '1ºE', '12:38:00', '12:50:00', '17:58:00', '17:40:00', 230, 20, '2021-04-10', '2000-01-01', 1, '2003-02-23', 7),
(19, 'Cris Guimarães da Mota Vasconcelos', '(44) 98573-5331', 'Rua Guilherme Ihlenfeldt, 12', 'Silezia Valente Sampaio', '575.538.919-59', 2, 'Tarde', '3ºC', '12:38:00', '12:50:00', '17:58:00', '17:40:00', 190, 10, '2021-05-14', '2000-01-01', 1, '2004-09-07', 7),
(20, 'David da Sousa Bravo de Carvalho', '(41) 99237-9136', 'Rua Enrico Caruso, 99', 'Manuella Marins Filho', '131.702.329-32', 2, 'Tarde', '2ºB', '12:34:00', '12:50:00', '17:59:00', '17:40:00', 150, 20, '2021-05-20', '2000-01-01', 1, '2005-12-10', 7),
(22, 'Gabriel H. F. Carvalho', '(41) 99698-4199', 'Rua Pe. São Luis, 1313', 'Luiza Monteiro Fernandes Pereira', '687.312.248-30', 3, 'Manhã', '7ªD', '06:15:00', '07:30:00', '12:30:00', '11:50:00', 220, 10, '2021-10-16', '2000-01-01', 1, '2006-11-22', 8),
(23, 'João Marcos M. Junior', '(41) 98744-2111', 'Rua Pe. Lucia Rosa, 2521', 'Maria Aparecida dos Santos', '631.782.489-43', 3, 'Manhã', '6ªA', '06:22:00', '07:30:00', '12:23:00', '11:50:00', 200, 15, '2021-03-10', '2000-01-01', 1, '2002-10-22', 8),
(24, 'Pedro Martins Antonio', '(42) 98271-0602', 'Rua Bento Muniz, 342', 'Bruna Figueiredo Chaves', '292.087.029-79', 3, 'Manhã', '7ªB', '06:25:00', '07:30:00', '12:20:00', '11:50:00', 200, 20, '2021-06-12', '2000-01-01', 1, '2001-01-09', 8),
(25, 'Dhieniffe Cindin', '(45) 98709-4590', 'Rua Emiliano Jorge, 562', 'Mikaela Cristo Nascimento', '424.881.189-82', 4, 'Tarde', '5ªA', '11:30:00', '13:00:00', '17:45:00', '17:30:00', 180, 10, '2021-08-14', '2000-01-01', 1, '2002-05-21', 8),
(26, 'Luis Louzano Biango', '(45) 99518-7453', 'Rua Mato Grosso, 66', 'Dilza Correia Mattos', '053.382.299-86', 3, 'Tarde', '8ªC', '12:29:00', '13:00:00', '17:40:00', '17:30:00', 250, 10, '2021-10-20', '2000-01-01', 1, '2005-01-31', 8),
(27, 'Robson Freitas de Jesus', '(45) 98122-4888', 'Rua Kival Fernando, 82', 'Lidia Constantino Santomauro', '558.832.819-15', 4, 'Manhã', '1ºC', '06:10:00', '07:25:00', '12:35:00', '12:00:00', 220, 15, '2021-11-25', '2000-01-01', 1, '2008-03-12', 8),
(28, 'Rosa Prata  de Sá', '(46) 97454-7299', 'Rua F. Kennedy,2992', 'Adelir Grilo Muniz', '754.576.869-81', 4, 'Manhã', '2ºA', '07:12:00', '07:25:00', '12:12:00', '12:00:00', 200, 10, '2021-06-03', '2000-01-01', 1, '2007-02-03', 9),
(29, 'Matheus Sampaio Brito', '(42) 97484-1255', 'Rua Dos Anjos, 122', 'Ariana Cordeiro Folly', '038.377.129-31', 4, 'Tarde', '1ºE', '12:38:00', '12:50:00', '17:58:00', '17:40:00', 230, 20, '2021-07-10', '2000-01-01', 1, '2003-02-23', 9),
(30, 'Miley Vasconcelos', '(44) 98573-5332', 'Rua Dos Anjos, 122', 'Silezia Valente Sampaio', '575.538.919-59', 4, 'Tarde', '3ºC', '12:38:00', '12:50:00', '17:58:00', '17:40:00', 190, 10, '2021-05-14', '2000-01-01', 1, '2004-09-07', 9),
(31, 'Daniela de Sousa', '(41) 99237-9135', 'Rua Enrico Keler, 99', 'Manuella Marins Filho', '131.702.329-32', 4, 'Tarde', '2ºB', '12:34:00', '12:50:00', '17:59:00', '17:40:00', 150, 20, '2021-08-20', '2000-01-01', 1, '2005-12-10', 9),
(21, 'Beatriz Lacout', '(45) 98818-7823', 'Rua Bom Jesus, 126', 'Dilza Correia Mattos', '053.382.299-86', 4, 'Tarde', '8ªC', '12:29:00', '12:50:00', '17:49:00', '17:40:00', 150, 10, '2021-03-20', '2021-11-01', 1, '2005-01-31', 9),
(33, 'Cecília Barbosa Souza Castro', '(041) 97387-0743', 'Alameda do Ipê, 188', 'Henrique Martins Costa Barbosa', '196.928.813-26', 6, 'Manhã', '8ºF', '07:37:00', '07:50:00', '12:22:00', '11:20:00', 250, 10, '2021-10-16', '2000-01-01', 1, '2009-10-20', 8),
(34, 'Mario Celso Polarini', '(41) 99877-3265', 'Rua James Portugal, 9000', 'Lucas Alves Polarini', '969.288.132-61', 5, 'Tarde', '2ªA', '13:57:00', '14:10:00', '', '', 180, 10, '2021-09-16', '2000-01-01', 1, '2012-06-12', 8),
(35, 'Miguel Moura Gomes Araújo', '(50) 98614-6669', 'Praça Guilherme Grovermann, 500', 'Taís Souza Barros Oliveira', '727.685.929-00', 1, 'Tarde', '2ªA', '12:27:00', '13:30:00', '17:49:00', '17:30:00', 230, 10, '2021-09-02', '2000-01-01', 1, '2008-02-12', 7);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=42 ;

--
-- Extraindo dados da tabela `contato`
--

INSERT INTO `contato` (`id_contato`, `id_aluno_contato`, `nome_contato`, `telefone_contato`, `parentesco`) VALUES
(28, 11, 'Luiza', '(41) 99867-5622', 'Mãe'),
(30, 33, 'Henrique', '(41) 99775-9713', 'Pai'),
(39, 25, 'Paulo', '(73) 96682-5384', 'Pai'),
(40, 34, 'Lucas Alves P.', '(41) 99987-6557', 'Pai'),
(41, 35, 'Taís', '(41) 99981-0144', 'Mãe');

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=451 ;

--
-- Extraindo dados da tabela `despesa`
--

INSERT INTO `despesa` (`id_despesa`, `valor_despesa`, `data_despesa`, `descricao_despesa`) VALUES
(243, 0, '2021-01-01', 'Registro da Kilometragem de veículo, NÃO EXCLUIR!'),
(244, 0, '2021-01-01', 'Registro da Kilometragem de veículo, NÃO EXCLUIR!'),
(245, 0, '2021-01-01', 'Registro da Kilometragem de veículo, NÃO EXCLUIR!'),
(246, 200, '2021-01-01', 'Abastecimento Diesel S-10'),
(247, 200, '2021-01-15', 'Abastecimento Diesel S-10'),
(248, 200, '2021-01-10', 'Abastecimento Diesel S-10'),
(249, 200, '2021-02-01', 'Abastecimento Diesel S-10'),
(250, 200, '2021-02-10', 'Abastecimento Diesel S-10'),
(251, 200, '2021-02-15', 'Abastecimento Diesel S-10'),
(252, 200, '2021-03-01', 'Abastecimento Diesel S-10'),
(253, 200, '2021-03-10', 'Abastecimento Diesel S-10'),
(254, 200, '2021-03-15', 'Abastecimento Diesel S-10'),
(255, 200, '2021-04-01', 'Abastecimento Diesel S-10'),
(256, 200, '2021-04-10', 'Abastecimento Diesel S-10'),
(257, 200, '2021-04-15', 'Abastecimento Diesel S-10'),
(258, 200, '2021-05-01', 'Abastecimento Diesel S-10'),
(259, 200, '2021-05-10', 'Abastecimento Diesel S-10'),
(260, 200, '2021-05-15', 'Abastecimento Diesel S-10'),
(261, 200, '2021-06-01', 'Abastecimento Diesel S-10'),
(262, 200, '2021-06-10', 'Abastecimento Diesel S-10'),
(263, 200, '2021-06-15', 'Abastecimento Diesel S-10'),
(264, 200, '2021-07-01', 'Abastecimento Diesel S-10'),
(265, 200, '2021-07-10', 'Abastecimento Diesel S-10'),
(266, 200, '2021-07-15', 'Abastecimento Diesel S-10'),
(267, 200, '2021-08-01', 'Abastecimento Diesel S-10'),
(268, 200, '2021-08-10', 'Abastecimento Diesel S-10'),
(272, 200, '2021-08-15', 'Abastecimento Diesel S-10'),
(273, 200, '2021-09-01', 'Abastecimento Diesel S-10'),
(339, 200, '2021-09-10', 'Abastecimento Diesel S-10'),
(340, 200, '2021-09-15', 'Abastecimento Diesel S-10'),
(341, 200, '2021-10-01', 'Abastecimento Diesel S-10'),
(342, 200, '2021-10-10', 'Abastecimento Diesel S-10'),
(343, 200, '2021-10-15', 'Abastecimento Diesel S-10'),
(344, 200, '2021-11-01', 'Abastecimento Diesel S-10'),
(345, 200, '2021-11-10', 'Abastecimento Diesel S-10'),
(346, 100, '2021-01-02', 'Abastecimento Diesel Comum'),
(347, 100, '2021-01-09', 'Abastecimento Diesel Comum'),
(348, 100, '2021-01-17', 'Abastecimento Diesel Comum'),
(349, 100, '2021-01-19', 'Abastecimento Diesel Comum'),
(350, 100, '2021-01-21', 'Abastecimento Diesel Comum'),
(351, 100, '2021-02-02', 'Abastecimento Diesel Comum'),
(352, 100, '2021-02-09', 'Abastecimento Diesel Comum'),
(353, 100, '2021-02-17', 'Abastecimento Diesel Comum'),
(354, 100, '2021-03-02', 'Abastecimento Diesel Comum'),
(355, 100, '2021-03-09', 'Abastecimento Diesel Comum'),
(356, 100, '2021-03-17', 'Abastecimento Diesel Comum'),
(357, 100, '2021-04-02', 'Abastecimento Diesel Comum'),
(358, 100, '2021-04-09', 'Abastecimento Diesel Comum'),
(359, 100, '2021-04-17', 'Abastecimento Diesel Comum'),
(360, 100, '2021-05-02', 'Abastecimento Diesel Comum'),
(361, 100, '2021-05-09', 'Abastecimento Diesel Comum'),
(362, 100, '2021-05-17', 'Abastecimento Diesel Comum'),
(363, 100, '2021-06-02', 'Abastecimento Diesel Comum'),
(364, 100, '2021-06-09', 'Abastecimento Diesel Comum'),
(365, 100, '2021-06-17', 'Abastecimento Diesel Comum'),
(366, 100, '2021-07-02', 'Abastecimento Diesel Comum'),
(367, 100, '2021-07-09', 'Abastecimento Diesel Comum'),
(368, 100, '2021-07-17', 'Abastecimento Diesel Comum'),
(369, 100, '2021-08-02', 'Abastecimento Diesel Comum'),
(370, 100, '2021-08-09', 'Abastecimento Diesel Comum'),
(371, 100, '2021-08-17', 'Abastecimento Diesel Comum'),
(372, 100, '2021-09-02', 'Abastecimento Diesel Comum'),
(373, 100, '2021-09-09', 'Abastecimento Diesel Comum'),
(374, 100, '2021-09-17', 'Abastecimento Diesel Comum'),
(375, 100, '2021-10-02', 'Abastecimento Diesel Comum'),
(376, 100, '2021-10-09', 'Abastecimento Diesel Comum'),
(377, 100, '2021-10-17', 'Abastecimento Diesel Comum'),
(378, 100, '2021-11-02', 'Abastecimento Diesel Comum'),
(379, 100, '2021-11-09', 'Abastecimento Diesel Comum'),
(380, 100, '2021-11-17', 'Abastecimento Diesel Comum'),
(381, 100, '2021-12-02', 'Abastecimento Diesel Comum'),
(382, 100, '2021-12-09', 'Abastecimento Diesel Comum'),
(383, 100, '2021-12-17', 'Abastecimento Diesel Comum'),
(396, 150, '2021-01-02', 'Abastecimento Diesel S-10'),
(397, 150, '2021-01-10', 'Abastecimento Diesel S-10'),
(398, 150, '2021-01-15', 'Abastecimento Diesel S-10'),
(399, 150, '2021-01-20', 'Abastecimento Diesel S-10'),
(400, 150, '2021-01-25', 'Abastecimento Diesel S-10'),
(401, 150, '2021-02-01', 'Abastecimento Diesel S-10'),
(402, 150, '2021-02-10', 'Abastecimento Diesel S-10'),
(403, 150, '2021-02-15', 'Abastecimento Diesel S-10'),
(404, 150, '2021-02-20', 'Abastecimento Diesel S-10'),
(405, 150, '2021-02-25', 'Abastecimento Diesel S-10'),
(406, 150, '2021-03-01', 'Abastecimento Diesel S-10'),
(407, 150, '2021-03-10', 'Abastecimento Diesel S-10'),
(408, 150, '2021-03-20', 'Abastecimento Diesel S-10'),
(409, 150, '2021-04-01', 'Abastecimento Diesel S-10'),
(410, 150, '2021-04-10', 'Abastecimento Diesel S-10'),
(411, 150, '2021-04-20', 'Abastecimento Diesel S-10'),
(412, 150, '2021-05-01', 'Abastecimento Diesel S-10'),
(413, 150, '2021-05-10', 'Abastecimento Diesel S-10'),
(414, 150, '2021-05-20', 'Abastecimento Diesel S-10'),
(415, 150, '2021-06-01', 'Abastecimento Diesel S-10'),
(416, 150, '2021-06-10', 'Abastecimento Diesel S-10'),
(417, 150, '2021-06-20', 'Abastecimento Diesel S-10'),
(418, 150, '2021-07-01', 'Abastecimento Diesel S-10'),
(419, 150, '2021-07-10', 'Abastecimento Diesel S-10'),
(420, 150, '2021-07-20', 'Abastecimento Diesel S-10'),
(421, 150, '2021-08-01', 'Abastecimento Diesel S-10'),
(422, 150, '2021-08-10', 'Abastecimento Diesel S-10'),
(423, 150, '2021-08-20', 'Abastecimento Diesel S-10'),
(450, 700, '2021-10-16', 'Freios'),
(449, 900, '2021-02-03', 'Vistoria e documentação para a licença na prefeitura'),
(448, 2600, '2021-01-10', 'IPVA'),
(447, 1450, '2021-03-16', 'Troca dos 4 Pneus'),
(446, 320, '2021-06-16', 'Troca de óleo'),
(438, 150, '2021-09-01', 'Abastecimento Diesel S-10'),
(439, 150, '2021-09-10', 'Abastecimento Diesel S-10'),
(440, 150, '2021-09-20', 'Abastecimento Diesel S-10'),
(441, 150, '2021-10-01', 'Abastecimento Diesel S-10'),
(442, 150, '2021-10-10', 'Abastecimento Diesel S-10'),
(443, 150, '2021-10-20', 'Abastecimento Diesel S-10'),
(444, 150, '2021-11-01', 'Abastecimento Diesel S-10'),
(445, 150, '2021-12-01', 'Abastecimento Diesel S-10');


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
(243, 7, 90420, 0, 'Registro de KM'),
(244, 8, 5780, 0, 'Registro de KM'),
(245, 9, 83490, 0, 'Registro de KM'),
(246, 7, 91100, 39.6, 'Ipiranga Av. Central'),
(247, 7, 91440, 39.6, 'Ipiranga Av. Central'),
(248, 7, 91780, 39.6, 'Ipiranga Av. Central'),
(249, 7, 92120, 39.6, 'Ipiranga Av. Central'),
(250, 7, 92460, 39.6, 'Ipiranga Av. Central'),
(251, 7, 92800, 39.6, 'Ipiranga Av. Central'),
(252, 7, 93140, 39.6, 'Ipiranga Av. Central'),
(253, 7, 93480, 39.6, 'Ipiranga Av. Central'),
(254, 7, 93820, 39.6, 'Ipiranga Av. Central'),
(255, 7, 94160, 39.6, 'Ipiranga Av. Central'),
(256, 7, 94500, 39.6, 'Ipiranga Av. Central'),
(257, 7, 94840, 39.6, 'Ipiranga Av. Central'),
(258, 7, 95180, 39.6, 'Ipiranga Av. Central'),
(259, 7, 95520, 39.6, 'Ipiranga Av. Central'),
(260, 7, 95860, 39.6, 'Ipiranga Av. Central'),
(261, 7, 96200, 39.6, 'Ipiranga Av. Central'),
(262, 7, 96540, 39.6, 'Ipiranga Av. Central'),
(263, 7, 96880, 39.6, 'Ipiranga Av. Central'),
(264, 7, 97220, 39.6, 'Ipiranga Av. Central'),
(265, 7, 97560, 39.6, 'Ipiranga Av. Central'),
(266, 7, 97900, 39.6, 'Ipiranga Av. Central'),
(267, 7, 98240, 39.6, 'Ipiranga Av. Central'),
(268, 7, 98580, 39.6, 'Ipiranga Av. Central'),
(269, 7, 98920, 39.6, 'Ipiranga Av. Central'),
(270, 7, 99260, 39.6, 'Ipiranga Av. Central'),
(271, 7, 99600, 39.6, 'Ipiranga Av. Central'),
(272, 7, 99940, 39.6, 'Ipiranga Av. Central'),
(273, 7, 100280, 39.6, 'Ipiranga Av. Central'),
(339, 7, 100620, 39.6, 'Ipiranga Av. Central'),
(340, 7, 100960, 39.6, 'Ipiranga Av. Central'),
(341, 7, 101300, 39.6, 'Ipiranga Av. Central'),
(342, 7, 101640, 39.6, 'Ipiranga Av. Central'),
(343, 7, 101980, 39.6, 'Ipiranga Av. Central'),
(344, 7, 102320, 39.6, 'Ipiranga Av. Central'),
(345, 7, 102660, 39.6, 'Ipiranga Av. Central'),
(346, 8, 6260, 21.8, 'Ipiranga Av. Central'),
(347, 8, 6600, 21.8, 'Ipiranga Av. Central'),
(348, 8, 6940, 21.8, 'Ipiranga Av. Central'),
(349, 8, 7280, 21.8, 'Ipiranga Av. Central'),
(350, 8, 7620, 21.8, 'Ipiranga Av. Central'),
(351, 8, 7960, 21.8, 'Ipiranga Av. Central'),
(352, 8, 8300, 21.8, 'Ipiranga Av. Central'),
(353, 8, 8640, 21.8, 'Ipiranga Av. Central'),
(354, 8, 8980, 21.8, 'Ipiranga Av. Central'),
(355, 8, 9320, 21.8, 'Ipiranga Av. Central'),
(356, 8, 9660, 21.8, 'Ipiranga Av. Central'),
(357, 8, 10000, 21.8, 'Ipiranga Av. Central'),
(358, 8, 10340, 21.8, 'Ipiranga Av. Central'),
(359, 8, 10680, 21.8, 'Ipiranga Av. Central'),
(360, 8, 11020, 21.8, 'Ipiranga Av. Central'),
(361, 8, 11360, 21.8, 'Ipiranga Av. Central'),
(362, 8, 11700, 21.8, 'Ipiranga Av. Central'),
(363, 8, 12040, 21.8, 'Ipiranga Av. Central'),
(364, 8, 12380, 21.8, 'Ipiranga Av. Central'),
(365, 8, 12720, 21.8, 'Ipiranga Av. Central'),
(366, 8, 13060, 21.8, 'Ipiranga Av. Central'),
(367, 8, 13400, 21.8, 'Ipiranga Av. Central'),
(368, 8, 13740, 21.8, 'Ipiranga Av. Central'),
(369, 8, 14080, 21.8, 'Ipiranga Av. Central'),
(370, 8, 14420, 21.8, 'Ipiranga Av. Central'),
(371, 8, 14760, 21.8, 'Ipiranga Av. Central'),
(372, 8, 15100, 21.8, 'Ipiranga Av. Central'),
(373, 8, 15440, 21.8, 'Ipiranga Av. Central'),
(374, 8, 15780, 21.8, 'Ipiranga Av. Central'),
(375, 8, 16120, 21.8, 'Ipiranga Av. Central'),
(376, 8, 16460, 21.8, 'Ipiranga Av. Central'),
(377, 8, 16800, 21.8, 'Ipiranga Av. Central'),
(378, 8, 17140, 21.8, 'Ipiranga Av. Central'),
(379, 8, 17480, 21.8, 'Ipiranga Av. Central'),
(380, 8, 17820, 21.8, 'Ipiranga Av. Central'),
(381, 8, 18160, 21.8, 'Ipiranga Av. Central'),
(382, 8, 18500, 21.8, 'Ipiranga Av. Central'),
(383, 8, 18840, 21.8, 'Ipiranga Av. Central'),
(396, 9, 84040, 32.7, 'Posto Ipiranga Av. Central'),
(397, 9, 84380, 32.7, 'Posto Ipiranga Av. Central'),
(398, 9, 84720, 32.7, 'Posto Ipiranga Av. Central'),
(399, 9, 85060, 32.7, 'Posto Ipiranga Av. Central'),
(400, 9, 85400, 32.7, 'Posto Ipiranga Av. Central'),
(401, 9, 85740, 32.7, 'Posto Ipiranga Av. Central'),
(402, 9, 86080, 32.7, 'Posto Ipiranga Av. Central'),
(403, 9, 86420, 32.7, 'Posto Ipiranga Av. Central'),
(404, 9, 86760, 32.7, 'Posto Ipiranga Av. Central'),
(405, 9, 87100, 32.7, 'Posto Ipiranga Av. Central'),
(406, 9, 87440, 32.7, 'Posto Ipiranga Av. Central'),
(407, 9, 87780, 32.7, 'Posto Ipiranga Av. Central'),
(408, 9, 88120, 32.7, 'Posto Ipiranga Av. Central'),
(409, 9, 88460, 32.7, 'Posto Ipiranga Av. Central'),
(410, 9, 88800, 32.7, 'Posto Ipiranga Av. Central'),
(411, 9, 89140, 32.7, 'Posto Ipiranga Av. Central'),
(412, 9, 89480, 32.7, 'Posto Ipiranga Av. Central'),
(413, 9, 89820, 32.7, 'Posto Ipiranga Av. Central'),
(414, 9, 90160, 32.7, 'Posto Ipiranga Av. Central'),
(415, 9, 90500, 32.7, 'Posto Ipiranga Av. Central'),
(416, 9, 90840, 32.7, 'Posto Ipiranga Av. Central'),
(417, 9, 91180, 32.7, 'Posto Ipiranga Av. Central'),
(418, 9, 91520, 32.7, 'Posto Ipiranga Av. Central'),
(419, 9, 91860, 32.7, 'Posto Ipiranga Av. Central'),
(420, 9, 92200, 32.7, 'Posto Ipiranga Av. Central'),
(421, 9, 92540, 32.7, 'Posto Ipiranga Av. Central'),
(422, 9, 92880, 32.7, 'Posto Ipiranga Av. Central'),
(423, 9, 93220, 32.7, 'Posto Ipiranga Av. Central'),
(438, 9, 98320, 32.7, 'Posto Ipiranga Av. Central'),
(439, 9, 98660, 32.7, 'Posto Ipiranga Av. Central'),
(440, 9, 99000, 32.7, 'Posto Ipiranga Av. Central'),
(441, 9, 99340, 32.7, 'Posto Ipiranga Av. Central'),
(442, 9, 99680, 32.7, 'Posto Ipiranga Av. Central'),
(443, 9, 100020, 32.7, 'Posto Ipiranga Av. Central'),
(444, 9, 100360, 32.7, 'Posto Ipiranga Av. Central'),
(445, 9, 100700, 32.7, 'Posto Ipiranga Av. Central');


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
(446, 7, 'Manutenção preventiva - Troca do óleo do motor, filtro de cabine e de combustivel', '90680'),
(447, 9, 'Pneus com desgastes', '89700'),
(450, 7, 'Desgastes nas pastilhas e disco de freios', '100476');


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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=587 ;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`id_pagamento`, `id_aluno`, `valor_pago`, `mes_referencia`, `data_pagamento`) VALUES
(346, 28, 200, '2021-01-01', '2021-01-10'),
(347, 29, 230, '2021-01-01', '2021-01-15'),
(348, 30, 190, '2021-01-01', '2021-01-15'),
(349, 31, 150, '2021-01-01', '2021-01-15'),
(350, 11, 200, '2021-02-01', '2021-02-02'),
(351, 12, 200, '2021-02-01', '2021-02-03'),
(352, 13, 200, '2021-02-01', '2021-02-05'),
(353, 14, 180, '2021-02-01', '2021-02-05'),
(354, 15, 250, '2021-02-01', '2021-02-09'),
(355, 16, 220, '2021-02-01', '2021-02-10'),
(356, 17, 200, '2021-02-01', '2021-02-10'),
(357, 18, 230, '2021-02-01', '2021-02-10'),
(358, 19, 150, '2021-02-01', '2021-02-15'),
(359, 20, 190, '2021-02-01', '2021-02-15'),
(360, 21, 150, '2021-02-01', '2021-02-02'),
(361, 22, 220, '2021-02-01', '2021-02-03'),
(362, 23, 200, '2021-02-01', '2021-02-05'),
(363, 24, 200, '2021-02-01', '2021-02-05'),
(370, 31, 150, '2021-02-01', '2021-02-15'),
(371, 11, 200, '2021-03-01', '2021-03-02'),
(372, 12, 200, '2021-03-01', '2021-03-03'),
(373, 13, 200, '2021-03-01', '2021-03-05'),
(374, 14, 180, '2021-03-01', '2021-03-05'),
(375, 15, 250, '2021-03-01', '2021-03-09'),
(376, 16, 220, '2021-03-01', '2021-03-10'),
(377, 17, 200, '2021-03-01', '2021-03-10'),
(378, 18, 230, '2021-03-01', '2021-03-10'),
(379, 19, 150, '2021-03-01', '2021-03-15'),
(380, 20, 190, '2021-03-01', '2021-03-15'),
(381, 21, 150, '2021-03-01', '2021-03-02'),
(382, 22, 220, '2021-03-01', '2021-03-03'),
(383, 23, 200, '2021-03-01', '2021-03-05'),
(384, 24, 200, '2021-03-01', '2021-03-05'),
(385, 25, 180, '2021-03-01', '2021-03-09'),
(386, 26, 250, '2021-03-01', '2021-03-10'),
(387, 27, 220, '2021-03-01', '2021-03-10'),
(388, 28, 200, '2021-03-01', '2021-03-10'),
(389, 29, 230, '2021-03-01', '2021-03-15'),
(390, 30, 190, '2021-03-01', '2021-03-15'),
(391, 31, 150, '2021-03-01', '2021-03-15'),
(392, 11, 200, '2021-04-01', '2021-04-02'),
(393, 12, 200, '2021-04-01', '2021-04-03'),
(394, 13, 200, '2021-04-01', '2021-04-05'),
(395, 14, 180, '2021-04-01', '2021-04-05'),
(399, 18, 230, '2021-04-01', '2021-04-10'),
(400, 19, 150, '2021-04-01', '2021-04-15'),
(401, 20, 190, '2021-04-01', '2021-04-15'),
(402, 21, 150, '2021-04-01', '2021-04-02'),
(403, 22, 220, '2021-04-01', '2021-04-03'),
(404, 23, 200, '2021-04-01', '2021-04-05'),
(405, 24, 200, '2021-04-01', '2021-04-05'),
(406, 25, 180, '2021-04-01', '2021-04-09'),
(407, 26, 250, '2021-04-01', '2021-04-10'),
(408, 27, 220, '2021-04-01', '2021-04-10'),
(409, 28, 200, '2021-04-01', '2021-04-10'),
(410, 29, 230, '2021-04-01', '2021-04-15'),
(411, 30, 190, '2021-04-01', '2021-04-15'),
(412, 31, 150, '2021-04-01', '2021-04-15'),
(413, 11, 200, '2021-05-01', '2021-05-02'),
(414, 12, 200, '2021-05-01', '2021-05-03'),
(415, 13, 200, '2021-05-01', '2021-05-05'),
(416, 14, 180, '2021-05-01', '2021-05-05'),
(417, 15, 250, '2021-05-01', '2021-05-09'),
(418, 16, 220, '2021-05-01', '2021-05-10'),
(423, 21, 150, '2021-05-01', '2021-05-02'),
(424, 22, 220, '2021-05-01', '2021-05-03'),
(425, 23, 200, '2021-05-01', '2021-05-05'),
(426, 24, 200, '2021-05-01', '2021-05-05'),
(427, 25, 180, '2021-05-01', '2021-05-09'),
(428, 26, 250, '2021-05-01', '2021-05-10'),
(429, 27, 220, '2021-05-01', '2021-05-10'),
(430, 28, 200, '2021-05-01', '2021-05-10'),
(431, 29, 230, '2021-05-01', '2021-05-15'),
(432, 30, 190, '2021-05-01', '2021-05-15'),
(433, 31, 150, '2021-05-01', '2021-05-15'),
(434, 11, 200, '2021-06-01', '2021-06-02'),
(435, 12, 200, '2021-06-01', '2021-06-03'),
(438, 15, 250, '2021-06-01', '2021-06-09'),
(439, 16, 220, '2021-06-01', '2021-06-10'),
(440, 17, 200, '2021-06-01', '2021-06-10'),
(441, 18, 230, '2021-06-01', '2021-06-10'),
(442, 19, 150, '2021-06-01', '2021-06-15'),
(443, 20, 190, '2021-06-01', '2021-06-15'),
(444, 21, 150, '2021-06-01', '2021-06-02'),
(445, 22, 220, '2021-06-01', '2021-06-03'),
(446, 23, 200, '2021-06-01', '2021-06-05'),
(447, 24, 200, '2021-06-01', '2021-06-05'),
(448, 25, 180, '2021-06-01', '2021-06-09'),
(449, 26, 250, '2021-06-01', '2021-06-10'),
(450, 27, 220, '2021-06-01', '2021-06-10'),
(451, 28, 200, '2021-06-01', '2021-06-10'),
(452, 29, 230, '2021-06-01', '2021-06-15'),
(453, 30, 190, '2021-06-01', '2021-06-15'),
(454, 31, 150, '2021-06-01', '2021-06-15'),
(455, 11, 200, '2021-07-01', '2021-07-02'),
(459, 15, 250, '2021-07-01', '2021-07-09'),
(460, 16, 220, '2021-07-01', '2021-07-10'),
(461, 17, 200, '2021-07-01', '2021-07-10'),
(462, 18, 230, '2021-07-01', '2021-07-10'),
(463, 19, 150, '2021-07-01', '2021-07-15'),
(464, 20, 190, '2021-07-01', '2021-07-15'),
(465, 21, 150, '2021-07-01', '2021-07-02'),
(466, 22, 220, '2021-07-01', '2021-07-03'),
(467, 23, 200, '2021-07-01', '2021-07-05'),
(468, 24, 200, '2021-07-01', '2021-07-05'),
(469, 25, 180, '2021-07-01', '2021-07-09'),
(470, 26, 250, '2021-07-01', '2021-07-10'),
(471, 27, 220, '2021-07-01', '2021-07-10'),
(472, 28, 200, '2021-07-01', '2021-07-10'),
(473, 29, 230, '2021-07-01', '2021-07-15'),
(474, 30, 190, '2021-07-01', '2021-07-15'),
(475, 31, 150, '2021-07-01', '2021-07-15'),
(476, 11, 200, '2021-08-01', '2021-08-02'),
(477, 12, 200, '2021-08-01', '2021-08-03'),
(478, 13, 200, '2021-08-01', '2021-08-05'),
(479, 14, 180, '2021-08-01', '2021-08-05'),
(480, 15, 250, '2021-08-01', '2021-08-09'),
(481, 16, 220, '2021-08-01', '2021-08-10'),
(482, 17, 200, '2021-08-01', '2021-08-10'),
(490, 25, 180, '2021-08-01', '2021-08-09'),
(491, 26, 250, '2021-08-01', '2021-08-10'),
(492, 27, 220, '2021-08-01', '2021-08-10'),
(493, 28, 200, '2021-08-01', '2021-08-10'),
(494, 29, 230, '2021-08-01', '2021-08-15'),
(495, 30, 190, '2021-08-01', '2021-08-15'),
(496, 31, 150, '2021-08-01', '2021-08-15'),
(497, 11, 200, '2021-09-01', '2021-09-02'),
(498, 12, 200, '2021-09-01', '2021-09-03'),
(499, 13, 200, '2021-09-01', '2021-09-05'),
(500, 14, 180, '2021-09-01', '2021-09-05'),
(501, 15, 250, '2021-09-01', '2021-09-09'),
(502, 16, 220, '2021-09-01', '2021-09-10'),
(503, 17, 200, '2021-09-01', '2021-09-10'),
(504, 18, 230, '2021-09-01', '2021-09-10'),
(505, 19, 150, '2021-09-01', '2021-09-15'),
(506, 20, 190, '2021-09-01', '2021-09-15'),
(507, 21, 150, '2021-09-01', '2021-09-02'),
(508, 22, 220, '2021-09-01', '2021-09-03'),
(586, 35, 250, '2021-12-01', '2021-12-16'),
(583, 35, 230, '2021-11-01', '2021-11-10'),
(512, 26, 250, '2021-09-01', '2021-09-10'),
(513, 27, 220, '2021-09-01', '2021-09-10'),
(514, 28, 200, '2021-09-01', '2021-09-10'),
(515, 29, 230, '2021-09-01', '2021-09-15'),
(516, 30, 190, '2021-09-01', '2021-09-15'),
(517, 31, 150, '2021-09-01', '2021-09-15'),
(518, 11, 200, '2021-10-01', '2021-10-02'),
(519, 12, 200, '2021-10-01', '2021-10-03'),
(582, 35, 230, '2021-10-01', '2021-12-06'),
(581, 35, 230, '2021-09-01', '2021-12-03'),
(522, 15, 250, '2021-10-01', '2021-10-09'),
(523, 16, 220, '2021-10-01', '2021-10-10'),
(524, 17, 200, '2021-10-01', '2021-10-10'),
(525, 18, 230, '2021-10-01', '2021-10-10'),
(526, 19, 150, '2021-10-01', '2021-10-15'),
(527, 20, 190, '2021-10-01', '2021-10-15'),
(528, 21, 150, '2021-10-01', '2021-10-02'),
(529, 22, 220, '2021-10-01', '2021-10-03'),
(530, 23, 200, '2021-10-01', '2021-10-05'),
(531, 24, 200, '2021-10-01', '2021-10-05'),
(532, 25, 180, '2021-10-01', '2021-10-09'),
(533, 26, 250, '2021-10-01', '2021-10-10'),
(534, 27, 220, '2021-10-01', '2021-10-10'),
(535, 28, 200, '2021-10-01', '2021-10-10'),
(536, 29, 230, '2021-10-01', '2021-10-15'),
(537, 30, 190, '2021-10-01', '2021-10-15'),
(538, 31, 150, '2021-10-01', '2021-10-15'),
(539, 11, 200, '2021-11-01', '2021-11-02'),
(540, 12, 200, '2021-11-01', '2021-11-03'),
(541, 13, 200, '2021-11-01', '2021-11-05'),
(542, 14, 180, '2021-11-01', '2021-11-05'),
(543, 15, 250, '2021-11-01', '2021-11-09'),
(544, 16, 220, '2021-11-01', '2021-11-10'),
(545, 17, 200, '2021-11-01', '2021-11-10'),
(546, 18, 230, '2021-11-01', '2021-11-10'),
(547, 19, 150, '2021-11-01', '2021-11-15'),
(548, 20, 190, '2021-11-01', '2021-11-15'),
(549, 21, 150, '2021-11-01', '2021-11-02'),
(550, 22, 220, '2021-11-01', '2021-11-03'),
(551, 23, 200, '2021-11-01', '2021-11-05'),
(552, 24, 200, '2021-11-01', '2021-11-05'),
(553, 25, 180, '2021-11-01', '2021-11-09'),
(554, 26, 250, '2021-11-01', '2021-11-10'),
(555, 27, 220, '2021-11-01', '2021-11-10'),
(556, 28, 200, '2021-11-01', '2021-11-10'),
(557, 29, 230, '2021-11-01', '2021-11-15'),
(558, 30, 190, '2021-11-01', '2021-11-15'),
(559, 31, 150, '2021-11-01', '2021-11-15'),
(560, 11, 200, '2021-12-01', '2021-12-02'),
(561, 12, 200, '2021-12-01', '2021-12-03'),
(562, 13, 200, '2021-12-01', '2021-12-05'),
(563, 14, 180, '2021-12-01', '2021-12-05'),
(564, 15, 250, '2021-12-01', '2021-12-09'),
(565, 16, 220, '2021-12-01', '2021-12-10'),
(566, 17, 200, '2021-12-01', '2021-12-10'),
(567, 18, 230, '2021-12-01', '2021-12-10'),
(568, 19, 150, '2021-12-01', '2021-12-15'),
(569, 20, 190, '2021-12-01', '2021-12-15'),
(570, 21, 150, '2021-12-01', '2021-12-02'),
(571, 22, 220, '2021-12-01', '2021-12-03'),
(572, 23, 200, '2021-12-01', '2021-12-05'),
(573, 24, 200, '2021-12-01', '2021-12-05'),
(574, 25, 180, '2021-12-01', '2021-12-09'),
(575, 26, 250, '2021-12-01', '2021-12-10'),
(576, 27, 220, '2021-12-01', '2021-12-10'),
(577, 28, 200, '2021-12-01', '2021-12-10'),
(578, 29, 230, '2021-12-01', '2021-12-15'),
(579, 30, 190, '2021-12-01', '2021-12-15'),
(580, 31, 150, '2021-12-01', '2021-12-15');


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
