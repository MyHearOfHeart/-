if exists(select name from sysobjects
where name='fish' and type='d')
drop database project

create database fish
on
(name='fish_data',/*�����ļ����߼�����,ע�ⲻ������־�߼�ͬ��*/
filename='g:\SQL\fish_data.mdf' ,/*�������ƣ�ע��·���������*/
size=10,/*���ݳ�ʼ����ΪM*/
maxsize=50,/*��󳤶�ΪM*/
filegrowth=5%)/*�����ļ�ÿ������M*/
log on
( name=project_log, 
filename='g:\SQL\fish_log.ldf ' , 
size=2 , 
maxsize=5 , 
filegrowth=1)