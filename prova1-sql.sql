CREATE DATABASE gerenciamento_tarefas;

USE gerenciamento_tarefas;

CREATE TABLE usuario (
	idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha CHAR(128) NOT NULL
);

CREATE TABLE tarefas (
	idTarefa BIGINT AUTO_INCREMENT,
	idUsuario BIGINT, 
	titulo VARCHAR(100) NOT NULL UNIQUE,
	tipo VARCHAR(50) NOT NULL,
	descricao VARCHAR(200),
	dataInicio DATE NOT NULL,
	posx INT NOT NULL,
	posy INT NOT NULL,
	comprimento INT NOT NULL,
	altura INT NOT NULL,
	cor INT NOT NULL,
	statusTarefa INT NOT NULL,
	PRIMARY KEY (idTarefa, idUsuario),
	FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

INSERT INTO usuario VALUES (1, "Vitor da Costa", "vitorcostagarcia2006@gmail.com", "12345");
INSERT INTO usuario VALUES (2, "Gustavo Naozuka", "gustavonaozuka@gmail.com", "1234");
INSERT INTO usuario VALUES (3, "Fulano de Souza", "fulanodesouza@gmail.com", "123");

INSERT INTO tarefas (idUsuario, titulo, tipo, descricao, dataInicio, posx, posy, comprimento, altura, cor, statusTarefa)
VALUES
(1, 'Comprar material de escritório', 'Compra', 'Comprar canetas, papel e clipes', '2025-10-01', 10, 20, 50, 30, 2, 0),
(1, 'Reunião equipe', 'Reunião', 'Discutir roadmap do projeto', '2025-10-01', 15, 25, 60, 40, 1, 1),
(1, 'Enviar e-mails pendentes', 'Comunicação', 'Responder e-mails do cliente', '2025-10-01', 12, 18, 40, 25, 0, 0),
(1, 'Enviar relatório financeiro', 'Financeiro', 'Enviar relatório do mês de setembro', '2025-10-01', 5, 10, 70, 35, 3, 0),
(1, 'Atualizar planilhas', 'Administração', 'Atualizar planilhas de controle', '2025-10-01', 20, 30, 55, 25, 0, 1),
(1, 'Reunião com fornecedor', 'Reunião', 'Discutir prazos de entrega', '2025-10-01', 18, 22, 50, 30, 2, 0),
(1, 'Treinamento equipe', 'Treinamento', 'Treinamento sobre novas ferramentas', '2025-10-01', 12, 18, 80, 45, 4, 0),
(2, 'Auditoria interna', 'Auditoria', 'Revisar processos internos', '2025-10-01', 8, 16, 65, 30, 5, 1),
(2, 'Planejamento estratégico', 'Planejamento', 'Definir metas trimestrais', '2025-10-01', 25, 35, 75, 40, 1, 0),
(3, 'Atualizar documentos legais', 'Jurídico', 'Atualizar contratos e termos', '2025-10-01', 10, 20, 60, 30, 3, 1);