package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {

    Flux<City> findAll();

    Mono<City> findById(long id, boolean withAirports);

    Flux<City> search(String inquiry);

    Mono<City> create(long countryId, City city);

    Mono<City> updateById(long id, long countryId, City city);

    Mono<Void> removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(String name, Long id);

}
