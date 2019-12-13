drop schema if exists finallab;
create schema finallab character set utf8 collate utf8_general_ci;
use finallab;

create table user
(
    id        Int auto_increment,
    username  varchar(255) unique,
    password  varchar(255) not null,
    `role`    enum ('LOADER','CASHIER','CHIEF_CASHIER'),
    primary key (id)
);

create table storage
(
    id       Int auto_increment,
    name     varchar(255) not null,
    price    int not null,
    quantity int not null,
    primary key (id)
);

create table receipt
(
    id          Int auto_increment,
    totalPrice  int not null,
    isClosed    boolean not null,
    primary key (id)
);

create table line
(
    id        Int auto_increment,
    name      varchar(255) not null,
    price     int not null,
    quantity  int not null,
    receiptId int not null,
    primary key (id),
    foreign key (receiptId) references receipt (id)
);

create table report
(
    id         Int auto_increment,
    totalPrice int not null,
    isClosed   boolean not null,
    `reportType`     enum ('X_REPORT','Z_REPORT'),
    username   varchar(255) not null, 
    primary key (id)
);

create table reportLine
(
    id        Int auto_increment,
    name      varchar(255) unique,
    price     int not null,
    quantity  int not null,
    reportId int not null,
    primary key (id),
    foreign key (reportId) references report (id)
);

INSERT INTO `user` (username, password, role) VALUES 
('loader','loader','LOADER'),
('cashier','cashier','CASHIER'),
('chief','chief','CHIEF_CASHIER');
INSERT INTO storage (name, price, quantity) VALUES 
('apple',10,10),
('meat',10,10),
('banana',10,10);
