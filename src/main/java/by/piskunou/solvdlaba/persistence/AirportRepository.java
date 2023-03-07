package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Airport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportRepository {

    Flux<Airport> findAll();

    Mono<Airport> findById(long id);

    Flux<Airport> search(Airport airport);

    void create(long cityId, Airport airport);

    void update(long cityId, Airport airport);

    void removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(Long id, String name);

    Mono<Boolean> isExistsByIata(Long id, String iata);

    Mono<Boolean> isExistsByIcao(Long id, String icao);

}
