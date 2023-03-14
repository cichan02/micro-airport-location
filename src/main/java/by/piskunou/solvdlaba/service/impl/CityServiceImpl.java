package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.CityRepository;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final CountryService countryService;

    @Override
    public Flux<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<City> findById(long id, boolean withAirports) {
        return withAirports ? repository.findByIdWithAirport(id) : repository.findById(id);
    }

    @Override
    public Flux<City> search(String inquiry) {
        return repository.search('%' + inquiry + '%');
    }

    @Override
    public Mono<City> create(long countryId, City city) {
        return countryService.isExists(countryId)
                .flatMap(existsCountry -> {
                    if (!existsCountry) {
                        return Mono.error(new ResourceNotExistsException("There's no country with such id"));
                    }
                    return isExistsByName(city.getName(), city.getId());
                }).flatMap(existsByName -> {
                    if (existsByName) {
                        return Mono.error(new ResourceAlreadyExistsException("City with such name has already exists"));
                    }
                    return repository.create(countryId, city);
                });
    }

    @Override
    public Mono<City> updateById(long id, long countryId, City city) {
        return isExistsById(id)
                .flatMap(existsById -> {
                    if (!existsById) {
                        return Mono.error(new ResourceNotExistsException("There's no city with such id"));
                    }
                    city.setId(id);
                    return countryService.isExists(countryId);
                }).flatMap(existsCountry -> {
                    if (!existsCountry) {
                        return Mono.error(new ResourceNotExistsException("There's no country with such id"));
                    }
                    return isExistsByName(city.getName(), city.getId());
                }).flatMap(existsByName -> {
                    if (existsByName) {
                        return Mono.error(new ResourceAlreadyExistsException("City with such name has already exists"));
                    }
                    return repository.update(countryId, city);
                });
    }

    @Override
    public Mono<Void> removeById(long id) {
        return repository.removeById(id);
    }

    @Override
    public Mono<Boolean> isExistsById(long id) {
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
