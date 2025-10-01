CREATE DATABASE gerenciamento_tarefas;

USE gerenciamento_tarefas;

CREATE TABLE usuario (
	idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150) NOT NULL UNIQUE
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

INSERT INTO usuario VALUES (1, "Vitor da Costa", "vitorcostagarcia2006@gmail.com");