use fish

create table custom(
uid int identity(4000,1) constraint PK_userID primary key,
uname varchar(50),
password varchar(18) not null,
mail varchar(30) not null 
);

create table video(
vid int identity(4000,1) constraint PK_videoID primary key,
uid int not null,
tid int not null,
title varchar(50) not null,
text varchar(100) not null,
video varchar(100) not null,
picture varchar(100) not null
);


create table comment(
cid int identity(4000,1) constraint PK_commentID primary key, 
vid int not null,
uid int not null,
text varchar(100) not null,
time varchar(100) not null
);



