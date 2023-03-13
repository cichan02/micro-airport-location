package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.City;
import by.piskunou.solvdlaba.service.CityService;
import by.piskunou.solvdlaba.web.dto.CityDTO;
import by.piskunou.solvdlaba.web.mapper.CityMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@Tag(name = "Cities", description = "Methods for work with cities")
public class CityController {

    private final CityService service;
    private final CityMapper mapper;

    @GetMapping
    @Operation(summary = "Information about all cities")
    public Flux<CityDTO> findAll() {
        return service.findAll().map(mapper::toDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about certain city by its id")
    @Parameters({
            @Parameter(name = "id", description = "City's unique identification number"),
            @Parameter(name = "withAirports", description = "Flag to return city with list of airports or no")
    })
    public Mono<CityDTO> findById(@PathVariable long id, @RequestParam(required = false) boolean withAirports) {
        return service.findById(id, withAirports).map(mapper::toDTO);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for city(-ies)")
    @Parameter(name = "inquiry", description = "Search cities with name like in this inquiry", example = "Mi")
    public Flux<CityDTO> search(@RequestParam String inquiry) {
        return service.search(inquiry).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create city")
    @Parameters({
            @Parameter(name = "countryId", description = "The country's unique id which this city belongs to"),
            @Parameter(name = "dto", description = "Created city")
    })
    public Mono<CityDTO> create(@RequestParam("country-id") long countryId,
                          @RequestBody @Validated CityDTO dto) {
        City city = mapper.toEntity(dto);
        return service.create(countryId, city).map(mapper::toDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update city by its id")
    @Parameters({
            @Parameter(name = "id", description = "The unique city's identification number"),
            @Parameter(name = "countryId", description = "The country's unique id which this city belongs to"),
            @Parameter(name = "dto", description = "Updated city")
    })
    public Mono<CityDTO> updateById(@PathVariable long id, @RequestParam("country-id") long countryId,
                              @RequestBody @Validated CityDTO dto) {
        City city = mapper.toEntity(dto);
        return service.updateById(id, countryId, city).map(mapper::toDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove city from system by its id")
    @Parameter(name = "id", description = "The unique city's identification number")
    public Mono<Void> removeById(@PathVariable long id) {
        return service.removeById(id);
    }

}
