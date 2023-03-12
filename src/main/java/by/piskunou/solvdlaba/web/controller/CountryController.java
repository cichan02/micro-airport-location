package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.service.CountryService;
import by.piskunou.solvdlaba.web.dto.CountryDTO;
import by.piskunou.solvdlaba.web.mapper.CountryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
@Tag(name = "Countries", description = "Methods for work with countries")
public class CountryController {

    private final CountryService service;
    private final CountryMapper mapper;

    @GetMapping
    @Operation(summary = "Information about all cities")
    public Flux<CountryDTO> findAll() {
        return service.findAll().map(mapper::toDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about certain country by its id")
    @Parameter(name = "id", description = "Country's unique identification number")
    public Mono<CountryDTO> findById(@PathVariable long id) {
        return service.findById(id).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new country")
    @Parameter(name = "dto", description = "Created country")
    public Mono<CountryDTO> create(@RequestBody @Validated CountryDTO dto) {
        Country country = mapper.toEntity(dto);
        return service.create(country).map(mapper::toDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update country by its id")
    @Parameters({
            @Parameter(name = "id", description = "The unique country's identification number"),
            @Parameter(name = "dto", description = "Updated country")
    })
    public Mono<CountryDTO> updateById(@PathVariable long id, @RequestBody @Validated CountryDTO dto) {
        Country country = mapper.toEntity(dto);
        return service.updateById(id, country).map(mapper::toDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove country from system by its id")
    @Parameter(name = "id", description = "The unique country's identification number")
    public Mono<Void> removeById(@PathVariable long id) {
        return service.removeById(id);
    }

}
