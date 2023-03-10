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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/exists/{id}")
    @Operation(summary = "Check if a country exists")
    @Parameter(name = "id", description = "Country's unique identification number")
    public Mono<Boolean> existsById(@PathVariable long id) {
        return service.isExists(id);
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
