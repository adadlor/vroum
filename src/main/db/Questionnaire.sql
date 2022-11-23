CREATE DATABASE questionnaire
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
Create table question (
	id int primary key, 
	question varchar(255))

create table reponse (
	id int pimary key, 
	reponse varchar(255),
	foreign key (id) references question(id))
