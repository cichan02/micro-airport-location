package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryRepository {

    Flux<Country> findAll();

    Mono<Country> findById(long id);

    void create(Country country);

    void update(Country country);

    void removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(Long id, String name);

}
