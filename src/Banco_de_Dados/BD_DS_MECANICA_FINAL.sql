drop database BD_mecanica2;

create database BD_mecanica2;

use BD_mecanica2;


create table Cliente(
	id_cli int primary key auto_increment not null,
	nome_cli varchar(100) not null,
	cpf_cli varchar(15) not null,
	sexo_cli varchar(20),
	rg_cli varchar(30) not null,
	tel_cli varchar(15) not null,
	email_cli varchar(100),
	end_cli varchar(100) not null,
	dataNasc_cli date not null
);

create table Produto(
	id_prod int primary key auto_increment not null,
	desc_prod varchar(200) not null,
	marca_prod varchar(50) not null,
	tamanho_prod varchar(50) not null,
	qtd_prod int not null,
	valor_prod double not null,
	tipo_prod varchar(50) not null
);


create table Funcionario(
	id_func int primary key auto_increment not null,
	nome_func varchar(100) not null,
	senha_func varchar(10) not null, 
	cpf_func varchar(15) not null,
	rg_func varchar(30) not null,
	tel_func varchar(15),
	email_func varchar (100),
	funcao_func varchar(80) not null,
	sexo_func varchar (20),
	salario_func double not null
);

create table Caixa(
	id_caixa int primary key auto_increment not null,
	dataIn_caixa date not null,
	saldoIn_caixa double not null,
	totalRec_caixa double not null,
	totalPag_caixa double not null,
    dataFin_caixa date not null,
	saldoFin_caixa double not null,
	id_funcionario_fk int not null,
	foreign key (id_funcionario_fk) references funcionario (id_func)
);
create table Fornecedor(
	id_forn int primary key auto_increment not null,
	nome_forn varchar(100) not null,
	cnpj_forn varchar(25) not null,
	end_forn varchar(200) not null,
	tel_forn varchar(20) not null
);
create table Compra(
	id_compra int primary key auto_increment not null,
	data_compra date not null,
	valor_compra double not null,
	formaPag_compra varchar(20) not null,
    parcelas_compra int not null,
	id_fornecedor_fk int not null,
	id_funcionario_fk int not null,
	foreign key (id_funcionario_fk) references funcionario (id_func),
	foreign key (id_fornecedor_fk) references Fornecedor(id_forn)
);

create table Compra_Prod(
	id_compProd int primary key auto_increment not null,
	id_compra_fk int not null,
	id_produto_fk int not null,
	quant_compProd int not null,
	valorUnit_compProd float not null,
	foreign key (id_compra_fk) references Compra (id_compra),
	foreign key (id_produto_fk) references produto (id_prod)
);

create table Despesa(
	id_desp int primary key auto_increment not null,
	desc_desp varchar(200),
	valor_desp double not null,
	data_desp date not null,
	formaPag_desp varchar(50) not null
);

create table Servico(
	id_serv int primary key auto_increment not null,
	desc_serv varchar(500) not null,
	valor_serv double not null,
	tempo_serv varchar(15),
	id_funcionario_fk int not null,
	foreign key (id_funcionario_fk) references funcionario (id_func)
);


create table Pagamento(
	id_pag int primary key auto_increment not null,
	valor_pag double not null,
	parcela_pag double not null,
	data_pag date not null,
	forma_pag varchar(50) not null,
	id_compra_fk int,
	id_caixa_fk int not null,
	id_despesa_fk int,
	foreign key (id_compra_fk) references compra (id_compra),
	foreign key (id_caixa_fk) references caixa (id_caixa),
	foreign key (id_despesa_fk) references despesa (id_desp)
);

create table Venda (
	id_venda int primary key auto_increment not null,
	valor_venda double not null,
	data_venda date not null,
	hora_venda varchar(10),
	formaPag_venda varchar(50),
	id_cliente_fk int not null,
	id_funcionario_fk int not null,
	foreign key (id_cliente_fk) references cliente (id_cli),
	foreign key (id_funcionario_fk) references funcionario (id_func)
);

create table Venda_Serv(
	id_vendaServ int primary key auto_increment not null,
	id_venda_fk int not null,
	id_servico_fk int not null,
	quant_vendaServ int not null,
    valorUnit_servico int not null,
	foreign key (id_venda_fk) references venda(id_venda),
	foreign key (id_servico_fk) references servico(id_serv)
);

create table Venda_Prod(
	id_vendaProd int primary key auto_increment not null,
	id_venda_fk int not null,
	valorUnit_produto double not null,
	id_produto_fk int not null,
	quant_vendaprod int not null,
	foreign key (id_venda_fk) references venda (id_venda),
	foreign key (id_produto_fk) references produto (id_prod)
);

create table Recebimento (
	id_receb int primary key auto_increment not null,
	valor_receb double not null,
	parcela_receb int not null,
	forma_receb varchar(50) not null,
	dataVenc_receb date not null,
	id_caixa_fk int not null,
	id_venda_fk int not null,
	foreign key (id_caixa_fk) references caixa (id_caixa),
	foreign key (id_venda_fk) references Venda (id_venda)
);


/*GATILHO PARA ATUALIZAR O VALOR DO CAIXA APÓS UMA COMPRA*/
delimiter $$
CREATE TRIGGER atualizarSaldo AFTER INSERT ON compra_prod
FOR EACH ROW
BEGIN
	declare i int;
    set  i = (select max(id_caixa) from caixa);
	UPDATE caixa 
SET 
    saldoIn_caixa = saldoIn_caixa - new.quant_compProd * valorUnit_compProd
WHERE
    id_caixa = i
        AND new.quant_compProd * valorUnit_compProd < saldoIn_caixa;
END;
$$ delimiter ;


/*GATILHO PARA ATUALIZAR AUTOMATICAMENTE O SALDO INICIAL DO CAIXA*/
delimiter $$
CREATE TRIGGER adicionarSaldoCaixaP AFTER INSERT ON venda
FOR EACH ROW
BEGIN
	declare i int;
    set  i = (select max(id_caixa) from caixa);
	UPDATE caixa set saldoIn_caixa = saldoIn_caixa + 
    new.valor_venda where id_caixa =
    i;
END;
$$ delimiter ;

/*quando realizar uma venda de produto baixar o estoque*/
delimiter $$
CREATE TRIGGER baixarEstoque AFTER INSERT ON venda_prod
FOR EACH ROW
BEGIN
	UPDATE produto set qtd_prod = qtd_prod - 
    new.quant_vendaprod where id_prod =
    id_produto_fk;
END;
$$ delimiter ;


/*quando realizar uma compra de produto aumentar o estoque*/
delimiter $$
CREATE TRIGGER aumentarEstoque AFTER INSERT ON compra_prod
FOR EACH ROW
BEGIN
	UPDATE produto set qtd_prod = qtd_prod + 
    new.quant_compProd where id_prod =
    id_produto_fk;
END;
$$ delimiter ;

drop procedure gerarCaixa
/*PROCEDURE PARA O CAIXA*/
delimiter $$
create procedure gerarCaixa(dataIni date, id_func int)
begin
    declare i int;
    declare count int;
    declare saldoF double;
    declare valor double;
    set count = (select count(*) from caixa);
    set i = (select max(id_caixa) from caixa);
    if(count = 0) then
		INSERT INTO caixa (dataIn_caixa, saldoIn_caixa, totalRec_caixa, totalPag_caixa, dataFin_caixa, saldoFin_caixa, id_funcionario_fk) values ( dataIni, 0, 0, 0, '1111/11/11', 0, id_func);
    else
		set saldoF = (select saldoFin_caixa from caixa where id_caixa = i);
		set valor =  (select saldoIn_caixa from caixa where id_caixa = i);
		select saldoIn_caixa from caixa where id_caixa = i;
		update caixa set dataFin_caixa = dataIni, saldoFin_caixa = valor  where id_caixa=i;
		INSERT INTO caixa (dataIn_caixa, saldoIn_caixa, totalRec_caixa, totalPag_caixa, dataFin_caixa, saldoFin_caixa, id_funcionario_fk) values ( dataIni, saldoF, 0, 0, '1111/11/11', 0, id_func);
	end if;
end;
$$ delimiter ;

call gerarCaixa('2000-10-10', 1)

/*PROCEDURE PARA A VENDA DE PRODUTOS
delimiter $$
create procedure inserirVendaProduto (valor float, dataV date, hora varchar(100), formaPag varchar(100), id_cliente int, id_funcionario int, id_produto int, quant int)
begin
	declare i int;
    declare valorUnit float;
	Insert into Venda values(null, valor, dataV, hora, formaPag, id_cliente, id_funcionario);
    set valorUnit = (select valor_prod from produto where id_prod = id_produto);
    set i = (select max(id_venda) from venda);
    Insert into Venda_Prod values(null, i, valorUnit, id_produto, quant );
end;
$$ delimiter ;*/

/*PROCEDURE PARA A VENDA DE SERVIÇOS
delimiter $$
create procedure inserirVendaServico (valor float, dataV date, hora varchar(100), formaPag varchar(100), id_cliente int, id_funcionario int, id_servico int, quant int)
begin
    declare i int;
    declare valorUnit float;
	Insert into Venda values(null, valor, dataV, hora, formaPag, id_cliente, id_funcionario);
    set valorUnit = (select valor_prod from produto where id_prod = id_produto);
    set i = (select max(id_venda) from venda);
    Insert into Venda_Prod values(null, i, valorUnit, id_produto, quant );
end;
$$ delimiter ;*/