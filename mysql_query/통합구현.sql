create database mydb;

use mydb;

drop table if exists board;
create table board(
   id int auto_increment primary key,
    borderTitle varchar(20),
    borderPassword varchar(20),
    comboPublic varchar(5),
    writerName varchar(20),
    textContent varchar(1000)
);
select *
  from board;