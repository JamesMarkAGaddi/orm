drop database if exists bms;
 
create database bms;
 
\c bms
 
drop table if exists sailor;
drop table if exists boat;
drop table if exists reservation;
 
create sequence sailor_id_seq start 22;
 
create table sailor
    (id      integer default nextval('sailor_id_seq'),  
     name    varchar(20) not null,
     rating  integer not null,
     age     integer not null,
     primary key (id));
 
create sequence boat_id_seq start 101;
 
create table boat
    (id      integer default nextval('boat_id_seq'),  
     name    varchar(20) not null,
     colour  varchar(20) not null,
     primary key (id));
 
create sequence reservation_id_seq start 1;
 
create table reservation
    (id      integer default nextval('reservation_id_seq'),
     sid     integer,
     bid     integer,
     date    date,
     primary key (id, sid, bid, date),
     foreign key (sid) references sailor(id),
     foreign key (bid) references boat(id));
 
grant all on sailor TO PUBLIC;
grant all on boat TO PUBLIC;
grant all on reservation TO PUBLIC;
 
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Dustin', 7, 45);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Brutus', 1, 33);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Lubber', 79, 65);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Andy', 25, 25);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Rusty', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Buplb', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Buplerb', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Albert', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'James', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Kulet', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Matt', 10, 35);
insert into sailor (id, name, rating, age) values (nextval('sailor_id_seq'), 'Migz', 10, 35);
 
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Interlake', 'blue');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Interlake', 'red');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Clipper', 'green');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Marine', 'red');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Sunny', 'yello');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Tune', 'white');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Titanic', 'silver');
insert into boat (id, name, colour) values (nextval('boat_id_seq'), 'Carpathia', 'silver');
 
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 22, 101, '2004-01-01');
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 22, 102, '2004-01-01');
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 22, 103, '2004-01-02');
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 22, 105, '2004-01-02');
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 31, 103, '2005-05-05');
insert into reservation (id, sid, bid, date) values (nextval('reservation_id_seq'), 32, 104, '2005-04-07');