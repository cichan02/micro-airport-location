package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.persistence.CountryRepository;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    @Override
    public Flux<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Country> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Country> create(Country country) {
        return repository.save(country);
    }

    @Override
    public Mono<Country> updateById(long id, Country country) {
        country.setId(id);
        return repository.save(country);
    }

    @Override
    public Mono<Void> removeById(long id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Boolean> existsById(long id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return repository.existsByName(name);
    }

}
