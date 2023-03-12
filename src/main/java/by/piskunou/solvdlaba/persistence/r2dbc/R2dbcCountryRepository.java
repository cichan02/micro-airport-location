package by.piskunou.solvdlaba.persistence.r2dbc;

import by.piskunou.solvdlaba.domain.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface R2dbcCountryRepository extends R2dbcRepository<Country, Long> {

    Mono<Boolean> existsByNameAndIdNot(String name, Long id);

}
