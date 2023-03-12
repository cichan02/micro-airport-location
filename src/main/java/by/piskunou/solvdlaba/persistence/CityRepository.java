package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityRepository {

    Flux<City> findAll();

    Mono<City> findById(long id);

    Mono<City> findByIdWithAirport(long id);

    Flux<City> search(String inquiry);

    Mono<City> create(long countryId, City city);

    Mono<City> update(long countryId, City city);

    Mono<Void> removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(String name, long id);

}
