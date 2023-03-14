package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportService {

    Flux<Airport> findAll();

    Mono<Airport> findById(long id);

    Flux<Airport> search(String inquiry);

    Mono<Airport> create(long cityId, Airport airport);

    Mono<Airport> updateById(long id, long cityId, Airport airport);

    Mono<Void> removeById(long id);

    Mono<Boolean> isExists(long id);

    Mono<Boolean> isExistsByName(String name, Long id);

    Mono<Boolean> isExistsByIata(String iata, Long id);

    Mono<Boolean> isExistsByIcao(String icao, Long id);

}
