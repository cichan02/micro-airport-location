package by.piskunou.solvdlaba.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {

    @Id
    private Long id;
    private String name;
    private Country country;
    private List<Airport> airports;

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

}
