package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirportRepository;
import by.piskunou.solvdlaba.service.AirportService;
import by.piskunou.solvdlaba.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository repository;
    private final CityService cityService;

    @Override
    public Flux<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Airport> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Airport> search(String inquiry) {
        return repository.search('%' + inquiry + '%');
    }

    @Override
    public Mono<Airport> create(long cityId, Airport airport) {
        Long id = airport.getId();
        return cityService.isExistsById(cityId)
                .flatMap(existsCity -> {
                    if(!existsCity) {
                        return Mono.error(new ResourceNotExistsException("There's no city with such id"));
                    }
                    return isExistsByName(airport.getName(), id);
                }).flatMap(existsByName -> {
                    if(existsByName) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such name has already exists"));
                    }
                    return isExistsByIata(airport.getIata(), id);
                }).flatMap(existsByIata -> {
                    if (existsByIata) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such iata has already exists"));
                    }
                    return isExistsByIcao(airport.getIcao(), id);
                }).flatMap(existsByIcao -> {
                    if(existsByIcao) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such icao has already exists"));
                    }
                    return repository.create(cityId, airport);
                });
    }

    @Override
    public Mono<Airport> updateById(long id, long cityId, Airport airport) {
        return isExists(id)
                .flatMap(existsById -> {
                    if(!existsById) {
                        return  Mono.error(new ResourceNotExistsException("There's no airport with such id"));
                    }
                    airport.setId(id);
                    return cityService.isExistsById(cityId);
                }).flatMap(existsCity -> {
                    if(!existsCity) {
                        return Mono.error(new ResourceNotExistsException("There's no city with such id"));
                    }
                    return isExistsByName(airport.getName(), id);
                }).flatMap(existsByName -> {
                    if(existsByName) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such name has already exists"));
                    }
                    return isExistsByIata(airport.getIata(), id);
                }).flatMap(existsByIata -> {
                    if (existsByIata) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such iata has already exists"));
                    }
                    return isExistsByIcao(airport.getIcao(), id);
                }).flatMap(existsByIcao -> {
                    if(existsByIcao) {
                        return Mono.error(new ResourceAlreadyExistsException("Airport with such icao has already exists"));
                    }
                    return repository.update(cityId, airport);
                });
    }

    @Override
    public Mono<Void> removeById(long id) {
        return repository.removeById(id);
    }

    @Override
    public Mono<Boolean> isExists(long id) {
        return repository.isExistsById(id);
    }

    @Override
    public Mono<Boolean> isExistsByName(String name, Long id) {
        long legalId = id == null ? 0 : id;
        return repository.isExistsByName(name, legalId);
    }

    @Override
    public Mono<Boolean> isExistsByIata(String iata, Long id) {
        long legalId = id == null ? 0 : id;
        return repository.isExistsByIata(iata, legalId);
    }

    @Override
    public Mono<Boolean> isExistsByIcao(String icao, Long id) {
        long legalId = id == null ? 0 : id;
        return repository.isExistsByIcao(icao, legalId);
    }

}
