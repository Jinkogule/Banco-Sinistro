CREATE DATABASE IF NOT EXISTS financeiro;
USE financeiro;

CREATE TABLE IF NOT EXISTS administradores (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(20) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO administradores (id, nome, cpf, senha) VALUES
(1, 'leo', '249.252.810-38', '111') AS new
ON DUPLICATE KEY UPDATE nome = new.nome, cpf = new.cpf, senha = new.senha;

CREATE TABLE IF NOT EXISTS categorias (
  id INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO categorias (id, descricao) VALUES
(1, 'água'),
(2, 'luz'),
(3, 'condomínio'),
(4, 'plano de saúde'),
(5, 'salário') AS new
ON DUPLICATE KEY UPDATE descricao = new.descricao;

CREATE TABLE IF NOT EXISTS usuarios (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(20) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  suspenso VARCHAR(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (id)
);

INSERT INTO usuarios (id, nome, cpf, senha, suspenso) VALUES
(1, 'leo', '870.304.050-05', '111', 'N'),
(2, 'maria', '167.740.300-41', '111', 'N') AS new
ON DUPLICATE KEY UPDATE nome = new.nome, cpf = new.cpf, senha = new.senha, suspenso = new.suspenso;

CREATE TABLE IF NOT EXISTS contas (
  id INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  nome_conta VARCHAR(20) NOT NULL,
  banco VARCHAR(3) NOT NULL,
  agencia VARCHAR(6) NOT NULL,
  conta_corrente VARCHAR(6) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios (id)
);

INSERT INTO contas (id, id_usuario, nome_conta, banco, agencia, conta_corrente) VALUES
(1, 1, 'conta itaú', '341', '12321', '1234-6'),
(2, 2, 'conta bb', '001', '5678', '9521-0') AS new
ON DUPLICATE KEY UPDATE id_usuario = new.id_usuario, nome_conta = new.nome_conta, banco = new.banco, agencia = new.agencia, conta_corrente = new.conta_corrente;

CREATE TABLE IF NOT EXISTS lancamentos (
  id INT NOT NULL AUTO_INCREMENT,
  id_conta INT NOT NULL,
  id_categoria INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  operacao VARCHAR(1) NOT NULL DEFAULT 'D',
  data DATE NOT NULL,
  descricao VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id),
  CONSTRAINT fk_contas FOREIGN KEY (id_conta) REFERENCES contas (id)
);

INSERT INTO lancamentos (id, id_conta, id_categoria, valor, operacao, data, descricao) VALUES
(1, 1, 3, '790.00', 'D', '2021-06-11', 'Pago com atraso'),
(2, 1, 2, '200.00', 'D', '2021-06-11', ''),
(3, 2, 1, '600.90', 'D', '2021-06-11', ''),
(4, 2, 2, '350.56', 'D', '2021-06-11', ''),
(5, 1, 5, '1590.88', 'C', '2021-06-15', NULL),
(6, 2, 5, '2135.00', 'C', '2021-06-11', NULL) AS new
ON DUPLICATE KEY UPDATE id_conta = new.id_conta, id_categoria = new.id_categoria, valor = new.valor, operacao = new.operacao, data = new.data, descricao = new.descricao;