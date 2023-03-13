package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
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
        return isExistsByName(country.getName(), country.getId())
                         .flatMap(exists -> {
                             if(exists) {
                                 return Mono.error(new ResourceAlreadyExistsException("Country with such name has already exists"));
                             }
                             return repository.save(country);
                         });
    }

    @Override
    public Mono<Country> updateById(long id, Country country) {
        return existsById(id)
                .flatMap(exists -> {
                    if(!exists) {
                        return Mono.error(new ResourceNotExistsException("There's no country with such id"));
                    }
                    country.setId(id);
                    return create(country);
                });
    }

    @Override
    public Mono<Void> removeById(long id) {
        return repository.removeById(id);
    }

    @Override
    public Mono<Boolean> existsById(long id) {
        return repository.isExistsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByName(String name, Long id) {
        long legalId = 0;
        if(id != null) {
            legalId = id;
        }
        return repository.isExistsByName(name, legalId);
    }

}
