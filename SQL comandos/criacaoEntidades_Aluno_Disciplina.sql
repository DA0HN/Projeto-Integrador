create table aluno(
	id int not null auto_increment,
	nome varchar(50) not null,
   	matricula varchar(50) not null,
   	senha varchar(50) not null,
   	email varchar(50) not null,
   	primary key(id)
);

create table disciplina(
	id int not null auto_increment,
	nome varchar(30) not null,
	professor varchar(50) not null,
	primary key(id)
);

create table frequenta(
	id int not null auto_increment,
    id_aluno int not null,
    id_disciplina int not null,
    id_notas int not null,
    quantidade_de_aulas int not null,
	faltas int not null,
    primary key(id),
    foreign key (id_notas) references notas(id),
    foreign key (id_aluno) references aluno(id),
    foreign key (id_disciplina) references disciplina(id)
);

create table notas(
	id int not null auto_increment,
    nota double not null,
    id_disciplina int not null,
    primary key(id),
    foreign key (id_disciplina) references disciplina(id)
);

INSERT INTO aluno (nome, matricula, senha, email) VALUES
    ('Luan', '454721', '53224', 'luan@luan.com'),
    ('Raul', '6332142', '3245612', 'raul@gmail.com'),
    ('Luis', '435711', '316437', 'luis@luis.com'),
    ('Gabrielly','157421', '135632', 'gabrielly@gabrielly.com');
    
insert into disciplina (nome, professor) values
	('Calculo IV','William'),
    ('Algoritmo I', 'Clodoaldo'),
    ('Eletrônica Digital', 'Mascarenhas'),
    ('Cálculo Numérico','Valtemir'),
    ('Banco de Dados','Juliana Saragiotto'),
    ('Algoritmo II', 'Ruy'),
    ('Estrutura de Dados I', 'Constantino'),
    ('Sinais e Sistemas Lineares', 'Walterley');

insert into frequenta (id_aluno, id_disciplina, id_notas, quantidade_de_aulas, faltas) values
	(1, 1, 80, 5),
    (1, 2, 80, 10),
    (1, 3, 60, 1),
    (2, 6, 80, 5),
    (2, 5, 60, 15),
    (3, 6, 80, 10),
    (3, 3, 60, 8),
    (3, 8, 90, 10),
    (4, 7, 60, 20),
    (4, 8, 90, 30),
    (4, 4, 60, 15);
    
insert into notas (nota, id_disciplina) values
	(5, 1),
    (7, 1),
    (2, 3),
    (8, 3),
    (2, 2),
    (9, 6),
    (10,7),
    (5, 8),
    (8, 5),
    (7, 4);
