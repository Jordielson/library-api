-- CREATE DATABASE biblioteca_db
-- USE biblioteca_db

-- DDL of tables
CREATE TABLE autor (
  id INT PRIMARY KEY AUTO_INCREMENT,
  primeiro_nome varchar(30) NOT NULL,
  sobrenome varchar(30) NOT NULL
);

CREATE TABLE estante(
	id INT PRIMARY KEY AUTO_INCREMENT,
	corredor INT
);

CREATE TABLE editora(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE livro(
	id INT PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(60) NOT NULL,
	editora INT NOT NULL,
	ano_publicacao INT,
	estante INT,
	CONSTRAINT fk_livro_est
	FOREIGN KEY(estante)
	REFERENCES estante(id),
	CONSTRAINT fk_livro_editora
	FOREIGN KEY(editora)
	REFERENCES editora(id)
);

CREATE TABLE autor_livro (
	autor INT,
	livro INT,
	PRIMARY KEY(autor, livro),
	CONSTRAINT fk_atlv_autor
	FOREIGN KEY(autor)
		REFERENCES autor(id),
	CONSTRAINT fk_atlv_livro
	FOREIGN KEY(livro)
		REFERENCES livro(id)
);

CREATE TABLE usuario(
	cpf VARCHAR(15) PRIMARY KEY NOT NULL,
	nome VARCHAR(60) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	cidade VARCHAR(40) NOT NULL,
	bairro VARCHAR(40) NOT NULL,
	rua VARCHAR(60) NOT NULL
);

CREATE TABLE email(
	usuario VARCHAR(15),
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY(usuario, email),
	CONSTRAINT fk_user_email
	FOREIGN KEY(usuario)
	REFERENCES usuario(cpf)
);

CREATE TABLE cadastro_livro (
	id INT PRIMARY KEY AUTO_INCREMENT,
	livro_id INT NOT NULL,
	data_cad DATE NOT NULL,
	CONSTRAINT fk_livro_cad
	FOREIGN KEY(livro_id)
	REFERENCES livro(id)
);

CREATE TABLE emprestimo (
	cadastro_id INT,	
	usuario_id VARCHAR(15),
	`data` DATE NOT NULL,
	PRIMARY KEY(cadastro_id, usuario_id),
	CONSTRAINT fk_user_emp
	FOREIGN KEY(usuario_id)
	REFERENCES usuario(cpf),
	CONSTRAINT fk_cadastro_emp
	FOREIGN KEY(cadastro_id)
	REFERENCES cadastro_livro(id)
);

CREATE TABLE `role`(
	papel VARCHAR(15) PRIMARY KEY NOT NULL
);

CREATE TABLE role_user(
	user_id VARCHAR(15) NOT NULL,
	role_id VARCHAR(15) NOT NULL,
	PRIMARY KEY(user_id, role_id),
	CONSTRAINT fk_role_ru
	FOREIGN KEY(role_id)
	REFERENCES `role`(papel),
	CONSTRAINT fk_user_ru
	FOREIGN KEY(user_id)
	REFERENCES usuario(cpf)
);


/* One insert for each tables */ 
INSERT INTO autor
(primeiro_nome, sobrenome)
VALUES('Jose', 'Silva');

INSERT INTO editora
(nome)
VALUES('Qualquer editora');

INSERT INTO estante
(corredor)
VALUES(1);

INSERT INTO livro
(titulo, editora, ano_publicacao, estante)
VALUES('Qualquer Livro', 1, 2015, 1);

INSERT INTO autor_livro
(autor, livro)
VALUES(1, 1);

INSERT INTO cadastro_livro
(livro_id, data_cad)
VALUES(1, '2017/11/21');

INSERT INTO usuario
(cpf, nome, senha, cidade, bairro, rua)
VALUES('156456645', 'joao', '$2a$10$Huy5rAQj/aoEnceH.aAikuS.L/Esu1IepslyMu6b41nWPMa1aV6ge', 'sume', 'centro', 'joaquim basto');
--Senha: 123

INSERT INTO email
(usuario, email)
VALUES('156456645', 'joao@gmail.com');

INSERT INTO emprestimo
(cadastro_id, usuario_id, `data`)
VALUES(1, '156456645', '2021/01/12');

INSERT INTO `role`
(papel)
VALUES
("ROLE_ADMIM"),
("ROLE_MANAGEMENT"),
("ROLE_SALES"),
("ROLE_USER");

INSERT INTO role_user
(user_id, role_id)
VALUES
('156456645', 'ROLE_USER'),
('156456645', 'ROLE_ADMIM');