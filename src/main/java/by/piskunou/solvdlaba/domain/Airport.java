package by.piskunou.solvdlaba.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airports")
public class Airport {

    @Id
    private Long id;
    private String name;
    private String iata;
    private String icao;

    public Airport(Long id) {
        this.id = id;
    }

    public Airport(String name) {
        this.name = name;
    }

    public Airport(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
