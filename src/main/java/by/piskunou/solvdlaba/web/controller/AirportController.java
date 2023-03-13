package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Airport;
import by.piskunou.solvdlaba.service.AirportService;
import by.piskunou.solvdlaba.web.dto.AirportDTO;
import by.piskunou.solvdlaba.web.dto.groups.onCreate;
import by.piskunou.solvdlaba.web.mapper.AirportMapper;
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
@RequestMapping("/airports")
@RequiredArgsConstructor
@Tag(name = "Airports", description = "Methods for work with airports")
public class AirportController {

    private final AirportService service;
    private final AirportMapper mapper;

    @GetMapping
    @Operation(summary = "Information about all airports")
    public Flux<AirportDTO> findAll() {
        return service.findAll().map(mapper::toDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Full information about certain airport by id")
    @Parameter(name = "id", description = "The airport's unique identification number")
    public Mono<AirportDTO> findById(@PathVariable long id) {
        return service.findById(id).map(mapper::toDTO);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for airports")
    @Parameter(name = "dto", description = "Search airports with fields like in this dto")
    public Flux<AirportDTO> search(@RequestParam String inquiry) {
        return service.search(inquiry).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create airport")
    @Parameters({
            @Parameter(name = "cityId", description = "The unique city's id to which this airport belongs"),
            @Parameter(name = "dto", description = "Created airport")
    })
    public Mono<AirportDTO> create(@RequestParam("city-id") long cityId,
                             @RequestBody @Validated(onCreate.class) AirportDTO dto) {
        Airport airport = mapper.toEntity(dto);
        return service.create(cityId, airport).map(mapper::toDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update airport by id")
    @Parameters({
            @Parameter(name = "id", description = "Airport's unique identification number"),
            @Parameter(name = "cityId", description = "City's unique identification number"),
            @Parameter(name = "dto", description = "Updated airport")
    })
    public Mono<AirportDTO> updateById(@PathVariable long id, @RequestParam("city-id") long cityId,
                                 @RequestBody @Validated(onCreate.class) AirportDTO dto) {
        Airport airport = mapper.toEntity(dto);
        return service.updateById(id, cityId, airport).map(mapper::toDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove airport by its id")
    @Parameter(name = "id", description = "Airport's unique identification number")
    public Mono<Void> removeById(@PathVariable long id) {
        return service.removeById(id);
    }

}
