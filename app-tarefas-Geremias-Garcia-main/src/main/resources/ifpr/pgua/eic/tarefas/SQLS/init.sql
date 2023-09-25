CREATE TABLE IF NOT EXISTS categorias (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    descricao varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tarefas (
    id int NOT NULL AUTO_INCREMENT,
    titulo varchar(255) NOT NULL,
    descricao varchar(255) NOT NULL,
    prazo date NOT NULL,
    categoria_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

