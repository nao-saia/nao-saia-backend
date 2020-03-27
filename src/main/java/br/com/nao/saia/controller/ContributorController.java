package br.com.nao.saia.controller;

import br.com.nao.saia.dto.ContributorDTO;
import br.com.nao.saia.service.ContributorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("contributors")
public class ContributorController {

    private final ContributorService service;

    public ContributorController(ContributorService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<ContributorDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ContributorDTO> findById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @PatchMapping("{id}")
    public Mono<ContributorDTO> patch(@PathVariable final UUID id,
                                      @RequestBody final ContributorDTO contributorDTO) {
        return service.patch(id, contributorDTO);
    }

    @PutMapping
    public Mono<ContributorDTO> update(@Valid @RequestBody final ContributorDTO contributorDTO) {
        return service.update(contributorDTO);
    }

    @PostMapping
    public Mono<ContributorDTO> save(@Valid @RequestBody final ContributorDTO contributorDTO) {
        return service.save(contributorDTO);
    }

}
