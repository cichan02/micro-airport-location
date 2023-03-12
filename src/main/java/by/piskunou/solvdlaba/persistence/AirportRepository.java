package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportRepository {

    Flux<Airport> findAll();

    Mono<Airport> findById(long id);

    Flux<Airport> search(String inquiry);

    Mono<Airport> create(long cityId, Airport airport);

    Mono<Airport> update(long cityId, Airport airport);

    Mono<Void> removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(String name, long id);

    Mono<Boolean> isExistsByIata(String iata, long id);

    Mono<Boolean> isExistsByIcao(String icao, long id);

}


