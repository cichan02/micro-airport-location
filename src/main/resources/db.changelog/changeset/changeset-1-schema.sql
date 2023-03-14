--liquibase formatted sql

--changeset cichan:create-countries-table
create table if not exists countries(
    id bigserial primary key,
    "name" varchar(50) not null unique
);
--rollback drop table countries;

--changeset cichan:create-cities-table
create table if not exists cities(
    id bigserial primary key,
    fk_country_id bigint not null references countries(id) on delete cascade on update no action,
    "name" varchar(50) not null unique
);
--rollback drop table cities;

--changeset cichan:create-airports-table
create table if not exists airports(
    id bigserial primary key,
    fk_city_id bigint not null references cities(id) on delete cascade on update no action,
    "name" varchar(200) not null unique,
    iata varchar check (length(iata) = 3) unique,
    icao varchar check (length(icao) = 4) unique
);
--rollback drop table airports;
