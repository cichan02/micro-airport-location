package by.piskunou.solvdlaba.web.controller;

import by.piskunou.solvdlaba.domain.Country;
import by.piskunou.solvdlaba.service.CountryService;
import by.piskunou.solvdlaba.web.dto.CountryDTO;
import by.piskunou.solvdlaba.web.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService service;
    private final CountryMapper mapper;

    @GetMapping
    public Flux<CountryDTO> findAll() {
        return service.findAll().map(mapper::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<CountryDTO> findById(@PathVariable long id) {
        return service.findById(id).map(mapper::toDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CountryDTO> create(@RequestBody @Validated CountryDTO dto) {
        Country country = mapper.toEntity(dto);
        return service.create(country).map(mapper::toDTO);
    }

    @PutMapping("/{id}")
    public Mono<CountryDTO> updateById(@PathVariable long id, @RequestBody @Validated CountryDTO dto) {
        Country country = mapper.toEntity(dto);
        return service.updateById(id, country).map(mapper::toDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> removeById(@PathVariable long id) {
        return service.removeById(id);
    }

}
