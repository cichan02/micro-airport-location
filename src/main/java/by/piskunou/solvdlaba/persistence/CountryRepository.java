package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CountryRepository extends R2dbcRepository<Country, Long> {

    Mono<Boolean> existsByName(String name);

}
