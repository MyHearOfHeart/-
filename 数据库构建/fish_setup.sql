if exists(select name from sysobjects
where name='fish' and type='d')
drop database project

create database fish
on
(name='fish_data',/*数据文件的逻辑名称,注意不能与日志逻辑同名*/
filename='g:\SQL\fish_data.mdf' ,/*物理名称，注意路径必须存在*/
size=10,/*数据初始长度为M*/
maxsize=50,/*最大长度为M*/
filegrowth=5%)/*数据文件每次增长M*/
log on
( name=project_log, 
filename='g:\SQL\fish_log.ldf ' , 
size=2 , 
maxsize=5 , 
filegrowth=1)