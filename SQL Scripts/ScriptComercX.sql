create database dbcomercx;
use dbcomercx;

create table usuario (
iduser int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null,
perfil varchar(20) not null
);
insert into usuario(nome, fone, login, senha, perfil)
values('Administrador', '00000000000', 'admin', 'admin', 'admin');

create table cliente(
idcliente int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
fonecli varchar(15) not null,
emailcli varchar(35)
);

create table produto(
idprod int primary key auto_increment,
nomeprod varchar(60) not null,
ean13 varchar(13) not null unique,
validade varchar(10),
precoprod decimal(18,2),
qtd int
);

create table venda(
idvenda int primary key auto_increment,
datavenda timestamp default current_timestamp,
totvenda decimal(18,2),
idcliente int not null,
situacvenda varchar(10),
constraint fk_cliente foreign key(idcliente) references cliente(idcliente)
);

create table itemVenda(
idprod int,
idvenda int,
valunititem decimal(10,2),
qtditem int,
constraint fk_produto foreign key(idprod) references produto(idprod),
constraint fk_venda foreign key(idvenda) references venda(idvenda)
);
