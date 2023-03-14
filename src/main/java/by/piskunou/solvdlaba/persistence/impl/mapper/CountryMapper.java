package by.piskunou.solvdlaba.persistence.impl.mapper;

import by.piskunou.solvdlaba.domain.Country;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CountryMapper {

    public Country fullMap(@NotNull Map<String, Object> map) {
        return Country.builder()
                .id( (Long) map.get("country_id") )
                .name( (String) map.get("country_name") )
                .build();
    }

}
