--liquibase formatted sql

--changeset cichan:insertion-into-countries
insert into countries(name) values('Belarus');
insert into countries(name) values('Russia');
insert into countries(name) values('Ukraine');
insert into countries(name) values('Poland');
insert into countries(name) values('German');
insert into countries(name) values('United Arab Emirates');

--changeset cichan:insertion-into-cities
insert into cities(fk_country_id, name) values (1, 'Miensk');
insert into cities(fk_country_id, name) values (1, 'Horadnia');
insert into cities(fk_country_id, name) values (1, 'Bierascie');
insert into cities(fk_country_id, name) values (2, 'Moscow');
insert into cities(fk_country_id, name) values (2, 'St.Petersburg');
insert into cities(fk_country_id, name) values (2, 'Yekaterinburg');
insert into cities(fk_country_id, name) values (3, 'Kiev');
insert into cities(fk_country_id, name) values (3, 'Odessa');
insert into cities(fk_country_id, name) values (3, 'Lviv');
insert into cities(fk_country_id, name) values (4, 'Warsaw');
insert into cities(fk_country_id, name) values (4, 'Krakow');
insert into cities(fk_country_id, name) values (5, 'Berlin');
insert into cities(fk_country_id, name) values (5, 'Bremen');
insert into cities(fk_country_id, name) values (5, 'Hanover');
insert into cities(fk_country_id, name) values (5, 'Munich');
insert into cities(fk_country_id, name) values (5, 'Frankfurt am Main');
insert into cities(fk_country_id, name) values (6, 'Dubai');
insert into cities(fk_country_id, name) values (6, 'Abu Dhabi');

--changeset cichan:insertion-into-airports
insert into airports(fk_city_id, name, iata, icao) values (1, 'Minsk National Airport', 'MSQ', 'UMMS');
insert into airports(fk_city_id, name, iata, icao) values (4, 'Domodedovo Mikhail Lomonosov International Airport', 'DME', 'UUDD');
insert into airports(fk_city_id, name, iata, icao) values (4, 'Sheremetyevo Alexander S. Pushkin International Airport', 'SVO', 'UUEE');
insert into airports(fk_city_id, name, iata, icao) values (4, 'Vnukovo Andrei Tupolev International Airport', 'VKO', 'UUWW');
insert into airports(fk_city_id, name, iata, icao) values (4, 'Zhukovsky International Airport', 'ZIA', 'UUBW');
insert into airports(fk_city_id, name, iata, icao) values (5, 'Pulkovo Airport', 'LED', 'ULLI');
insert into airports(fk_city_id, name, iata, icao) values (6, 'Koltsovo International Airport', 'SVX', 'USSS');
insert into airports(fk_city_id, name, iata, icao) values (7, 'Ihor Sikorsky Kyiv International Airport (Zhuliany)', 'IEV', 'UKKK');
insert into airports(fk_city_id, name, iata, icao) values (8, 'Odesa International Airport', 'ODS', 'UKOO');
insert into airports(fk_city_id, name, iata, icao) values (9, 'Lviv Danylo Halytskyi International Airport', 'LWO', 'UKLL');
insert into airports(fk_city_id, name, iata, icao) values (10, 'Warsaw Chopin Airport', 'WAW', 'EPWA');
insert into airports(fk_city_id, name, iata, icao) values (10, 'Warsaw Modlin Airport', 'WMI', 'EPMO');
insert into airports(fk_city_id, name, iata, icao) values (11, 'Kraków John Paul II International Airport', 'KRK', 'EPKK');
insert into airports(fk_city_id, name, iata, icao) values (12, 'Berlin Brandenburg Airport Willy Brandt', 'BER', 'EDDB');
insert into airports(fk_city_id, name, iata, icao) values (13, 'Bremen Airport', 'BRE', 'EDDW');
insert into airports(fk_city_id, name, iata, icao) values (14, 'Hannover Airport', 'HAJ', 'EDDV');
insert into airports(fk_city_id, name, iata, icao) values (15, 'Munich International Airport - Franz Josef Strauß', 'MUC', 'EDDM');
insert into airports(fk_city_id, name, iata, icao) values (16, 'Frankfurt Airport', 'FRA', 'EDDF');
insert into airports(fk_city_id, name, iata, icao) values (17, 'Dubai International Airport', 'DXB', 'OMDB');
insert into airports(fk_city_id, name, iata, icao) values (18, 'Abu Dhabi International Airport', 'AUH', 'OMAA');

