package by.piskunou.solvdlaba.persistence.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.persistence.CountryRepository;
import by.piskunou.solvdlaba.persistence.r2dbc.R2dbcCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private final R2dbcCountryRepository repository;

    @Override
    public Flux<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Country> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Country> save(Country country) {
        return repository.save(country);
    }

    @Override
    public Mono<Void> removeById(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Boolean> isExistsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByName(String name, long id) {
        return repository.existsByNameAndIdNot(name, id);
    }

}
