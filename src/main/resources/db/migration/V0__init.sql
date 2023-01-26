drop table if exists airplane CASCADE;
drop table if exists member CASCADE;
drop table if exists seat CASCADE;
drop table if exists ticket CASCADE;

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