package by.piskunou.solvdlaba.persistence.impl.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AirportMapper {

    public Airport fullMap(@NotNull Map<String, Object> map) {
        return Airport.builder()
                .id( (Long) map.get("airport_id") )
                .name( (String) map.get("airport_name") )
                .iata( (String) map.get("airport_iata") )
                .icao( (String) map.get("airport_icao") )
                .build();
    }

}
