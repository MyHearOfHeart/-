use fish

create table custom(
userID int identity(4000,1) constraint PK_userID primary key,
userName varchar(50),
password varchar(18) not null,
mail varchar(30) not null 
);

create table movie(
movieID int identity(4000,1) constraint PK_movieID primary key,
userID int not null,
title varchar(50) not null,
path varchar(100) not null 
);


create table comment(
commentID int identity(4000,1) constraint PK_commentID primary key, 
movieID int not null,
userID int not null,
content varchar(100) not null
);



