drop table if exists airplane;
drop table if exists member;
drop table if exists seat;
drop table if exists ticket;

create table airplane (
    id bigint not null,
    arrival varchar(255),
    departure varchar(255),
    registration_number varchar(255),
    primary key (id)
);

create table member (
    id bigint not null,
    created_date timestamp,
    updated_date timestamp,
    email varchar(255),
    enabled boolean not null,
    password varchar(255),
    role varchar(255),
    username varchar(255),
    primary key (id)
);

create table seat (
    id bigint not null,
    created_date timestamp,
    updated_date timestamp,
    reservation_enable boolean,
    seat_number varchar(255),
    airplane_id bigint REFERENCES airplane,
    primary key (id)
);

create table ticket (
    id bigint not null,
    created_date timestamp,
    updated_date timestamp,
    arrival varchar(255),
    boarding_time timestamp,
    departure varchar(255),
    passenger_name varchar(255),
    seat_number varchar(255),
    primary key (id)
);


insert into airplane (id, arrival, departure, registration_number)
values (1, '제주도', '서울', 'A101');

insert into seat (id, created_date, updated_date, reservation_enable, seat_number, airplane_id)
values (1, now(), now(), true, 'A01', 1);
insert into seat (id, created_date, updated_date, reservation_enable, seat_number, airplane_id)
values (2, now(), now(), true, 'A02', 1);
insert into seat (id, created_date, updated_date, reservation_enable, seat_number, airplane_id)
values (3, now(), now(), true, 'B01', 1);
insert into seat (id, created_date, updated_date, reservation_enable, seat_number, airplane_id)
values (4, now(), now(), true, 'B02', 1);