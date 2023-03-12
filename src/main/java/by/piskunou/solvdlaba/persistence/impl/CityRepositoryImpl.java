package by.piskunou.solvdlaba.persistence.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.persistence.CityRepository;
import by.piskunou.solvdlaba.persistence.impl.mapper.CityMapper;
import by.piskunou.solvdlaba.persistence.r2dbc.R2dbcCityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {

    private final R2dbcCityRepository r2dbcRepository;
    private final DatabaseClient db;
    private final CityMapper mapper;

    @Override
    public Flux<City> findAll() {
        return r2dbcRepository.findAll();
    }

    @Override
    public Mono<City> findById(long id) {
        return db.sql("""
            select cities.id as city_id,
                   cities.name as city_name,
    
                   countries.id as country_id,
                   countries.name as country_name
    
            from cities inner join countries on cities.fk_country_id = countries.id
            where cities.id = :id""")
                .bind("id", id)
                .fetch()
                .one()
                .map(mapper::withCountryMap);
    }

    @Override
    public Mono<City> findByIdWithAirport(long id) {
        return db.sql("""
            select cities.id as city_id,
                   cities.name as city_name,
                   
                   countries.id as country_id,
                   countries.name as country_name,
                   
                   airports.id as airport_id,
                   airports.name as airport_name,
                   airports.iata as airport_iata,
                   airports.icao as airport_icao
            
            from cities inner join countries on cities.fk_country_id = countries.id
                        left join airports on cities.id = airports.fk_city_id
            where cities.id = :id""")
                .bind("id", id)
                .fetch()
                .all()
                .collectList()
                .map(mapper::fullMap);
    }

    @Override
    public Flux<City> search(String inquiry) {
        return db.sql("""
            select cities.id as city_id,
                   cities.name as city_name,
                   
                   countries.id as country_id,
                   countries.name as country_name,
                   
                   airports.id as airport_id,
                   airports.name as airport_name,
                   airports.iata as airport_iata,
                   airports.icao as airport_icao
            
            from cities inner join countries on cities.fk_country_id = countries.id
                        inner join airports on cities.id = airports.fk_city_id
            where cities.name like :inquiry or
                  countries.name like :inquiry or
                  airports.name like :inquiry or
                  airports.iata like :inquiry or
                  airports.icao like :inquiry""")
                .bind("inquiry", inquiry)
                .fetch()
                .all()
                .collectList()
                .flatMapIterable(mapper::searchMap);
    }

    @Override
    public Mono<City> create(long countryId, City city) {
        return db.sql("""
                insert into cities(fk_country_id, name)
                values(:countryId, :name)""")
                .bind("countryId", countryId)
                .bind("name", city.getName())
                .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
                .fetch()
                .one()
                .doOnNext(result -> city.setId( (Long) result.get("id") ))
                .thenReturn(city);
    }

    @Override
    public Mono<City> update(long countryId, City city) {
        return db.sql("""
                update cities set fk_country_id = :countryId,
                                  name = :name
                where id = :id""")
                .bind("countryId", countryId)
                .bind("name", city.getName())
                .bind("id", city.getId())
                .fetch()
                .one()
                .thenReturn(city);
    }

    @Override
    public Mono<Void> removeById(long id) {
        return r2dbcRepository.deleteById(id);
    }

    @Override
    public Mono<Boolean> isExistsById(long id) {
        return r2dbcRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByName(String name, long id) {
        return r2dbcRepository.existsByNameAndIdNot(name, id);
    }

}
