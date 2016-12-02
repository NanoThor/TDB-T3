create table if not exists cursos (
	codigo int,
	descricao varchar(60) not null,
	carga_horaria int not null,
	primary key (codigo));

create table if not exists alunos(
	matricula int,
	nome varchar(60),
	email varchar(60),
	cod_curso int,
	primary key (matricula),
	foreign key (cod_curso) references cursos);
