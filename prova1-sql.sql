CREATE DATABASE gerenciamento_tarefas;

USE gerenciamento_tarefas;

CREATE TABLE usuario (
	idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    dataNascimento DATETIME NOT NULL,
    email VARCHAR(150) NOT NULL
);

CREATE TABLE tarefas (
	idTarefa BIGINT AUTO_INCREMENT,
	idUsuario BIGINT, 
	titulo VARCHAR(100) NOT NULL UNIQUE,
	tipo VARCHAR(50) NOT NULL,
	descricao VARCHAR(200),
	dataInicio DATETIME NOT NULL,
	dataFim DATETIME,
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

INSERT INTO usuario VALUES (1, "Vitor da Costa", "2006-08-01", "vitorcostagarcia2006@gmail.com");

INSERT INTO tarefas VALUES (1,
						    1,
                            "Titulogenerico",
							"Geral",
                            "Essa é a descrição da minha tarefa que ficará escrita no postit",
                            "2024-08-08",
                            null,
                            100,
                            100,
                            100,
                            100,
                            0,
                            0
                           );

SELECT * FROM tarefas;