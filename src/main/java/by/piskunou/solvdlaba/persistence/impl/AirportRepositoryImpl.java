package by.piskunou.solvdlaba.persistence.impl;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.persistence.AirportRepository;
import by.piskunou.solvdlaba.persistence.impl.mapper.AirportMapper;
import by.piskunou.solvdlaba.persistence.r2dbc.R2dbcAirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AirportRepositoryImpl implements AirportRepository {

    private final R2dbcAirportRepository r2dbcRepository;
    private final DatabaseClient db;
    private final AirportMapper mapper;

    @Override
    public Flux<Airport> findAll() {
        return r2dbcRepository.findAll();
    }

    @Override
    public Mono<Airport> findById(long id) {
        return r2dbcRepository.findById(id);
    }

    @Override
    public Flux<Airport> search(String inquiry) {
        return db.sql("""
              select id as airport_id,
                     name as airport_name,
                     iata as airport_iata,
                     icao as airport_icao
              from airports
              where name like :inquiry or
                    iata like :inquiry or
                    icao like :inquiry""")
                .bind("inquiry", inquiry)
                .fetch()
                .all()
                .map(mapper::fullMap);
    }

    @Override
    public Mono<Airport> create(long cityId, Airport airport) {
        return db.sql("""
               insert into airports(fk_city_id, name, iata, icao)
               values(:cityId, :name, :iata, :icao)""")
                .bind("cityId", cityId)
                .bind("name", airport.getName())
                .bind("iata", airport.getIata())
                .bind("icao", airport.getIcao())
                .filter((statement, executeFunction) -> statement.returnGeneratedValues("id").execute())
                .fetch()
                .one()
                .doOnNext(result -> airport.setId( (Long) result.get("id") ))
                .thenReturn(airport);
    }

    @Override
    public Mono<Airport> update(long cityId, Airport airport) {
        return db.sql("""
               update airports set fk_city_id = :cityId,
                            name = :name,
                            iata = :iata,
                            icao = :icao
                where id = :id""")
                .bind("cityId", cityId)
                .bind("name", airport.getName())
                .bind("iata", airport.getIata())
                .bind("icao", airport.getIcao())
                .bind("id", airport.getId())
                .fetch()
                .one()
                .thenReturn(airport);
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

    @Override
    public Mono<Boolean> isExistsByIata(String iata, long id) {
        return r2dbcRepository.existsByIataAndIdNot(iata, id);
    }

    @Override
    public Mono<Boolean> isExistsByIcao(String icao, long id) {
        return r2dbcRepository.existsByIcaoAndIdNot(icao, id);
    }

}
