package by.piskunou.solvdlaba.persistence.r2dbc;

import by.piskunou.solvdlaba.domain.Airport;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcAirportRepository extends R2dbcRepository<Airport, Long> {

    Mono<Boolean> existsByNameAndIdNot(String name, Long id);

    Mono<Boolean> existsByIataAndIdNot(String name, Long id);

    Mono<Boolean> existsByIcaoAndIdNot(String name, Long id);

}
