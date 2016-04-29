-- 如果数据库存在就删除
drop database if exists region;

-- 创建数据库region
create database region;

-- 选择数据库region
use region;

-- 创建省
create table provinces (
   id int not null primary key,
   name varchar(20) not null
);
-- 创建市
create table cities (
   id int not null primary key,
   name varchar(20) not null,
   provinceID int not null,
   foreign key(provinceID) references provinces(id) ON DELETE CASCADE ON UPDATE NO ACTION
);
-- 创建县
create table countries (
   id int not null primary key,
   name varchar(30) not null,-- 最长的11个字
   cityID int not null,
   foreign key(cityID) references Cities(id) ON DELETE CASCADE ON UPDATE NO ACTION
);