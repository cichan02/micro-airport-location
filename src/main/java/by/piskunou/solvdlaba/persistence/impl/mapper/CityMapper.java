package by.piskunou.solvdlaba.persistence.impl.mapper;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.domain.City;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final CountryMapper countryMapper;
    private final AirportMapper airportMapper;

    public City elementaryMap(@NotNull Map<String, Object> map) {
        return City.builder()
                   .id( (Long) map.get("city_id") )
                   .name( (String) map.get("city_name") )
                   .build();
    }

    public City withCountryMap(@NotNull Map<String, Object> map) {
        City city = elementaryMap(map);
        city.setCountry( countryMapper.fullMap(map) );
        return city;
    }

    public City fullMap(@NotNull List<Map<String, Object>> maps) {
        City city = withCountryMap(maps.get(0));

        List<Airport> airports = maps.stream()
                                     .map(airportMapper::fullMap)
                                     .toList();

        city.setAirports(airports);

        return city;
    }

    public List<City> searchMap(List<Map<String, Object>> maps) {
        List<City> cities = new LinkedList<>();

        if(maps.isEmpty()) {
            return cities;
        }

        City city = withCountryMap( maps.get(0) );
        List<Airport> airports = new LinkedList<>();

        for (Map<String, Object> map : maps) {
            if ( !map.get("city_id").equals(city.getId()) ) {
                city.setAirports(airports);
                cities.add(city);

                airports = new LinkedList<>();
                city = withCountryMap(map);
            }

            Airport airport = airportMapper.fullMap(map);
            airports.add(airport);
        }
        city.setAirports(airports);
        cities.add(city);

        return cities;
    }

}
