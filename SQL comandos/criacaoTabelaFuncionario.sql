CREATE TABLE funcionario(
	id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome varchar(38) NOT NULL,
    cpf varchar(20) UNIQUE NOT NULL,
    funcao varchar(28) NOT NULL
);

INSERT INTO funcionario (nome, cpf, funcao) VALUES
	('Gabriel Pereira', 	'123124643', 'Engenheiro Eletricista'),
    ('Gabrielly Ferrari', 	'164631235', 'Secretaria'),
    ('Renan Honda', 		'641545621', 'Administrador'),
    ('Icaro Alencar', 		'186442463', 'TI'),
    ('Bruno de Souza', 		'108756235', 'Estagiário'),
    ('Lia Menezes', 		'652647456', 'Secretária'),
    ('João Gabriel', 		'197679563', 'Engenheiro da Computação'),
    ('Luan Fernandes', 		'167624645', 'Advogado'),
    ('Renan dos Santos',	'675135456', 'Psicólogo');
