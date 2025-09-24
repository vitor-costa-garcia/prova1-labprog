CREATE DATABASE gerenciamento_tarefas;

USE gerenciamento_tarefas;

CREATE TABLE usuario (
	id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATETIME NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE tipo_tarefa (
	tipo_id BIGINT PRIMARY KEY NOT NULL,
    tipo VARCHAR(50),
    medida VARCHAR(50),
    categoria VARCHAR(50)
);

CREATE TABLE tarefas (
	id_tarefa BIGINT AUTO_INCREMENT,
	id_usuario BIGINT, 
	titulo VARCHAR(100) NOT NULL UNIQUE,
	tipo_id INT NOT NULL DEFAULT 0,
	descricao VARCHAR(200),
	meta_inicio INT,
	meta_fim INT,
	data_inicio DATETIME NOT NULL,
	data_fim DATETIME,
	posx INT NOT NULL,
	posy INT NOT NULL,
	comprimento INT NOT NULL,
	altura INT NOT NULL,
	cor VARCHAR(12) NOT NULL,
	status_tarefa INT NOT NULL,
	PRIMARY KEY (id_tarefa, id_usuario),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
	FOREIGN KEY (tipo_id) REFERENCES tipo_tarefa(tipo_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);