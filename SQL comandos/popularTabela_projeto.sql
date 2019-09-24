
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

insert into frequenta (id_aluno, id_disciplina, quantidade_de_aulas, faltas) values
	(1, 1, 80, 5),
    (1, 2, 80, 10),
    (1, 3, 60, 0),
    (2, 6, 80, 5),
    (2, 5, 60, 15),
    (3, 6, 80, 10),
    (3, 3, 60, 8),
    (3, 8, 90, 10),
    (4, 7, 60, 20),
    (4, 8, 90, 30),
    (4, 4, 60, 15);
