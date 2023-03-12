package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryService {

    Flux<Country> findAll();

    Mono<Country> findById(long id);

    Mono<Country> create(Country country);

    Mono<Country> updateById(long id, Country country);

    Mono<Void> removeById(long id);

    Mono<Boolean> existsById(long id);

    Mono<Boolean> isExistsByName(String name, Long id);

}
