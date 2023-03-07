package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CityRepository {

    Flux<City> findAll();

    Mono<City> findById(long id);

    Mono<City> findByIdWithAirports(long id);

    Flux<City> search(String inquiry);

    void create(long countryId, City city);

    void update(long countryId, City city);

    void removeById(long id);

    Mono<Boolean> isExistsById(long id);

    Mono<Boolean> isExistsByName(Long id, String name);

}
